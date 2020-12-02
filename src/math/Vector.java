package math;


public class Vector {
	public Vector() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
		w = 0.0;
	}
	public static double getVectorLength(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
	public Vector(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public math.Vector copy(math.Vector v) {
		x = v.x;
		y = v.y;
		z = v.z;
		w = v.w;

		return this;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getZ() {
		return z;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getW() {
		return w;
	}

	public math.Vector add(math.Vector v) {
		return new math.Vector(x + v.x, y + v.y, z + v.z, w + v.w);
	}

	public math.Vector mul(double a) {
		return new math.Vector(x * a, y * a, z * a, w * a);
	}

	public math.Vector perspectiveDivide() {
		return new math.Vector(x / w, y / w, z / w, 1.0);
	}

	private double x;
	private double y;
	private double z;
	private double w;
}
