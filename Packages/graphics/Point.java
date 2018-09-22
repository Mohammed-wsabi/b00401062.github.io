public class Point {
	public double[] c;
	public int d;
	Point(double[] c) {
		this.c = c;
		this.d = c.length;
	}
	public static double distance(Point[] p) {
		assert(p.length == 2);
		assert(p[0].d == p[1].d);
		return new Vector(IntStream.range(0, p[0].d).mapToDouble(i -> p[0].c[i] - p[1].c[i]).toArray()).length();
	}
	public double distance(Point that) {
		assert(this.d == that.d);
		return new Vector(IntStream.range(0, this.d).mapToDouble(i -> this.c[i] - that.c[i]).toArray()).length();
	}
}
