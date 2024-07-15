package ModelDrawer;

import CollideModel.CollideObject;

import java.awt.*;

public interface ObjectDrawer {
    Color getMainColor();
    void setMainColor(Color newColor);
    Color getBorderColor();
    void setBorderColor(Color newColor);
    CollideObject getCollideModel();
    void draw(Graphics2D g);
}
