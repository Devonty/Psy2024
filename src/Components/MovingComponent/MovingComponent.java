package Components.MovingComponent;

import CollideModel.GameObject;
import CollideModel.Movable.MovableObject;
import Components.BaseComponent;
import MyMath.Vector2d;

import java.util.function.Function;

public class MovingComponent extends BaseComponent<MovableObject> {
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
        allObjects.forEach(o -> o.move(o.velocity()));
    }
}
