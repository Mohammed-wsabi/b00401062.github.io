#define _POSIX_C_SOURCE 199309L
#include <assert.h>
#include <sched.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define MAX_NAME 33
#define MAX_POLICY_NAME 5
#define MAX_PRIOR sched_get_priority_max(SCHED_FIFO)
#define MAX_PRIOR_NON_PREM sched_get_priority_max(SCHED_FIFO) - 1
#define MAX_PRIOR_PREM sched_get_priority_max(SCHED_FIFO) - 2
#define TIME_STRLEN 20
#define LOOPS_PER_UNIT 1000000UL

struct process {
	pid_t pid;
	char name[MAX_NAME]; // process name
	int ready_time; // ready time
	int exec_time; // execution time
	int rmn_time; // remaining time
	int prior_tmp;
};

#ifdef DEBUG
int elapsed(struct timespec start, struct timespec end) {
	// calculate elapsed time in time unit
	const long long TIME_UNIT_NS = 2400000LL;
	long long elapsed_ns = 1000000000LL * (end.tv_sec - start.tv_sec) + (end.tv_nsec - start.tv_nsec);
	return elapsed_ns / TIME_UNIT_NS;
}

void proc_show(struct process *processes, int nprocs) {
	// show processes
	for (int id = 0; id < nprocs; id++)
		printf("%d: rmn_time = %d, prior = %d\n", processes[id].pid, processes[id].rmn_time, processes[id].prior_tmp);
}

void sched_show(pid_t pid) {
	// show scheduler
	struct sched_param sched_param;
	sched_getparam(pid, &sched_param);
	printf("%d: policy: %d, priority: %d\n", pid, sched_getscheduler(pid), sched_param.sched_priority);
}
#endif

int cmp_proc_fcfs(struct process *a, struct process *b) {
	// sort ready time in increasing order
	if (a->ready_time != b->ready_time)
		return a->ready_time - b->ready_time;
	else if (a->rmn_time != b->rmn_time)
		return a->rmn_time - b->rmn_time;
	else
		return strcmp(a->name, b->name);
}

int cmp_proc_sjf(struct process *a, struct process *b) {
	// sort execution time in increasing order
	if (a->rmn_time != b->rmn_time)
		return a->rmn_time - b->rmn_time;
	else if (a->ready_time != b->ready_time)
		return a->ready_time - b->ready_time;
	else
		return strcmp(a->name, b->name);
}

void sched_set(pid_t pid, int policy, int priority) {
	// set scheduler
	struct sched_param sched_param;
	sched_param.sched_priority = priority;
	assert(sched_setscheduler(pid, policy, &sched_param) != -1);
}

void prior_update_sort(struct process *processes, int nprocs) {
	// update priority and sort
	qsort(processes, nprocs, sizeof(struct process), cmp_proc_sjf);
	for (int id = 0; id < nprocs; id++)
		processes[id].prior_tmp = MAX_PRIOR_PREM - id;
	qsort(processes, nprocs, sizeof(struct process), cmp_proc_fcfs);
}

int main(int argc, char *argv[]) {
	// scanf input data
	char policy_name[MAX_POLICY_NAME]; // policy name
	scanf("%s\n", policy_name);

	int nprocs; // the number of processes
	scanf("%d\n", &nprocs);

	struct process *processes = (struct process *) malloc(nprocs * sizeof(struct process));
	for (int id = 0; id < nprocs; id++) {
		scanf("%s %d %d", processes[id].name, &processes[id].ready_time, &processes[id].exec_time);
		processes[id].rmn_time = processes[id].exec_time;
	}

	// update priority and sort
	prior_update_sort(processes, nprocs);
#ifdef DEBUG
	// record current time
	struct timespec zero_time;
	clock_gettime(CLOCK_REALTIME, &zero_time);
#endif
	// set main process to the highest priority
	sched_set(getpid(), SCHED_FIFO, MAX_PRIOR);

	// init a share memory which store i of each child process
	int shmid = shmget(IPC_PRIVATE, sizeof(unsigned long) * nprocs, S_IRUSR | S_IWUSR);
	volatile unsigned long *is = (unsigned long *) shmat(shmid, NULL, 0);

	// fork child processes and set scheduler
	int time_units = 0;
	for (int id = 0; id < nprocs; id++) {

		// define TIME_UNIT_US
		const int TIME_UNIT_US = strcmp(policy_name, "PSJF") == 0 ? 2500 : 2300;

		// wait for next ready time
		if (processes[id].ready_time > time_units)
			usleep(TIME_UNIT_US * (processes[id].ready_time - time_units));
		time_units = processes[id].ready_time;

		// fork child processes
		if ((processes[id].pid = fork()) == 0) { // child process

			// non-preemptive setting
			if (strcmp(policy_name, "FIFO") == 0 || strcmp(policy_name, "SJF") == 0)
				sched_set(getpid(), SCHED_FIFO, MAX_PRIOR_NON_PREM);

			// record starting time
			struct timespec start_time;
			clock_gettime(CLOCK_REALTIME, &start_time);

			// start looping
			for (volatile unsigned long *i = is + id; (*i) < LOOPS_PER_UNIT * processes[id].rmn_time; (*i)++);

			// record ending time
			struct timespec end_time;
			clock_gettime(CLOCK_REALTIME, &end_time);

			// print output
#ifdef DEBUG
			printf("%s %d %d %d\n", processes[id].name, getpid(), elapsed(zero_time, start_time), elapsed(zero_time, end_time));
#else
			printf("%s %d\n", processes[id].name, getpid());
			char output_buffer[128];
			sprintf(output_buffer, "echo \"[Project1] %d %d.%09d %d.%09d\" > /dev/kmsg", getpid(), start_time.tv_sec, start_time.tv_nsec, end_time.tv_sec, end_time.tv_nsec);
			system(output_buffer);
#endif
			return 0;
		} else if (processes[id].pid > 0) { // parent process

			// define scheduler parameters
			int policy, priority;

			// update priority for PSJF
			if (strcmp(policy_name, "PSJF") == 0) {
				for (int tmp_id = 0; tmp_id < id; tmp_id++)
					processes[tmp_id].rmn_time = processes[tmp_id].exec_time - is[tmp_id] / LOOPS_PER_UNIT;
				// update priority and sort
				prior_update_sort(processes, nprocs);
				for (int tmp_id = 0; tmp_id < id; tmp_id++)
					sched_set(processes[tmp_id].pid, SCHED_FIFO, processes[tmp_id].prior_tmp);
			}
#ifdef DEBUG
			proc_show(processes, nprocs);
#endif
			// set scheduler
			if (strcmp(policy_name, "FIFO") == 0) {
				policy = SCHED_FIFO;
				priority = MAX_PRIOR_PREM - id;
			} else if (strcmp(policy_name, "SJF") == 0) {
				policy = SCHED_FIFO;
				priority = processes[id].prior_tmp;
			} else if (strcmp(policy_name, "PSJF") == 0) {
				policy = SCHED_FIFO;
				priority = processes[id].prior_tmp;
			} else if (strcmp(policy_name, "RR") == 0) {
				// TODO echo 1200 > /proc/sys/kernel/sched_rr_timeslice_ms
				policy = SCHED_RR;
				priority = MAX_PRIOR;
			}
			sched_set(processes[id].pid, policy, priority);
			sched_yield();
		}
	}
	// wait child processes and detach from shared memory
	while (waitpid(-1, NULL, 0) > 0);
	shmdt(is);
	shmctl(shmid, IPC_RMID, NULL);
	return 0;
}
