package GameParts;

import CollideModel.Movable.CircleModel;
import CollideModel.NotMovable.WallModel;
import GameParts.FieldParts.Field;
import GameParts.FieldParts.FieldController;
import GameParts.SwingParts.GamePanel;
import GameParts.SwingParts.MainWindow;
import ModelDrawer.CircleDrawer;
import ModelDrawer.ObjectDrawer;
import ModelDrawer.WallDrawer;
import MyMath.Point2d;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Game implements Runnable {
    private final Field field; // contains
    private final FieldController fieldController; // any interactions

    private final GamePanel gamePanel; // only draw

    public final double clock;
    private int frameCount;
    private boolean keepRunning = true;
    private boolean keepUpdating = true;

    private final List<ObjectDrawer> toAddList;


    public Game(double fps) {
        this.clock = 1000000000.0 / fps;
        this.frameCount = 0;
        this.field = new Field();
        this.fieldController = new FieldController(field);
        this.gamePanel = new GamePanel(this);
        this.toAddList = new LinkedList<>();

        // Add JPanel to JFrame
        MainWindow mn = new MainWindow(gamePanel);

        mn.setSize(1280, 585);
        mn.setExtendedState(Frame.MAXIMIZED_BOTH);
        mn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mn.setVisible(true);
    }

    public Game() {this(60d);}
    @Override
    public void run() {
        keepRunning = true;
        keepUpdating = true;
        long lastTime = System.nanoTime();
        double delta = 0;

        while (keepRunning) {
            addObjectsFromQueue();
            long now = System.nanoTime();
            delta += (now - lastTime) / clock;

            while (delta >= 1) {
                System.out.println(++frameCount);
                update();
                delta=0;
            }
            lastTime = now;
            render();
        }
    }

    public synchronized void addCircle(Point2d center, double radios) {
        toAddList.add(new CircleDrawer(new CircleModel(center, radios)));
    }

    public synchronized void addWall(Point2d p1, Point2d p2, double width) {
        toAddList.add(new WallDrawer(new WallModel(p1, p2, width)));
    }
    public synchronized void addWall(Point2d p1, Point2d p2) {
        addWall(p1, p2, 50);
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public void stopUpdating() {
        keepUpdating = false;
    }

    public void startUpdating() {
        keepUpdating = true;
    }


    public void stop() {
        keepRunning = false;
    }

    private synchronized void addObjectsFromQueue() {
        synchronized (toAddList) {
            toAddList.stream().filter(Objects::nonNull).forEach(fieldController::add);
            toAddList.clear();
        }
    }

    private void render() {
        gamePanel.repaint();
    }

    private void update() {
        if (!keepUpdating) return;
        fieldController.update();
    }


}
