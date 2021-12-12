package com.QLBN.Poly.Utils;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MsgBox {

    /**
     * Hiển thị thông báo cho người dùng
     *
     * @param parent là cửa sổ chứa thông báo
     * @param message là thông báo
     */
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,
                "Hệ thống quản lý bệnh nhân", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message,
                "Hệ thống quản lý bệnh nhân",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Hiển thị thông báo yêu cầu nhập dữ liệu
     *
     * @param parent là cửa sổ chứa thông báo
     * @param message là thông báo nhắc nhở nhập
     * @return là kết quả nhận được từ người sử dụng nhập vào
     */
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message,
                "Hệ thống quản lý bệnh nhân", JOptionPane.INFORMATION_MESSAGE);
    }
}
