package MyMath;

public class NotMutableVector2d extends Vector2d{

    public NotMutableVector2d(double x, double y) {
        super(x, y);
    }

    public NotMutableVector2d(Point2d from, Point2d to) {
        super(from, to);
    }

    public NotMutableVector2d(Point2d toCopy) {
        super(toCopy);
    }

    public NotMutableVector2d() {
    }

    @Override
    public Vector2d normalize() {
        return super.getNormalized();
    }

    @Override
    public Vector2d mul(double k) {
        return super.getMul(k);
    }

    @Override
    public Vector2d move(Point2d delta) {
        return super.getMoved(delta);
    }

    @Override
    public Vector2d move(Vector2d delta) {
        return super.getMoved(delta);
    }

    @Override
    public Vector2d sub(Vector2d delta) {
        return super.getSub(delta);
    }

    @Override
    public Vector2d addNoiseVector(double ratio) {
        return new Vector2d(this).addNoiseVector(ratio);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
    }

    @Override
    public void set(double x, double y) {
        super.set(x, y);
    }

    @Override
    public void set(Point2d point) {
        super.set(point);
    }

    @Override
    public Point2d sub(Point2d delta) {
        return super.sub(delta);
    }
}
