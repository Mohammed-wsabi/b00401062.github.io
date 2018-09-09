#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int *edges;
	int edges_len;
	int depth;
} vertex_t;

#define MAX_N 200000
#define MAX_M 500000
#define MAX_Q 100000
#define MIN(a,b) ((a) < (b) ? (a) : (b))

static int T, N, M, Q;
static int queue[MAX_N];

void BFS(vertex_t *g, int s) {
	for (int i = 1; i <= N; i++)
		g[i].depth = N;
	bool visited[MAX_N + 1] = { false };
	int head = 0, tail = 0;
	queue[tail++] = s;
	visited[s] = true;
	g[s].depth = 0;
	while (head != tail) {
		int u = queue[head++];
		for (int i = 0; i < g[u].edges_len; i++) {
			int v = g[u].edges[i];
			if (!visited[v]) {
				queue[tail++] = v;
				visited[v] = true;
				g[v].depth = g[u].depth + 1;
			}
		}
	}
}

int main() {
	int a, b, as[MAX_M], bs[MAX_M];
	scanf("%d\n", &T);
	for (int t = 0; t < T; t++) {
		scanf("%d %d %d\n", &N, &M, &Q);
		vertex_t *src = calloc(N + 1, sizeof(vertex_t));
		vertex_t *dst = calloc(N + 1, sizeof(vertex_t));
		int src_cnt[MAX_N + 1] = { 0 };
		int dst_cnt[MAX_N + 1] = { 0 };
		for (int m = 0; m < M; m++) {
			scanf("%d %d\n", as + m, bs + m);
			src_cnt[as[m]]++;
			dst_cnt[bs[m]]++;
		}
		int src_edges[MAX_M + MAX_N];
		int dst_edges[MAX_M + MAX_N];
		for (int n = 1, cumcnt = 0; n <= N; n++) {
			src[n].edges = src_edges + cumcnt + n-1;
			cumcnt += src_cnt[n];
		}
		for (int n = 1, cumcnt = 0; n <= N; n++) {
			dst[n].edges = dst_edges + cumcnt + n-1;
			cumcnt += dst_cnt[n];
		}
		for (int m = 0; m < M; m++) {
			vertex_t *v1 = src + as[m];
			v1->edges[v1->edges_len++] = bs[m];
			vertex_t *v2 = dst + bs[m];
			v2->edges[v2->edges_len++] = as[m];
		}
		BFS(src, 1);
		BFS(dst, N);
		for (int q = 0; q < Q; q++) {
			scanf("%d %d\n", &a, &b);
			printf("%d\n", MIN(src[N].depth, src[a].depth + 1 + dst[b].depth));
		}
		free(src);
		free(dst);
	}
	return 0;
}
