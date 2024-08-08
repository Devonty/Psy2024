package MyUtilz;

import CollideModel.CollideObject;

import java.util.LinkedList;
import java.util.List;

public class Cell {
    protected final List<CollideObject> collideObjectList;

    public Cell() {
        this.collideObjectList = new LinkedList<>();
    }

    public void clear(){
        collideObjectList.clear();
    }

    public void add(CollideObject collideObject){
        collideObjectList.add(collideObject);
    }

    public List<CollideObject> getList(){
        return collideObjectList;
    }
}
