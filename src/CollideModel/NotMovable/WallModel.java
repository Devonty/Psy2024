package CollideModel.NotMovable;

import CollideModel.GameObject;
import MyMath.Point2d;
import MyMath.Segment2d;
import MyMath.Vector2d;

import java.util.Arrays;

public class WallModel extends NotMovableObject {
    protected Segment2d segment;
    protected double width;
    protected final Point2d[] rectPoints;

    public WallModel(Segment2d segment, double width) {
        super(segment.end().move(segment.start()).mul(0.5d));
        this.rectPoints = new Point2d[4];
        this.width = width;
        this.segment = new Segment2d(segment);

        this.calcRectPoints();
    }

    public WallModel(Point2d p1, Point2d p2, double width) {
        this(new Segment2d(p1, p2), width);
    }

    public WallModel(Point2d p1, Point2d p2) {
        this(p1, p2, 35d);
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
    public Point2d getSupportPoint(GameObject other) {
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

    protected void calcRectPoints(){
        Vector2d orthogonal = segment.direction().getOrthogonal().normalize().mul(width);
        rectPoints[0] = segment.start().move(orthogonal);
        rectPoints[1] = segment.end().move(orthogonal);
        orthogonal.mul(-1);
        rectPoints[2] = segment.end().move(orthogonal);
        rectPoints[3] = segment.start().move(orthogonal);
    }

    public Point2d[] getRectPoints(){
        return Arrays.copyOf(rectPoints, rectPoints.length);
    }
}
