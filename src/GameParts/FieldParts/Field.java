package GameParts.FieldParts;

import CollideModel.GameObject;
import ModelDrawer.ObjectDrawer;

import java.util.*;

public class Field {
    protected List<ObjectDrawer> objectDrawers;
    protected List<GameObject> allObjects;


    public Field() {
        this.objectDrawers = new ArrayList<>();
        this.allObjects = new ArrayList<>();

    }

    protected void add(ObjectDrawer toAdd) {
        objectDrawers.add(toAdd);
        allObjects.add(toAdd.getCollideModel());
    }

}
