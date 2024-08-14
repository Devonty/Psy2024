package ColorObject;

import CollideModel.GameObject;

import java.awt.*;
import static ColorObject.ColorUtils.mixColors;

public class SumMoveColorProvider extends CollideObjectColorProvider {
    protected Color slowColor;
    protected Color fastColor;
    protected Color stopColor;
    public SumMoveColorProvider(GameObject gameObject, Color slowColor, Color fastColor, Color stopColor) {
        super(gameObject);
        this.slowColor = slowColor;
        this.fastColor = fastColor;
        this.stopColor = stopColor;
    }
    public SumMoveColorProvider(GameObject gameObject) {
        this(gameObject, Color.BLUE, Color.RED, Color.YELLOW);
    }


    protected static double normalized0to1(double num) {
        return 1d / (1d + Math.exp(-num));
    }

    @Override
    public Color getColor() {
        double length = gameObject.sumMove().length();

        if (length <= 1E-4) return stopColor;

        double ratio = (normalized0to1(length * 2) - 0.5) * 2;
        return mixColors(slowColor, fastColor, ratio);
    }
}
