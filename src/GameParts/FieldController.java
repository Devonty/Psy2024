package GameParts;

import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;

import java.awt.*;

public class FieldController {
    public final Field field;

    public FieldController() {
        this.field = new Field();
    }

    public void collide(){
        collideMovableAndMovable();
        collideNotMovableAndMovable();
        resetSumMove();
    }

    private void collideNotMovableAndMovable(){
        for (MovableObject obj1 : field.movableObjects) {
            for (NotMovableObject obj2 : field.notMovableObjects) {
                obj1.collide(obj2);
            }
        }
    }

    private void resetSumMove(){
        for (MovableObject obj : field.movableObjects) {
            System.out.println(obj.sumMove());
            obj.resetSumMove();
        }
    }

    private void collideMovableAndMovable(){
        for (MovableObject obj1 : field.movableObjects) {
            for (MovableObject obj2 : field.movableObjects) {
                obj1.collide(obj2);
            }
        }
    }

    public void add(ObjectDrawer objectDrawer){
        field.add(objectDrawer);
    }

    public void draw(Graphics2D g) {
        field.allObjects.forEach(x -> x.draw(g));
    }
}
