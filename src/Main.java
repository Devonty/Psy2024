import GameParts.Game;
import MyMath.Point2d;
import MyMath.Vector2d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // GamePanel
        Game game = new Game(30);
        fillSample1(game);
        //fillSample2(game);
        //fillSample3 (game);
        //fillSample4(game);

        game.start();


    }


    public static void fillSample1(Game game){
        double size = 700d;
        double step = 10;
        Point2d leftTop = new Point2d(100d, 100d);
        double[] X = new double[]{0, 2 * size, 2 * size, 0, 0};
        double[] Y = new double[]{0, 0, size, size, 0};
        double[] Xs = new double[]{-step, step, step, -step, -step};
        double[] Ys = new double[]{-step, -step, step, step, -step};

        // Circles
        for (int i = 0; i < 0; i++) {
            game.addCircle(new Point2d(size + i%2, size / 2 + Math.random() * 10).move(leftTop), Math.random() * 10 + 1);
        }

        // Walls
        for (int k = 0; k < 1; k++) {
            for (int i = 0; i < X.length - 1; i++) {
                Point2d p1 = new Point2d(X[i] + k * Xs[i], Y[i] + k * Ys[i]).move(leftTop);
                Point2d p2 = new Point2d(X[i + 1] + k * Xs[i + 1], Y[i + 1] + k * Ys[i + 1]).move(leftTop);
                game.addWall(p1, p2, 25);
            }
        }
    }

    public static void fillSample2(Game game) {
        game.addCircle(new Point2d(100, 100), 10);
        game.addWall(new Point2d(200, 0), new Point2d(200, 300));
    }

    public static void fillSample3(Game game) {
        game.addCircle(new Point2d(900, 700), 100, new Vector2d(100, 0));
        game.addCircle(new Point2d(400, 700), 100, new Vector2d(-100    , 0));
    }

    public static void fillSample4(Game game) {
        game.addCircle(new Point2d(900, 300), 10, new Vector2d(0, 0));
        for (int y = 300; y <= 800; y+=50) {
            game.addWall(new Point2d(600, y), new Point2d(800, y), 5);
        }
        game.addWall(new Point2d(800, 800), new Point2d(1000, 800), 20);
    }
}
