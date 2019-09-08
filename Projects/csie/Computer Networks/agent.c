#include <assert.h>
#include <fcntl.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include "header.h"

int main(int argc, char *argv[]) {
	// check arguments
	if (argc != 4) {
		printf("./agent [IP] [port] [loss rate]\n");
		return 0;
	}
	// socket address
	struct sockaddr_in agent;
	// initialize socket address
	sockaddr_init(&agent, argv[1], atoi(argv[2]));
	// create UDP socket
	int udp = socket(PF_INET, SOCK_DGRAM, 0);
	bind(udp, (struct sockaddr *) &agent, sockaddr_size);
	// loss rate
	double loss_rate = atof(argv[3]);
	int total_pkt_cnt = 0, dropped_pkt_cnt = 0;
	srand(time(NULL));
	// main loop
	Datagram datagram;
	while (true) {
		// receive packets from anywhere
		int pkt_size = recvfrom(udp, &datagram, sizeof(Datagram), 0, NULL, NULL);
		// check packet type
		if (!datagram.ack && !datagram.fin) { // data
			printf("get\tdata\t#%d\n", datagram.seqno);
			total_pkt_cnt++;
			// determine to drop/keep a packet
			if (rand() % 10000 < 10000 * loss_rate) { // drop
				dropped_pkt_cnt++;
				double real_rate = (double) dropped_pkt_cnt / total_pkt_cnt;
				printf("drop\tdata\t#%d,\tloss rate = %.4lf\n", datagram.seqno, real_rate);
				continue;
			} else { // keep
				double real_rate = (double) dropped_pkt_cnt / total_pkt_cnt;
				printf("fwd\tdata\t#%d,\tloss rate = %.4lf\n", datagram.seqno, real_rate);
			}
		} else if (datagram.ack && datagram.fin) { // finack
			printf("get\tfinack\n");
			printf("fwd\tfinack\n");
		} else if (datagram.ack) { // ack
			printf("get\tack\t#%d\n", datagram.ackno);
			printf("fwd\tack\t#%d\n", datagram.ackno);
		} else if (datagram.fin) { // fin
			printf("get\tfin\n");
			printf("fwd\tfin\n");
		} else assert(false);
		// send packets to destination
		sendto(udp, &datagram, pkt_size, 0, (struct sockaddr *) &datagram.dst, sockaddr_size);
	}
	return 0;
}
