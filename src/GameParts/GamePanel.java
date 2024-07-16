package GameParts;

import CollideModel.Movable.CircleModel;
import CollideModel.NotMovable.WallModel;
import ModelDrawer.CircleDrawer;
import ModelDrawer.WallDrawer;
import MyMath.Point2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements ActionListener{

    Timer timer=new Timer(50, this);
    private final FieldController fieldController;
    private static int collideNumber = 0;

    public GamePanel() {
        super();
        this.fieldController = new FieldController();
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point2d point = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                addCircle(point, 15);
            }
        });
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            System.out.println(collideNumber++); // выводи в консоль номер итерации коллизии
            fieldController.collide();
            repaint();
        }
    }
    public void addCircle(Point2d center, double radios) {
        fieldController.add(new CircleDrawer(new CircleModel(center, radios)));
    }
    public void addWall(Point2d p1, Point2d p2) {
        fieldController.add(new WallDrawer(new WallModel(p1, p2)));
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        fieldController.draw(g);
    }
}
