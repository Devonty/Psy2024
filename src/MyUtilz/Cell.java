package MyUtilz;

import CollideModel.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Cell {
    protected final List<GameObject> gameObjectList;

    public Cell() {
        this.gameObjectList = new LinkedList<>();
    }

    public void clear(){
        gameObjectList.clear();
    }

    public void add(GameObject gameObject){
        gameObjectList.add(gameObject);
    }

    public List<GameObject> getList(){
        return gameObjectList;
    }
}
