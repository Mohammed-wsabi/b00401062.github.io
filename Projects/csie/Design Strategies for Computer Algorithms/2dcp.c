#include <assert.h>
#include <limits.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <vector>

#define MAXN 200000
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))
#define sumsqr(a,b) ((long long)(a) * (a) + (long long)(b) * (b))

using namespace std;

static double xm, delta;

typedef struct {
	int x, y, i;
} Point;

typedef struct {
	Point p1, p2;
} Pair;

bool byx(Point p1, Point p2) {
	return p1.x < p2.x;
}

bool byy(Point p1, Point p2) {
	return p1.y < p2.y;
}

bool byi(Pair p1, Pair p2) {
	return p1.p1.i < p2.p1.i || (p1.p1.i == p2.p1.i && p1.p2.i < p2.p2.i);
}

bool out(Point p) {
	return xm - delta > p.x || xm + delta < p.x;
}

bool same(Pair p1, Pair p2) {
	return p1.p1.i == p2.p1.i && p1.p2.i == p2.p2.i;
}

long long distsqr(Point p1, Point p2) {
	return sumsqr(p1.x - p2.x, p1.y - p2.y);
}

vector<Pair> clean(vector<Pair> &pairs) {
	sort(pairs.begin(), pairs.end(), byi);
	pairs.erase(unique(pairs.begin(), pairs.end(), same), pairs.end());
	return pairs;
}

void show(vector<Pair> pairs) {
	long long ds = distsqr(pairs[0].p1, pairs[0].p2);
	printf("%lld %lu\n", ds, pairs.size());
	for (Pair pair : pairs)
		printf("%d %d\n", pair.p1.i+1, pair.p2.i+1);
}

vector<Pair> bf(vector<Point> &points, int l, int r) {
	long long dsmin = LLONG_MAX;
	vector<Pair> pairs;
	for (int i = l; i < r; i++)
		for (int j = i+1; j < r; j++) {
			long long ds = distsqr(points[i], points[j]);
			if (ds > dsmin) continue;
			if (ds < dsmin) pairs.clear();
			int argmin = (points[i].i < points[j].i) ? i : j;
			int argmax = i + j - argmin;
			Pair pair = { points[argmin], points[argmax] };
			pairs.push_back(pair);
			dsmin = ds;
		}
	return pairs;
}

vector<Pair> dc(vector<Point> &points, int l, int r) {
	if (r-l <= 6) return bf(points, l, r);
	int m = (l+r)/2;
	vector<Pair> pl = dc(points, l, m);
	vector<Pair> pr = dc(points, m, r);
	long long dsl = distsqr(pl[0].p1, pl[0].p2);
	long long dsr = distsqr(pr[0].p1, pr[0].p2);
	long long dsmin = min(dsl, dsr);
	xm = points[m].x;
	delta = sqrt(dsmin);
	vector<Point> candidates(points.begin() + l, points.begin() + r);
	candidates.erase(remove_if(candidates.begin(), candidates.end(), out), candidates.end());
	int n = candidates.size();
	sort(candidates.begin(), candidates.end(), byy);
	vector<Pair> pairs;
	if (dsl == dsr) {
		pairs.insert(pairs.end(), pl.begin(), pl.end());
		pairs.insert(pairs.end(), pr.begin(), pr.end());
	} else
		pairs = (dsl < dsr) ? pl : pr;
	if (n == 1)
		return clean(pairs);
	else if (n < 6) {
		vector<Pair> ps = bf(candidates, 0, n);
		long long ds = distsqr(ps[0].p1, ps[0].p2);
		if (ds > dsmin) return clean(pairs);
		if (ds < dsmin) pairs.clear();
		pairs.insert(pairs.end(), ps.begin(), ps.end());
	} else for (int i = 0; i <= n-6; i++) {
		vector<Pair> ps = bf(candidates, i, i+6);
		long long ds = distsqr(ps[0].p1, ps[0].p2);
		if (ds > dsmin) continue;
		if (ds < dsmin) pairs.clear();
		pairs.insert(pairs.end(), ps.begin(), ps.end());
		dsmin = ds;
	}
	return clean(pairs);
}

int main(int argc, char *argv[]) {
	int N;
	scanf("%d", &N);
	vector<Point> points;
	for (int i = 0; i < N; i++) {
		int x, y;
		scanf("%d %d", &x, &y);
		Point p = { x, y, i };
		points.push_back(p);
	}
	sort(points.begin(), points.end(), byx);
	show(dc(points, 0, N));
	return 0;
}
