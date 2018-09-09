#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 102
#define MAXLINES 200000

static int COUNT, T[MAX][MAX], TOPS[MAX], PATH[MAXLINES][2];
static char STR0[MAX], STR1[MAX], BUF[MAX], REC[MAXLINES][MAX], *PTR[MAXLINES];

int cmp(const void *a, const void *b) {
    return strcmp(*(const char **) a, *(const char **) b);
}

bool isin(int level, int i, int j) {
	for (int k = TOPS[level+1]; k < TOPS[level]; k++)
		if (PATH[k][0] == i && PATH[k][1] == j)
			return true;
	return false;
}

int max(int a, int b, int c) {
	int max = a;
	(max < b) && (max = b);
	(max < c) && (max = c);
	return max;
}

void trace(int level, int i, int j) {
	if (level == 0) {
		sprintf(REC[COUNT++], "%s", BUF+1);
		return;
	}
	if (STR0[i] == STR1[j]) {
		if (isin(level, i, j)) return;
		if (TOPS[level] == 0) TOPS[level] = TOPS[level+1];
		PATH[TOPS[level]][0] = i;
		PATH[TOPS[level]][1] = j;
		TOPS[level]++;
		BUF[level] = STR0[i];
		trace(level-1, i-1, j-1);
		for (int l = 0; l < level; l++) TOPS[l] = 0;
	} else if (T[i][j] == T[i-1][j-1]) trace(level, i-1, j-1);
	if (T[i][j] == T[i-1][j]) trace(level, i-1, j);
	if (T[i][j] == T[i][j-1]) trace(level, i, j-1);
}

int main(int argc, char *argv[]) {
	scanf("%s\n%s", STR0+1, STR1+1);
	int M = strlen(STR0+1), N = strlen(STR1+1);
	for (int i = 1; i <= M; i++)
		for (int j = 1; j <= N; j++)
			T[i][j] = max((STR0[i] == STR1[j]) + T[i-1][j-1], T[i-1][j], T[i][j-1]);
	trace(T[M][N], M, N);
	printf("%d %d\n", T[M][N], COUNT);
	for (int i = 0; i < COUNT; i++) PTR[i] = REC[i];
	qsort(PTR, COUNT, sizeof(char *), cmp);
	for (int i = 0; i < COUNT; i++) printf("%s\n", PTR[i]);
	return 0;
}
