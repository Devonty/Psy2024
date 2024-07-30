package ColorObject;

import java.awt.*;

public class ColorUtils {
    public static Color mixColors(Color color1, Color color2, double ratio) {
        int red = (int) (color1.getRed() * ratio + color2.getRed() * (1 - ratio));
        int green = (int) (color1.getGreen() * ratio + color2.getGreen() * (1 - ratio));
        int blue = (int) (color1.getBlue() * ratio + color2.getBlue() * (1 - ratio));

        return new Color(red, green, blue);
    }
}
