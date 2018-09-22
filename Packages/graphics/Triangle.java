public class Triangle {
	public Point[] p;
	public Vector[] v;
	Triangle(Point[] p) {
		assert(p.length == 3);
		this.v = new Vector[] {
			new Segment(new Point[] { p[0], p[1] }).vector(),
			new Segment(new Point[] { p[0], p[2] }).vector()
		};
		assert(!Vector.parallel(v));
		this.p = p;
	}
}
