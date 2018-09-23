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
		return new Vector(that.c).minus(new Vector(this.c)).length();
	}
}
