package MyMath;

import javax.sound.sampled.Line;
import java.util.Objects;

public class Line2d {
    protected final Point2d start;
    protected final Vector2d direction;

    public Line2d(Point2d from, Vector2d direction) {
        this.start = from;
        this.direction = direction.normalize();
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
        Vector2d cor = direction.getMul(delta.mul(direction));
        Vector2d res = new Vector2d(cor, delta);
        return p.getMoved(res);
    }

    public Line2d move(Vector2d delta){
        start.move(delta);
        return this;
    }

    public Point2d start() {
        return new Point2d(start);
    }

    public Vector2d direction() {
        return new Vector2d(direction);
    }
}
