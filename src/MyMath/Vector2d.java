package MyMath;

public class Vector2d extends Point2d {
    final public static Vector2d ZERO_VECTOR = new Vector2d(0d, 0d);
    public Vector2d(double x, double y) {
        super(x, y);
    }

    public Vector2d(Point2d from, Point2d to) {
        super(to.x - from.x, to.y - from.y);
    }

    public Vector2d(Vector2d toCopy) {
        super(toCopy.x, toCopy.y);
    }

    public Vector2d() {
        super();
    }

    public double length() {
        return this.getDistanceTo(ZERO_POINT);
    }


    public Vector2d normalize() {
        this.mul(1d / this.length());
        return this;
    }
    public Vector2d add(Point2d delta){
        this.x += delta.x;
        this.y += delta.y;
        return this;
    }

    public double mul(Vector2d other) {
        return x * other.x + y * other.y;
    }

    public Vector2d mul(double k) {
        this.x *= k;
        this.y *= k;
        return this;
    }

    public Vector2d getMul(double k) {
        Vector2d muled = new Vector2d(this);
        return muled.mul(k);
    }
}
