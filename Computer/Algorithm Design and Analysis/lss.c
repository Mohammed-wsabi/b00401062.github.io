#include <stdio.h>

#define MAXLEN 2000

#define min(a,b) ((a < b) ? (a) : (b))

int main(int argc, char *argv[]) {
	int N, M, K;
	int A[MAXLEN+1], B[MAXLEN+1];
	int L[2][MAXLEN+1] = {{ 0 }}, D[2][MAXLEN+1] = {{ 0 }};
	scanf("%d %d %d", &N, &M, &K);
	for (int i = 1; i <= N; i++)
		scanf("%d", A + i);
	for (int i = 1; i <= M; i++)
		scanf("%d", B + i);
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			int i0 = i%2, i1 = (i+1)%2;
			if (A[i] == B[j]) {
				L[i0][j] = L[i1][j-1] + 1;
				D[i0][j] = D[i1][j-1];
			} else if (L[i1][j-1] >= L[i1][j] && L[i1][j-1] >= L[i0][j-1]) {
				L[i0][j] = L[i1][j-1];
				D[i0][j] = D[i1][j-1] + 1;
			} else if (L[i1][j] >= L[i0][j-1]) {
				L[i0][j] = L[i1][j];
				D[i0][j] = D[i1][j];
			} else {
				L[i0][j] = L[i0][j-1];
				D[i0][j] = D[i0][j-1];
			}
		}
	}
	printf("%d\n", L[N%2][M] + min(D[N%2][M], K));
	return 0;
}
