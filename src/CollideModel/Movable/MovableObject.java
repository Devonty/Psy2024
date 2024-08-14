package CollideModel.Movable;

import CollideModel.BaseGameObject;
import MyMath.Point2d;
import MyMath.Vector2d;

abstract public class MovableObject extends BaseGameObject {
    protected Vector2d velocity;

    public MovableObject(Point2d center, double mass) {
        super(center, mass);
        this.velocity = new Vector2d(5, 5);
    }

    public Vector2d velocity() {
        return new Vector2d(velocity);
    }

    public void addVelocity(Vector2d deltaVelocity) {
        velocity.move(deltaVelocity);
    }

    public void setVelocity(Vector2d newVelocity) {
        velocity.set(newVelocity);
    }

}
