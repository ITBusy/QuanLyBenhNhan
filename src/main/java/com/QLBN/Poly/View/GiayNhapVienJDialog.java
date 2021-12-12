package com.QLBN.Poly.View;

import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XImages;
import java.awt.print.PrinterException;
import java.util.Date;

public class GiayNhapVienJDialog extends javax.swing.JDialog {

    public GiayNhapVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    void init(String hoTenBN, Date NgSinh, String gioiTinh, String BHYT, String NgayNhapVien,
            String chuanDoan, String TenBacSy) {
        int a = 0;
        String Tuoi;
        if (XDate.getYear(NgSinh) == XDate.getYear(new Date())) {
            if (XDate.getMonth(NgSinh) == XDate.getMonth(new Date())) {
                a = XDate.getDay(new Date()) - XDate.getDay(NgSinh);
                Tuoi = a + " Ngày";
            } else {
                a = XDate.getMonth(new Date()) - XDate.getMonth(NgSinh);
                Tuoi = a + " Tháng";
            }
        } else {
            a = XDate.getYear(new Date()) - XDate.getYear(NgSinh);
            Tuoi = a + "";
        }
        this.setTitle("BỆNH VIỆN GIA ĐỊNH");
        this.setIconImage(XImages.getAppIcon());
        this.setLocationRelativeTo(null);
        jTextArea1.setText(" SỞ Y TẾ:… QUẬN 1….\t\t\tCỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM\n"
                + " BỆNH VIÊN: ….GIA ĐỊNH….\t\t\t            Độc lập - Tự do - Hạnh phúc\n"
                + "\t\t\t\t                 ----------------------------\n"
                + "\n"
                + "\t\t\tGIẤY NHẬP VIỆN\n"
                + "\n\n"
                + " Họ tên bệnh nhân:     " + hoTenBN + "\t Tuổi:    " + Tuoi + "    Giới tính:   " + gioiTinh + "\n\n"
                + " BHYT số: " + BHYT + " \n\n"
                + " Ngày vào viện:     " + NgayNhapVien + "\n\n"
                + " Chuẩn đoán:     " + chuanDoan + "\n\n\n"
                + "\t\t\t\tNgày      " + XDate.getDay(XDate.toDate(NgayNhapVien, "dd/MM/yyyy")) + "    Tháng    " + XDate.getMonth(XDate.toDate(NgayNhapVien, "dd/MM/yyyy")) + "   Năm   " + XDate.getYear(XDate.toDate(NgayNhapVien, "dd/MM/yyyy")) + " \n\n"
                + "\t\t\t\t\tBÁC SỸ\n"
                + "\n"
                + "\n"
                + "\n"
                + "                      \n"
                + "\t\t\t\tHọ Tên: \t" + TenBacSy);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(64, 148, 94));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hủy");

        jButton2.setBackground(new java.awt.Color(64, 148, 94));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            jTextArea1.print();
        } catch (PrinterException e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(GiayNhapVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiayNhapVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiayNhapVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiayNhapVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GiayNhapVienJDialog dialog = new GiayNhapVienJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
