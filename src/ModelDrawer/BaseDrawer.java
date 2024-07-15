package ModelDrawer;

import CollideModel.CollideObject;

import java.awt.*;

abstract public class BaseDrawer implements ObjectDrawer {
    protected Color color;
    protected CollideObject collideModel;

    public BaseDrawer(CollideObject collideModel, Color color) {
        this.collideModel = collideModel;
        this.color = color;
    }

    public BaseDrawer(CollideObject collideModel) {
        this(collideModel, new Color((int)(Math.random() * 0x1000000)).brighter());
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color newColor) {
        color = newColor;
    }

    @Override
    public CollideObject getCollideModel() {
        return collideModel;
    }

    @Override
    final public void draw(Graphics2D g) {
        Color save = g.getColor();
        subDraw(g);
        g.setColor(save);
    }

    abstract protected void subDraw(Graphics2D g); // Don't save enter color
}
