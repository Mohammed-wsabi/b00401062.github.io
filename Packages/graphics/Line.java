public class Line {
	public Point p;
	public Vector v;
	Line(Point p, Vector v) {
		assert(p.dim() == v.dim());
		this.p = p;
		this.v = v.unit();
	}
	Line(Point[] p) {
		assert(p.length == 2);
		assert(p[0].dim() == p[1].dim());
		this.p = p;
		this.v = Vector.minus(new Vector(p[1].c), new Vector(p[0].c)).unit();
	}
	public int dim() {
		return this.p[0].length;
	}
	public boolean contains(Point p) {
		assert(this.dim() == p.dim());
		// TODO
		return false;
	}
	public double distance(Line that) {
		assert(this.dim() == that.dim());
		// TODO
		return 0;
	}
	public static double distance(Line[] l) {
		assert(l.length == 2);
		assert(l[0].dim() == l[1].dim());
		// TODO
		return 0;
	}
}
