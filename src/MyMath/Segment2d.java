package MyMath;

public class Segment2d extends Line2d {
    private double length;

    public Segment2d(Point2d from, Point2d to) {
        super(from, to);
        length = from.getDistanceTo(to);
    }
    public Segment2d(Segment2d toCopy) {
        this(toCopy.start(), toCopy.end());
    }

    @Override
    public boolean isOnLine(Point2d point) {
        return this.isInsideBorderSquare(point) && super.isOnLine(point);
    }

    private boolean isInsideBorderSquare(Point2d point) {
        Point2d end = end();
        return Math.min(start.x, end.x) <= point.x && point.x <= Math.max(start.x, end.x) &&
        Math.min(start.y, end.y) <= point.y && point.y <= Math.max(start.y, end.y);
    }

    public Point2d end(){
        return start.getMoved(direction.getMul(length));
    }
}
