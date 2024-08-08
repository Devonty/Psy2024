package GameParts.SwingParts;

import CollideModel.Movable.CircleModel;
import CollideModel.NotMovable.WallModel;
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

public class GamePanel extends JPanel{
    private final Game game;
    private final FieldController fieldController;

    public GamePanel(Game game) {
        super();
        this.game = game;
        this.fieldController = game.getFieldController();
        this.initListeners();
    }


    private void initListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    Point2d point = new Point2d(e.getXOnScreen(), e.getYOnScreen() - 15);
                    for (int i = 0; i < 10; i++) {
                        game.addCircle(new Point2d(point).addNoiseVector(1E-1), 15);

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
        fieldController.draw(g);
    }
}
