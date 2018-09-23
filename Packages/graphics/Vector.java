public class Vector {
	public double[] c;
	Vector(double[] c) {
		this.c = c;
	}
	public int dim() {
		return this.c.length;
	}
	public double dot(Vector that) {
		assert(this.dim() == that.dim());
		return IntStream.range(0, this.dim()).mapToDouble(i -> this.c[i] * that.c[i]).sum();
	}
	public static double dot(Vector[] v) {
		assert(v.length == 2);
		assert(v[0].dim() == v[1].dim());
		return IntStream.range(0, v[0].dim()).mapToDouble(i -> v[0].c[i] * v[1].c[i]).sum();
	}
	public Vector scale(double s) {
		return new Vector(Arrays.stream(this.c).map(x -> s * x).toArray());
	}
	public Vector unit() {
		return this.scale(1/this.length());
	}
	public double length() {
		return Math.sqrt(IntStream.range(0, this.dim()).mapToDouble(i -> Math.pow(this.c[i], 2)).sum());
	}
	public static double length(Vector v) {
		return Math.sqrt(IntStream.range(0, v.dim()).mapToDouble(i -> Math.pow(v.c[i], 2)).sum());
	}
	public double angle(Vector that) {
		return Math.acos(this.dot(that) / (this.length() * that.length()));
	}
	public static double angle(Vector[] v) {
		return Math.acos(v[0].dot(v[1]) / (v[0].length() * v[1].length()));
	}
	public boolean parallel(Vector that) {
		assert(this.dim() == that.dim());
		for (int i = 1; i < this.dim(); i++) {
			if (this.c[i-1] * that.c[i] != this.c[i] * that.c[i-1]) {
				return false;
			}
		}
		return true;
	}
	public static boolean parallel(Vector[] v) {
		assert(v.length == 2);
		assert(v[0].dim() == v[1].dim());
		for (int i = 1; i < v[0].dim(); i++) {
			if (v[0].c[i-1] * v[1].c[i] != v[0].c[i] * v[1].c[i-1]) {
				return false;
			}
		}
		return true;
	}
	public Vector plus(Vector that) {
		assert(this.dim() == that.dim());
		return new Vector(IntStream.range(0, this.dim()).mapToDouble(i -> this.c[i] + that.c[i]).toArray());
	}
	public static Vector plus(Vector[] v) {
		assert(v.length == 2);
		assert(v[0].dim() == v[1].dim());
		return new Vector(IntStream.range(0, v[0].dim()).mapToDouble(i -> v[0].c[i] + v[1].c[i]).toArray());
	}
	public Vector minus(Vector that) {
		assert(this.dim() == that.dim());
		return new Vector(IntStream.range(0, this.dim()).mapToDouble(i -> this.c[i] - that.c[i]).toArray());
	}
	public static Vector minus(Vector[] v) {
		assert(v.length == 2);
		assert(v[0].dim() == v[1].dim());
		return new Vector(IntStream.range(0, v[0].dim()).mapToDouble(i -> v[0].c[i] + v[1].c[i]).toArray());
	}
}
