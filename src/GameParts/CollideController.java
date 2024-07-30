package GameParts;

import CollideModel.CollideObject;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;
import MyMath.Vector2d;

import java.util.List;

public class CollideController {
    public static void collide(MovableObject first, MovableObject second){
        Vector2d thisDelta = first.getDeltaAfterCollide(second);
        Vector2d otherDelta = second.getDeltaAfterCollide(first);

        first.move(thisDelta);
        second.move(otherDelta);
    }
    public static void collide(MovableObject movableObject, NotMovableObject notMovableObject){
        Vector2d thisDelta = movableObject.getDeltaAfterCollide(notMovableObject);
        movableObject.move(thisDelta.mul(2d));
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
