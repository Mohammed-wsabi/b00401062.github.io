#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <setjmp.h>

#define MAXLEN 100002
#define DENOMINATOR 1000000007

typedef enum { INVALID, NUMBER, PAREN_LT, PAREN_RT, PLUS, MINUS, TIMES } Type;

typedef struct {
	long long number;
	char operator;
	Type type;
	int rank;
} Symbol;

typedef struct {
	Symbol *symbols;
	int size;
	int capacity;
} SymbolStack;

static jmp_buf error_buffer;

SymbolStack *new(int capacity) {
	SymbolStack *stack = (SymbolStack *) malloc(sizeof(SymbolStack));
	stack->symbols = (Symbol *) malloc(capacity * sizeof (Symbol));
	stack->size = 0;
	stack->capacity = capacity;
	return stack;
}

Symbol *last(SymbolStack *stack) {
	return stack->size == 0 ? NULL : stack->symbols + stack->size - 1;
}

void push(SymbolStack *stack, Symbol symbol) {
	if (stack->size == stack->capacity) {
		longjmp(error_buffer, 1);
	}
	stack->symbols[stack->size++] = symbol;
}

Symbol pop(SymbolStack *stack) {
	if (stack->size == 0) {
		longjmp(error_buffer, 1);
	}
	return stack->symbols[--stack->size];
}

SymbolStack *split_line(char *line) {
	int line_len = strlen(line);
	SymbolStack *array_symbol = new(line_len + 1);
	if (array_symbol == NULL) {
		longjmp(error_buffer, 1);
	}
	for (int i = 0; i < line_len; i++) {
		Symbol symbol;
		if (isdigit(line[i])) {
			int ndigits = strcspn(line + i, " ()+-*/");
			if (line[i] == '0' && ndigits > 1) {
				longjmp(error_buffer, 1);
			}
			char number[MAXLEN] = { 0 };
			memcpy(number, line + i, ndigits);
			symbol.number = 0;
			int number_len = strlen(number);
			for (int c = 0; c < number_len; c++) {
				symbol.number = (symbol.number * 10 + (number[c] - '0')) % DENOMINATOR;
			}
			symbol.type = NUMBER;
			symbol.rank = 0;
			i += (ndigits - 1);
		} else if (line[i] == '(') {
			symbol.operator = '(';
			symbol.type = PAREN_LT;
			symbol.rank = 0;
		} else if (line[i] == ')') {
			if (last(array_symbol) != NULL && last(array_symbol)->type == PAREN_LT) {
				longjmp(error_buffer, 1);
			}
			symbol.operator = ')';
			symbol.type = PAREN_RT;
			symbol.rank = 0;
		} else if (line[i] == '+') {
			symbol.operator = '+';
			symbol.type = PLUS;
			symbol.rank = 2;
		} else if (line[i] == '-') {
			symbol.operator = '-';
			symbol.type = MINUS;
			symbol.rank = 2;
		} else if (line[i] == '*') {
			symbol.operator = '*';
			symbol.type = TIMES;
			symbol.rank = 3;
		} else {
			longjmp(error_buffer, 1);
		}
		push(array_symbol, symbol);
	}
	return array_symbol;
}

SymbolStack *infix2postfix(SymbolStack *array_symbol) {
	SymbolStack *stack_operator = new(array_symbol->size + 1);
	SymbolStack *array_postfix = new(array_symbol->size + 1);
	if (stack_operator == NULL || array_postfix == NULL) {
		longjmp(error_buffer, 1);
	}
	for (int i = 0; i < array_symbol->size; i++) {
		Symbol *symbol_prev = last(stack_operator);
		Symbol symbol_curr = array_symbol->symbols[i];
		switch (symbol_curr.type) {
		case NUMBER:
			push(array_postfix, symbol_curr);
			break;
		case PAREN_LT:
			push(stack_operator, symbol_curr);
			break;
		case PAREN_RT:
			while (symbol_prev != NULL && symbol_prev->type != PAREN_LT) {
				push(array_postfix, pop(stack_operator));
				symbol_prev = last(stack_operator);
			}
			if (symbol_prev == NULL || pop(stack_operator).type != PAREN_LT) {
				longjmp(error_buffer, 1);
			}
			break;
		case PLUS:
		case MINUS:
		case TIMES:
			while (symbol_prev != NULL && symbol_prev->rank >= symbol_curr.rank) {
				push(array_postfix, pop(stack_operator));
				symbol_prev = last(stack_operator);
			}
			push(stack_operator, symbol_curr);
			break;
		default:
			break;
		}
	}
	while (stack_operator->size > 0) {
		push(array_postfix, pop(stack_operator));
	}
	free(stack_operator->symbols);
	free(stack_operator);
	return array_postfix;
}

long long calculate(long long operand1, long long operand2, char operator) {
	switch (operator) {
	case '+':
		return operand1 + operand2;
	case '-':
		return operand1 - operand2;
	case '*':
		return operand1 * operand2;
	default:
		longjmp(error_buffer, 1);
	}
}

long long process_postfix(SymbolStack *array_postfix) {
	SymbolStack *array_answer = new(array_postfix->size + 1);
	if (array_answer == NULL) {
		longjmp(error_buffer, 1);
	}
	for (int i = 0; i < array_postfix->size; i++) {
		long long operand1, operand2;
		char operator;
		switch (array_postfix->symbols[i].type) {
		case PLUS:
		case MINUS:
		case TIMES:
			operand2 = pop(array_answer).number;
			operand1 = pop(array_answer).number;
			operator = array_postfix->symbols[i].operator;
			long long answer = calculate(operand1, operand2, operator) % DENOMINATOR;
			Symbol symbol = { .number = answer, .type = NUMBER, .rank = 0 };
			push(array_answer, symbol);
			break;
		default:
			push(array_answer, array_postfix->symbols[i]);
			break;
		}
	}
	if (array_answer->size > 1) {
		longjmp(error_buffer, 1);
	}
	return pop(array_answer).number;
}

int main(int argc, char *argv[]) {
	int nlines;
	char line[MAXLEN];
	SymbolStack *array_symbol;
	SymbolStack *array_postfix;
	assert(scanf("%d\n", &nlines) != EOF);
	for (int nline = 0; nline < nlines; nline++) {
		assert(fgets(line, MAXLEN, stdin) != NULL);
		int line_len = strlen(line);
		if (line[line_len - 1] == '\n') {
			line[line_len - 1] = '\0';
		}
		if (setjmp(error_buffer)) {
			printf("invalid\n");
		} else {
			array_symbol = split_line(line);
			array_postfix = infix2postfix(array_symbol);
			printf("%lld\n", (process_postfix(array_postfix) + DENOMINATOR) % DENOMINATOR);
			free(array_symbol->symbols);
			free(array_symbol);
			free(array_postfix->symbols);
			free(array_postfix);
		}
	}
	return 0;
}
