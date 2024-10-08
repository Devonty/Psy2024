package ModelDrawer;

import CollideModel.Movable.CircleModel;
import ColorObject.RandomColorProvider;
import MyMath.Vector2d;

import java.awt.*;

public class CircleDrawer extends BaseDrawer {
    public CircleDrawer(CircleModel circleModel) {
        super(circleModel, new RandomColorProvider());
    }

    @Override
    protected void subDraw(Graphics2D g) {
        CircleModel model = (CircleModel) collideModel;

        int diameter = (int) model.radius() * 2;
        int x = (int) (model.x() - model.radius());
        int y = (int) (model.y() - model.radius());

        g.setColor(mainColorProvider.getColor());
        g.fillOval(x, y, diameter, diameter);
        g.setColor(borderColorProvider.getColor());
        g.drawOval(x, y, diameter, diameter);

        // add speed vector
        g.setColor(Color.BLUE);
        g.drawLine((int) model.x(), (int) model.y(), (int) (model.x() + model.velocity().x()), (int) (model.y() + model.velocity().y()));

        double k = 10;
        g.setColor(Color.RED);
        for(Vector2d direction : model.getBlockDirections()){
            g.drawLine((int) model.x(), (int) model.y(), (int) (model.x() + direction.x() * k), (int) (model.y() + direction.y() * k));
        }
    }
}
