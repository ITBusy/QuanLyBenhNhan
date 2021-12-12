package com.QLBN.Poly.View;

import com.QLBN.Poly.Entity.BienLai;
import com.QLBN.Poly.Utils.XOther;
import java.awt.print.PrinterException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintBienLai extends javax.swing.JDialog {

    public PrintBienLai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    void getData(List<BienLai> l, double tongTien, String tenBN) {
        txtPrint.setText("\n\t                          BIÊN LAI CHI PHÍ                    \n\n");
        txtPrint.setText(txtPrint.getText() + "  Tên Bệnh Nhân:  " + tenBN + "\n\n");
        txtPrint.setText(txtPrint.getText() + "-------------------------------------------------------------------------------------------------\n");
        txtPrint.setText(txtPrint.getText() + "     Khoản Chi Phí\t     |                     Thành Tiền               \n");
        txtPrint.setText(txtPrint.getText() + "-------------------------------------------------------------------------------------------------\n");
        for (BienLai b : l) {
            txtPrint.setText(txtPrint.getText() + "      " + b.getTenKhoanChiPhi() + "\t\t     |                     " + XOther.convertCurrency(b.getTienChiPhi()) + " VND\n");
            txtPrint.setText(txtPrint.getText() + "-------------------------------------------------------------------------------------------------\n");
        }
        txtPrint.setText(txtPrint.getText() + "\nTổng Tiền:  " + XOther.convertCurrency(tongTien).toUpperCase() + " VND\n");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPrint = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(64, 148, 94));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hủy");

        btnPrint.setBackground(new java.awt.Color(64, 148, 94));
        btnPrint.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("In Phiếu");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        txtPrint.setEditable(false);
        txtPrint.setColumns(20);
        txtPrint.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        txtPrint.setRows(5);
        jScrollPane1.setViewportView(txtPrint);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnPrint))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            txtPrint.print();
        } catch (PrinterException ex) {
            Logger.getLogger(PrintBienLai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

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
            java.util.logging.Logger.getLogger(PrintBienLai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrintBienLai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrintBienLai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrintBienLai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PrintBienLai dialog = new PrintBienLai(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtPrint;
    // End of variables declaration//GEN-END:variables
}
