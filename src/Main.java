import GameParts.GamePanel;
import GameParts.MainWindow;
import MyMath.Point2d;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GamePanel gp = new GamePanel();
        for (int i = 0; i < 100; i++) {
            gp.addCircle(new Point2d(800 + i*3, 400 + Math.random()*10), 10);
        }
        JFrame mn = new MainWindow(gp);


        mn.setSize(1280, 585);
        mn.setExtendedState(Frame.MAXIMIZED_BOTH);
        mn.setVisible(true);


    }
}
