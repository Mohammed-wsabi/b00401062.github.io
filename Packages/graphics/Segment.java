public class Segment {
	public Point[] p;
	Segment(Point[] p) {
		assert(p.length == 2);
		this.p = p;
	}
	public Vector vector() {
		return new Vector(IntStream.range(0, p[0].d).mapToDouble(i -> p[1].c[i] - p[0].c[i]).toArray());
	}
}
