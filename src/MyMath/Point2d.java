package MyMath;

import static MyMath.Comparator.almostEqual;

public class Point2d {
    final public static Point2d ZERO_POINT = new Point2d(0d, 0d);
    protected double x, y;

    public Point2d(Point2d toCopy) {
        this(toCopy.x, toCopy.y);
    }
    public Point2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2d addNoiseVector(double ratio){
        this.move(Vector2d.getRandomDirection(ratio));
        return this;
    }


    public Point2d() {
       this(0,0);
    }
    public double getDistanceTo(Point2d other) {
        return Math.sqrt(getDistancePow2To(other));
    }
    public double getDistancePow2To(Point2d other) {
        double deltaX = this.x - other.x;
        double deltaY = this.y - other.y;
        return deltaX*deltaX + deltaY*deltaY;
    }

    public Point2d move(Point2d delta){
        x+=delta.x;
        y+=delta.y;
        return this;
    }

    public Point2d getMoved(Point2d delta){
        Point2d moved = new Point2d(this);
        moved.move(delta);
        return moved;
    }


    public double x() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double y() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void set(Point2d point){
        set(point.x, point.y);
    }

    public Point2d mul(double k) {
        this.x *= k;
        this.y *= k;
        return this;
    }

    public Point2d getMul(double k) {
        Point2d muled = new Point2d(this);
        return muled.mul(k);
    }


    public Point2d getSub(Point2d other){
        return new Point2d(this).sub(other);
    }

    public Point2d sub(Point2d delta) {
        x -= delta.x;
        y -= delta.y;
        return this;
    }

    public static Vector2d sub(Vector2d v1, Vector2d v2){
        return new Vector2d(v1).sub(v2);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point2d && almostEqual(((Point2d)obj).x, x) && almostEqual(((Point2d)obj).y, y);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}
