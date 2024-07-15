package GameParts;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel jp;

    public MainWindow(JPanel panel) throws HeadlessException {
        this.jp = panel;
        this.setContentPane(jp);
    }

    public static void main(String[] args) {

    }

}
