package GameParts.FieldParts;

import CollideModel.CollideObject;
import ModelDrawer.ObjectDrawer;

import java.awt.*;

public class FieldController {
    public final Field field;

    public FieldController(Field field) {
        this.field = field;
    }

    public void collide() {
        field.allObjects.forEach(CollideObject::resetSumMove);

        field.makeProjection();
        field.movableObjects.forEach(this::collideWithNeighbors);
        //CollideController.collide(field.movableObjects);
        CollideController.collide(field.movableObjects, field.notMovableObjects);
        //field.calcMinMaxXY();
    }

    private void collideWithNeighbors(CollideObject collideObject){
            CollideController.collide(collideObject, field.getNeighborList(collideObject));
    }

    public void add(ObjectDrawer objectDrawer) {
        field.add(objectDrawer);
    }

    public void draw(Graphics2D g) {
        field.objectDrawers.forEach(x -> x.draw(g));
    }
}
