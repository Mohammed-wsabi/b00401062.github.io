#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <vector>

#ifdef DEBUG
#define MAXN 10
#else
#define MAXN N
#endif

using namespace std;

typedef enum { UNKNOWN, LT, RT } Side;

int main(int argc, char *argv[]) {
	int T;
	scanf("%d", &T);
	while (T--) {
		int N, M;
		scanf("%d %d", &N, &M);
		vector<int> adlst[MAXN+1];
		for (int i = 1; i <= M; i++) {
			int u, v;
			scanf("%d %d", &u, &v);
			adlst[u].push_back(v);
			adlst[v].push_back(u);
		}
		long long sum = 0;
		bool visited[MAXN+1] = { false };
		Side sides[MAXN+1] = { UNKNOWN };
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;
			int queue[N+1], head = 0, tail = 0;
			int len[3] = { 0 };
			bool bipartite = true;
			sides[i] = LT;
			visited[i] = true;
			queue[tail++] = i;
			while (head != tail) {
				int u = queue[head++];
				len[sides[u]]++;
				Side s = (sides[u] == LT) ? RT : LT;
				for (int v : adlst[u]) {
					if (sides[u] == sides[v])
						bipartite = false;
					sides[v] = s;
					if (visited[v]) continue;
					visited[v] = true;
					queue[tail++] = v;
				}
			}
			int nedges = 0;
			for (int i = 0; i < tail; i++)
				nedges += adlst[queue[i]].size();
			nedges /= 2;
			if (!bipartite)
				sum += (long long) tail * (tail-1) / 2 - nedges;
			else if (len[LT] > 1 && len[RT] > 1)
				sum += (long long) len[LT] * len[RT] - nedges;
		}
		printf("%lld\n", sum);
	}
	return 0;
}
