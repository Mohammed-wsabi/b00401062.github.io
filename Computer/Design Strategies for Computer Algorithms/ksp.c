#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#define max(a,b) (a > b ? a : b)

static int W, N;
static int lb = 0;

typedef struct {
	int v, w;
} Item;

typedef struct node {
	short i;
	int v, w;
	double ub;
	struct node *lt, *rt;
} Node;

int cmp(Item *a, Item *b) {
	double da = (double) a->v / a->w;
	double db = (double) b->v / b->w;
	return (da < db) - (da > db);
}

double ub(Node *node, Item *items) {
	int w = node->w;
	double ub = node->v;
	for (int i = node->i + 1; i <= N; i++)
		if (w + items[i].w > W) {
		 	ub += (double) items[i].v * (W - w) / items[i].w;
			break;
		} else {
			w += items[i].w;
			ub += items[i].v;
		}
	return ub;
}

void init(Node *root, Item *items) {
	root->i = 0;
	root->v = 0;
	root->w = 0;
	root->ub = ub(root, items);
}

void knapsack(Node *node, Item *items) {
	int i = node->i;
	if (i == N) {
		lb = max(lb, node->v);
		return;
	}
	bool lt = true, rt = true;
	node->lt = malloc(sizeof(Node));
	node->rt = malloc(sizeof(Node));
	node->lt->i = node->rt->i = i + 1;
	if (node->w + items[i+1].w <= W) {
		node->lt->v = node->v + items[i+1].v;
		node->lt->w = node->w + items[i+1].w;
		node->lt->ub = ub(node->lt, items);
	} else lt = false;
	node->rt->v = node->v;
	node->rt->w = node->w;
	node->rt->ub = ub(node->rt, items);
	if (node->lt->ub <= lb)
		lt = false;
	if (node->rt->ub <= lb)
		rt = false;
	if (lt && rt) {
		if (node->lt->ub > node->rt->ub) {
			knapsack(node->lt, items);
			knapsack(node->rt, items);
		} else {
			knapsack(node->rt, items);
			knapsack(node->lt, items);
		}
	} else if (lt)
		knapsack(node->lt, items);
	else if (rt)
		knapsack(node->rt, items);
	free(node->lt);
	free(node->rt);
}

int main(int argc, char *argv[]) {
	scanf("%d %d", &W, &N);
	Item items[N+1];
	for (int i = 1; i <= N; i++)
		scanf("%d %d",	&items[i].v, &items[i].w);
	qsort(items+1, N, sizeof(Item), cmp);
	Node root;
	init(&root, items);
	knapsack(&root, items);
	printf("%d\n", lb);
	return 0;
}
