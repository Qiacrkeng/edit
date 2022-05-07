package basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

// 菜单栏
public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        this.setBackground(Color.WHITE);

        Menu fileButton = new FileView("文件(F)");
        fileButton.setMnemonic(KeyEvent.VK_F); // 添加快捷鍵,Alt + F
        this.add(fileButton);

        Menu editButton = new Menu("编辑(E)");
        editButton.add(new MenuItem("粘貼"));
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
