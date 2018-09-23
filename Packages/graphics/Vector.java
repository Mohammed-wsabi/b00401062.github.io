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
	public Vector scale(double s) {
		return new Vector(Arrays.stream(this.c).map(x -> s * x).toArray());
	}
	public Vector unit() {
		return this.scale(1/this.length());
	}
	public double length() {
		return Math.sqrt(this.dot(this));
	}
	public double angle(Vector that) {
		return Math.acos(this.dot(that) / (this.length() * that.length()));
	}
	public boolean parallel(Vector that) {
		assert(this.dim() == that.dim());
		return IntStream.range(1, this.dim()).allMatch(i -> this.c[i-1] * that.c[i] == this.c[i] * that.c[i-1]);
	}
	public boolean perpendicular(Vector that) {
		return this.dot(that) == 0;
	}
	public Vector project(Vector that) {
		return this.scale(this.dot(that) / this.dot(this));
	}
	public Vector plus(Vector that) {
		assert(this.dim() == that.dim());
		return new Vector(IntStream.range(0, this.dim()).mapToDouble(i -> this.c[i] + that.c[i]).toArray());
	}
	public Vector minus(Vector that) {
		assert(this.dim() == that.dim());
		return new Vector(IntStream.range(0, this.dim()).mapToDouble(i -> this.c[i] - that.c[i]).toArray());
	}
}
