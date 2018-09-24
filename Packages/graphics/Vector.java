public class Vector {
	public double[] x;
	Vector(double[] x) {
		this.x = x;
	}
	public int dim() {
		return this.x.length;
	}
	public double dot(Vector that) {
		assert(this.dim() == that.dim());
		return IntStream.range(0, this.dim()).mapToDouble(i -> this.x[i] * that.x[i]).sum();
	}
	public Vector scale(double s) {
		return new Vector(Arrays.stream(this.x).map(x -> s * x).toArray());
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
		return IntStream.range(1, this.dim()).allMatch(i -> this.x[i-1] * that.x[i] == this.x[i] * that.x[i-1]);
	}
	public boolean perpendicular(Vector that) {
		return this.dot(that) == 0;
	}
	public Vector project(Vector that) {
		return this.scale(this.dot(that) / this.dot(this));
	}
	public Vector plus(Vector that) {
		assert(this.dim() == that.dim());
		return new Vector(IntStream.range(0, this.dim()).mapToDouble(i -> this.x[i] + that.x[i]).toArray());
	}
	public Vector minus(Vector that) {
		assert(this.dim() == that.dim());
		return new Vector(IntStream.range(0, this.dim()).mapToDouble(i -> this.x[i] - that.x[i]).toArray());
	}
}
