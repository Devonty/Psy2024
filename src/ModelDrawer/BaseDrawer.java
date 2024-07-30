package ModelDrawer;

import CollideModel.CollideObject;
import ColorObject.ColorProvider;
import ColorObject.RandomColorProvider;
import ColorObject.SimpleColorProvider;

import java.awt.*;

abstract public class BaseDrawer implements ObjectDrawer {
    protected ColorProvider mainColorProvider;
    protected ColorProvider borderColorProvider;
    protected CollideObject collideModel;

    public BaseDrawer(CollideObject collideModel, ColorProvider mainColorProvider, ColorProvider borderColorProvider) {
        this.collideModel = collideModel;
        this.mainColorProvider = mainColorProvider;
        this.borderColorProvider = borderColorProvider;
    }

    public BaseDrawer(CollideObject collideModel, ColorProvider mainColorProvider) {
        this(collideModel, mainColorProvider, new SimpleColorProvider(Color.GRAY));
    }

    public BaseDrawer(CollideObject collideModel) {
        this(collideModel, new SimpleColorProvider(Color.YELLOW));
    }

    @Override
    public Color getMainColor() {
        return mainColorProvider.getColor();
    }

    @Override
    public void setMainColorProvider(ColorProvider newColorProvider) {
        mainColorProvider = newColorProvider;
    }

    @Override
    public CollideObject getCollideModel() {
        return collideModel;
    }

    @Override
    public Color getBorderColorProvider() {
        return borderColorProvider.getColor();
    }

    @Override
    public void setBorderColorProvider(ColorProvider newColorProvider) {
        borderColorProvider = newColorProvider;
    }

    @Override
    final public void draw(Graphics2D g) {
        Color save = g.getColor();
        subDraw(g);
        g.setColor(save);
    }

    abstract protected void subDraw(Graphics2D g); // Don't save enter color
}
