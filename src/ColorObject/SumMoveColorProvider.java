package ColorObject;

import CollideModel.CollideObject;

import java.awt.*;
import static ColorObject.ColorUtils.mixColors;

public class SumMoveColorProvider extends CollideObjectColorProvider {
    protected Color slowColor;
    protected Color fastColor;
    protected Color stopColor;
    public SumMoveColorProvider(CollideObject collideObject, Color slowColor, Color fastColor, Color stopColor) {
        super(collideObject);
        this.slowColor = slowColor;
        this.fastColor = fastColor;
        this.stopColor = stopColor;
    }
    public SumMoveColorProvider(CollideObject collideObject) {
        this(collideObject, Color.BLUE, Color.RED, Color.YELLOW);
    }


    protected static double normalized0to1(double num) {
        return 1d / (1d + Math.exp(-num));
    }

    @Override
    public Color getColor() {
        double length = collideObject.sumMove().length();

        if (length <= 1E-5) return stopColor;

        double ratio = (normalized0to1(length * 2) - 0.5) * 2;
        return mixColors(slowColor, fastColor, ratio);
    }
}
