package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Entity.NhanVien;
import com.QLBN.Poly.Entity.RememberMe;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XImages;
import com.QLBN.Poly.Utils.XOther;
import com.QLBN.Poly.Utils.XRemember;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class ManHinhDangNhap extends javax.swing.JFrame {

    Point point = new Point();
    NhanVienServiceImp nv;

    public ManHinhDangNhap() {
        initComponents();
        nv = new NhanVienServiceImp();
        init();
    }

    void init() {
        try {
            setIconImage(XImages.getAppIcon());
            this.setLocationRelativeTo(null);
            String ip = Inet4Address.getLocalHost().getHostAddress();
            for (RememberMe n : XRemember.setNhanVienFile()) {
                if (n.isRemember() && n.getIpCoputer().equals(ip)) {
                    txtMaNV.setText(n.getTenTk());
                    txtMatKhau.setText(n.getMaKhau());
                    jCheckBoxRememberMe.setSelected(n.isRemember());
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ManHinhDangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean checkForm() {
        String[] nameError = {jLabelTenTaiKhoan.getText(), jLabelMatKhau.getText()};
        String[] title = {txtMaNV.getText(), new String(txtMatKhau.getPassword())};
        String mess = "";
        for (int j = 0; j < title.length; j++) {
            if (title[j].isEmpty()) {
                mess += nameError[j] + ", ";
            }
        }
        if (!mess.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + mess.substring(0, mess.lastIndexOf(", ")));
            return false;
        } else {
            return true;
        }
    }

    void DoiMatKhau() {
        if (txtMaNV.getText().isEmpty()) {

        } else if (nv.selectByID(txtMaNV.getText()) == null) {
            MsgBox.alert(this, "Tài khoản không tồn tại");
        } else {
            DoiMatKhauJDialog doiMatKhauJDialog = new DoiMatKhauJDialog(this, rootPaneCheckingEnabled);
            doiMatKhauJDialog.init();
            doiMatKhauJDialog.setTenTaiKhoan(txtMaNV.getText());
//            doiMatKhauJDialog.guiMaCode();
            doiMatKhauJDialog.setVisible(true);
        }
    }

    void dangNhap() {
        if (checkForm()) {
            String manv = txtMaNV.getText();
            String matKhau = new String(txtMatKhau.getPassword());
            NhanVien nhanVien = nv.selectByID(manv);

            if (nhanVien == null) {//ko tìm thấy user name 
                MsgBox.alert(this, "Sai tên đăng nhập!");
            } else if (!XOther.convertPass(matKhau, nhanVien.getMatKhau())) {
                MsgBox.alert(this, "Sai mật khẩu!");
            } else if (!nhanVien.isActive()) {
                MsgBox.alert(this, "Tài khoản của bạn đã bị khóa");
            } else {
                if (jCheckBoxRememberMe.isSelected()) {
                    try {
                        RememberMe me = new RememberMe();
                        me.setRemember(jCheckBoxRememberMe.isSelected());
                        String ip = Inet4Address.getLocalHost().getHostAddress();
                        XRemember.li.add(new RememberMe(nhanVien.getMaNV(), matKhau, me.isRemember(), ip));
                        XRemember.saveNhanVienFile(XRemember.li);
                        XRemember.li.clear();
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(ManHinhDangNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    XRemember.setNhanVienFile().forEach(r -> {
                        if (r.getTenTk().equals(manv)) {
                            XRemember.saveNhanVienFile(XRemember.li);
                            XRemember.li.clear();
                        }
                    });
                }
                Auth.user = nhanVien;
                ManagerSecurityDAO.list.add(new ManagerSecurity(nhanVien.getMaNV(), nhanVien.getMatKhau(), new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\DangNhap.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\DangNhap.txt");
                ManagerSecurityDAO.list.clear();
                this.dispose();
                new ManHinhChinh().setVisible(true);
            }
        }
    }

    void ketThuc() {
        if (MsgBox.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")) {
            System.exit(0);
        }
    }

    void getKeyCode(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER: {
                dangNhap();
                break;
            }
            case KeyEvent.VK_HOME: {
                ketThuc();
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelTenTaiKhoan = new javax.swing.JLabel();
        jLabelMatKhau = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jCheckBox_Eye = new javax.swing.JCheckBox();
        jCheckBoxRememberMe = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanelTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/Images/Logo01Small.png")).getImage());
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(64, 148, 94));
        jPanel1.setPreferredSize(new java.awt.Dimension(562, 223));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/security.jpg"))); // NOI18N
        jLabel2.setOpaque(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTenTaiKhoan.setBackground(new java.awt.Color(255, 204, 204));
        jLabelTenTaiKhoan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelTenTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user_1.png"))); // NOI18N
        jLabelTenTaiKhoan.setText("Tên Tài Khoản");

        jLabelMatKhau.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/password.png"))); // NOI18N
        jLabelMatKhau.setText("Mật Khẩu");

        txtMaNV.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMaNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaNVKeyReleased(evt);
            }
        });

        txtMatKhau.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(51, 102, 255));
        txtMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(204, 204, 204)));
        txtMatKhau.setEchoChar('\uf0b5');
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhautxtMaNVKeyReleased(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(64, 148, 94));
        btnLogin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/enter_80px.png"))); // NOI18N
        btnLogin.setText("Đăng Nhập");
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(204, 204, 204));
        btnCancel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cancel_32px.png"))); // NOI18N
        btnCancel.setText("Kết Thúc");
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jCheckBox_Eye.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_Eye.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)));
        jCheckBox_Eye.setBorderPainted(true);
        jCheckBox_Eye.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox_Eye.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox_Eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eye_hide.png"))); // NOI18N
        jCheckBox_Eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox_EyeMouseClicked(evt);
            }
        });

        jCheckBoxRememberMe.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxRememberMe.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCheckBoxRememberMe.setText("Ghi Nhớ Tài Khoản?");
        jCheckBoxRememberMe.setFocusPainted(false);
        jCheckBoxRememberMe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCheckBoxRememberMetxtMaNVKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Quên Mật Khẩu");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTenTaiKhoan)
                    .addComponent(jLabelMatKhau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCheckBoxRememberMe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtMatKhau)
                        .addGap(0, 0, 0)
                        .addComponent(jCheckBox_Eye, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaNV))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addGap(54, 54, 54))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTenTaiKhoan))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_Eye, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelMatKhau)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxRememberMe)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnLogin))
                .addContainerGap())
        );

        jPanelTitle.setOpaque(false);
        jPanelTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelTitleMouseDragged(evt);
            }
        });
        jPanelTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelTitleMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HỆ THỐNG QUẢN LÝ BỆNH VIỆN");

        jLabel3.setBackground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete_white.png"))); // NOI18N
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTitleLayout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(129, 129, 129)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addGroup(jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNVKeyReleased
        getKeyCode(evt);
    }//GEN-LAST:event_txtMaNVKeyReleased

    private void txtMatKhautxtMaNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhautxtMaNVKeyReleased
        getKeyCode(evt);
    }//GEN-LAST:event_txtMatKhautxtMaNVKeyReleased

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        dangNhap();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        ketThuc();
    }//GEN-LAST:event_btnCancelActionPerformed
    int i = 0;
    private void jCheckBox_EyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox_EyeMouseClicked
        i++;
        if (i % 2 == 0) {
            jCheckBox_Eye.setIcon(new ImageIcon("E://Java//QuanLyBenhNhan//src//main//resources//Images//eye_hide.png"));
            jCheckBox_Eye.setOpaque(true);
            txtMatKhau.setEchoChar('\uf0b5');
            txtMatKhau.requestFocus();
        } else {
            jCheckBox_Eye.setIcon(new ImageIcon("E://Java//QuanLyBenhNhan//src//main//resources//Images//eye_open.png"));
            jCheckBox_Eye.setOpaque(true);
            txtMatKhau.setEchoChar((char) 0);
            txtMatKhau.requestFocus();
        }
    }//GEN-LAST:event_jCheckBox_EyeMouseClicked

    private void jCheckBoxRememberMetxtMaNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBoxRememberMetxtMaNVKeyReleased
        getKeyCode(evt);
    }//GEN-LAST:event_jCheckBoxRememberMetxtMaNVKeyReleased

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // the clicked will program
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanelTitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTitleMouseDragged
        this.setLocation(evt.getXOnScreen() - point.x, evt.getYOnScreen() - point.y);
    }//GEN-LAST:event_jPanelTitleMouseDragged

    private void jPanelTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTitleMousePressed
        point.x = evt.getX();
        point.y = evt.getY();
    }//GEN-LAST:event_jPanelTitleMousePressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        DoiMatKhau();
    }//GEN-LAST:event_jLabel4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
 /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManHinhDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox jCheckBoxRememberMe;
    private javax.swing.JCheckBox jCheckBox_Eye;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelMatKhau;
    private javax.swing.JLabel jLabelTenTaiKhoan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelTitle;
    public javax.swing.JTextField txtMaNV;
    public javax.swing.JPasswordField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
