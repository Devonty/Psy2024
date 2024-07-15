package CollideModel.NotMovable;

import CollideModel.BaseCollideObject;
import CollideModel.CollideObject;
import MyMath.Point2d;
import MyMath.Vector2d;

abstract public class NotMovableObject extends BaseCollideObject {

    @Override
    public Vector2d getDeltaAfterCollide(CollideObject other) {
        return new Vector2d(Vector2d.ZERO_VECTOR);
    }
}
