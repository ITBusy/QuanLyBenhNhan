package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Entity.DichVu;
import com.QLBN.Poly.Entity.DichVuSD;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Service.BenhNhanServiceImp;
import com.QLBN.Poly.Service.DichVuSDServiceImp;
import com.QLBN.Poly.Service.DichVuServiceImp;
import com.QLBN.Poly.Service.HoSoBenhAnServiceImp;
import com.QLBN.Poly.Utils.Auth;
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

public class DV_BenhNhan_SDJPanel extends javax.swing.JPanel {

    DichVuSDServiceImp dvsdsi = new DichVuSDServiceImp();
    BenhNhanServiceImp bnsi = new BenhNhanServiceImp();
    DichVuServiceImp dvsi = new DichVuServiceImp();
    int index = -1;
    List<DichVuSD> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();

    public DV_BenhNhan_SDJPanel() {
        initComponents();
    }

    void init() {
        this.FillDataTable(dvsdsi.getAll());
        this.FillComboboxBenhNhan();
        this.FillComboboxDichVu();
        this.clear();
        if (Auth.isManager()) {
            ckbSelectAll.setVisible(true);
        } else {
            ckbSelectAll.setVisible(false);
        }
        btnRestore.setVisible(false);
        btnDel.setVisible(false);
        this.setStatus(true);
    }

    void FillComboboxBenhNhan() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenBN.getModel();
        model.removeAllElements();
        bnsi.getAll().forEach(bn -> {
            model.addElement(bn);
        });
        cbbTenBN.setSelectedIndex(0);
    }

    void FillComboboxDichVu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenDV.getModel();
        model.removeAllElements();
        if (!dvsi.getAll().isEmpty()) {
            dvsi.getAll().forEach(dv -> {
                model.addElement(dv);
            });
            cbbTenDV.setSelectedIndex(0);
        }
    }

    void FillDataTable(List<DichVuSD> l) {
        String[] columnIndex = {"STT", "Mã SDDV", "Mã Dịch Vụ", "Mã Bệnh Nhân", "Ngày Sử Dụng", "Xóa"};
        String[] column = {"STT", "Mã DV", "Mã Dịch Vụ", "Mã Bệnh Nhân", "Ngày Sử Dụng"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(Auth.isManager() ? columnIndex : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }

        };
        boolean isChecked = false;
        int i = 0;
        tblPhieuDichVu.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblPhieuDichVu.getModel();
        model.setRowCount(0);
        for (DichVuSD dv : l) {
            Object[] rowData = new Object[6];
            rowData[0] = i + 1;
            rowData[1] = dv.getSphieuSD();
            rowData[2] = dv.getMaDV();
            rowData[3] = dv.getMaBN();
            rowData[4] = XDate.toString(dv.getNgaySD(), "dd/MM/yyyy");
            rowData[5] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblPhieuDichVu.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblPhieuDichVu.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        cbbTenBN.setEnabled(insertable);
        
        boolean first = this.index > 0;
        boolean last = this.index < tblPhieuDichVu.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void clear() {
        cbbTenDV.setToolTipText("");
        if (cbbTenDV.getSelectedItem() != null) {
            cbbTenDV.setSelectedIndex(0);
        }
        if (cbbTenBN.getSelectedItem() != null) {
            cbbTenBN.setSelectedIndex(0);
        }
        txtNgaySD.setText(XDate.toString(new Date(), "dd/MM/yyyy"));
        this.setStatus(true);

    }

    void setModel(DichVuSD dv) {
        DefaultComboBoxModel modelBenhNhan = (DefaultComboBoxModel) cbbTenBN.getModel();
        DefaultComboBoxModel modelDichVu = (DefaultComboBoxModel) cbbTenDV.getModel();
        modelBenhNhan.setSelectedItem(bnsi.selectByID(dv.getMaBN()));
        modelDichVu.setSelectedItem(dvsi.selectByID(dv.getMaDV()));

        cbbTenDV.setToolTipText(String.valueOf(dv.getSphieuSD()));
        cbbTenDV.setSelectedItem(modelDichVu.getSelectedItem());
        cbbTenBN.setSelectedItem(modelBenhNhan.getSelectedItem());
        txtNgaySD.setText(XDate.toString(dv.getNgaySD(), "dd/MM/yyyy"));
    }

    DichVuSD getModel() {
        DichVuSD dv = new DichVuSD();
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        DichVu d = (DichVu) cbbTenDV.getSelectedItem();
        if (!cbbTenDV.getToolTipText().isEmpty()) {
            dv.setMaDV(Integer.parseInt(cbbTenDV.getToolTipText()));
        }
        dv.setMaBN(bn.getMaBN());
        dv.setMaDV(d.getMaDV());
        dv.setNgaySD(XDate.toDate(txtNgaySD.getText(), "dd/MM/yyyy"));
        return dv;
    }

    void edit() {
        cbbTenBN.setEnabled(false);
        int id = (int) tblPhieuDichVu.getValueAt(index, 1);
        DichVuSD dv = dvsdsi.selectByID(id);
        if (dv != null) {
            this.setModel(dv);
            this.setStatus(false);
            tblPhieuDichVu.setRowSelectionInterval(index, index);
            tblPhieuDichVu.scrollRectToVisible(tblPhieuDichVu.getCellRect(index, 0, true));
        }
    }

    void insert() {
        DichVuSD dv = getModel();
        if (!new HoSoBenhAnServiceImp().isDead(dv.getMaBN())) {
            if (dvsdsi.insert(dv) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Dịch Vụ Bệnh Nhân Sử Dụng", dvsdsi.getRowLast() + "", "Thêm", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();

                this.FillDataTable(dvsdsi.getAll());
                this.clear();
                MsgBox.alert(this, "Thêm mới thành công");
            } else {
                MsgBox.alert(this, "Thêm mới thất bại");
            }
        } else {
            MsgBox.alert(this, "Không thể thêm bệnh nhân này");
        }
    }

    void update() {
        DichVuSD dv = getModel();
        if (dvsdsi.update(dv) == 1) {
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Dịch Vụ Bệnh Nhân Sử Dụng", dv.getSphieuSD() + "", "Cập Nhật", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();

            this.FillDataTable(dvsdsi.getAll());
            this.clear();
            MsgBox.alert(this, "Cập nhật thành công");
        } else {
            MsgBox.alert(this, "Cập nhật thất bại");
        }
    }

    void getData(int row, List<DichVuSD> l) {
        //"Mã DV", "Mã Dịch Vụ", "Mã Bệnh Nhân", "Ngày Sử Dụng"
        int sphieuSD = (int) tblPhieuDichVu.getValueAt(row, 1);
        int maDV = (int) tblPhieuDichVu.getValueAt(row, 2);
        int maBN = (int) tblPhieuDichVu.getValueAt(row, 3);
        String ngaySD = (String) tblPhieuDichVu.getValueAt(row, 4);

        l.add(new DichVuSD(sphieuSD, maBN, maDV, XDate.toDate(ngaySD, "dd/MM/yyyy")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        cbbTenBN = new javax.swing.JComboBox<>();
        cbbTenDV = new javax.swing.JComboBox<>();
        btnFirst = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtNgaySD = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPhieuDichVu = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        ckbSelectAll = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)), "Cập Nhật", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Tên Bệnh Nhân");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tên Dịch Vụ");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Ngày Sử Dụng");

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

        cbbTenBN.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbTenBN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTenDV.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbTenDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        txtNgaySD.setEditable(false);
        txtNgaySD.setBackground(new java.awt.Color(255, 255, 255));
        txtNgaySD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbTenBN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbTenDV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNew))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(btnFirst)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPreviuos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNext)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLast)))
                                .addGap(0, 12, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNgaySD)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgaySD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPreviuos)
                    .addComponent(btnFirst))
                .addGap(47, 47, 47))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)), "Danh Sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tblPhieuDichVu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhieuDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuDichVuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPhieuDichVu);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Tìm Kiếm:");

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jLabel12.setOpaque(true);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll)
                        .addGap(21, 21, 21))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRestore)
                        .addComponent(btnDel)
                        .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DỊCH VỤ BỆNH NHÂN SỬ DỤNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.index = tblPhieuDichVu.getRowCount() - 1;
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

    private void tblPhieuDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuDichVuMouseClicked
        this.index = tblPhieuDichVu.rowAtPoint(evt.getPoint());
        this.edit();

        if (Auth.isManager()) {
            int row = tblPhieuDichVu.getSelectedRow();
            boolean isChecked = (boolean) tblPhieuDichVu.getValueAt(row, 5);

            if (isChecked) {
                lis.add((int) tblPhieuDichVu.getValueAt(row, 1));
                getData(row, list);
                btnDel.setVisible(true);
            } else {
                int id = Integer.valueOf(tblPhieuDichVu.getValueAt(row, 1).toString());
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
    }//GEN-LAST:event_tblPhieuDichVuMouseClicked

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
                    for (DichVuSD dvsd : list) {
                        dvsdsi.insertRetore(new DichVuSD(dvsd.getSphieuSD(), dvsd.getMaBN(), dvsd.getMaDV(), dvsd.getNgaySD()));
                        id += dvsd.getSphieuSD() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(dvsdsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Dịch Vụ Bệnh Nhân Sử Dụng", id.substring(0, id.lastIndexOf(", ")),
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
                    dvsdsi.insertRetore(new DichVuSD(list.get(r).getSphieuSD(), list.get(r).getMaBN(),
                            list.get(r).getMaDV(), list.get(r).getNgaySD()));
                    this.FillDataTable(dvsdsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Dịch Vụ Bệnh Nhân Sử Dụng", list.get(r).getSphieuSD() + "",
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
                dvsdsi.insertRetore(new DichVuSD(list.get(0).getSphieuSD(), list.get(0).getMaBN(),
                        list.get(0).getMaDV(), list.get(0).getNgaySD()));

                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Dịch Vụ Bệnh Nhân Sử Dụng", list.get(0).getSphieuSD() + "",
                        "Khôi Phục", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                btnRestore.setVisible(false);
                this.FillDataTable(dvsdsi.getAll());
                isSucc = true;
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (dvsdsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Dịch Vụ Bệnh Nhân Sử Dụng", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(dvsdsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        for (int i = 0; i < tblPhieuDichVu.getRowCount(); i++) {
            if (ckbSelectAll.isSelected()) {
                tblPhieuDichVu.setValueAt(true, i, 5);
                lis.add((int) tblPhieuDichVu.getValueAt(i, 1));
                getData(i, list);
                btnDel.setVisible(true);
            } else {
                lis.clear();
                list.clear();
                tblPhieuDichVu.setValueAt(false, i, 5);
                btnDel.setVisible(false);
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.getText().isEmpty()) {
            this.FillDataTable(dvsdsi.getAll());
        } else {
            this.FillDataTable(dvsdsi.timKiemMaDVSD(txtSearch.getText()));
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
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbTenBN;
    private javax.swing.JComboBox<String> cbbTenDV;
    private javax.swing.JCheckBox ckbSelectAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblPhieuDichVu;
    private javax.swing.JTextField txtNgaySD;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
