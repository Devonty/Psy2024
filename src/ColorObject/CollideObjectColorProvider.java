package ColorObject;

import CollideModel.GameObject;

abstract public class CollideObjectColorProvider implements ColorProvider {
    protected final GameObject gameObject;

    public CollideObjectColorProvider(GameObject gameObject) {
       this.gameObject = gameObject;
    }

}
