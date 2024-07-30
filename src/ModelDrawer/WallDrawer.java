package ModelDrawer;

import CollideModel.NotMovable.WallModel;
import MyMath.Point2d;

import java.awt.*;

public class WallDrawer extends BaseDrawer{
    public WallDrawer(WallModel wallModel) {
        super(wallModel);
    }
    @Override
    protected void subDraw(Graphics2D g) {
        WallModel model = (WallModel) collideModel;

        Point2d p1 = model.start();
        Point2d p2 = model.end();

        g.setColor(mainColorProvider.getColor());
        g.drawLine((int) p1.x(), (int) p1.y(), (int) p2.x(), (int) p2.y());
    }
}
