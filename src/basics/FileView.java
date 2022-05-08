package basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class FileView extends Menu {
    FileView(String text) {
        super(text);
        Menu xinjian = new Menu("新建");
        this.add(xinjian);
        this.addSeparator(); // 分割线
        xinjian.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // 对话框
                int result = JOptionPane.showConfirmDialog(null, "当前的内容需要保存吗？", "确认框",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == 0)
                    MainWindow.MainText.setText("");
            }
        });

        MenuItem open = new MenuItem("打開...");
        this.add(open);
        // 设置修改键，它能直接调用菜单项的操作侦听器而不必显示菜单的层次结构
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        // 在给出一个数字键代码和一组修饰符的情况下，返回 KeyStroke 的一个共享实例。
        open.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JFileChooser fileChoose = new JFileChooser("F:\\");
                fileChoose.showOpenDialog(null);
                File openFile = fileChoose.getSelectedFile();
                Reader reader = null;
                int tempChar;
                String result = "";
                try {
                    // 拿到输入读取流和文件输入流
                    reader = new InputStreamReader(new FileInputStream(openFile));
                    while ((tempChar = reader.read()) != -1) {
                        result += new String(Character.toChars(tempChar));
                    }
                    reader.close();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                MainWindow.MainText.setText(result);
            }
        });

        MenuItem save = new MenuItem("保存...");
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JFileChooser fileChoose = new JFileChooser("F:\\");
                int value = fileChoose.showSaveDialog(null);
            }
        });
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        this.add(save);
        this.addSeparator();
        MenuItem exitB = new MenuItem("退出...");
        this.add(exitB);
        exitB.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.exit(1);
            }
        });
    }
}
