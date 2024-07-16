package GameParts;

import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import ModelDrawer.ObjectDrawer;

import java.awt.*;

public class FieldController {
    public final Field field;

    public FieldController() {
        this.field = new Field();
    }

    public void collide() {
        collideMovableAndMovable();
        collideNotMovableAndMovable();
        paintBySumMove();
        resetSumMove();
    }

    private void paintBySumMove() {
        for (ObjectDrawer drawer : field.objectDrawers) {
            drawer.setMainColor(getColorByNumber(drawer.getCollideModel().sumMove().length()));
        }
    }

    public static Color getColorByNumber(double num, Color c1, Color c2) {
        if (num <= 1E-4d) return Color.YELLOW;
        return mixColors(c1, c2, normalized0to1(num*1.5d) * 2 - 1);
    }
    public static Color getColorByNumber(double num) {
        return getColorByNumber(num, Color.BLUE, Color.RED);
    }

    public static double normalized0to1(double num){
        return 1d/(1d+Math.exp(-num));
    }

    public static Color mixColors(Color color1, Color color2, double ratio) {
        int red = (int) (color1.getRed() * ratio + color2.getRed() * (1 - ratio));
        int green = (int) (color1.getGreen() * ratio + color2.getGreen() * (1 - ratio));
        int blue = (int) (color1.getBlue() * ratio + color2.getBlue() * (1 - ratio));

        return new Color(red, green, blue);
    }

    private void collideNotMovableAndMovable() {
        for (MovableObject obj1 : field.movableObjects) {
            for (NotMovableObject obj2 : field.notMovableObjects) {
                obj1.collide(obj2);
            }
        }
    }

    private void resetSumMove() {
        for (MovableObject obj : field.movableObjects) {
            obj.resetSumMove();
        }
    }

    private void collideMovableAndMovable() {
        for (MovableObject obj1 : field.movableObjects) {
            for (MovableObject obj2 : field.movableObjects) {
                obj1.collide(obj2);
            }
        }
    }

    public void add(ObjectDrawer objectDrawer) {
        field.add(objectDrawer);
    }

    public void draw(Graphics2D g) {
        field.objectDrawers.forEach(x -> x.draw(g));
    }
}
