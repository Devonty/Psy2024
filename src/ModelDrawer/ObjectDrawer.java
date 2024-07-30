package ModelDrawer;

import CollideModel.CollideObject;
import ColorObject.ColorProvider;

import java.awt.*;

public interface ObjectDrawer {
    Color getMainColor();
    void setMainColorProvider(ColorProvider newColorProvider);
    Color getBorderColorProvider();
    void setBorderColorProvider(ColorProvider newColorProvider);
    CollideObject getCollideModel();
    void draw(Graphics2D g);
}
