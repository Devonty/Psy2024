package GameParts;

import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;

import java.util.ArrayList;

public class Field {
    protected ArrayList<ObjectDrawer> objectDrawers;
    protected ArrayList<MovableObject> movableObjects;
    protected ArrayList<NotMovableObject> notMovableObjects;

    public Field() {
        this.objectDrawers = new ArrayList<>();
        this.movableObjects = new ArrayList<>();
        this.notMovableObjects = new ArrayList<>();
    }

    protected void add(ObjectDrawer toAdd){
        objectDrawers.add(toAdd);
        if(toAdd.getCollideModel() instanceof MovableObject) movableObjects.add((MovableObject)toAdd.getCollideModel());
        if(toAdd.getCollideModel() instanceof NotMovableObject) notMovableObjects.add((NotMovableObject)toAdd.getCollideModel());
    }
    protected ObjectDrawer get(int index){
        return objectDrawers.get(index);
    }
    public int count(){return objectDrawers.size();}
}
