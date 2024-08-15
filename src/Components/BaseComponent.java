package Components;

import CollideModel.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

abstract public class BaseComponent<T> implements Component{
    protected final List<T> allObjects;
    protected Function<GameObject, Boolean> addingRule;
    public static final Function<GameObject, Boolean> DEFAULT_ADDING_RULE = Objects::nonNull;

    public BaseComponent(Function<GameObject, Boolean> addingRule) {
        this.allObjects = new ArrayList<>();
        this.addingRule = addingRule;
    }

    public BaseComponent() {
        this(DEFAULT_ADDING_RULE);
    }

    protected abstract void subAdd(GameObject toAdd);

    @Override
    public void add(GameObject toAdd) {
        if(!isCorrectByAddingRule(toAdd)) return;
        subAdd(toAdd);
    }

    @Override
    public void setAddingRule(Function<GameObject, Boolean> function) {
        addingRule = function;
    }

    @Override
    public boolean isCorrectByAddingRule(GameObject gameObject) {
        return addingRule.apply(gameObject);
    }
}
