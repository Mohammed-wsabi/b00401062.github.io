#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

static int Q, L, T;
static int q, a, b, c, d;
static int c_seed = 0, s_seed = 0;
static char cmd, *s;
static int machines[33554432] = { 0 };

void next_request() {
	c_seed = (c_seed * a + b) & 255;
	cmd = "AC"[c_seed & 1];
	for(int i = 0; i < L; i++){
		s_seed = (s_seed * c + d) & 1023;
		s[i] = (s_seed >> 3) & 63;
	}
}

unsigned int hash() {
	unsigned int code = 0;
	for (int i = 0; i < L; i++)
		code += (((int) s[i]) << (i * 6));
	return code;
}

bool has_key() {
	unsigned int code = hash();
	return ((machines[code / 32] & (1 << (code % 32)))) != 0;
}

void add_key() {
	unsigned int code = hash();
	machines[code / 32] |= (1 << (code % 32));
}

int main(int argc, char *argv[]) {
	// TODO
	scanf("%d %d %d %d %d %d %d\n", &Q, &L, &T, &a, &b, &c, &d);
	s = (char *) calloc(L + 1, sizeof(char));
	long long S = 0, X = 0;
	int next = 1;
	for (int t = 0; t < T; t++) {
		scanf("%d ", &q);
		while (next < q) {
			next_request();
			if (cmd == 'A')
				add_key();
			else if (cmd == 'C' && has_key())
				S += next, X ^= next;
			next++;
		}
		c_seed = s_seed = 0;
		scanf("%d %d %d %d\n", &a, &b, &c, &d);
		next_request();
		if (cmd == 'A')
			add_key();
		else if (cmd == 'C' && has_key())
			S += q, X ^= q;
		next++;
	}
	while (next <= Q) {
		next_request();
		if (cmd == 'A')
			add_key();
		else if (cmd == 'C' && has_key())
			S += next, X ^= next;
		next++;
	}
	printf("%lld %lld\n", S, X);
	return 0;
}
