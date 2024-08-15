package CollideModel.Movable;

import CollideModel.BaseGameObject;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

abstract public class MovableObject extends BaseGameObject {
    protected Vector2d velocity;
    protected List<Vector2d> blockDirections;


    public MovableObject(Point2d center, double mass) {
        super(center, mass);
        this.velocity = new Vector2d(5, 5);
        this.blockDirections = new LinkedList<>();
    }

    @Override
    public void move(Vector2d delta) {
        super.move(getClearedBlockedDirections(delta));
    }

    public Vector2d velocity() {
        return new Vector2d(velocity);
    }

    public void addVelocity(Vector2d deltaVelocity) {
        velocity.move(deltaVelocity);
        velocity = getClearedBlockedDirections(velocity);
    }

    public void setVelocity(Vector2d newVelocity) {
        velocity.set(newVelocity);
        velocity = getClearedBlockedDirections(velocity);
    }

    public Vector2d getClearedBlockedDirections(Vector2d vector){
        Vector2d toClear = new Vector2d(vector);
        for(Vector2d direction : blockDirections){
            Vector2d projection = Vector2d.getProjection(toClear, direction);
            if(Vector2d.areSameDirection(direction, projection))
                toClear.move(projection.getMul(-1));
        }
        return toClear;
    }

    public void mulVelocity(double mul) {
        velocity.mul(mul);
    }

    public boolean isStableAtDirection(Vector2d direction) {
        return Objects.equals(Vector2d.ZERO_VECTOR, getClearedBlockedDirections(new Vector2d(direction)));
    }

    public void setStableAtDirection(Vector2d direction) {
        blockDirections.add(new Vector2d(direction).normalize());
    }

    public void setUnstable() {
        blockDirections.clear();
    }
}
