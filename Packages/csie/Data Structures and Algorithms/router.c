#include <assert.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

struct trie {
	struct trie *lt;
	struct trie *rt;
	bool end;
};

void trie_init(struct trie *trie) {
	trie->lt = trie->rt = NULL;
	trie->end = false;
}

void trie_insert(struct trie *trie, int address, int mask) {
	struct trie *ptr = trie;
	for (int i = 0; i < mask; i++) {
		bool bit = (address & (1 << (31 - i))) != 0;
		if (bit && !ptr->rt)
			ptr->rt = calloc(1, sizeof(struct trie));
		else if (!bit && !ptr->lt)
			ptr->lt = calloc(1, sizeof(struct trie));
		ptr = bit ? ptr->rt : ptr->lt;
		if (ptr->end) return;
	}
	ptr->end = true;
}

bool trie_contain(struct trie *trie, int address) {
	struct trie *ptr = trie;
	for (int i = 0; i < 32; i++) {
		bool bit = (address & (1 << (31 - i))) != 0;
		ptr = bit ? ptr->rt : ptr->lt;
		if (!ptr) return false;
		if (ptr->end) return true;
	}
	assert(false);
}

int main(int argc, char *argv[]) {
	// TODO
	int T, M, N;
	scanf("%d\n", &T);
	for (int t = 0; t < T; t++) {
		struct trie trie;
		trie_init(&trie);
		scanf("%d %d\n", &M, &N);
		for (int m = 0; m < M; m++) {
			int a, b, c, d;
			int address, mask;
			scanf("%d.%d.%d.%d/%d\n", &a, &b, &c, &d, &mask);
			address = (a << 24) + (b << 16) + (c << 8) + d;
			trie_insert(&trie, address, mask);
		}
		for (int n = 0; n < N; n++) {
			int a, b, c, d;
			int address;
			scanf("%d.%d.%d.%d\n", &a, &b, &c, &d);
			address = (a << 24) + (b << 16) + (c << 8) + d;
			if (trie_contain(&trie, address))
				printf("TRUE\n");
			else
				printf("FALSE\n");
		}
	}
	return 0;
}
