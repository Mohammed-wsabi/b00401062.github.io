public class Vector {
	public double[] c;
	public int d;
	Vector(double[] c) {
		this.c = c;
		this.d = c.length;
	}
	public Vector scale(double s) {
		return new Vector(Arrays.stream(this.c).map(x -> s * x).toArray());
	}
	public double length() {
		return Math.sqrt(IntStream.range(0, this.d).mapToDouble(i -> Math.pow(this.c[i], 2)).sum());
	}
	public static double length(Vector v) {
		return Math.sqrt(IntStream.range(0, v.d).mapToDouble(i -> Math.pow(v.c[i], 2)).sum());
	}
	public boolean parallel(Vector that) {
		assert(this.d == that.d);
		for (int i = 1; i < this.d; i++) {
			if (this.c[i-1] * that.c[i] != this.c[i] * that.c[i-1]) {
				return false;
			}
		}
		return true;
	}
	public static boolean parallel(Vector[] v) {
		assert(v.length == 2);
		assert(v[0].d == v[1].d);
		for (int i = 1; i < v[0].d; i++) {
			if (v[0].c[i-1] * v[1].c[i] != v[0].c[i] * v[1].c[i-1]) {
				return false;
			}
		}
		return true;
	}
}
