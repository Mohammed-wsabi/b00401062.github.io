#include <stdbool.h>
#include <stdio.h>

#ifdef DEBUG
#define MAXDIM 32
#else
#define MAXDIM 3002
#endif

int main(int argc, char *argv[]) {
	int R, C;
	char rooms[MAXDIM][MAXDIM];
	const int di[] = { -1, 1, 0, 0 };
	const int dj[] = { 0, 0, -1, 1 };
	int head = 0, tail = 0;
	int queue[MAXDIM * MAXDIM][2];
	int dists[MAXDIM][MAXDIM] = {{ 0 }};
	bool visited[MAXDIM][MAXDIM] = {{ false }};
	scanf("%d %d", &R, &C);
	for (int i = 1; i <= R; i++)
		scanf("%s\n", &rooms[i][1]);
	for (int i = 1; i <= R; i++)
		rooms[i][0] = rooms[i][C+1] = 'F';
	for (int j = 1; j <= C; j++)
		rooms[0][j] = rooms[R+1][j] = 'F';
	for (int i = 1; i <= R; i++)
		for (int j = 1; j <= C; j++)
			if (rooms[i][j] == 'E') {
				queue[tail][0] = i, queue[tail++][1] = j;
				dists[i][j] = 0;
				visited[i][j] = true;
			}
	while (tail - head > 0) {
		int i = queue[head][0], j = queue[head++][1];
		for (int d = 0; d < 4; d++) {
			int in = i + di[d], jn = j + dj[d];
			if (rooms[in][jn] != 'F' && !visited[in][jn]) {
				queue[tail][0] = in, queue[tail++][1] = jn;
				dists[in][jn] = dists[i][j] + 1;
				visited[in][jn] = true;
			}
		}
	}
	for (int i = 1; i <= R; i++)
		for (int j = 1; j <= C; j++)
			if (rooms[i][j] == 'P') {
				if (dists[i][j])
					printf("%d\n", dists[i][j]);
				else
					printf("Died\n");
			}
	return 0;
}
