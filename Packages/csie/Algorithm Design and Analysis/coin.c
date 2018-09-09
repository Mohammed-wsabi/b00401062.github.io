#include <stdio.h>
#include <stdlib.h>

int cmpfunc(const void * a, const void * b) {
	return *(int *) a - *(int *) b;
}

int main(int argc, char *argv[]) {
	int N, P;
	int coins[100] = { 0 };
	long long table[10001] = { 0 };
	table[0] = 1;
	scanf("%d %d\n", &N, &P);
	for (int n = 0; n < N; n++)
		scanf("%d", &coins[n]);
	qsort(coins, N, sizeof(int), cmpfunc);
	for (int n = 0; n < N; n++)
	    for (int i = coins[n]; i <= P; i++)
	        table[i] = (table[i] + table[i-coins[n]]) % 1000000007;
	printf("%lld\n", table[P]);
	return 0;
}
