package ColorObject;

import java.awt.*;

public class RandomColorProvider extends SimpleColorProvider{
    public RandomColorProvider() {
        super(new Color((int) (Math.random() * 0x1000000)).brighter());
    }
}
