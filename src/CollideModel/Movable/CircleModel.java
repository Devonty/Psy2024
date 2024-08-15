package CollideModel.Movable;

import MyMath.Point2d;
import MyMath.Vector2d;

public class CircleModel extends MovableObject {
    double radius;


    public CircleModel(Point2d center, double radius) {
        super(center, radius * radius * Math.PI);
        this.radius = radius;
    }

    public CircleModel(double x, double y, double radius) {
        this(new Point2d(x, y), radius);
    }


    @Override
    public Point2d calcClosestPointOutsideFor(Point2d point) {
        return new Vector2d(center, point).normalize().mul(radius).move(center);
    }

    @Override
    public boolean isPointInside(Point2d point) {
        // на границе не считается
        return center.getDistancePow2To(point) <= radius * radius ;
    }

    public double radius() {
        return radius;
    }
}

