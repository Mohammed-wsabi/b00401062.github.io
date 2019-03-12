#include <assert.h>
#include <stdio.h>

#define MAXN 1000
#define MAXK 10000

#define MIN(a,b) ((a) < (b) ? a : b)
#define MAX(a,b) ((a) > (b) ? a : b)

typedef struct {
	short i, t;
	int f, F, D;
} Pond;

long long sum(long long f, long long d, long long t) {
	if (d == 0) return f * t;
	long long ts = f / d;
	if (t <= ts) return (f * 2 - d * (t-1)) * t / 2;
	else return (f * 2 - d * (ts-1)) * ts / 2 + f % d;
}

void sink(Pond *ponds, int N, int s) {
	while (s < N) {
		int l = s * 2 + 1;
		int r = s * 2 + 2;
		if (l >= N) return;
		int m = (r >= N) ? l : ((ponds[l].f > ponds[r].f) ? l : r);
		if (ponds[m].f > ponds[s].f) {
			Pond pond = ponds[s];
			ponds[s] = ponds[m];
			ponds[m] = pond;
			s = m;
		} else return;
	}
}

int swim(Pond *ponds, int s) {
	while (s > 0) {
		int m = (s-1) / 2;
		if (ponds[m].f < ponds[s].f) {
			Pond pond = ponds[s];
			ponds[s] = ponds[m];
			ponds[m] = pond;
			s = m;
		} else break;
	}
	return s;
}

int extract(Pond *ponds, int N) {
	int max = ponds->f;
	int f = ponds->f - ponds->D;
	ponds->f = (f > 0) ? f : 0;
	ponds->t++;
	sink(ponds, N, 0);
	return max;
}

Pond plug(Pond *ponds, int N, int s) {
	for (int i = 0; i < N; i++)
		if (ponds[i].i == s) {
			Pond pond = ponds[i];
			ponds[i] = ponds[N-1];
			sink(ponds, N-1, swim(ponds, i));
			return pond;
		}
	assert(0);
}

int main(int argc, char *argv[]) {
	int T, N, K;
	scanf("%d", &T);
	while (T-- > 0) {
		Pond PONDS[MAXN];
		long long FISH = 0;
		int TIME[MAXN] = { 0 };
		scanf("%d %d", &N, &K);
		for (int i = 0; i < N; i++) {
			scanf("%d %d", &(PONDS[i].F), &(PONDS[i].D));
			PONDS[i].i = i, PONDS[i].t = 0, PONDS[i].f = PONDS[i].F;
		}
		N = MIN(N, K);
		long long fish = 0;
		for (int i = N/2; i >= 0; i--)
			sink(PONDS, N, i);
		int t = K - N + 1;
		for (int n = N; n >= 1; n--) {
			while (t-- > 0)
				fish += extract(PONDS, n);
			if (fish > FISH) {
				FISH = fish;
				for (int i = 0; i < n; i++)
					TIME[PONDS[i].i] = PONDS[i].t;
				for (int i = n; i < N; i++)
					TIME[i] = 0;
			}
			Pond pond = plug(PONDS, n, n-1);
			assert(pond.i == n-1);
			fish -= sum(pond.F, pond.D, pond.t);
			t = pond.t + 1;
		}
		int count = 0;
		for (int i = 0; i < N; i++)
			count += (TIME[i] != 0);
		assert(count != 0);
		printf("%lld %d\n", FISH, 2 * count - (TIME[0] != 0));
		if (TIME[0] != 0)
			printf("fish %d\n", TIME[0]);
		for (int i = 1; i < N; i++) {
			if (TIME[i] == 0) continue;
			printf("move %d\n", i+1);
			printf("fish %d\n", TIME[i]);
		}
	}
	return 0;
}
