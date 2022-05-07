package basics;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenu {
    public Menu(String text) {
        super(text);

        this.setBorderPainted(false);
        setBackground(Color.WHITE);
    }
}
