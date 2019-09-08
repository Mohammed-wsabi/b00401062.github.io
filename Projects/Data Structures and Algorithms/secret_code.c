#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXLEN 1000002
#define NALPHABETS 26

struct alphabet {
	char symbol;
	int count;
};

int compare_alphabet(struct alphabet *a, struct alphabet *b) {
	if (b->count != a->count)
		return b->count - a->count;
	else
		return a->symbol - b->symbol;
}

char *cipher(char *line) {
	int line_len = strlen(line);
	// count occurrence of each alphabet
	struct alphabet alphabets[NALPHABETS];
	for (int i = 0; i < NALPHABETS; i++) {
		alphabets[i].symbol = 'a' + i;
		alphabets[i].count = 0;
	}
	for (int i = 0; i < line_len; i++)
		if (isalpha(line[i]))
			alphabets[line[i] - 'a'].count++;
	// sort alphabets
	qsort(alphabets, NALPHABETS, sizeof(struct alphabet), compare_alphabet);
	// collect alphabets in the line
	int char_set_len = 0;
	for (; char_set_len < NALPHABETS && alphabets[char_set_len].count != 0; char_set_len++);
	char char_set[NALPHABETS] = { 0 };
	for (int i = 0; i < char_set_len; i++)
		char_set[alphabets[i].symbol - 'a'] = alphabets[char_set_len - i - 1].symbol;
	// cipher the line
	for (int i = 0; i < line_len; i++)
		if (isalpha(line[i]))
			line[i] = char_set[line[i] - 'a'];
	return line;
}

int main(int argc, char *argv[]) {
	int n_lines;
	char line[MAXLEN];
	assert(scanf("%d\n", &n_lines) != EOF);
	for (int n_line = 0; n_line < n_lines; n_line++) {
		assert(fgets(line, MAXLEN, stdin) != NULL);
		assert(line[strlen(line) - 1] == '\n');
		printf("%s", cipher(line));
	}
	return 0;
}
