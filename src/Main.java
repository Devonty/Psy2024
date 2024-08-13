import GameParts.Game;
import MyMath.Point2d;

public class Main {
    public static void main(String[] args) {
        // GamePanel
        Game game = new Game(30);
        //fillSample1(game);

        Thread gameThread = new Thread(game, "gameThread");
        gameThread.start();
    }


    public static void fillSample1(Game game){
        double size = 400d;
        double step = 10;
        Point2d leftTop = new Point2d(100d, 100d);
        double[] X = new double[]{0, 2 * size, 2 * size, 0, 0};
        double[] Y = new double[]{0, 0, size, size, 0};
        double[] Xs = new double[]{-step, step, step, -step, -step};
        double[] Ys = new double[]{-step, -step, step, step, -step};

        // Circles
        for (int i = 0; i < 20; i++) {
            game.addCircle(new Point2d(size + i%2, size / 2 + Math.random() * 10).move(leftTop), Math.random() * 10 + 1);
        }

        // Walls
        for (int k = 0; k < 1; k++) {
            for (int i = 0; i < X.length - 1; i++, i++) {
                Point2d p1 = new Point2d(X[i] + k * Xs[i], Y[i] + k * Ys[i]).move(leftTop);
                Point2d p2 = new Point2d(X[i + 1] + k * Xs[i + 1], Y[i + 1] + k * Ys[i + 1]).move(leftTop);
                game.addWall(p1, p2, 10);
            }
        }
    }
}
