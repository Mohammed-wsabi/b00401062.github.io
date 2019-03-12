#include <assert.h>
#include <stdio.h>
#include <string.h>

#define MAXLEN 100002

static char text[MAXLEN], pattern[MAXLEN];

int compute(char S[], char T[], int len1, int len2) { // naive algorithm
	if (len1 > len2) {
		strncpy(text, S, len1);
		text[len1] = '\0';
		strncpy(pattern, T, len2);
		pattern[len2] = '\0';
	} else {
		strncpy(pattern, S, len1);
		pattern[len1] = '\0';
		strncpy(text, T, len2);
		text[len2] = '\0';
	}
	int count = 0;
	for (char *ptr = text; ; ptr++, count++) {
		ptr = strstr(ptr, pattern);
		if (ptr == NULL)
			return count;
	}
}

int main(int argc, char *argv[]) {
	int n_lines;
	assert(scanf("%d\n", &n_lines) != EOF);
	for (int n_line = 0; n_line < n_lines; n_line++) {
		char S[MAXLEN];
		char T[MAXLEN];
		assert(fgets(S, MAXLEN, stdin) != NULL);
		assert(fgets(T, MAXLEN, stdin) != NULL);
		int n_pairs;
		assert(scanf("%d\n", &n_pairs) != EOF);
		for (int n_pair = 0; n_pair < n_pairs; n_pair++) {
			int i1, j1, i2, j2;
			assert(scanf("%d %d %d %d\n", &i1, &j1, &i2, &j2) != EOF);
			printf("%d\n", compute(S + i1, T + i2, j1 - i1 + 1, j2 - i2 + 1));
		}
	}
	return 0;
}
