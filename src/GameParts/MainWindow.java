package GameParts;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JPanel jp;

    public MainWindow(JPanel panel) throws HeadlessException {
        this.jp = panel;
        this.setContentPane(jp);
    }

    public MainWindow() throws HeadlessException {
        this(new GamePanel());
    }
}
