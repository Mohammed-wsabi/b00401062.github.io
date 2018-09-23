public class Point {
	public double[] c;
	Point(double[] c) {
		this.c = c;
	}
	public int dim() {
		return this.c.length;
	}
	public double distance(Point that) {
		assert(this.dim() == that.dim());
		return Vector.minus(new Vector(that.c), new Vector(this.c)).length();
	}
	public static double distance(Point[] p) {
		assert(p.length == 2);
		assert(p[0].dim() == p[1].dim());
		return Vector.minus(new Vector(p[1].c), new Vector(p[0].c)).length();
	}
}
