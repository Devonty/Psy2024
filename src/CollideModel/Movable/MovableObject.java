package CollideModel.Movable;

import CollideModel.BaseCollideObject;
import MyMath.Point2d;

abstract public class MovableObject extends BaseCollideObject {
    public MovableObject(Point2d center, double mass) {
        super(center, mass);
    }

}
