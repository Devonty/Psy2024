package CollideModel.NotMovable;

import CollideModel.BaseGameObject;
import MyMath.Point2d;

abstract public class NotMovableObject extends BaseGameObject {

    public NotMovableObject(Point2d center) {
        super(center, Double.POSITIVE_INFINITY);
    }
}
