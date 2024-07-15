package GameParts;

import CollideModel.Movable.CircleModel;
import ModelDrawer.CircleDrawer;
import MyMath.Point2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{

    Timer timer=new Timer(100, this);
    private final FieldController fieldController;
    private static int frameNumber = 0;
    private static int collideNumber = 0;

    public GamePanel() {
        super();
        this.fieldController = new FieldController();
        timer.start();
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            System.out.println(collideNumber++);
            fieldController.collide();
            repaint();
        }
    }
    public void addCircle(Point2d center, double radios) {
        fieldController.add(new CircleDrawer(new CircleModel(center, radios)));
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        fieldController.draw(g);
    }
}
