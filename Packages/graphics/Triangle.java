public class Triangle {
	public Point[] p;
	Triangle(Point[] p) {
		assert(p.length == 3);
		this.p = p;
	}
	public double area() {
		Vector[] v = new Vector[] {
			Vector.minus(new Vector(this.p[1].c), new Vector(this.p[0].c)),
			Vector.minus(new Vector(this.p[2].c), new Vector(this.p[0].c))
		};
		return (v[0].length() * v[1].length()) * Math.sin(Vector.angle(v)) / 2;
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
			Vector.angle(new Vector[] {
				Vector.minus(new Vector(this.p[1].c), new Vector(this.p[0].c)),
				Vector.minus(new Vector(this.p[2].c), new Vector(this.p[0].c))
			}),
			Vector.angle(new Vector[] {
				Vector.minus(new Vector(this.p[0].c), new Vector(this.p[1].c)),
				Vector.minus(new Vector(this.p[2].c), new Vector(this.p[1].c))
			}),
			Vector.angle(new Vector[] {
				Vector.minus(new Vector(this.p[0].c), new Vector(this.p[2].c)),
				Vector.minus(new Vector(this.p[1].c), new Vector(this.p[2].c))
			})
		};
	}
}
