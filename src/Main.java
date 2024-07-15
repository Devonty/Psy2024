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

        // Walls
        double size = 400d;
        Point2d leftTop = new Point2d(100d, 100d);
        double[] X = new double[]{0,size,size,0, 0};
        double[] Y = new double[]{0,0,size,size,0};

        for (int i = 0; i < X.length - 1; i++) {
            Point2d p1 = new Point2d(X[i], Y[i]).move(leftTop);
            Point2d p2 = new Point2d(X[i + 1], Y[i + 1]).move(leftTop);
            gp.addWall(p1, p2);
        }

        // Circles
        for (int i = 0; i < 300; i++) {
            gp.addCircle(new Point2d(i*2,  size/2 + Math.random()*10).move(leftTop), 15);
        }
        JFrame mn = new MainWindow(gp);


        mn.setSize(1280, 585);
        //mn.setExtendedState(Frame.MAXIMIZED_BOTH);
        mn.setVisible(true);


    }
}
