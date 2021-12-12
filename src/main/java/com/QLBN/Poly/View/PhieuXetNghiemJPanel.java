package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Entity.PhieuXetNghiem;
import com.QLBN.Poly.Entity.XetNghiem;
import com.QLBN.Poly.Service.BenhNhanServiceImp;
import com.QLBN.Poly.Service.HoSoBenhAnServiceImp;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Service.PhieuXetNghiemServiceImp;
import com.QLBN.Poly.Service.XetNghiemServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.XAutoCompletion;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuXetNghiemJPanel extends javax.swing.JPanel {

    BenhNhanServiceImp bnsi = new BenhNhanServiceImp();
    PhieuXetNghiemServiceImp pxnsi = new PhieuXetNghiemServiceImp();
    NhanVienServiceImp nvsi = new NhanVienServiceImp();
    XetNghiemServiceImp xnsi = new XetNghiemServiceImp();
    int index = -1;
    List<PhieuXetNghiem> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();

    public PhieuXetNghiemJPanel() {
        initComponents();
    }

    void init() {
        this.FillDataComboboxBenhNhan();
        this.FillDataComboboxXetNghiem();
        this.FillDataTable(pxnsi.getAll());
        txtTenBacSy.setText(Auth.user.getTenNV());
        this.clear();
        this.setStatus(true);
        if (Auth.isManager()) {
            ckbSelectAll.setVisible(true);
        } else {
            ckbSelectAll.setVisible(false);
        }
        btnRestore.setVisible(false);
        btnDel.setVisible(false);
    }

    void FillDataComboboxBenhNhan() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenBN.getModel();
        model.removeAllElements();
        if (!bnsi.getAll().isEmpty()) {
            bnsi.getAll().forEach(bn -> {
                model.addElement(bn);
            });
            cbbTenBN.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenBN);
        }
    }

    void FillDataComboboxXetNghiem() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenXN.getModel();
        model.removeAllElements();
        if (!xnsi.getAll().isEmpty()) {
            xnsi.getAll().forEach(b -> {
                model.addElement(b);
            });
            cbbTenXN.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenXN);
        }
    }

    void FillDataTable(List<PhieuXetNghiem> l) {
        String[] columnAdmin = {"STT", "Mã Phiếu XN", "Mã XN", "Mã BN", "Ngày XN", "Kết Quả", "Lý Do",
            "Mã BS", "Xóa"};
        String[] column = {"STT", "Mã Phiếu", "Mã XN", "Mã BN", "Ngày XN", "Kết Quả", "Lý Do",
            "Mã BS"};
        DefaultTableModel dtm = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
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
        tblPhieuXetNghiem.setModel(dtm);
        DefaultTableModel model = (DefaultTableModel) tblPhieuXetNghiem.getModel();
        model.setRowCount(0);
        for (PhieuXetNghiem pt : l) {
            Object[] rowData = new Object[10];
            rowData[0] = i + 1;
            rowData[1] = pt.getSphieuXN();
            rowData[2] = pt.getMaXN();
            rowData[3] = pt.getMaBN();
            rowData[4] = XDate.toString(pt.getNgayXN(), "dd/MM/yyyy HH:mm:ss");
            rowData[5] = pt.getKquaXN();
            rowData[6] = pt.getLidoXN();
            rowData[7] = pt.getNguoiYeuCau();
            rowData[8] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblPhieuXetNghiem.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblPhieuXetNghiem.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
    }

    void clear() {
        txtTenBacSy.setToolTipText("");
        if (cbbTenXN.getSelectedItem() != null) {
            cbbTenXN.setSelectedIndex(0);
        }
        if (cbbTenBN.getSelectedItem() != null) {
            cbbTenBN.setSelectedIndex(0);
        }
        txtTenBacSy.setText(Auth.user.getTenNV());
        txtKetQua.setText("");
        txtNgayXN.setText(XDate.toString(new Date(), "dd/MM/yyyy HH:mm:ss"));
        txtLyDo.setText("");
        this.setStatus(true);
    }

    boolean checkForm() {
        if (txtKetQua.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập kết quả");
            return false;
        } else if (txtLyDo.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập lý do");
            return false;
        } else if (txtKetQua.getText().matches("^([0-9])*$")) {
            MsgBox.alert(this, "Kết quả phải là số");
            return false;
        } else if (txtLyDo.getText().matches("^([0-9])*$")) {
            MsgBox.alert(this, "Lý do phải là số");
            return false;
        } else {
            return true;
        }
    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnPrint.setEnabled(!insertable);
        cbbTenBN.setEnabled(insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblPhieuXetNghiem.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void setModel(PhieuXetNghiem pt) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenXN.getModel();
        DefaultComboBoxModel modelBN = (DefaultComboBoxModel) cbbTenBN.getModel();

        modelBN.setSelectedItem(bnsi.selectByID(pt.getMaBN()));
        model.setSelectedItem(xnsi.selectByID(pt.getMaXN()));

        txtTenBacSy.setToolTipText(String.valueOf(pt.getSphieuXN()));
        txtNgayXN.setText(XDate.toString(pt.getNgayXN(), "dd/MM/yyyy HH:mm:ss"));
        txtTenBacSy.setText(nvsi.selectByID(pt.getNguoiYeuCau()).getTenNV());
        cbbTenXN.setSelectedItem(model.getSelectedItem());
        cbbTenBN.setSelectedItem(modelBN.getSelectedItem());
        txtKetQua.setText(pt.getKquaXN() + "");
        txtLyDo.setText(pt.getLidoXN());
    }

    PhieuXetNghiem getModel() {
        PhieuXetNghiem pt = new PhieuXetNghiem();
        XetNghiem k = (XetNghiem) cbbTenXN.getSelectedItem();
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        if (!txtTenBacSy.getToolTipText().isEmpty() || txtTenBacSy.getToolTipText() != null) {
            pt.setSphieuXN(Integer.parseInt(txtTenBacSy.getToolTipText()));
        }
        pt.setNgayXN(XDate.toDate(txtNgayXN.getText(), "dd/MM/yyyy HH:mm:ss"));
        pt.setNguoiYeuCau(Auth.user.getMaNV());
        pt.setMaXN(k.getMaXN());
        pt.setMaBN(bn.getMaBN());
        pt.setKquaXN(txtKetQua.getText());
        pt.setLidoXN(txtLyDo.getText());
        return pt;
    }

    void edit() {
        int id = (int) tblPhieuXetNghiem.getValueAt(index, 1);
        PhieuXetNghiem pt = pxnsi.selectByID(id);
        if (pt != null) {
            this.setModel(pt);
            this.setStatus(false);
            tblPhieuXetNghiem.setRowSelectionInterval(index, index);
            tblPhieuXetNghiem.scrollRectToVisible(tblPhieuXetNghiem.getCellRect(index, 0, true));
        }
    }

    void insert() {
        if (checkForm()) {
            PhieuXetNghiem pt = getModel();
            if (!new HoSoBenhAnServiceImp().isDead(pt.getMaBN())) {
                if (pxnsi.insert(pt) == 1) {
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Phiếu Xét Nghiệm", pxnsi.getRowLast() + "", "Thêm", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();

                    this.FillDataTable(pxnsi.getAll());
                    this.clear();
                    MsgBox.alert(this, "Thêm mới thành công");
                } else {
                    MsgBox.alert(this, "Thêm mới thất bại");
                }
            } else {
                MsgBox.alert(this, "Không thể thêm bệnh nhân này");
            }
        }
    }

    void update() {
        if (checkForm()) {
            PhieuXetNghiem pt = getModel();
            if (pxnsi.update(pt) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Phiếu Xét Nghiệm", pt.getSphieuXN() + "", "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();

                this.FillDataTable(pxnsi.getAll());
                this.clear();
                MsgBox.alert(this, "Cập nhật thành công");
            } else {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void getData(int row, List<PhieuXetNghiem> l) {
        int sphieuXN = (int) tblPhieuXetNghiem.getValueAt(row, 1);
        int MaXN = (int) tblPhieuXetNghiem.getValueAt(row, 2);
        int maBN = (int) tblPhieuXetNghiem.getValueAt(row, 3);
        String ngayXN = (String) tblPhieuXetNghiem.getValueAt(row, 4);
        String kquaXN = (String) tblPhieuXetNghiem.getValueAt(row, 5);
        String lidoXN = (String) tblPhieuXetNghiem.getValueAt(row, 6);
        String NguoiYeuCau = (String) tblPhieuXetNghiem.getValueAt(row, 7);

        l.add(new PhieuXetNghiem(sphieuXN, MaXN, maBN, XDate.toDate(ngayXN, "dd/MM/yyyy HH:mm:ss"), kquaXN, lidoXN, NguoiYeuCau));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuXetNghiem = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        ckbSelectAll = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLyDo = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        cbbTenBN = new javax.swing.JComboBox<>();
        cbbTenXN = new javax.swing.JComboBox<>();
        txtTenBacSy = new javax.swing.JTextField();
        txtKetQua = new javax.swing.JTextField();
        txtNgayXN = new javax.swing.JTextField();
        btnFirst = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 475));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHIẾU XÉT NGHIỆM");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 153, 255)), "Danh Sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tblPhieuXetNghiem.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhieuXetNghiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuXetNghiemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuXetNghiem);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jLabel10.setOpaque(true);

        btnRestore.setBackground(new java.awt.Color(64, 148, 94));
        btnRestore.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("Khôi Phục");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(64, 148, 94));
        btnDel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        ckbSelectAll.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbSelectAll.setText("Chọn Tất Cả");
        ckbSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbSelectAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestore, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll)))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRestore)
                        .addComponent(btnDel)
                        .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(12, 12, 12))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 153, 255)), "Cập Nhật", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tên Xét Nghiệm");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Tên Bác Sỹ");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Tên Bệnh Nhân");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Kết Quả");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Ngày Xét Nghiệm");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Lý Do Xét Nghiệm");

        txtLyDo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnAdd.setBackground(new java.awt.Color(64, 148, 94));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(64, 148, 94));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(64, 148, 94));
        btnNew.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(64, 148, 94));
        btnPrint.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("In Ấn");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        cbbTenBN.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbbTenBN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTenXN.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbbTenXN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtTenBacSy.setEditable(false);
        txtTenBacSy.setBackground(new java.awt.Color(255, 255, 255));
        txtTenBacSy.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtKetQua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtNgayXN.setEditable(false);
        txtNgayXN.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayXN.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnFirst.setBackground(new java.awt.Color(64, 148, 94));
        btnFirst.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPreviuos.setBackground(new java.awt.Color(64, 148, 94));
        btnPreviuos.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPreviuos.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviuos.setText("<<");
        btnPreviuos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviuosActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(64, 148, 94));
        btnNext.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(64, 148, 94));
        btnLast.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(6, 6, 6)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTenXN, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenBacSy, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(txtNgayXN, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnFirst)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPreviuos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLast)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTenXN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenBacSy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayXN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew)
                    .addComponent(btnPrint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPreviuos)
                    .addComponent(btnFirst))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviuosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviuosActionPerformed
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnPreviuosActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.index = tblPhieuXetNghiem.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblPhieuXetNghiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuXetNghiemMouseClicked
        this.index = tblPhieuXetNghiem.rowAtPoint(evt.getPoint());
        this.edit();

        if (Auth.isManager()) {
            int row = tblPhieuXetNghiem.getSelectedRow();
            boolean isChecked = (boolean) tblPhieuXetNghiem.getValueAt(row, 8);

            if (isChecked) {
                lis.add((int) tblPhieuXetNghiem.getValueAt(row, 1));
                getData(row, list);
                btnDel.setVisible(true);
            } else {
                int id = Integer.valueOf(tblPhieuXetNghiem.getValueAt(row, 1).toString());
                for (int j = 0; j < lis.size(); j++) {
                    if (lis.get(j) == id) {
                        lis.remove(j);
                        list.remove(j);
                    }
                }
                if (lis.isEmpty()) {
                    btnDel.setVisible(false);
                }
                ckbSelectAll.setSelected(false);
            }
        }
    }//GEN-LAST:event_tblPhieuXetNghiemMouseClicked

    int r;

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        if (list.size() > 1) {
            Object[] obj = {"Khôi phục tất cả", "Khôi phục một dòng"};
            int indexes = JOptionPane.showOptionDialog(this, "Khôi phục " + list.size() + " dòng",
                    "Thông Báo", 0, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
            switch (indexes) {
                case 0: {
                    boolean isSucc = false;
                    String id = "";
                    for (PhieuXetNghiem pxn : list) {
                        pxnsi.insertRetore(new PhieuXetNghiem(pxn.getSphieuXN(), pxn.getMaXN(), pxn.getMaBN(),
                                pxn.getNgayXN(), pxn.getKquaXN(), pxn.getLidoXN(), pxn.getNguoiYeuCau()));
                        id += pxn.getSphieuXN() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(pxnsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Phiếu Xét Nghiệm", id.substring(0, id.lastIndexOf(", ")),
                                "Khôi Phục", new Date()));
                        ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                        ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                        ManagerSecurityDAO.list.clear();
                        list.clear();
                        id = "";
                    }
                    break;
                }
                case 1: {
                    r = list.size() - 1;
                    pxnsi.insertRetore(new PhieuXetNghiem(list.get(r).getSphieuXN(),
                            list.get(r).getMaXN(), list.get(r).getMaBN(),
                            list.get(r).getNgayXN(), list.get(r).getKquaXN(),
                            list.get(r).getLidoXN(), list.get(r).getNguoiYeuCau()));
                    this.FillDataTable(pxnsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Phiếu Xét Nghiệm", list.get(r).getSphieuXN() + "",
                            "Khôi Phục", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();
                    list.remove(r);
                    if (list.isEmpty()) {
                        btnRestore.setVisible(false);
                    }
                }
                default:
                    break;
            }
        } else {
            boolean isSucc = false;
            if (MsgBox.confirm(this, "Bạn có muốn khôi phục dữ liệu không?")) {
                pxnsi.insertRetore(new PhieuXetNghiem(list.get(0).getSphieuXN(), list.get(0).getMaXN(),
                        list.get(0).getMaBN(), list.get(0).getNgayXN(), list.get(0).getKquaXN(),
                        list.get(0).getLidoXN(), list.get(0).getNguoiYeuCau()));

                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Phiếu Xét Nghiệm", list.get(0).getSphieuXN() + "",
                        "Khôi Phục", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                btnRestore.setVisible(false);
                this.FillDataTable(pxnsi.getAll());
                isSucc = true;
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (pxnsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Phiếu Xét Nghiệm", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(pxnsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        for (int i = 0; i < tblPhieuXetNghiem.getRowCount(); i++) {
            if (ckbSelectAll.isSelected()) {
                tblPhieuXetNghiem.setValueAt(true, i, 8);
                lis.add((int) tblPhieuXetNghiem.getValueAt(i, 1));
                getData(i, list);
                btnDel.setVisible(true);
            } else {
                lis.clear();
                list.clear();
                tblPhieuXetNghiem.setValueAt(false, i, 8);
                btnDel.setVisible(false);
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            if (txtSearch.getText().isEmpty()) {
                this.FillDataTable(pxnsi.getAll());
            } else {
                this.FillDataTable(pxnsi.timKiemMaPXN(txtSearch.getText()));
            }
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPreviuos;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbTenBN;
    private javax.swing.JComboBox<String> cbbTenXN;
    private javax.swing.JCheckBox ckbSelectAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPhieuXetNghiem;
    private javax.swing.JTextField txtKetQua;
    private javax.swing.JTextField txtLyDo;
    private javax.swing.JTextField txtNgayXN;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenBacSy;
    // End of variables declaration//GEN-END:variables
}
