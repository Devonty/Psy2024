package GameParts;

import CollideModel.Movable.CircleModel;
import ModelDrawer.CircleDrawer;
import MyMath.Point2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private final FieldController fieldController;
    private static int frameNumber = 0;
    private static int collideNumber = 0;

    public GamePanel() {
        this.fieldController = new FieldController();
    }

    public void addCircle(Point2d center, double radios) {
        fieldController.add(new CircleDrawer(new CircleModel(center, radios)));

        // repaint timer
        javax.swing.Timer timer = new Timer(2000, e -> {
            // ППЦ Кастыль чтобы убрать двойной вызов
            if(collideNumber++ % 2 == 0) return;

            fieldController.collide();
            repaint();
        });

        timer.start();
    }

    @Override
    public void paint(Graphics gr) {
        System.out.println(frameNumber++);
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        fieldController.draw(g);
    }
}
