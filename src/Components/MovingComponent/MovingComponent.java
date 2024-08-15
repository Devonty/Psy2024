package Components.MovingComponent;

import CollideModel.GameObject;
import CollideModel.Movable.MovableObject;
import Components.BaseComponent;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.function.Function;

public class MovingComponent extends BaseComponent<MovableObject> {
    public static final double timeStep = 0.2;
    public static final double stopK = 0.05;
    public static final Vector2d gravity = new Vector2d(0, 9.81);
    public MovingComponent(Function<GameObject, Boolean> addingRule) {
        super(addingRule);

    }

    public MovingComponent() {
        this(obj -> obj instanceof MovableObject);
    }

    @Override
    protected void subAdd(GameObject toAdd) {
        allObjects.add((MovableObject) toAdd);
    }

    @Override
    public void update() {
        allObjects.forEach(MovingComponent::updateByVelocity);
    }

    private static void updateByVelocity(MovableObject movable){
        movable.addVelocity(gravity.getMul(timeStep));
        Vector2d stopDelta = new Vector2d(movable.velocity()).mul(-stopK * movable.velocity().getDistancePow2To(Point2d.ZERO_POINT) / (2d * movable.mass()));
        movable.addVelocity(stopDelta);
        movable.addVelocity(movable.velocity().normalize().mul(-Math.min(stopK, movable.velocity().length())));
        movable.move(movable.velocity().mul(timeStep));
    }
}
