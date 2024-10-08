package CollideModel.Movable;

import CollideModel.BaseGameObject;
import CollideModel.GameObject;
import Components.CollideComponent.CollideController;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.*;

abstract public class MovableObject extends BaseGameObject {
    protected Vector2d velocity;
    protected List<Vector2d> blockDirections;


    public MovableObject(Point2d center, double mass) {
        super(center, mass);
        this.velocity = new Vector2d();
        this.blockDirections = new LinkedList<>();
    }

    @Override
    public GameObject move(Vector2d delta) {
        super.move(getClearedBlockedDirections(delta));
        return this;
    }

    public Vector2d velocity() {
        return new Vector2d(velocity);
    }

    public void addVelocity(Vector2d deltaVelocity) {
        velocity.move(deltaVelocity);
        fixVelocityByBlockDirections();
    }

    public void setVelocity(Vector2d newVelocity) {
        velocity.set(newVelocity);
        fixVelocityByBlockDirections();
    }

    private void fixVelocityByBlockDirections(){
        Vector2d clear = getClearedBlockedDirections(velocity);
        velocity = clear.move(velocity.getSub(clear).mul(-1));
    }

    public Vector2d getClearedBlockedDirections(Vector2d vector) {
        Vector2d toClear = new Vector2d(vector);
        for (Vector2d direction : blockDirections) {
            Vector2d projection = Vector2d.getProjection(toClear, direction);
            if (Vector2d.areSameDirection(direction, projection))
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
        if (direction == null || Objects.equals(direction, Vector2d.ZERO_VECTOR)) return;
        blockDirections.add(new Vector2d(direction).normalize());
    }

    public void setUnstable() {
        blockDirections.clear();
    }

    public List<Vector2d> getBlockDirections() {
        return blockDirections;
    }
}
