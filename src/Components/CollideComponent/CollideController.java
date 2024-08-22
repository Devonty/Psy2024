package Components.CollideComponent;

import CollideModel.GameObject;
import CollideModel.Movable.CircleModel;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import Components.MovingComponent.MovingComponent;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.Objects;

public class CollideController {
    public static void collide(MovableObject first, MovableObject second) {
        if (first == second || Objects.equals(first.center(), second.center())) return;
        if (first instanceof CircleModel && second instanceof CircleModel) {
            calcVelocityAfterCollide((CircleModel) first, (CircleModel) second);
        }
        DeltaVectorInfo delta = getDeltaVector(first, second);
        if (delta == null) return;

        if (delta.length != 0) moveByMass(first, second, delta.direction.getMul(delta.length));

    }

    private static void calcVelocityAfterCollide(CircleModel first, CircleModel second) {
        Vector2d v1 = first.velocity();
        Vector2d v2 = second.velocity();

        double m1 = first.mass();
        double m2 = second.mass();

        double r1 = first.radius();
        double r2 = second.radius();

        Vector2d deltaV = v1.getSub(v2);
        Vector2d deltaC = new Vector2d(second.center(), first.center());

        if(deltaV.equals(Vector2d.ZERO_VECTOR) || deltaC.equals(Vector2d.ZERO_VECTOR)) return;

        double D_div4 = Math.pow(deltaC.getMul(deltaV), 2) - Math.pow(deltaV.length(), 2) * Math.pow(deltaC.length(), 2) + Math.pow(deltaV.length(), 2) * Math.pow(r1 + r2, 2);
        if(D_div4 < 0) return;

        double t0 = -(deltaC.getMul(deltaV) + Math.sqrt(D_div4)) / Math.pow(deltaV.length(), 2);
        if (t0 < 0 || t0 > MovingComponent.timeStep) return;

        Vector2d p = deltaC.getMoved(deltaV.getMul(t0));

        Vector2d base = p.getMul(p.getMul(v2.getSub(v1)) / Math.pow(p.length(), 2));
        Vector2d u1 = v1.getMoved(base.getMul(2*m2/(m1+m2)));
        Vector2d u2 = v2.getMoved(base.getMul(- 2*m1/(m1+m2)));

        first.setVelocity(u1.mul(Math.sqrt(velColK)));
        second.setVelocity(u2.mul(Math.sqrt(velColK)));

        first.move(v1.getMul(t0));
        second.move(v2.getMul(t0));
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
        if (d2_clear.length() <= toler) first.setStableAtDirection(d1_delta.getMoved(d1_clear.getMul(-1)));
        if (d1_clear.length() <= toler) second.setStableAtDirection(d2_delta.getMoved(d2_clear.getMul(-1)));

        //first.move(d1_clear).move(d1_delta);
        //second.move(d2_clear).move(d2_delta);

        first.move(d1_clear);
        second.move(d2_clear);
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
        if(verticalVelocity.areSameDirection(delta.direction))
            movable.addVelocity(verticalVelocity.getMul(-(1 + velColK)));

    }

    public record DeltaVectorInfo(Vector2d direction, double length) {
    }

    public static DeltaVectorInfo getDeltaVector(GameObject first, GameObject second) {
        Point2d firstSupport = first.getSupportPoint(second);
        Point2d secondSupport = second.getSupportPoint(first);

        Point2d firstP = first.calcClosestPointOutsideFor(secondSupport);
        Point2d secondP = second.calcClosestPointOutsideFor(firstSupport);

        if (Objects.equals(firstP, secondP)) return new DeltaVectorInfo(new Vector2d(firstSupport, secondSupport), 0);
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
        if(first == second) return;
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
