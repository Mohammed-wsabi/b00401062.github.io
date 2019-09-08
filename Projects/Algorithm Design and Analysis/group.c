#include <stdio.h>

#define MIN(a,b) ((a < b) ? a : b)

static int N, K, W[2000001];
static long long M[21][2000001];
static long long COST[2000001];

int main(int argc, char *argv[]) {
	scanf("%d %d\n", &N, &K);
	for (int n = 1; n <= N; n++)
		scanf("%d", &W[n]);
	for (int n = 1; n <= N; n++) {
		for (int k = 1; k <= MIN(n, K); k++) {
			for (int l = n; l > 0; l--)
				COST[l-1] = W[l] * (n-l) + COST[l];
			M[k][n] = M[k-1][k-1] + COST[k-1];
			if (k == 1) continue;
			for (int i = k; i < n; i++)
				M[k][n] = MIN(M[k][n], M[k-1][i] + COST[i]);
		}
	}
	printf("%lld\n", M[K][N]);
	return 0;
}
