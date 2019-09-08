#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

const static int MAX_ROBOT = 1000000;
const static char *RESULTS = "SWNL";

static int T, N, Q;
static int RESULTS_ID[26];

typedef struct node {
	int id;
	struct node *next;
} Node;

void unite(int *setid, int *setsz, char *choices, Node **head, Node **tail, int x, int y, char c) {
	int max, min;
	if (setsz[setid[x]] >= setsz[setid[y]])
		max = x, min = y;
	else {
		max = y, min = x;
		if (c == 'W') c = 'L';
		else if (c == 'L') c = 'W';
	}
	int diff = (choices[max] - choices[min] - RESULTS_ID[c-'A']) % 4;
	int maxid = setid[max], minid = setid[min];
	setsz[maxid] += setsz[minid];
	setsz[minid] = 0;
	tail[maxid]->next = head[minid];
	tail[maxid] = tail[minid];
	for (Node *ptr = head[minid]; ptr != NULL; ptr = ptr->next) {
		setid[ptr->id] = maxid;
		choices[ptr->id] = '0' + (choices[ptr->id] + diff + 4) % 4;
	}
	head[minid] = tail[minid] = NULL;
}

char ask(int *setid, char *choices, int x, int y) {
	return setid[x] == setid[y] ? RESULTS[(choices[x] - choices[y] + 4) % 4]: '?';
}

bool valid(int *setid, char *choices, int x, int y, char c) {
	return setid[x] == setid[y] ? ask(setid, choices, x, y) == c : true;
}

void results_init() {
	RESULTS_ID['S'-'A'] = 0;
	RESULTS_ID['W'-'A'] = 1;
	RESULTS_ID['N'-'A'] = 2;
	RESULTS_ID['L'-'A'] = 3;
}

void robots_init(int *setid, int *setsz, char *choices, Node *robots, Node **head, Node **tail) {
	for (int n = 1; n <= N; n++) {
		setid[n] = n;
		setsz[n] = 1;
		choices[n] = '0';
		robots[n].next = NULL;
		head[n] = tail[n] = robots + n;
	}
}

int main(int argc, char *argv[]) {
	results_init();
	int setid[MAX_ROBOT + 1];
	int setsz[MAX_ROBOT + 1];
	char choices[MAX_ROBOT + 1];
	Node robots[MAX_ROBOT + 1];
	Node *head[MAX_ROBOT + 1];
	Node *tail[MAX_ROBOT + 1];
	for (int n = 1; n <= MAX_ROBOT; n++)
		robots[n].id = n;
	scanf("%d\n", &T);
	for (int t = 0; t < T; t++) {
		scanf("%d %d\n", &N, &Q);
		robots_init(setid, setsz, choices, robots, head, tail);
		for (int q = 0; q < Q; q++) {
			int x, y;
			char c;
			if (getchar() == 'R') {
				scanf(" %d %d %c\n", &x, &y, &c);
				if (!valid(setid, choices, x, y, c))
					printf("X\n");
				else if (setid[x] != setid[y]) {
					unite(setid, setsz, choices, head, tail, x, y, c);
					printf("O\n");
				} else
					printf("O\n");
			} else {
				scanf(" %d %d\n", &x, &y);
				printf("%c\n", ask(setid, choices, x, y));
			}
		}
	}
	return 0;
}
