package ColorObject;

import CollideModel.CollideObject;

abstract public class CollideObjectColorProvider implements ColorProvider {
    protected final CollideObject collideObject;

    public CollideObjectColorProvider(CollideObject collideObject) {
       this.collideObject = collideObject;
    }

}
