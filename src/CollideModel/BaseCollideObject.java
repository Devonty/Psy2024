package CollideModel;

import CollideModel.NotMovable.NotMovableObject;
import MyMath.Point2d;
import MyMath.Vector2d;

abstract public class BaseCollideObject implements CollideObject {
    protected Point2d center;

    @Override
    public void collide(CollideObject other) {
        Vector2d thisDelta = this.getDeltaAfterCollide(other);
        Vector2d otherDelta = other.getDeltaAfterCollide(this);

        if(other instanceof NotMovableObject) thisDelta.mul(2d);

        this.move(thisDelta);
        other.move(otherDelta);
    }

    @Override
    public Point2d center() {
        return new Point2d(center);
    }

    @Override
    public void move(Vector2d delta) {
        center.move(delta);
    }

    @Override
    public double x() {
        return center.x();
    }

    @Override
    public double y() {
        return center.y();
    }
}
