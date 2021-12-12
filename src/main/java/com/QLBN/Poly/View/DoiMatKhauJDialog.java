package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Entity.NhanVien;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XImages;
import com.QLBN.Poly.Utils.XOther;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DoiMatKhauJDialog extends javax.swing.JDialog {

    int checkLength = 0;

    NhanVienServiceImp nvsi = new NhanVienServiceImp();

    public DoiMatKhauJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    void init() {
        this.setIconImage(XImages.getAppIcon());
        this.setTitle("Bệnh Viện Gia Định");
        this.setLocationRelativeTo(null);
        this.getImg();
        if (Auth.user == null) {
            jLabelMatKhauCu.setVisible(false);
            txtMatKhauCu.setVisible(false);
        } else {
            jLabelMatKhauCu.setVisible(true);
            txtMatKhauCu.setVisible(true);
        }
    }

    void setTenTaiKhoan(String tenTk) {
        txtMaNV.setText(tenTk);
    }

    void disableBtnResendCode() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                btnGuiLai.setEnabled(false);
                for (int i = 60; i >= 0; i--) {
                    try {
                        lblTrangThai.setText(i + " Giây");
                        Thread.sleep(1000);
                        if (i == 0) {
                            btnGuiLai.setEnabled(true);
                            lblTrangThai.setText("");
                            XOther.IDVerify = "";
                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Error getCode: " + ex.getMessage());
                    }
                }
            }
        };
        thread.start();
    }

    boolean checkForm() {
        String[] nameError = {jLabelMauKhauMoi.getText(),
            jLabelMatKhauMoi2.getText(), jLabelMaXacNhan.getText()};
        String[] title = {
            new String(txtMatKhauMoi.getPassword()), new String(txtMatKhauMoi2.getPassword()),
            txtMaXacNhan.getText()};
        String mess = "";
        for (int i = 0; i < title.length; i++) {
            if (title[i].isEmpty()) {
                mess += nameError[i] + ", ";
            }
        }
        if (!mess.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + mess.substring(0, mess.lastIndexOf(", ")));
            return false;
        } else if (new String(txtMatKhauCu.getPassword()).isEmpty() && Auth.user != null) {
            MsgBox.alert(this, "Vui lòng nhập " + jLabelMatKhauCu.getText());
            return false;
        } else if (!XOther.convertPass(new String(txtMatKhauCu.getPassword()), nvsi.selectByID(txtMaNV.getText()).getMatKhau())
                && Auth.user != null) {
            MsgBox.alert(this, "Sai mật khẩu!");
            return false;
        } else if (checkLength == 2) {
            MsgBox.alert(this, "Mật khẩu quá yếu!");
            return false;
        } else if (XOther.convertPass(new String(txtMatKhauMoi.getPassword()), nvsi.selectByID(txtMaNV.getText()).getMatKhau())
                && Auth.user != null) {
            MsgBox.alert(this, "Không được đặt mật khẩu trùng với mật khẩu cũ!");
            return false;
        } else if (!new String(txtMatKhauMoi.getPassword()).equals(new String(txtMatKhauMoi2.getPassword()))) {
            MsgBox.alert(this, "Mật khẩu không khớp!");
            return false;
        } else if (!txtMaXacNhan.getText().equals(XOther.IDVerify)) {
            MsgBox.alert(this, "Mã xác nhận không đúng");
            return false;
        } else {
            return true;
        }
    }

    boolean checkPassword() {
        String input = new String(txtMatKhauMoi.getPassword());
        switch (input) {
            case "123456":
                return true;
            case "0123456":
                return true;
            case "654321":
                return true;
            case "6543210":
                return true;
            case "0123456789":
                return true;
            case "123456789":
                return true;
            case "987654321":
                return true;
            case "9876543210":
                return true;
            default:
                return false;
        }
    }

    void doiMatKhau() {
        if (checkForm()) {
            String matKhauMoi = new String(txtMatKhauMoi.getPassword());
            String matKhauMoiCu = new String(txtMatKhauCu.getPassword());
            NhanVien n = nvsi.selectByID(txtMaNV.getText());
            String mk = n.getMatKhau();
            n.setMatKhau(XOther.MaHoaPass(matKhauMoi));

            if (nvsi.update(n) == 1) {
                System.out.println(n.getMatKhau());
                ManagerSecurityDAO.list.add(new ManagerSecurity(n.getMaNV(),
                        txtMatKhauCu.isVisible() ? XOther.MaHoaPass(matKhauMoiCu) : mk,
                        XOther.MaHoaPass(matKhauMoi), n.getEmail(), new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\ThayDoiMK.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\ThayDoiMK.txt");
                ManagerSecurityDAO.list.clear();
                MsgBox.alert(this, "Đổi mật khẩu thành công!");

                this.dispose();
            }
        }
    }

    void ketThuc() {
        if (MsgBox.confirm(this, "Bạn muốn kết thúc làm việc?")) {
            System.exit(0);
        }
    }

    void guiMaCode() {
        NhanVien nv = nvsi.selectByID(txtMaNV.getText());
        lblTrangThai.setEnabled(true);
        disableBtnResendCode();
        XOther.sendCodeConfirm(this, nv.getEmail(), nv.getTenNV().substring(nv.getTenNV().lastIndexOf(" ") + 1));
    }

    private void getImg() {
        URL url = getClass().getResource("/Images/hinhDong.gif");
        Icon icon = new ImageIcon(url);
        JLabel jLabel = new JLabel(icon);
        jLabel.setSize(new Dimension(jPanel1.getWidth(), jPanel1.getHeight()));
        jPanel1.add(jLabel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabelMauKhauMoi = new javax.swing.JLabel();
        txtMatKhauMoi = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        lblLevel_2 = new javax.swing.JLabel();
        lblLevel_1 = new javax.swing.JLabel();
        lblLevel_3 = new javax.swing.JLabel();
        jLabelMatKhauCu = new javax.swing.JLabel();
        jLabelMatKhauMoi2 = new javax.swing.JLabel();
        txtMatKhauCu = new javax.swing.JPasswordField();
        txtMatKhauMoi2 = new javax.swing.JPasswordField();
        jLabelMaXacNhan = new javax.swing.JLabel();
        txtMaXacNhan = new javax.swing.JTextField();
        btnGuiLai = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnThayDoi = new javax.swing.JButton();
        lblTrangThai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(419, 353));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THAY ĐỔI MẬT KHẨU");

        jLabelMaNV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMaNV.setText("Mã Nhân Viên");

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNV.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelMauKhauMoi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMauKhauMoi.setText("Mật Khẩu Mới");

        txtMatKhauMoi.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtMatKhauMoi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtMatKhauMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauMoiKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblLevel_2.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_2.setOpaque(true);

        lblLevel_1.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_1.setOpaque(true);

        lblLevel_3.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_3.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(lblLevel_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblLevel_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblLevel_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel_1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevel_2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevel_3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jLabelMatKhauCu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMatKhauCu.setText("Mật Khẩu Cũ");

        jLabelMatKhauMoi2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMatKhauMoi2.setText("Nhập Lại Mật Khẩu");

        txtMatKhauCu.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        txtMatKhauMoi2.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        jLabelMaXacNhan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMaXacNhan.setText("Mã Xác Nhận");

        txtMaXacNhan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        btnGuiLai.setBackground(new java.awt.Color(64, 148, 94));
        btnGuiLai.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuiLai.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiLai.setText("Gửi Lại");
        btnGuiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiLaiActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(64, 148, 94));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnThayDoi.setBackground(new java.awt.Color(64, 148, 94));
        btnThayDoi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThayDoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThayDoi.setText("Thay Đổi");
        btnThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiActionPerformed(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhauMoi2)
                    .addComponent(txtMatKhauCu)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNV)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMatKhauMoi)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMaNV)
                            .addComponent(jLabelMatKhauCu)
                            .addComponent(jLabelMauKhauMoi)
                            .addComponent(jLabelMatKhauMoi2)
                            .addComponent(jLabelMaXacNhan)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuiLai)))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnThayDoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jLabelMaNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelMatKhauCu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelMauKhauMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jLabelMatKhauMoi2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhauMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelMaXacNhan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaXacNhan)
                    .addComponent(btnGuiLai, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnThayDoi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatKhauMoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauMoiKeyReleased
        String length = txtMatKhauMoi.getText();
        if (length.length() != 0) {
            if ((length.length() > 0 && length.length() <= 3) || checkPassword() == true
                    || !length.matches("[A-Z][a-z]+[0-9]+(\\w)*")) {
                lblLevel_1.setOpaque(true);
                lblLevel_2.setOpaque(false);
                lblLevel_3.setOpaque(false);

                lblLevel_1.setBackground(Color.RED);
                lblLevel_2.setBackground(new Color(153, 204, 255));
                lblLevel_3.setBackground(new Color(153, 204, 255));
                checkLength = 2;
            } else if (length.length() > 3 && length.length() <= 9) {
                lblLevel_1.setOpaque(true);
                lblLevel_2.setOpaque(true);
                lblLevel_3.setOpaque(false);

                lblLevel_1.setBackground(new Color(255, 255, 0));
                lblLevel_2.setBackground(new Color(255, 255, 0));
                lblLevel_3.setBackground(new Color(153, 204, 255));
                checkLength = 0;
            } else {
                lblLevel_1.setOpaque(true);
                lblLevel_2.setOpaque(true);
                lblLevel_3.setOpaque(true);

                lblLevel_1.setBackground(new Color(0, 179, 89));
                lblLevel_2.setBackground(new Color(0, 179, 89));
                lblLevel_3.setBackground(new Color(0, 179, 89));
                checkLength = 0;
            }
        } else {
            lblLevel_1.setOpaque(false);
            lblLevel_2.setOpaque(false);
            lblLevel_3.setOpaque(false);

            lblLevel_1.setBackground(new Color(153, 204, 255));
            lblLevel_2.setBackground(new Color(153, 204, 255));
            lblLevel_3.setBackground(new Color(153, 204, 255));
        }
    }//GEN-LAST:event_txtMatKhauMoiKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ketThuc();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGuiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiLaiActionPerformed
        XOther.IDVerify = null;
        NhanVien nv = nvsi.selectByID(txtMaNV.getText());
        lblTrangThai.setEnabled(true);
        disableBtnResendCode();
        XOther.sendCodeConfirm(this, nv.getEmail(), nv.getTenNV().substring(nv.getTenNV().lastIndexOf(" ") + 1));
    }//GEN-LAST:event_btnGuiLaiActionPerformed

    private void btnThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiActionPerformed
        doiMatKhau();
    }//GEN-LAST:event_btnThayDoiActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DoiMatKhauJDialog dialog = new DoiMatKhauJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuiLai;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelMaNV;
    private javax.swing.JLabel jLabelMaXacNhan;
    private javax.swing.JLabel jLabelMatKhauCu;
    private javax.swing.JLabel jLabelMatKhauMoi2;
    private javax.swing.JLabel jLabelMauKhauMoi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblLevel_1;
    private javax.swing.JLabel lblLevel_2;
    private javax.swing.JLabel lblLevel_3;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaXacNhan;
    private javax.swing.JPasswordField txtMatKhauCu;
    private javax.swing.JPasswordField txtMatKhauMoi;
    private javax.swing.JPasswordField txtMatKhauMoi2;
    // End of variables declaration//GEN-END:variables
}
