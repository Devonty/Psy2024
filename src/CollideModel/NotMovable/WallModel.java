package CollideModel.NotMovable;

import CollideModel.CollideObject;
import MyMath.Point2d;
import MyMath.Segment2d;
import MyMath.Vector2d;

public class WallModel extends NotMovableObject {
    protected Segment2d segment;
    protected double width;

    public WallModel(Segment2d segment, double width) {
        super(segment.end().move(segment.start()).multiply(0.5d));
        this.width = width;
        this.segment = new Segment2d(segment);
    }

    public WallModel(Point2d p1, Point2d p2, double width) {
        this(new Segment2d(p1, p2), width);
    }

    public WallModel(Point2d p1, Point2d p2) {
        this(p1, p2, 5d);
    }

    @Override
    public Point2d calcClosestPointOutsideFor(Point2d point) {
        Point2d projection = segment.projectionOnLine(point);
        if (segment.isOnSegment(projection)) return projection.move(new Vector2d(projection, point).normalize().mul(width));
        return projection.getDistancePow2To(segment.start()) < projection.getDistancePow2To(segment.end()) ?
                segment.start() : segment.end();
    }

    @Override
    public boolean isPointInside(Point2d point) {
        Point2d projection = segment.projectionOnLine(point);
        return segment.isOnSegment(projection) && projection.getDistanceTo(point) <= width;
    }

    @Override
    public Point2d getSupportPoint(CollideObject other) {
        Point2d projection = segment.projectionOnLine(other.center());
        if (segment.isOnSegment(projection)) return projection;
        return projection.getDistancePow2To(segment.start()) < projection.getDistancePow2To(segment.end()) ?
                segment.start() : segment.end();
    }

    public Point2d start(){
        // already copied inside
        return segment.start();
    }
    public Point2d end(){
        // already copied inside
        return segment.end();
    }

    public double width(){
        return width;
    }
}
