package CollideModel.NotMovable;

import CollideModel.BaseCollideObject;
import MyMath.Point2d;

abstract public class NotMovableObject extends BaseCollideObject {

    public NotMovableObject(Point2d center) {
        super(center, Double.POSITIVE_INFINITY);
    }
}
