package ModelDrawer;

import CollideModel.Movable.CircleModel;

import java.awt.*;

public class CircleDrawer extends BaseDrawer {
    public CircleDrawer(CircleModel circleModel) {
        super(circleModel);
    }

    @Override
    protected void subDraw(Graphics2D g) {
        CircleModel model = (CircleModel) collideModel;

        int diameter = (int) model.radius() * 2;
        int x = (int) (model.x() - model.radius());
        int y = (int) (model.y() - model.radius());

        g.setColor(mainColor);
        g.fillOval(x, y, diameter, diameter);
        g.setColor(borderColor);
        g.drawOval(x, y, diameter, diameter);
    }
}
