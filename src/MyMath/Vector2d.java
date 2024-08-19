package MyMath;

import java.util.Objects;

public class Vector2d extends Point2d {
    final public static Vector2d ZERO_VECTOR = new Vector2d(0d, 0d);

    public Vector2d(double x, double y) {
        super(x, y);
    }

    public Vector2d(Point2d from, Point2d to) {
        super(to.x - from.x, to.y - from.y);
    }

    public Vector2d(Point2d toCopy) {
        super(toCopy.x, toCopy.y);
    }

    public Vector2d() {
        super();
    }

    public boolean areSameDirection(Vector2d other) {
        return areSameDirection(this, other);
    }

    public static boolean areSameDirection(Vector2d first, Vector2d second) {
        return Objects.equals(first.getNormalized(), second.getNormalized());
    }

    public Vector2d getOrthogonal() {
        return new Vector2d(-y, x);
    }

    public static Vector2d getOrthogonal(Vector2d vector2d) {
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

    public static Vector2d getProjection(Vector2d toProject, Vector2d projectOn) {
        return projectOn.getNormalized().mul(toProject.mul(projectOn) / projectOn.length());
    }

    public Vector2d normalize() {
        if (Objects.equals(this, Vector2d.ZERO_VECTOR)) return this;
        this.mul(1d / this.length());
        return this;
    }


    public Vector2d getNormalized() {
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

    public Vector2d getSub(Vector2d other){
        return new Vector2d(this).sub(other);
    }

    public Vector2d move(Vector2d delta){
        x+=delta.x;
        y+=delta.y;
        return this;
    }

    public Vector2d getMoved(Point2d delta){
        return new Vector2d(super.getMoved(delta));
    }

    public Vector2d sub(Vector2d delta) {
        x -= delta.x;
        y -= delta.y;
        return this;
    }

    public static Vector2d sub(Vector2d v1, Vector2d v2){
        return new Vector2d(v1).sub(v2);
    }
}
