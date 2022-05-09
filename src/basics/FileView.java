package basics;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
                if (result == 0) {
                    new SaveFileEvent();
                    MainWindow.MainText.setText("");
                }
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
                new CreateFileEvent();
            }
        });

        MenuItem save = new MenuItem("保存...");
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new SaveFileEvent();
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

class SaveFileEvent {
    SaveFileEvent() {
        String result = MainWindow.MainText.getText();
        if (result.length() < 1) {
            JOptionPane.showMessageDialog(MainWindow.MainJFrame, "不能為空");
            return;
        }

        JFileChooser fileChoose = new JFileChooser("F:\\");
        // 後綴名
        FileNameExtensionFilter filter = new FileNameExtensionFilter("文本(*.txt)", "txt");
        fileChoose.setFileFilter(filter);

        File file = fileChoose.getSelectedFile();
        int status = fileChoose.showSaveDialog(null);
        if (status == JFileChooser.CANCEL_OPTION)
            return;
        // 文件輸出流
        FileOutputStream fsOut = null;
        try {
            String fileName = file.getName(); // 文件名輸入框
            // 創建文件
            File newFile = new File(fileChoose.getCurrentDirectory() + "/" + fileName + ".txt");
            fsOut = new FileOutputStream(newFile);
            // 寫入
            fsOut.write(result.getBytes());
            fsOut.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

class CreateFileEvent {
    CreateFileEvent() {
        JFileChooser fileChoose = new JFileChooser("F:\\");
        int status = fileChoose.showOpenDialog(null);
        if (status == JFileChooser.CANCEL_OPTION)
            return;
        File openFile = fileChoose.getSelectedFile();
        Reader reader = null;
        int tempChar;
        String result = "";
        try {
            // 拿到输入流读取器和文件输入流，并必须指定读取编码
            reader = new InputStreamReader(new FileInputStream(openFile), "UTF-8");
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
}