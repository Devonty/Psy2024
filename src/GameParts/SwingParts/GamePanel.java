package GameParts.SwingParts;

import Components.CollideComponent.CollideComponent;
import GameParts.FieldParts.FieldController;
import GameParts.Game;
import MyMath.Point2d;
import MyMath.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class GamePanel extends JPanel {
    private final Game game;

    public void setCircleOnLMB(int circleOnLMB) {
        this.circleOnLMB = circleOnLMB;
    }

    private int circleOnLMB = 1;
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
                    for (int i = 0; i < circleOnLMB; i++) {
                        game.addCircle(new Point2d(point).addNoiseVector(1), 15);

                    }
                } else if (e.getButton() == 2) {
                    if (first == null) first = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                    else if (second == null) {
                        second = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                        System.out.printf("%s\t%s\n", first, second);
                        if (!Objects.equals(first, second))
                            game.addWall(first, second, 10);
                        first = null;
                        second = null;
                    }
                    System.out.printf("%s\t%s\n", first, second);

                } else {
                    Point2d point = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                    if (first == null)
                        game.addCircle(point, 100);
                    else {
                        second = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                        System.out.printf("%s\t%s\n", first, second);
                        game.addCircle(first, 100, new Vector2d(first, second).mul(0.25));
                        first = null;
                        second = null;
                    }
                }
                repaint();
            }
        });
    }

    private static final Color BACKGROUNG_COLOR = new Color(0,0,0, 240);
    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(BACKGROUNG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (first != null) {
            g.setColor(Color.CYAN);
            double r = 5;
            g.fillOval((int) (first.x() - r), (int) (first.y() - r), (int) (2 * r), (int) (2 * r));
        }

        CollideComponent component = (CollideComponent) game.getFieldController().getComponent(0);
        Point2d startPoint = component.getStartPoint();
        double deltaStep = component.getDeltaStep();

        g.setColor(Color.BLUE);
        for (int i = 0; i < component.getHeight(); i++) {
            for (int j = 0; j < component.getWidth(); j++) {
                g.drawRect((int) (j * deltaStep + startPoint.x()), (int) (i * deltaStep + startPoint.y()), (int) deltaStep, (int) deltaStep);
            }
        }

        // draw
        fieldController.draw(g);
    }
}
