import CollideModel.NotMovable.WallModel;
import GameParts.GamePanel;
import GameParts.MainWindow;
import ModelDrawer.WallDrawer;
import MyMath.Point2d;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // GamePanel
        GamePanel gp = new GamePanel();


        double size = 600d;
        double step = 10;
        Point2d leftTop = new Point2d(100d, 100d);
        double[] X = new double[]{0, 2 * size, 2 * size, 0, 0};
        double[] Y = new double[]{0, 0, size, size, 0};
        double[] Xs = new double[]{-step, step, step, -step, -step};
        double[] Ys = new double[]{-step, -step, step, step, -step};

        // Circles
        for (int i = 0; i < 1400; i++) {
            gp.addCircle(new Point2d(size + i%2, size / 2 + Math.random() * 10).move(leftTop), Math.random() * 5 + 5);
        }
        JFrame mn = new MainWindow(gp);

        // Walls

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < X.length - 1; i++) {
                Point2d p1 = new Point2d(X[i] + k * Xs[i], Y[i] + k * Ys[i]).move(leftTop);
                Point2d p2 = new Point2d(X[i + 1] + k * Xs[i + 1], Y[i + 1] + k * Ys[i + 1]).move(leftTop);
                gp.addWall(p1, p2);
            }
        }


        mn.setSize(1280, 585);
        mn.setExtendedState(Frame.MAXIMIZED_BOTH);
        mn.setVisible(true);


    }
}
