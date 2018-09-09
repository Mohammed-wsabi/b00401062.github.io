#include <string.h>
#include <arpa/inet.h>
#include "header.h"

/**
 *
 * @param addr socket address
 * @param ip ip address
 * @param port port number
 */
void sockaddr_init(struct sockaddr_in *addr, char *ip, int port) {
	addr->sin_family = AF_INET;
	addr->sin_port = htons(port);
	addr->sin_addr.s_addr = inet_addr(ip);
	memset(addr->sin_zero, 0, sizeof(addr->sin_zero));
}

/**
 *
 * @param datagram datagram
 */
void sockaddr_swap(Datagram *datagram) {
	struct sockaddr_in tmp = datagram->src;
	datagram->src = datagram->dst;
	datagram->dst = tmp;
}
