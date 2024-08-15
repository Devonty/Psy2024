package Components;

import CollideModel.GameObject;
import java.util.function.Function;

public interface Component{
    void add(GameObject toAdd);
    void update();
    void setAddingRule(Function<GameObject, Boolean> function);
    boolean isCorrectByAddingRule(GameObject gameObject);
}
