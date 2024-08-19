package Components.CollideComponent;

import CollideModel.GameObject;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.List;
import java.util.Objects;

public class CollideController {
    public static void collide(MovableObject first, MovableObject second) {
        if (first == second || Objects.equals(first.center(), second.center())) return;
        DeltaVectorInfo delta = getDeltaVector(first, second);
        if (delta == null) return;
        //if (delta.length <= 1E2) return;
        calcVelocityAfterCollide(first, second, delta.direction);
        if(delta.length != 0) moveByMass(first, second, delta.direction.getMul(delta.length));

    }

    private static void calcVelocityAfterCollide(MovableObject first, MovableObject second, Vector2d delta) {
        Vector2d v1 = Vector2d.getProjection(first.velocity(), delta);
        Vector2d v2 = Vector2d.getProjection(second.velocity(), delta);

        double m1 = first.mass();
        double m2 = second.mass();

        Vector2d v1_ = v1.getMul(m1 - m2).move(v2.getMul(2 * m2)).mul(1d / (m1 + m2));
        Vector2d v2_ = v1.getMul(2 * m1).move(v2.getMul(m2 - m1)).mul(1d / (m1 + m2));

        Vector2d v1_adding = v1.getMul(-1).move(v1_);
        Vector2d v2_adding = v2.getMul(-1).move(v2_);

        first.addVelocity(v1_adding);
        second.addVelocity(v2_adding);

        //Vector2d v1_clear = first.getClearedBlockedDirections(v1_adding);
        //Vector2d v2_clear = second.getClearedBlockedDirections(v2_adding);

        //Vector2d v1_delta = v1_adding.getSub(v1_clear).mul(-Math.sqrt(m1/m2) +1);
        //Vector2d v2_delta = v2_adding.getSub(v2_clear).mul(-Math.sqrt(m1/m2) +1);

        //first.addVelocity(v2_delta);
        //second.addVelocity(v1_delta);
    }

    private static void moveByMass(MovableObject first, MovableObject second, Vector2d delta) {
        double totalMass = first.mass() + second.mass();
        double firstK = second.mass() / totalMass;
        double secondK = first.mass() / totalMass;

        Vector2d d1 = delta.getMul(-firstK);
        Vector2d d2 = delta.getMul(secondK);

        Vector2d d1_clear = first.getClearedBlockedDirections(d1);
        Vector2d d2_clear = second.getClearedBlockedDirections(d2);

        Vector2d d1_delta = d1.getSub(d1_clear).mul(-velColK);
        Vector2d d2_delta = d2.getSub(d2_clear).mul(-velColK);


        // if(delta.length() <= 1E-2) return;
        double toler = 1E-4;
        if(d2_clear.length() <= toler) first.setStableAtDirection(d1_delta.getMoved(d1_clear.getMul(-1)));
        if(d1_clear.length() <= toler) second.setStableAtDirection(d2_delta.getMoved(d2_clear.getMul(-1)));

        //first.move(d1_clear).move(d1_delta);
        //second.move(d2_clear).move(d2_delta);

        first.move(d1);
        second.move(d2);
    }



    public static double velColK = 0.70710678118;

    public static void collide(MovableObject movable, NotMovableObject notMovable) {
        // if on same position
        if (Objects.equals(movable.center(), notMovable.center())) return;

        // collide
        DeltaVectorInfo delta = getDeltaVector(movable, notMovable);
        if (delta == null) return;
        movable.move(delta.direction.getMul(-delta.length));

        // velocity
        Vector2d verticalVelocity = Vector2d.getProjection(movable.velocity(), delta.direction);
        movable.setStableAtDirection(delta.direction);

        //if(verticalVelocity.areSameDirection(delta.direction))
            movable.addVelocity(verticalVelocity.getMul(-(1 + velColK)));

    }

    public record DeltaVectorInfo(Vector2d direction, double length){}

    public static DeltaVectorInfo getDeltaVector(GameObject first, GameObject second) {
        Point2d firstSupport = first.getSupportPoint(second);
        Point2d secondSupport = second.getSupportPoint(first);

        Point2d firstP = first.calcClosestPointOutsideFor(secondSupport);
        Point2d secondP = second.calcClosestPointOutsideFor(firstSupport);

        if(Objects.equals(firstP, secondP)) return new DeltaVectorInfo(new Vector2d(firstSupport, secondSupport),0);
        if (!first.isPointInside(secondP) && !second.isPointInside(firstP)) return null;

        double betweenCenterDistance = firstSupport.getDistanceTo(secondSupport);
        double firstDistance = firstSupport.getDistanceTo(firstP);
        double secondDistance = secondSupport.getDistanceTo(secondP);

        double deltaLength = firstDistance + secondDistance - betweenCenterDistance;
        Vector2d direction = new Vector2d(firstSupport, secondSupport).normalize();
        return new DeltaVectorInfo(direction, deltaLength);
    }

    public static void collide(NotMovableObject notMovableObject, MovableObject movableObject) {
        collide(movableObject, notMovableObject); // mirror case
    }

    public static void collide(NotMovableObject notMovableObject1, NotMovableObject notMovableObject2) {
        // nothing to do
    }

    public static void collide(GameObject first, GameObject second) {
        if (first instanceof MovableObject) {
            if (second instanceof MovableObject) collide((MovableObject) first, (MovableObject) second);
            else collide((MovableObject) first, (NotMovableObject) second);
        } else {
            if (second instanceof MovableObject) collide((NotMovableObject) first, (MovableObject) second);
            else collide((NotMovableObject) first, (NotMovableObject) second);
        }
    }

    public static <T1 extends GameObject, T2 extends GameObject> void collide(Iterable<T1> group1, Iterable<T2> group2) {
        for (GameObject obj1 : group1) {
            for (GameObject obj2 : group2) {
                if (obj1 == obj2) continue;
                collide(obj1, obj2);
            }
        }
    }

    public static <T extends GameObject> void collide(Iterable<T> group) {
        collide(group, group);
    }

    public static <T extends GameObject> void collide(GameObject gameObject, Iterable<T> group) {
        for (T t : group) {
            collide(gameObject, t);
        }
    }
}
