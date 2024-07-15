package CollideModel.Movable;

import CollideModel.BaseCollideObject;
import CollideModel.CollideObject;
import MyMath.Point2d;
import MyMath.Vector2d;

abstract public class MovableObject extends BaseCollideObject {
    @Override
    public Vector2d getDeltaAfterCollide(CollideObject other) {
        Point2d otherClosest = other.calcClosestPointTo(this.getSupportPoint(other));
        // Если не пересекается, то не двигаемся (нулевой сдвиг)
        if (!this.isPointInside(otherClosest)) return Vector2d.ZERO_VECTOR;
        // Иначе считаем на сколько сдвинуться
        Point2d thisClosest = this.calcClosestPointTo(other.getSupportPoint(this));
        // Возвращаем верктор сдвига, длинной в половину длины коллизии
        return new Vector2d(thisClosest, otherClosest).mul(0.50d);
    }
}
