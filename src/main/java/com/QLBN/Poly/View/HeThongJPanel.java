package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Entity.NhanVien;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XDate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HeThongJPanel extends javax.swing.JPanel {

    public HeThongJPanel() {
        initComponents();
    }

    void init() {
        FillDataCombobox();
        FillDataTableDangNhap();
        FillDataTableThayDoiMK();
        FillDataTableTacVu(cbbBang.getSelectedItem().toString());
        FillDataTableMoTK();
    }

    void FillDataCombobox() {
        List<String> lis = new ArrayList<>();
        lis.add("Tất Cả");
        ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt").forEach(m -> {
            lis.add(m.getBang());
        });
        List<String> filter = new ArrayList<>();
        for (int i = 0; i < lis.size(); i++) {
            if (!filter.contains(lis.get(i))) {
                filter.add(lis.get(i));
            }
        }
        lis.clear();
        lis.addAll(filter);
        filter.clear();
        lis.forEach(m -> {
            cbbBang.addItem(m);
        });
        cbbBang.setSelectedIndex(0);
    }

    void FillDataTableDangNhap() {
        String[] column = {"STT", "Tên Tài Khoản", "Mật Khẩu", "Thời Gian"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tblDangNhap.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblDangNhap.getModel();
        model.setRowCount(0);
        int i = 0;
        for (ManagerSecurity m : ManagerSecurityDAO.OpenFileRestore("\\DangNhap.txt")) {
            Object[] rowData = new Object[4];
            rowData[0] = i + 1;
            rowData[1] = m.getMaNV();
            rowData[2] = m.getMatKhau();
            rowData[3] = XDate.toString(m.getThoiGian(), "dd/MM/yyyy HH:mm:ss");
            i++;
            model.addRow(rowData);
        }

        tblDangNhap.getTableHeader().setPreferredSize(new Dimension(100, 40));
        tblDangNhap.getTableHeader().setBackground(new Color(64, 148, 94));
        tblDangNhap.getTableHeader().setForeground(new Color(255, 255, 255));
        tblDangNhap.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblDangNhap.setRowHeight(25);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblDangNhap.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

        tblDangNhap.getColumnModel().getColumn(0).setMinWidth(50);
        tblDangNhap.getColumnModel().getColumn(0).setMaxWidth(50);
        tblDangNhap.getColumnModel().getColumn(0).setPreferredWidth(50);

    }

    void FillDataTableThayDoiMK() {
        String[] column = {"STT", "Tên Tài Khoản", "Mật Khẩu Cũ", "Mật Khẩu Mới", "Email", "Thời Gian"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tblThayDoiMK.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblThayDoiMK.getModel();
        model.setRowCount(0);
        int i = 0;
        for (ManagerSecurity m : ManagerSecurityDAO.OpenFileRestore("\\ThayDoiMK.txt")) {
            Object[] rowData = new Object[6];
            rowData[0] = i + 1;
            rowData[1] = m.getMaNV();
            rowData[2] = m.getMatKhau();
            rowData[3] = m.getMatKhauCu();
            rowData[4] = m.getEmail();
            rowData[5] = XDate.toString(m.getThoiGian(), "dd/MM/yyyy HH:mm:ss");
            i++;
            model.addRow(rowData);
        }
        tblThayDoiMK.getTableHeader().setPreferredSize(new Dimension(100, 40));
        tblThayDoiMK.getTableHeader().setBackground(new Color(64, 148, 94));
        tblThayDoiMK.getTableHeader().setForeground(new Color(255, 255, 255));
        tblThayDoiMK.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblThayDoiMK.setRowHeight(25);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblThayDoiMK.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

        tblThayDoiMK.getColumnModel().getColumn(0).setMinWidth(50);
        tblThayDoiMK.getColumnModel().getColumn(0).setMaxWidth(50);
        tblThayDoiMK.getColumnModel().getColumn(0).setPreferredWidth(50);
    }

    void FillDataTableTacVu(String Bang) {
        String[] column = {"STT", "Tên TK", "Người TH", "Chức Vụ", "Bảng TĐ", "ID TĐ", "Nội Dung", "Thời Gian"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tblTacVu.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblTacVu.getModel();
        model.setRowCount(0);
        int i = 0;
        for (ManagerSecurity m : ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt")) {
            Object[] rowData = new Object[8];
            if ("Tất Cả".equals(Bang)) {
                rowData[0] = i + 1;
                rowData[1] = m.getMaNV();
                rowData[2] = m.getNguoiThucHien();
                rowData[3] = m.getChucVu();
                rowData[4] = m.getBang();
                rowData[5] = m.getID_Bang();
                rowData[6] = m.getNoiDung();
                rowData[7] = XDate.toString(m.getThoiGian(), "dd/MM/yyyy HH:mm:ss");
                i++;
                model.addRow(rowData);
            } else if (m.getBang().equals(Bang)) {
                rowData[0] = i + 1;
                rowData[1] = m.getMaNV();
                rowData[2] = m.getNguoiThucHien();
                rowData[3] = m.getChucVu();
                rowData[4] = m.getBang();
                rowData[5] = m.getID_Bang();
                rowData[6] = m.getNoiDung();
                rowData[7] = XDate.toString(m.getThoiGian(), "dd/MM/yyyy HH:mm:ss");
                i++;
                model.addRow(rowData);
            }
        }
        tblTacVu.getTableHeader().setPreferredSize(new Dimension(100, 40));
        tblTacVu.getTableHeader().setBackground(new Color(64, 148, 94));
        tblTacVu.getTableHeader().setForeground(new Color(255, 255, 255));
        tblTacVu.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblTacVu.setRowHeight(25);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblTacVu.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

        tblTacVu.getColumnModel().getColumn(0).setMinWidth(50);
        tblTacVu.getColumnModel().getColumn(0).setMaxWidth(50);
        tblTacVu.getColumnModel().getColumn(0).setPreferredWidth(50);
    }

    void FillDataTableMoTK() {
        String[] columnAdmin = {"STT", "Mã NV", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Điện Thoại", "Email", "Giới Tính", "Mở Khóa"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnAdmin, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 8 ? Boolean.class : String.class;
            }
        };
        boolean isChecked = false;
        int i = 0;
        tblMoTK.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblMoTK.getModel();
        model.setRowCount(0);
        for (NhanVien nv : new NhanVienServiceImp().taiKhoanBiKhoa()) {
            model.addRow(new Object[]{
                i + 1,
                nv.getMaNV(),
                nv.getTenNV(),
                XDate.toString(nv.getNgaysinhNV(), "dd/MM/yyyy"),
                nv.getDiachi(),
                nv.getSodienthoai(),
                nv.getEmail(),
                nv.getGioitinh(),
                isChecked

            });
            i++;
        }

        tblMoTK.getTableHeader().setPreferredSize(new Dimension(100, 40));
        tblMoTK.getTableHeader().setBackground(new Color(64, 148, 94));
        tblMoTK.getTableHeader().setForeground(new Color(255, 255, 255));
        tblMoTK.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblMoTK.setRowHeight(25);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblMoTK.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

        tblMoTK.getColumnModel().getColumn(0).setMinWidth(50);
        tblMoTK.getColumnModel().getColumn(0).setMaxWidth(50);
        tblMoTK.getColumnModel().getColumn(0).setPreferredWidth(50);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDangNhap = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThayDoiMK = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTacVu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbbBang = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMoTK = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)));

        tblDangNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblDangNhap);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đăng Nhập", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)));

        tblThayDoiMK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblThayDoiMK);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quên Mật Khẩu", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)));

        tblTacVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTacVu);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Bảng");

        cbbBang.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbBang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbBangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbBang, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbBang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tác Vụ", jPanel3);

        tblMoTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMoTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMoTKMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMoTK);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Mở Tài Khoản", jPanel4);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ HỆ THỐNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbBangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbBangActionPerformed
        FillDataTableTacVu(cbbBang.getSelectedItem().toString());
    }//GEN-LAST:event_cbbBangActionPerformed

    private void tblMoTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMoTKMouseClicked
        int row = tblMoTK.getSelectedRow();
        if (MsgBox.confirm(this, "Bạn có muốn khóa tài khoản này không")) {
            NhanVien n = new NhanVien();
            n.setActive(true);
            n.setMaNV((String) tblMoTK.getValueAt(row, 1));
            new NhanVienServiceImp().updateActive(n);
            this.FillDataTableMoTK();
        } else {
            tblMoTK.setValueAt(false, row, 9);
        }
    }//GEN-LAST:event_tblMoTKMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbBang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDangNhap;
    private javax.swing.JTable tblMoTK;
    private javax.swing.JTable tblTacVu;
    private javax.swing.JTable tblThayDoiMK;
    // End of variables declaration//GEN-END:variables
}
