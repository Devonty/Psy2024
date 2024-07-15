package GameParts;

import ModelDrawer.ObjectDrawer;

import java.util.ArrayList;

public class Field {
    protected ArrayList<ObjectDrawer> allObjects;

    public Field() {
        this.allObjects = new ArrayList<>();
    }

    protected void add(ObjectDrawer toAdd){
        allObjects.add(toAdd);
    }
    protected ObjectDrawer get(int index){
        return allObjects.get(index);
    }
    public int count(){return allObjects.size();}
}
