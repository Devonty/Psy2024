package Components.MovingComponent;

import CollideModel.GameObject;
import CollideModel.Movable.MovableObject;
import Components.BaseComponent;
import MyMath.Vector2d;

import java.util.function.Function;

public class MovingComponent extends BaseComponent<MovableObject> {
    public static final double timeStep = 0.25;
    public static final double stopK = 0.98;
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
        movable.move(movable.velocity().mul(timeStep));
        movable.addVelocity(gravity.getMul(timeStep));
        movable.mulVelocity(stopK);
    }
}
