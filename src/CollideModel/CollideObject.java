package CollideModel;

import MyMath.Point2d;
import MyMath.Vector2d;

public interface CollideObject {
    void collide(CollideObject other);

    CollideObject copy(CollideObject collideObject);

    Vector2d getDeltaAfterCollide(CollideObject other);

    Point2d calcClosestPointTo(Point2d point);

    boolean isPointInside(Point2d point2d);

    Point2d center();

    void move(Vector2d delta);

    double x();

    double y();

}
