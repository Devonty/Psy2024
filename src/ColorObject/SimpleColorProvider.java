package ColorObject;

import java.awt.*;

public class SimpleColorProvider implements ColorProvider {
    protected Color mainColor;

    public SimpleColorProvider(Color mainColor) {
        this.mainColor = mainColor;
    }

    public SimpleColorProvider() {
        this(Color.YELLOW);
    }

    @Override
    public Color getColor() {
        return mainColor;
    }
}
