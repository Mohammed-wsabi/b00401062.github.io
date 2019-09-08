#include <stdio.h>

#define MAX 64

int main(int argc, char *argv[]) {
	int T, N, ncand;
	unsigned long long k, s[MAX], cand[MAX];
	scanf("%d", &T);
	for (int t = 0; t < T; t++) {
		scanf("%d %llu", &N, &k);
		for (int n = 0; n < N; n++)
			scanf("%llu", s + n);
		ncand = 0;
		for (int n = N-1; n >= 0 && k > 0; n--)
			if (s[n] <= k)
				k -= (cand[ncand++] = s[n]);
		if (k == 0) {
			printf("%d\n", ncand);
			for (int i = ncand-1; i >= 0; i--)
				printf("%llu%c", cand[i], i == 0 ? '\n':' ');
		} else
			printf("%d\n", -1);
	}
	return 0;
}
