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
	if (argc != 4) {
		printf("./receiver [IP] [port] [file]\n");
		return 0;
	}
	// socket address
	struct sockaddr_in agent;
	struct sockaddr_in receiver;
	// initialize socket address
	sockaddr_init(&receiver, argv[1], atoi(argv[2]));
	// create UDP socket
	int udp = socket(PF_INET, SOCK_DGRAM, 0);;
	bind(udp, (struct sockaddr *) &receiver, sockaddr_size);

	int fd = open(argv[3], O_WRONLY | O_CREAT, 0644);
	char buffer[DATA_SIZE * PKTS_PER_BUFFER];
	// state parameters
	int recv_base = 0;
	int buffer_size = 0;
	// main loop
	Datagram datagram;
	while (true) {
		// receive packets from agent
		int pkt_size = recvfrom(udp, &datagram, sizeof(Datagram), 0, (struct sockaddr *) &agent, &sockaddr_size);
		// break main loop on fin
		if (datagram.fin)
			break;
		printf("recv\tdata\t#%d\n", datagram.seqno);
		// check if a packet is the expected one
		if (datagram.seqno == recv_base) {
			int data_size =  pkt_size - HEADER_SIZE;
			// check if the buffer is full or not
			if (buffer_size + data_size > sizeof(buffer)) { // full
				printf("drop\tseq\t#%d\n", datagram.seqno);
				// flush the buffer
				write(fd, buffer, buffer_size);
				printf("flush\n");
				buffer_size = 0;
				continue;
			} else { // not full
				memcpy(buffer + buffer_size, datagram.data, data_size);
				buffer_size += data_size;
				recv_base++;
			}
		}
		// make and send packets to agent
		sockaddr_swap(&datagram);
		datagram.ackno = recv_base - 1;
		datagram.ack = true, datagram.fin = false;
		sendto(udp, &datagram, HEADER_SIZE, 0, (struct sockaddr *) &agent, sockaddr_size);
		printf("send\tack\t#%d\n", datagram.ackno);
	}
	printf("recv\tfin\n");
	// make and send packets to agent
	sockaddr_swap(&datagram);
	datagram.ack = datagram.fin = true;
	sendto(udp, &datagram, HEADER_SIZE, 0, (struct sockaddr *) &agent, sockaddr_size);
	printf("send\tfinack\n");
	// flush the buffer
	write(fd, buffer, buffer_size);
	printf("flush\n");
	close(fd);
	return 0;
}
