package ModelDrawer;

import CollideModel.GameObject;
import ColorObject.ColorProvider;

import java.awt.*;

public interface ObjectDrawer {
    Color getMainColor();
    void setMainColorProvider(ColorProvider newColorProvider);
    Color getBorderColorProvider();
    void setBorderColorProvider(ColorProvider newColorProvider);
    GameObject getCollideModel();
    void draw(Graphics2D g);
}
