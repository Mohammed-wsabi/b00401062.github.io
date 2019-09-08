#include <stdio.h>
#include <string.h>

#ifdef DEBUG
#define MAXS 101
#else
#define MAXS 1000001
#endif

typedef struct {
	int right, delta;
} Cost;

typedef struct {
	Cost costs[MAXS];
	int size;
} Heap;

void swim(Heap *heap, int s) {
	Cost *costs = heap->costs;
	while (s > 0) {
		int p = (s-1) / 2;
		if (costs[p].delta > costs[s].delta) {
			Cost cost = costs[s];
			costs[s] = costs[p];
			costs[p] = cost;
			s = p;
		} else return;
	}
}

void sink(Heap *heap, int s) {
	Cost *costs = heap->costs;
	const int size = heap->size;
	while (s < size) {
		int l = 2 * s + 1;
		int r = 2 * s + 2;
		if (l >= size) return;
		int m = (r >= size) ? l : ((costs[l].delta < costs[r].delta) ? l : r);
		if (costs[m].delta < costs[s].delta) {
			Cost cost = costs[s];
			costs[s] = costs[m];
			costs[m] = cost;
			s = m;
		} else return;
	}
}

void insert(Heap *heap, int lt, int rt) {
	Cost cost = { rt, lt-rt };
	heap->costs[heap->size++] = cost;
	swim(heap, heap->size-1);
}

Cost extract(Heap *heap) {
	Cost cost = *(heap->costs);
	heap->costs[0] = heap->costs[--heap->size];
	sink(heap, 0);
	return cost;
}

int main(int argc, char *argv[]) {
	char s[MAXS];
	scanf("%s", s);
	int slen = strlen(s);
	Heap heap;
	heap.size = 0;
	int imbalance = 0;
	long long cost = 0;
	for (int i = 0; i < slen; i++) {
		imbalance += (s[i] == '(') ? 1 : -1;
		if (s[i] == '?') {
			int lt, rt;
			scanf("%d %d", &lt, &rt);
			cost += rt;
			insert(&heap, lt, rt);
		}
		if (imbalance < 0) {
			if (heap.size == 0) break;
			imbalance += 2;
			cost += extract(&heap).delta;
		}
	}
	if (imbalance)
		printf("Impossible\n");
	else
		printf("%lld\n", cost);
	return 0;
}
