package CollideModel;

import MyMath.Point2d;
import MyMath.Vector2d;

public interface GameObject {

    Point2d calcClosestPointOutsideFor(Point2d point);

    default Point2d getSupportPoint(GameObject other) {
        return center();
    }

    boolean isPointInside(Point2d point);

    Point2d center();

    GameObject move(Vector2d delta);

    Vector2d sumMove();

    void resetSumMove();

    double x();

    double y();

    double mass();

}
