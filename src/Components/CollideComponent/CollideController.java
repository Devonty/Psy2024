package Components.CollideComponent;

import CollideModel.GameObject;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.List;
import java.util.Objects;

public class CollideController {
    public static void collide(MovableObject first, MovableObject second){
        if (Objects.equals(first.center(), second.center())) return;
        Vector2d delta = getDeltaVector(first, second);

        double totalMass = first.mass() + second.mass();
        double firstK = second.mass() / totalMass;
        double secondK = first.mass() / totalMass;

        first.move(delta.getMul(-firstK));
        second.move(delta.getMul(secondK));
    }
    public static void collide(MovableObject movable, NotMovableObject notMovable){
        if (Objects.equals(movable.center(), notMovable.center())) return;
        Vector2d delta = getDeltaVector(movable, notMovable).mul(-1);
        if (Objects.equals(delta, Vector2d.ZERO_VECTOR)) return;

        double oldVel = movable.velocity().length();

        movable.addVelocity(delta.normalize().getMul(-2 * movable.velocity().mul(delta) / delta.length()));
        movable.move(delta);
    }

    public static Vector2d getDeltaVector(GameObject first, GameObject second){
        Point2d firstSupport = first.getSupportPoint(second);
        Point2d secondSupport = second.getSupportPoint(first);

        Point2d firstP = first.calcClosestPointOutsideFor(secondSupport);
        Point2d secondP = second.calcClosestPointOutsideFor(firstSupport);

        if (!first.isPointInside(secondP)) return new Vector2d(Vector2d.ZERO_VECTOR);

        double betweenCenterDistance = firstSupport.getDistanceTo(secondSupport);
        double firstDistance = firstSupport.getDistanceTo(firstP);
        double secondDistance = secondSupport.getDistanceTo(secondP);

        double deltaLength = firstDistance + secondDistance - betweenCenterDistance;
        Vector2d delta = new Vector2d(firstSupport, secondSupport).normalize().mul(deltaLength);
        return delta;
    }
    public static void collide(NotMovableObject notMovableObject, MovableObject movableObject){
        collide(movableObject, notMovableObject); // mirror case
    }
    public static void collide(NotMovableObject notMovableObject1, NotMovableObject notMovableObject2){
        // nothing to do
    }

    public static void collide(GameObject first, GameObject second){
        if(first instanceof MovableObject){
            if(second instanceof MovableObject) collide((MovableObject) first, (MovableObject) second);
            else collide((MovableObject) first, (NotMovableObject) second);
        } else{
            if(second instanceof MovableObject) collide((NotMovableObject) first, (MovableObject) second);
            else collide((NotMovableObject) first, (NotMovableObject) second);
        }
    }

    public static void collide(ObjectDrawer first, ObjectDrawer second){
        collide(first.getCollideModel(), second.getCollideModel());
    }

    public static <T1 extends GameObject, T2 extends GameObject> void collide(List<T1> group1, List<T2> group2) {
        for (GameObject obj1 : group1) {
            for (GameObject obj2 : group2) {
                if(obj1 == obj2) continue;
                collide(obj1, obj2);
            }
        }
    }
    public static <T extends GameObject> void collide(List<T> group) {
        collide(group, group);
    }
    public static <T extends GameObject> void collide(GameObject gameObject, List<T> group) {
        for(T t: group){
            collide(gameObject, t);
            }
    }
}
