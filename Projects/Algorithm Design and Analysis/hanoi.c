#include <assert.h>
#include <stdio.h>

static unsigned long long L, R, i;

void move(int n, int from, int to) {
	if (i > R)
		return;
	if (n == 1) {
		i++;
		if (L <= i && i <= R)
			printf("Step %llu: #%d -> #%d\n", i, from, to);
		return;
	}
	unsigned long long s = ((unsigned long long) 1 << (n - 1)) - 1;
	if (n == 65) s = ~(unsigned long long) 0;
	if (i + s >= L)
		move(n - 1, from, 6 - from - to);
	else
		i += s;
	move(1, from, to);
	if (i + s >= L)
		move(n - 1, 6 - from - to, to);
	else
		i += s;
}

int main(int argc, char *argv[]) {
	int T, N;
	scanf("%d\n", &T);
	for (int t = 1; t <= T; t++) {
		printf("Case #%d:\n", t);
		scanf("%d %llu %llu\n", &N, &L, &R);
		i = 0;
		if (N > 64) N = (N % 2 == 0) ? 64 : 65;
		move(N, 1, 3);
	}
	return 0;
}
