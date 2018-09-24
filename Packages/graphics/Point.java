public class Point {
	public double[] x;
	Point(double[] x) {
		this.x = x;
	}
	public int dim() {
		return this.x.length;
	}
	public double distance(Point that) {
		assert(this.dim() == that.dim());
		return new Vector(that.x).minus(new Vector(this.x)).length();
	}
}
