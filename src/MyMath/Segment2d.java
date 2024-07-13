package MyMath;

public class Segment2d extends Line2d {
    public final Point2d end;

    public Segment2d(Point2d from, Point2d to) {
        super(from, to);
        end = to;
    }

    @Override
    public boolean isOnLine(Point2d point) {
        return this.isInsideBorderSquare(point) && super.isOnLine(point);
    }

    private boolean isInsideBorderSquare(Point2d point) {
        return Math.min(start.x, end.x) <= point.x && point.x <= Math.max(start.x, end.x) &&
        Math.min(start.y, end.y) <= point.y && point.y <= Math.max(start.y, end.y);
    }
}
