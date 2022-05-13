package basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.datatransfer.*;

// 菜单栏
public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        this.setBackground(Color.WHITE);

        Menu fileButton = new FileView("文件(F)");
        fileButton.setMnemonic(KeyEvent.VK_F); // 添加快捷鍵,Alt + F
        this.add(fileButton);

        Menu editButton = new Menu("编辑(E)");
        MenuItem fuzhi = new MenuItem("複製");
        MenuItem zhantie = new MenuItem("粘貼");
        editButton.add(fuzhi);
        editButton.add(zhantie);
        zhantie.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String result = "";
                // Clipboard用来实现复制粘貼操作，Transferable传输操作（获取剪切板的内容）
                Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable clipTf = sysClip.getContents(null);
                try {
                    // 数据提取为字符串
                    result = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
                    MainWindow.mMainText.setText(result);
                } catch (UnsupportedFlavorException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                return;
            }
        });

        fuzhi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String result = MainWindow.mMainText.getText();
                Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable clipTf = new StringSelection(result);
                // 字符串拿去做剪切板
                sysClip.setContents(clipTf, null);
                return;
            }
        });
        this.add(editButton);

        Menu formatButton = new Menu("格式(O)");
        formatButton.add(new MenuItem("字體"));
        this.add(formatButton);

        Menu readButton = new Menu("查看(V)");
        readButton.add(new JCheckBox("状态栏"));
        this.add(readButton);

        Menu helpButton = new Menu("帮助(H)");
        helpButton.add(new MenuItem("關於本記事本"));
        this.add(helpButton);
    }
}
