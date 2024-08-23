package Components.MovingComponent;

import CollideModel.GameObject;
import CollideModel.Movable.MovableObject;
import Components.BaseComponent;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.function.Function;

import static MyMath.Constants.*;

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
        allObjects.forEach(MovingComponent::updateByVelocity);
    }

    private static void updateByVelocity(MovableObject movable){
        if(movable.velocity().length() >0 && movable.velocity().length() > movable.sumMove().length() / timeStep) movable.setVelocity(movable.sumMove().getMul(1/timeStep));
        Vector2d sumVelocityDelta = new Vector2d();
        sumVelocityDelta.move(forceGravity(movable));
        sumVelocityDelta.move(forceStop(movable));

        movable.addVelocity(sumVelocityDelta.mul(timeStep));

        //if(movable.velocity().length() > movable.sumMove().length() / timeStep) movable.setVelocity(movable.velocity().normalize().mul(movable.sumMove().length() / timeStep));
        movable.move(movable.velocity().mul(timeStep));

    }

    private static Vector2d forceGravity(MovableObject movable){
        Vector2d total = new Vector2d();
        if(movable.isStableAtDirection(gravity)) return total;
        Vector2d toAdd = movable.getClearedBlockedDirections(gravity);
        total.move(toAdd);
        return total;
    }
    private static Vector2d forceStop(MovableObject movable){
        Vector2d totalStop = new Vector2d();
        // V^2 force
        Vector2d sqrStop = new Vector2d(movable.velocity()).mul(-stopK * movable.velocity().getDistancePow2To(Point2d.ZERO_POINT) / (2d * movable.mass()));
        totalStop.move(sqrStop);
        // linealStop
        Vector2d linStop = movable.velocity().normalize().mul(stopK * timeStep);
        totalStop.move(linStop);
        return totalStop;
    }
}
