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
		this.p = p[0];
		this.v = new Vector(p[1].x).minus(new Vector(p[0].x)).unit();
	}
	public int dim() {
		return this.p.dim();
	}
	public boolean contains(Point p) {
		assert(this.dim() == p.dim());
		Vector v = new Vector(p.x).minus(new Vector(this.p.x));
		return this.v.parallel(v);
	}
	public boolean parallel(Line that) {
		return this.v.parallel(that.v);
	}
	public Point project(Point p) {
		assert(this.dim() == p.dim());
		Vector v = new Vector(p.x).minus(new Vector(this.p.x));
		Vector e = this.v.project(v);
		return new Point(new Vector(this.p.x).plus(e).x);
	}
	public double distance(Point p) {
		assert(this.dim() == p.dim());
		Vector v = new Vector(p.x).minus(new Vector(this.p.x));
		Vector e = this.v.project(v);
		return v.minus(e).length();
	}
	public double distance(Line that) {
		assert(this.dim() == that.dim());
		assert(this.parallel(that));
		Vector v = new Vector(that.p.x).minus(new Vector(this.p.x));
		Vector e = this.v.project(v);
		return v.minus(e).length();
	}
}
