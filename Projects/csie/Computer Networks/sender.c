#include <assert.h>
#include <fcntl.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include "header.h"

int main(int argc, char** argv){
	// check arguments
	if (argc != 8) {
		printf("./sender [IP] [port] [agent IP] [agent port] [receiver IP] [receiver port] [file]\n");
		return 0;
	}
	// socket address
	struct sockaddr_in agent;
	struct sockaddr_in receiver;
	struct sockaddr_in sender;
	// initialize socket address
	sockaddr_init(&sender, argv[1], atoi(argv[2]));
	sockaddr_init(&agent, argv[3], atoi(argv[4]));
	sockaddr_init(&receiver, argv[5], atoi(argv[6]));
	// create UDP socket
	int udp = socket(PF_INET, SOCK_DGRAM, 0);
	bind(udp, (struct sockaddr *) &sender, sockaddr_size);
	// set time out interval
	struct timeval tv;
	tv.tv_sec = 1;
	tv.tv_usec = 0;
	setsockopt(udp, SOL_SOCKET, SO_RCVTIMEO, &tv, sizeof(struct timeval));
	// open file discriptor
	int fd = open(argv[7], O_RDONLY);
	// state parameters
	int send_base = 0;
	int threshold = 16;
	int win_size = 1;
	int last_sent = -1;
	int max_sent = -1;
	int max_seqno = -1;
	bool rollback = false;
	// main loop
	Datagram datagram;
	while (true) {
		int limit = send_base + win_size;
		// send all packet from base to limit seqno
		for (int seqno = send_base; seqno < limit; seqno++) {
			// check whether to roll back
			if (!rollback && seqno <= last_sent)
				continue;
			// check to break if there is no data left
			lseek(fd, DATA_SIZE * seqno, SEEK_SET);
			int data_size = read(fd, datagram.data, DATA_SIZE);
			if (!data_size) {
				max_seqno = seqno - 1;
				break;
			}
			// make and send packets to agent
			datagram.src = sender;
			datagram.dst = receiver;
			datagram.seqno = last_sent = seqno;
			datagram.ack = datagram.fin = false;
			sendto(udp, &datagram, HEADER_SIZE + data_size, 0, (struct sockaddr *) &agent, sockaddr_size);
			printf("%s\tdata\t#%d,\twinSize = %d\n", (seqno <= max_sent) ? "resnd" : "send", datagram.seqno, win_size);
			max_sent = MAX(max_sent, seqno);
		}
		// check timeout
		if (recvfrom(udp, &datagram, HEADER_SIZE, 0, (struct sockaddr *) &agent, &sockaddr_size) == -1) { // timeout
			rollback = true;
			threshold = MAX(win_size/2, 1);
			win_size = 1;
			printf("time\tout,\t\tthreshold = %d\n", threshold);
		} else { // not timeout
			rollback = false;
			assert(datagram.ack);
			assert(datagram.ackno <= send_base);
			printf("recv\tack\t#%d\n", datagram.ackno);
			// check to break if ackno equals max seqno
			if (datagram.ackno == max_seqno)
				break;
			// check if ackno equal base seqno
			if (datagram.ackno == send_base) {
				win_size += (win_size < threshold) ? win_size : 1;
				send_base++;
			}
		}
	}
	// make and send packets to agent
	datagram.src = sender;
	datagram.dst = receiver;
	datagram.ack = false, datagram.fin = true;
	sendto(udp, &datagram, HEADER_SIZE, 0, (struct sockaddr *) &agent, sockaddr_size);
	printf("send\tfin\n");
	// receive packets from agent
	recvfrom(udp, &datagram, HEADER_SIZE, 0, (struct sockaddr *) &agent, &sockaddr_size);
	assert(datagram.ack && datagram.fin);
	printf("recv\tfinack\n");
	close(fd);
	return 0;
}
