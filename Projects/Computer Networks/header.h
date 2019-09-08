#include <stdbool.h>
#include <stddef.h>
#include <netinet/in.h>

#define PKTS_PER_BUFFER 32
#define HEADER_SIZE offsetof(Datagram, data)
#define DATA_SIZE 1000

#define MAX(a,b) ((a) > (b) ? (a) : (b))
#define MIN(a,b) ((a) < (b) ? (a) : (b))

typedef struct {
	struct sockaddr_in src;
	struct sockaddr_in dst;
	int seqno;
	int ackno;
	bool ack;
	bool fin;
	char data[DATA_SIZE];
} Datagram;

static socklen_t sockaddr_size = sizeof(struct sockaddr_in);

void sockaddr_init(struct sockaddr_in *addr, char *ip, int port);
void sockaddr_swap(Datagram *datagram);
