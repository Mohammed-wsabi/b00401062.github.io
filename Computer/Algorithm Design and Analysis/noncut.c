#include <assert.h>
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <vector>

#define MIN(a,b) (a < b ? a : b)

#ifdef DEBUG
#define MAX 30
#else
#define MAX 300001
#endif

using namespace std;

typedef struct {
	vector<int> ns;
	vector<int> ws;
	int id;
	long long d;
	bool visited;
} Node;

void sink(Node *heap[], int size, int p) {
	while (p < size) {
		int l = p * 2 + 1;
		int r = p * 2 + 2;
		if (l >= size) return;
		int c = (r >= size) ? l : (heap[l]->d < heap[r]->d ? l : r);
		if (heap[c]->d < heap[p]->d) {
			Node *node = heap[p];
			heap[p] = heap[c];
			heap[c] = node;
			heap[p]->id = p;
			heap[c]->id = c;
			p = c;
		} else return;
	}
}

void swim(Node *heap[], int c) {
	while (c > 0) {
		int p = (c-1) / 2;
		if (heap[p]->d > heap[c]->d) {
			Node *node = heap[c];
			heap[c] = heap[p];
			heap[p] = node;
			heap[p]->id = p;
			heap[c]->id = c;
			c = p;
		} else return;
	}
}

Node *extract(Node *heap[], int size) {
	Node *node = heap[0];
	heap[0] = heap[size-1];
	heap[0]->id = 0;
	sink(heap, size-1, 0);
	return node;
}

void insert(Node *heap[], Node *node, int size) {
	heap[size] = node;
	heap[size]->id = size;
	swim(heap, size);
}

static Node nodes[MAX];

int main(int argc, char *argv[]) {
	int N, M, s, t;
	scanf("%d %d %d %d", &N, &M, &s, &t);
	for (int i = 1; i <= N; i++)
		nodes[i].d = LLONG_MAX;
	long long sum = 0;
	for (int i = 1; i <= M; i++) {
		int u, v, w;
		scanf("%d %d %d", &u, &v, &w);
		nodes[u].ns.push_back(v);
		nodes[u].ws.push_back(w);
		nodes[v].ns.push_back(u);
		nodes[v].ws.push_back(w);
		sum += w;
	}
	Node *heap[MAX];
	int size = 0;
	nodes[s].d = 0;
	insert(heap, nodes + s, size++);
	while (size) {
		Node *u = extract(heap, size--);
		u->visited = true;
		for (int i = 0; i < u->ns.size(); i++) {
			Node *v = nodes + u->ns[i];
			if (v->visited) {
				if (v == nodes + t) {
					printf("%lld\n", sum - nodes[t].d);
					return 0;
				} else continue;
			}
			if (v->d == LLONG_MAX) {
				v->d = u->d + u->ws[i];
				insert(heap, v, size++);
			} else if (u->d + u->ws[i] < v->d) {
				v->d = u->d + u->ws[i];
				swim(heap, v->id);
			}
		}
	}
	printf("%lld\n", nodes[t].d == LLONG_MAX ? -1 : sum - nodes[t].d);
	return 0;
}
