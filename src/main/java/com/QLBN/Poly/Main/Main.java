
package com.QLBN.Poly.Main;

import com.QLBN.Poly.View.ManHinhChao;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        try {
//            com.sun.java.swing.plaf.gtk.GTKLookAndFeel
//            com.sun.java.swing.plaf.motif.MotifLookAndFeel
//            com.sun.java.swing.plaf.windows.WindowsLookAndFeel
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            ManHinhChao chao = new ManHinhChao(null, true);
            chao.setVisible(true);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
    }
}
