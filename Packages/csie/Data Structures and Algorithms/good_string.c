#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXLEN 1000002
#define NALPHABETS 26

long long process_line(char *line) {
	int buckets[NALPHABETS] = { 0 };
	long long good_value = 0;
	int empty = NALPHABETS, head = 0, tail = 0;
	int line_len = strlen(line);
	for (; tail < line_len && empty > 0; tail++) {
		int to_enqueue = line[tail] - 'a';
		empty -= (buckets[to_enqueue] == 0);
		buckets[to_enqueue]++;
	}
	if (empty > 0) {
		return 0;
	}
	buckets[line[--tail] - 'a']--;
	do {
		buckets[line[tail++] - 'a']++;
		while (buckets[line[head] - 'a'] > 1) {
			buckets[line[head++] - 'a']--;
		}
		good_value += (head + 1);
	} while (tail < line_len);
	return good_value;
}

int main(int argc, char *argv[]) {
	int nlines;
	char line[MAXLEN];
	assert(scanf("%d\n", &nlines) != EOF);
	for (int nline = 0; nline < nlines; nline++) {
		assert(fgets(line, MAXLEN, stdin) != NULL);
		if (line[strlen(line) - 1] == '\n') {
			line[strlen(line) - 1] = '\0';
		}
		printf("%lld\n", process_line(line));
	}
	return 0;
}
