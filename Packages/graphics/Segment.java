public class Segment {
	public Point[] p;
	Segment(Point[] p) {
		assert(p.length == 2);
		assert(p[0].dim() == p[1].dim());
		this.p = p;
	}
	public int dim() {
		return this.p[0].length;
	}
	public boolean contains(Point p) {
		assert(this.dim() == p.dim());
		// TODO
		return false;
	}
	public double length() {
		return this.vector().length();
	}
	public static double length(Point[] p) {
		assert(p.length == 2);
		assert(p[0].dim() == p[1].dim());
		return new Segment(p).length();
	}
}
