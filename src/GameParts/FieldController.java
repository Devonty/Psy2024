package GameParts;

import CollideModel.CollideObject;
import ModelDrawer.ObjectDrawer;

import java.awt.*;

public class FieldController {
    public final Field field;

    public FieldController() {
        this.field = new Field();
    }

    public void collide(){
        for (int i = 0; i < field.count(); i++) {
            for (int j = 0; j < field.count(); j++) {
                if(i==j) continue;
                CollideObject model1 = field.get(i).getCollideModel();
                CollideObject model2 = field.get(j).getCollideModel();
                model1.collide(model2);
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
