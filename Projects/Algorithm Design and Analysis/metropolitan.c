#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <vector>

#ifdef DEBUG
#define MAXN 10
#else
#define MAXN N
#endif

using namespace std;

int main(int argc, char *argv[]) {
	int N, M;
	scanf("%d %d", &N, &M);
	if (M == 0) {
		printf("1\n");
		return 0;
	}
	vector<int> adlst[MAXN+1];
	for (int i = 1; i <= M; i++) {
		int u, v;
		scanf("%d %d", &u, &v);
		adlst[u].push_back(v);
		adlst[v].push_back(u);
	}
	for (int i = 1; i <= N; i++)
		sort(adlst[i].begin(), adlst[i].end());
	bool atleast3 = false;
	vector<int> v1;
	for (int i = 1; i <= N; i++)
		if (adlst[i].size() >= 3)
			v1.push_back(i);
	for (int i : v1) {
		for (int j : adlst[i]) {
			if (adlst[j].size() < 3) continue;
			vector<int> v2;
			set_intersection(adlst[i].begin(), adlst[i].end(), adlst[j].begin(), adlst[j].end(), back_inserter(v2));
			if (!v2.size()) continue;
			atleast3 = true;
			for (int k : v2) {
				if (adlst[k].size() < 3) continue;
				vector<int> v3;
				set_intersection(v2.begin(), v2.end(), adlst[k].begin(), adlst[k].end(), back_inserter(v3));
				if (!v3.size()) continue;
				printf("4\n");
				return 0;
			}
		}
	}
	if (atleast3) {
		printf("3\n");
		return 0;
	}
	vector<int> v4;
	for (int i = 1; i <= N; i++)
		if (adlst[i].size() >= 2)
			v4.push_back(i);
	for (int i : v4) {
		for (int j : adlst[i]) {
			if (adlst[j].size() < 2) continue;
			vector<int> v5;
			set_intersection(adlst[i].begin(), adlst[i].end(), adlst[j].begin(), adlst[j].end(), back_inserter(v5));
			if (!v5.size()) continue;
			printf("3\n");
			return 0;
		}
	}
	printf("2\n");
	return 0;
}
