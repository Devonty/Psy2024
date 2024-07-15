package CollideModel.NotMovable;

import MyMath.Point2d;
import MyMath.Segment2d;

public class WallModel extends NotMovableObject {
    protected Segment2d segment;

    public WallModel(Segment2d segment) {
        this.center = new Point2d(segment.start()).move(segment.end()).multiply(0.5d);
        this.segment = new Segment2d(segment);
    }

    public WallModel(Point2d p1, Point2d p2) {
        this(new Segment2d(p1, p2));
    }

    @Override
    public Point2d calcClosestPointTo(Point2d point) {
        Point2d projection = segment.projectionOnLine(point);
        if (segment.isOnLine(projection)) return projection;
        return projection.getDistancePow2To(segment.start()) < projection.getDistancePow2To(segment.end()) ?
                new Point2d(segment.start()) : new Point2d(segment.end());
    }

    @Override
    public boolean isPointInside(Point2d point) {
        return segment.isOnLine(point);
    }

    public Point2d start(){
        // already copied inside
        return segment.start();
    }
    public Point2d end(){
        // already copied inside
        return segment.end();
    }
}
