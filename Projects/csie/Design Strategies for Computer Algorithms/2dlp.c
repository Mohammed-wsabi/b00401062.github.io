#include <assert.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#define MAXN 100000
#define ERROR .1
#define DBL_MAX 10000.

#define MIN(a,b) ((a) < (b) ? (a) : (b))
#define MAX(a,b) ((a) > (b) ? (a) : (b))

typedef enum { N, P, M } Class;
typedef enum { L, R } LR;

typedef struct {
	int a, b, c;
	double s, i;
} Line;

typedef struct {
	double x, y;
} Point;

typedef struct {
	Line *l1, *l2;
	Line *smin, *smax;
	Line *imin, *imax;
	Class class;
	Point *p;
} Cross;

static int NLINES, NPOOLS, NCROSSES;
static double XRANGES[2] = { -DBL_MAX, DBL_MAX };
static Line LINES[MAXN];
static Line *POOLS[MAXN];
static Cross CROSSES[MAXN];

void shuffle() {
	for (int i = 1; i < NPOOLS; i++) {
		int x = rand() % (i + 1);
		Line *l = POOLS[x];
		POOLS[x] = POOLS[i];
		POOLS[i] = l;
	}
}

Cross cross(Line *l1, Line *l2) {
	Line *smin = l2, *smax = l1;
	if (l1->s < l2->s)
		smin = l1, smax = l2;
	Line *imin = l2, *imax = l1;
	if (l1->i < l2->i)
		imin = l1, imax = l2;
	Class class = (l1->b > 0) + (l2->b > 0);
	class = (class % 2) * 2 + class / 2;
	Cross cross = { l1, l2, smin, smax, imin, imax, class, NULL };
	int det = l1->a * l2->b - l1->b * l2->a;
	if (det != 0) {
		cross.p = (Point *) malloc(sizeof(Point));
		cross.p->x = (double) (l1->c * l2->b - l1->b * l2->c) / det;
		cross.p->y = (double) (l1->a * l2->c - l1->c * l2->a) / det;
	}
	return cross;
}

double solve(Line *l, double x) {
	return (-x * l->a + l->c) / l->b;
}

void prune(double xm, LR lr) {
	NPOOLS = 0;
	int sign = lr ? 1 : -1;
	for (int i = 0; i < NCROSSES; i++) {
		if (!CROSSES[i].p) {
			if (CROSSES[i].class)
				POOLS[NPOOLS++] = CROSSES[i].imin;
			else
				POOLS[NPOOLS++] = CROSSES[i].imax;
		} else if (CROSSES[i].p->x * sign >= xm * sign) {
			if (CROSSES[i].class ^ lr)
				POOLS[NPOOLS++] = CROSSES[i].smin;
			else
				POOLS[NPOOLS++] = CROSSES[i].smax;
		} else {
			POOLS[NPOOLS++] = CROSSES[i].l1;
			POOLS[NPOOLS++] = CROSSES[i].l2;
		}
	}
}

void search() {
	shuffle();
	Line *queue[2] = { NULL };
	for (int i = 0; i < NPOOLS; i++) {
		Class class = (POOLS[i]->b > 0);
		if (queue[class]) {
			CROSSES[NCROSSES++] = cross(queue[class], POOLS[i]);
			queue[class] = NULL;
		} else
			queue[class] = POOLS[i];
	}
	double xm = CROSSES[rand() % NCROSSES].p->x;
	double yranges[2] = { -DBL_MAX, DBL_MAX };
	double smin[2] = { DBL_MAX, DBL_MAX };
	double smax[2] = { -DBL_MAX, -DBL_MAX };
	for (int i = 0; i < NPOOLS; i++) {
		Class class = (POOLS[i]->b > 0);
		int sign = class ? 1 : -1;
		if (solve(POOLS[i], xm) * sign < yranges[class] * sign - ERROR) {
			yranges[class] = solve(POOLS[i], xm);
			smin[class] = smax[class] = POOLS[i]->s;
		} else if (solve(POOLS[i], xm) * sign < yranges[class] * sign + ERROR) {
			smin[class] = MIN(smin[class], POOLS[i]->s);
			smax[class] = MAX(smax[class], POOLS[i]->s);
		}
	}
	if (yranges[N] <= yranges[P] && smin[N] <= 0 && smax[N] >= 0) {
		if (XRANGES[L] <= xm && xm <= XRANGES[R])
			printf("%.0f\n", ceil(yranges[N]-ERROR));
		else assert(false);
		exit(0);
	} else if (yranges[N] == -DBL_MAX) {
		printf("-INF\n");
		exit(0);
	} else if (yranges[N] > yranges[P] && smax[N] >= smin[P] && smin[N] <= smax[P]) {
		printf("NA\n");
		exit(0);
	} else if ((yranges[N] <= yranges[P] && smax[N] < 0) || (yranges[N] > yranges[P] && smax[N] < smin[P])) {
		prune(xm, L);
	} else if ((yranges[N] <= yranges[P] && smin[N] > 0) || (yranges[N] > yranges[P] && smin[N] > smax[P])) {
		prune(xm, R);
	}
	if (queue[0]) POOLS[NPOOLS++] = queue[0];
	if (queue[1]) POOLS[NPOOLS++] = queue[1];
	for (int i = 0; i < NCROSSES; i++)
		free(CROSSES[i].p);
	NCROSSES = 0;
}

int main(int argc, char *argv[]) {
	scanf("%d", &NLINES);
	for (int i = 0; i < NLINES; i++) {
		scanf("%d %d %d", &(LINES[i].a), &(LINES[i].b), &(LINES[i].c));
		if (LINES[i].b == 0) {
			if (LINES[i].a > 0)
				XRANGES[R] = MIN(XRANGES[R], (double) LINES[i].c / LINES[i].a);
			else
				XRANGES[L] = MAX(XRANGES[L], (double) LINES[i].c / LINES[i].a);
		} else {
			LINES[i].s = (double) -LINES[i].a / LINES[i].b;
			LINES[i].i = (double) LINES[i].c / LINES[i].b;
			POOLS[NPOOLS++] = LINES + i;
		}
	}
	if (XRANGES[L] > XRANGES[R]) {
		printf("NA\n");
		return 0;
	}
	while (NPOOLS > 2)
		search();
	if (NPOOLS == 0)
		printf("-INF\n");
	else if (NPOOLS == 1) {
		if ((*POOLS)->s == 0)
			printf("%.0f\n", ceil((double) (*POOLS)->i));
		else if ((*POOLS)->s > 0 && (*POOLS)->b < 0 && XRANGES[L] != -DBL_MAX)
			printf("%.0f\n", ceil(solve(*POOLS, XRANGES[L])));
		else if ((*POOLS)->s < 0 && (*POOLS)->b < 0 && XRANGES[R] != DBL_MAX)
			printf("%.0f\n", ceil(solve(*POOLS, XRANGES[R])));
		else
			printf("-INF\n");
	} else if (NPOOLS == 2) {
		CROSSES[NCROSSES++] = cross(POOLS[0], POOLS[1]);
		if (!(*CROSSES).p) {
			if ((*CROSSES).l1->s == 0 && (*CROSSES).l2->s == 0 && (*CROSSES).imin->b < 0)
				printf("%.0f\n", ceil((*CROSSES).imax-> b > 0 ? (*CROSSES).imin->i : (*CROSSES).imax->i));
			else if ((*CROSSES).imin->b > 0 && (*CROSSES).imax->b < 0)
				printf("NA\n");
			else
				printf("-INF\n");
		} else if (
			((*CROSSES).class == M && ((*CROSSES).smax->s <= 0 || (*CROSSES).smin->s >= 0)) ||
			((*CROSSES).class == N && ((*CROSSES).smax->s >= 0 && (*CROSSES).smin->s <= 0))) {
			double ans = ceil((*CROSSES).p->y);
			if (XRANGES[L] <= (*CROSSES).p->x && (*CROSSES).p->x <= XRANGES[R])
				printf("%.0f\n", ceil((*CROSSES).p->y-ERROR));
			else assert(false);
		} else
			printf("-INF\n");
	}
	return 0;
}
