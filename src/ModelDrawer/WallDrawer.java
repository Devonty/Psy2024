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
        WallModel wallModel = (WallModel) collideModel;

        Point2d p1 = wallModel.start();
        Point2d p2 = wallModel.end();

        // wall
        g.setColor(mainColorProvider.getColor());
        Polygon polygon = new Polygon();
        Point2d[] points = wallModel.getRectPoints();
        for(Point2d point : points){
            polygon.addPoint((int) point.x(), (int) point.y());
        }
        g.fillPolygon(polygon);

        // segment
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke((float) (1)));
        g.drawLine((int) p1.x(), (int) p1.y(), (int) p2.x(), (int) p2.y());
    }
}
