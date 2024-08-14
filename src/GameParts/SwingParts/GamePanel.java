package GameParts.SwingParts;

import CollideModel.Movable.CircleModel;
import CollideModel.NotMovable.WallModel;
import ColorObject.ColorUtils;
import ColorObject.SumMoveColorProvider;
import GameParts.FieldParts.Field;
import GameParts.FieldParts.FieldController;
import GameParts.Game;
import ModelDrawer.CircleDrawer;
import ModelDrawer.WallDrawer;
import MyMath.Point2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class GamePanel extends JPanel {
    private final Game game;
    private final FieldController fieldController;

    public GamePanel(Game game) {
        super();
        this.game = game;
        this.fieldController = game.getFieldController();
        this.initListeners();
    }

    private Point2d first = null;
    private Point2d second = null;
    private void initListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    Point2d point = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                    for (int i = 0; i < 10; i++) {
                        game.addCircle(new Point2d(point).addNoiseVector(1E-1), 15);

                    }
                } else if (e.getButton() == 2) {
                    System.out.printf("%s\t%s\n", first, second);
                    if(first == null) first = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                    else if(second == null) {
                        second = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                        if(!Objects.equals(first, second))
                            game.addWall(first, second, 10);
                        first = null;
                        second = null;
                    }
                } else {
                    Point2d point = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                    game.addCircle(point, 100);
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (first != null) {
            g.setColor(Color.CYAN);
            double r = 5;
            g.fillOval((int) (first.x() - r), (int) (first.y() - r), (int) (2*r), (int) (2*r));
        }

        // fieldMask
       //Point2d startPoint = fieldController.field.getStartPoint();
       //double deltaStep = fieldController.field.getDeltaStep();

       //g.setColor(Color.BLUE);
       //for (int i = 0; i < fieldController.field.getHeight(); i++) {
       //    for (int j = 0; j < fieldController.field.getWidth(); j++) {
       //        g.drawRect((int) (j * deltaStep + startPoint.x()), (int) (i * deltaStep + startPoint.y()), (int) deltaStep, (int) deltaStep);
       //    }
       //}

        // draw
        fieldController.draw(g);
    }
}
