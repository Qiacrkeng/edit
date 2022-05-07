package basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    static TextArea MainText;
    public MainWindow() {
        super();

        this.setTitle("乾坤道長的记事本");
        this.setBounds(100,100,400, 400);
        // BorderLayout边框样式
        JPanel panel = new JPanel(new BorderLayout()); // 轻量容器
        panel.add(new MenuBar(), BorderLayout.PAGE_START);
        MainWindow.MainText = new TextArea();
        panel.add(MainWindow.MainText,BorderLayout.CENTER);

        String logoUrl = "src/img/乾坤道長.jpg";
        this.setIconImage(new ImageIcon(logoUrl).getImage());

        this.setContentPane(panel); // 设置内容窗格
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(1);
            }
        });
    }
}