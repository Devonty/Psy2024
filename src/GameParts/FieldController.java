package GameParts;

import CollideModel.CollideObject;
import ModelDrawer.ObjectDrawer;

import java.awt.*;

public class FieldController {
    public final Field field;

    public FieldController() {
        this.field = new Field();
    }

    public void collide() {
        field.movableObjects.forEach(CollideObject::resetSumMove);
        CollideController.collide(field.movableObjects);
        CollideController.collide(field.movableObjects, field.notMovableObjects);
    }


    public void add(ObjectDrawer objectDrawer) {
        field.add(objectDrawer);
    }

    public void draw(Graphics2D g) {
        field.objectDrawers.forEach(x -> x.draw(g));
    }
}
