public class Segment {
	public Point[] p;
	Segment(Point[] p) {
		assert(p.length == 2);
		assert(p[0].dim() == p[1].dim());
		this.p = p;
	}
	public int dim() {
		return this.p[0].dim();
	}
	public boolean contains(Point p) {
		assert(this.dim() == p.dim());
		Vector[] v = new Vector[] {
			new Vector(this.p[0].x).minus(new Vector(p.x)),
			new Vector(this.p[1].x).minus(new Vector(p.x))
		};
		return v[0].angle(v[1]) == Math.PI;
	}
	public double length() {
		return new Vector(this.p[1].x).minus(new Vector(this.p[0].x)).length();
	}
}
