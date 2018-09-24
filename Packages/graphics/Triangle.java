public class Triangle {
	public Point[] p;
	Triangle(Point[] p) {
		assert(p.length == 3);
		this.p = p;
	}
	public double area() {
		Vector[] v = new Vector[] {
			new Vector(this.p[1].x).minus(new Vector(this.p[0].x)),
			new Vector(this.p[2].x).minus(new Vector(this.p[0].x))
		};
		return (v[0].length() * v[1].length()) * Math.sin(v[0].angle(v[1])) / 2;
	}
	public double[] sides() {
		return new double[] {
			new Segment(new Point[] { this.p[1], this.p[2] }).length(),
			new Segment(new Point[] { this.p[0], this.p[2] }).length(),
			new Segment(new Point[] { this.p[0], this.p[1] }).length()
		};
	}
	public double[] angles() {
		return new double[] {
			new Vector(this.p[1].x).minus(new Vector(this.p[0].x)).angle(new Vector(this.p[2].x).minus(new Vector(this.p[0].x))),
			new Vector(this.p[0].x).minus(new Vector(this.p[1].x)).angle(new Vector(this.p[2].x).minus(new Vector(this.p[1].x))),
			new Vector(this.p[0].x).minus(new Vector(this.p[2].x)).angle(new Vector(this.p[1].x).minus(new Vector(this.p[2].x)))
		};
	}
}
