package ModelDrawer;

import CollideModel.CollideObject;

import java.awt.*;

public interface ObjectDrawer {
    Color getColor();
    void setColor(Color newColor);
    CollideObject getCollideModel();
    void draw(Graphics2D g);
}
