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

    public Vector2d getOrthogonal(){
        return new Vector2d(-y, x);
    }

    public static Vector2d getOrthogonal(Vector2d vector2d){
        return new Vector2d(-vector2d.y, vector2d.x);
    }

    public static Vector2d getRandomDirection() {
        return getRandomDirection(1d);
    }

    public static Vector2d getRandomDirection(double length) {
        return new Vector2d(Math.random() - 0.5, Math.random() - 0.5).normalize().mul(length);
    }

    public double length() {
        return this.getDistanceTo(ZERO_POINT);
    }

    public static Vector2d getProjection(Vector2d toProject, Vector2d projectOn){
        return projectOn.getNormalize().mul(toProject.mul(projectOn) / projectOn.length());
    }

    public Vector2d normalize() {
        this.mul(1d / this.length());
        return this;
    }


    public Vector2d getNormalize() {
        return new Vector2d(this).normalize();
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
