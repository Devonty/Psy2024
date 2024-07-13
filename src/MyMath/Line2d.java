package MyMath;

import java.util.Objects;

public class Line2d {
    protected final Point2d start;
    protected final Vector2d dir;

    public Line2d(Point2d from, Vector2d dir) {
        this.start = from;
        this.dir = dir.normalize();
    }

    public Line2d(Point2d from, Point2d to) {
        this(from, new Vector2d(from, to));
    }

    public boolean isOnLine(Point2d point){
        // System.out.printf("%s == %s  -> %s", point, this.projectionOnLine(point), Objects.equals(point, this.projectionOnLine(point)));
        return Objects.equals(point, this.projectionOnLine(point));
    }

    public Point2d projectionOnLine(Point2d p){
        Vector2d delta = new Vector2d(p, this.start);
        Vector2d cor = dir.getMul(delta.mul(dir));
        Vector2d res = new Vector2d(cor, delta);
        return p.getMoved(res);
    }
}
