package Components;

import CollideModel.GameObject;
import Components.CollideComponent.CollideComponent;
import Components.MovingComponent.MovingComponent;

import java.util.function.Function;

public class MovingCollideComponent extends BaseComponent<GameObject> {

    protected final MovingComponent movingComponent;
    protected final CollideComponent collideComponent;

    public MovingCollideComponent(Function<GameObject, Boolean> addingRule) {
        super(addingRule);
        this.movingComponent = new MovingComponent();
        this.collideComponent = new CollideComponent();
    }

    public MovingCollideComponent() {
        this(null);
        setAddingRule(obj -> movingComponent.isCorrectByAddingRule(obj) || collideComponent.isCorrectByAddingRule(obj));
    }

    @Override
    protected void subAdd(GameObject toAdd) {
        movingComponent.add(toAdd);
        collideComponent.add(toAdd);
        if(movingComponent.isCorrectByAddingRule(toAdd) && collideComponent.isCorrectByAddingRule(toAdd)){
            allObjects.add(toAdd);
        }
    }

    @Override
    public void update() {
        movingComponent.update();
        collideComponent.update();
    }
}
