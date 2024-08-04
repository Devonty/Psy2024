package GameParts.FieldParts;

import CollideModel.CollideObject;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.nio.channels.Pipe;
import java.util.List;
import java.util.Objects;

public class CollideController {
    public static void collide(MovableObject first, MovableObject second){
        if (Objects.equals(first.center(), second.center())) return;
        Point2d firstP = first.calcClosestPointOutsideFor(second.getSupportPoint(first));
        Point2d secondP = second.calcClosestPointOutsideFor(first.getSupportPoint(second));

        double betweenCenterDistance = first.center().getDistanceTo(second.center());
        double firstDistance = first.center().getDistanceTo(firstP);
        double secondDistance = second.center().getDistanceTo(secondP);

        double deltaLength = firstDistance + secondDistance - betweenCenterDistance;

        if (deltaLength <= 0d) return;

        Vector2d delta = new Vector2d(first.center(), second.center()).normalize().mul(deltaLength / 2d);

        double totalMass = first.mass() + second.mass();
        double firstK = second.mass() / totalMass;
        double secondK = first.mass() / totalMass;

        first.move(delta.getMul(-firstK));
        second.move(delta.getMul(secondK));
    }
    public static void collide(MovableObject movable, NotMovableObject notMovable){
        if (Objects.equals(movable.center(), notMovable.center())) return;

        Point2d firstSupport = movable.getSupportPoint(notMovable);
        Point2d secondSupport = notMovable.getSupportPoint(movable);

        Point2d firstP = movable.calcClosestPointOutsideFor(secondSupport);
        Point2d secondP = notMovable.calcClosestPointOutsideFor(firstSupport);

        if (!movable.isPointInside(secondP)) return;

        double betweenCenterDistance = movable.center().getDistanceTo(secondSupport);
        double firstDistance = firstSupport.getDistanceTo(firstP);
        double secondDistance = secondSupport.getDistanceTo(secondP);

        double deltaLength = firstDistance + secondDistance - betweenCenterDistance;

        if (deltaLength <= 0d) return;

        Vector2d delta = new Vector2d(firstSupport, secondSupport).normalize().mul(-deltaLength);
        movable.move(delta);
    }
    public static void collide(NotMovableObject notMovableObject, MovableObject movableObject){
        collide(movableObject, notMovableObject); // mirror case
    }
    public static void collide(NotMovableObject notMovableObject1, NotMovableObject notMovableObject2){
        // nothing to do
    }

    public static void collide(CollideObject first, CollideObject second){
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

    public static <T1 extends CollideObject, T2 extends CollideObject> void collide(List<T1> group1, List<T2> group2) {
        for (CollideObject obj1 : group1) {
            for (CollideObject obj2 : group2) {
                if(obj1 == obj2) continue;
                collide(obj1, obj2);
            }
        }
    }
    public static <T extends CollideObject> void collide(List<T> group) {
        collide(group, group);
    }
}
