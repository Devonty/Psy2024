package ModelDrawer;

import CollideModel.CollideObject;

import java.awt.*;

abstract public class BaseDrawer implements ObjectDrawer {
    protected Color mainColor;
    protected Color borderColor = Color.GRAY;
    protected CollideObject collideModel;

    public BaseDrawer(CollideObject collideModel, Color mainColor, Color borderColor) {
        this.collideModel = collideModel;
        this.mainColor = mainColor;
        this.borderColor = borderColor;
    }

    public BaseDrawer(CollideObject collideModel, Color mainColor) {
        this.collideModel = collideModel;
        this.mainColor = mainColor;
    }

    public BaseDrawer(CollideObject collideModel) {
        this(collideModel, new Color((int) (Math.random() * 0x1000000)).brighter());
    }

    @Override
    public Color getMainColor() {
        return mainColor;
    }

    @Override
    public void setMainColor(Color newColor) {
        mainColor = newColor;
    }

    @Override
    public CollideObject getCollideModel() {
        return collideModel;
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    @Override
    public void setBorderColor(Color newColor) {
        borderColor = newColor;
    }

    @Override
    final public void draw(Graphics2D g) {
        Color save = g.getColor();
        subDraw(g);
        g.setColor(save);
    }

    abstract protected void subDraw(Graphics2D g); // Don't save enter color
}
