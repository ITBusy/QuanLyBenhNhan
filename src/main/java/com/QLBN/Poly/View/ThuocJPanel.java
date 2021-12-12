package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Entity.Thuoc;
import com.QLBN.Poly.Service.ThuocServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XOther;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ThuocJPanel extends javax.swing.JPanel {

    int index = -1;
    ThuocServiceImp tsi = new ThuocServiceImp();
    List<Thuoc> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();

    public ThuocJPanel() {
        initComponents();
    }

    void init() {
        this.FillDataTable(tsi.getAll());
        FillComboboxDVT();
        this.clear();
        this.setStatus(true);
        if (Auth.isManager()) {
            ckbSelectAll.setVisible(true);
        } else {
            ckbSelectAll.setVisible(false);
        }
        btnDel.setVisible(false);
        btnRestore.setVisible(false);
    }

    void FillComboboxDVT() {
        String[] value = {"Tuýp", "Lọ", "Chai", "Gói", "Viên", "Hộp", "Viên Sủi", "Ống"};
        List<String> l = Arrays.asList(value);
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbDVT.getModel();
        model.removeAllElements();
        l.forEach(dvt -> {
            model.addElement(dvt);
        });
    }

    void FillDataTable(List<Thuoc> l) {
        String[] columnAdmin = {"STT", "Mã Thuốc", "Tên Biệt Dược", "Tên Hoạt Dược", "Hàm Lường", "DVT", "Đơn Giá", "Xóa"};
        String[] column = {"STT", "Mã Thuốc", "Tên Biệt Dược", "Tên Hoạt Dược", "Hàm Lường", "DVT", "Đơn Giá"};
        DefaultTableModel dtm = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
        };
        boolean isChecked = false;
        int i = 0;
        tblThuoc.setModel(dtm);
        DefaultTableModel model = (DefaultTableModel) tblThuoc.getModel();
        model.setRowCount(0);
        for (Thuoc t : l) {
            Object[] rowData = new Object[8];
            rowData[0] = i + 1;
            rowData[1] = t.getMaThuoc();
            rowData[2] = t.getTenBietDuoc();
            rowData[3] = t.getTenHoatDuoc();
            rowData[4] = t.getHamLuong();
            rowData[5] = t.getDVT();
            rowData[6] = XOther.convertCurrency(t.getDonGia());
            rowData[7] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblThuoc.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblThuoc.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
    }

    void clear() {
        XOther.isDigits('.', txtDonGia);
        txtTenBietDuoc.setToolTipText("");
        txtTenBietDuoc.setText("");
        txtTenHoatDuoc.setText("");
        txtHamLuong.setText("");
        txtDonGia.setText("");
        cbbDVT.setSelectedIndex(0);
        this.setStatus(true);
    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblThuoc.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    boolean checkForm() {
        String[] name = {jLabelTenBietDuoc.getText(), jLabelTenHoatDuoc.getText(), jLabelHamLuong.getText(), jLabelDG.getText()};
        String[] er = {txtTenBietDuoc.getText(), txtTenHoatDuoc.getText(), txtHamLuong.getText(), txtDonGia.getText()};
        String mess = "";
        for (int i = 0; i < er.length; i++) {
            if (er[i].isEmpty()) {
                mess += name[i] + ", ";
            }
        }
        if (!mess.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + mess.substring(0, mess.lastIndexOf(", ")));
            return false;
        } else if (txtTenBietDuoc.getText().matches("^([0-9])*$")) {
            MsgBox.alert(this, "Tên biệt dược phải là chữ");
            return false;
        } else if (txtTenHoatDuoc.getText().matches("^([0-9])*$")) {
            MsgBox.alert(this, "Tên hoạt dược phải là số");
            return false;
//        } else if (!txtHamLuong.getText().matches("(\\d+(,+\\d+(%|mg|ml|g|+)|%|mg|ml|;|g|+))+(\\d+(.+\\d|%|mg|ml|;|g|+))*")) {
//            MsgBox.alert(this, "Hàm lượng không hợp lệ! VD: 500mg");
//            return false;
        } else if (!txtDonGia.getText().matches("\\d+(.+\\d)*")) {
            MsgBox.alert(this, "Đơn giá phải là số");
            return false;
        } else {
            return true;
        }
    }

    void setModel(Thuoc t) {
        txtTenBietDuoc.setToolTipText(String.valueOf(t.getMaThuoc()));
        txtTenBietDuoc.setText(t.getTenBietDuoc());
        txtTenHoatDuoc.setText(t.getTenHoatDuoc());
        txtHamLuong.setText(t.getHamLuong());
        txtDonGia.setText(XOther.convertString(t.getDonGia()));
        cbbDVT.setSelectedItem(t.getDVT());
    }

    Thuoc getModel() {
        Thuoc t = new Thuoc();
        if (!txtTenBietDuoc.getToolTipText().isEmpty()) {
            t.setMaThuoc(Integer.parseInt(txtTenBietDuoc.getToolTipText()));
        }
        t.setTenBietDuoc(XOther.Rename(txtTenBietDuoc.getText()));
        t.setTenHoatDuoc(txtTenHoatDuoc.getText());
        t.setHamLuong(txtHamLuong.getText());
        t.setDonGia(Double.parseDouble(txtDonGia.getText()));
        t.setDVT(cbbDVT.getSelectedItem().toString());
        return t;
    }

    void edit() {
        int id = (int) tblThuoc.getValueAt(index, 1);
        Thuoc t = tsi.selectByID(id);
        if (t != null) {
            this.setModel(t);
            this.setStatus(false);
            tblThuoc.setRowSelectionInterval(index, index);
            tblThuoc.scrollRectToVisible(tblThuoc.getCellRect(index, 0, true));
        }
    }

    void insert() {
        if (checkForm()) {
            Thuoc t = getModel();
            if (tsi.insert(t) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Thuốc", tsi.getRowLast() + "", "Thêm", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                this.FillDataTable(tsi.getAll());
                this.clear();
                MsgBox.alert(this, "Thêm mới thành công");
            } else {
                MsgBox.alert(this, "Thêm mới thất bại");
            }
        }
    }

    void update() {
        if (checkForm()) {
            Thuoc t = getModel();
            if (tsi.update(t) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Thuóc", t.getMaThuoc() + "", "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                this.FillDataTable(tsi.getAll());
                this.clear();
                MsgBox.alert(this, "Cập nhật thành công");
            } else {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void getData(int row, List<Thuoc> l) {
        int MaThuoc = (int) tblThuoc.getValueAt(row, 1);
        String TenBietDuoc = (String) tblThuoc.getValueAt(row, 2);
        String tenHoatDuoc = (String) tblThuoc.getValueAt(row, 3);
        String HamLuong = (String) tblThuoc.getValueAt(row, 4);
        String DVT = (String) tblThuoc.getValueAt(row, 5);
        String donGia = (String) tblThuoc.getValueAt(row, 6);

        l.add(new Thuoc(MaThuoc, TenBietDuoc, tenHoatDuoc, HamLuong, DVT, XOther.convertDouble(donGia)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuoc = new javax.swing.JTable();
        ckbSelectAll = new javax.swing.JCheckBox();
        btnDel = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelTenBietDuoc = new javax.swing.JLabel();
        jLabelTenHoatDuoc = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        txtTenBietDuoc = new javax.swing.JTextField();
        jLabelDG = new javax.swing.JLabel();
        jLabelDVT = new javax.swing.JLabel();
        cbbDVT = new javax.swing.JComboBox<>();
        txtTenHoatDuoc = new javax.swing.JTextField();
        txtHamLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabelHamLuong = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QUẢN LÝ THUỐC");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 204)), "Danh Sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tblThuoc.setModel(new javax.swing.table.DefaultTableModel(
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
        tblThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuoc);

        ckbSelectAll.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbSelectAll.setText("Chọn Tất Cả");
        ckbSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbSelectAllActionPerformed(evt);
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

        btnRestore.setBackground(new java.awt.Color(64, 148, 94));
        btnRestore.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("Khôi Phục");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll)
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRestore)
                        .addComponent(btnDel)
                        .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)), "Cập Nhật", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabelTenBietDuoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTenBietDuoc.setText("Tên Thuốc");

        jLabelTenHoatDuoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTenHoatDuoc.setText("Tên Hoạt Dược");

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

        btnLast.setBackground(new java.awt.Color(64, 148, 94));
        btnLast.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(64, 148, 94));
        btnNext.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPreviuos.setBackground(new java.awt.Color(64, 148, 94));
        btnPreviuos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPreviuos.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviuos.setText("<<");
        btnPreviuos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviuosActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(64, 148, 94));
        btnFirst.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        txtTenBietDuoc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelDG.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDG.setText("Đơn Giá");

        jLabelDVT.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDVT.setText("Đơn Vị Tính");

        cbbDVT.setBackground(new java.awt.Color(255, 255, 255));
        cbbDVT.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbbDVT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chai", "Lọ", "Viên", "Hộp" }));

        txtTenHoatDuoc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtHamLuong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtDonGia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDonGia.setVerifyInputWhenFocusTarget(false);

        jLabelHamLuong.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabelHamLuong.setText("Hàm Lượng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTenBietDuoc)
                    .addComponent(txtTenBietDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenHoatDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPreviuos)
                        .addGap(12, 12, 12)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast))
                    .addComponent(txtHamLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNew))
                    .addComponent(jLabelDG)
                    .addComponent(jLabelHamLuong)
                    .addComponent(jLabelTenHoatDuoc)
                    .addComponent(jLabelDVT)
                    .addComponent(cbbDVT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabelTenBietDuoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenBietDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabelTenHoatDuoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenHoatDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelHamLuong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHamLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabelDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnPreviuos)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

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
        this.index = tblThuoc.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocMouseClicked
        this.index = tblThuoc.rowAtPoint(evt.getPoint());
        this.edit();
        if (Auth.isManager()) {
            int row = tblThuoc.getSelectedRow();
            boolean isChecked = (boolean) tblThuoc.getValueAt(row, 7);
            boolean isExist = false;

            for (int i = 0; i < tsi.getMaThuoc().size(); i++) {
                int id = Integer.valueOf(tblThuoc.getValueAt(row, 1).toString());
                if (tsi.getMaThuoc().get(i) == id) {
                    isExist = true;
                }
            }
            if (isChecked) {
                if (isExist) {
                    lis.add((int) tblThuoc.getValueAt(row, 1));
                    getData(row, list);
                    btnDel.setVisible(true);
                } else {
                    MsgBox.alert(this, "Không thể xóa");
                    tblThuoc.setValueAt(false, row, 7);
                }
            } else {
                int id = Integer.valueOf(tblThuoc.getValueAt(row, 1).toString());
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
    }//GEN-LAST:event_tblThuocMouseClicked

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
                    for (Thuoc t : list) {
                        tsi.insertRetore(new Thuoc(t.getMaThuoc(), t.getTenBietDuoc(), t.getTenHoatDuoc(),
                                t.getHamLuong(), t.getDVT(), t.getDonGia()));
                        id += t.getMaThuoc() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(tsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Thuốc", id.substring(0, id.lastIndexOf(", ")),
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
                    tsi.insertRetore(new Thuoc(list.get(r).getMaThuoc(), list.get(r).getTenBietDuoc(), list.get(r).getTenHoatDuoc(),
                            list.get(r).getHamLuong(), list.get(r).getDVT(), list.get(r).getDonGia()));
                    this.FillDataTable(tsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Thuốc", list.get(r).getMaThuoc() + "",
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
                for (Thuoc t : list) {
                    tsi.insertRetore(new Thuoc(t.getMaThuoc(), t.getTenBietDuoc(), t.getTenHoatDuoc(),
                            t.getHamLuong(), t.getDVT(), t.getDonGia()));

                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Thuốc", t.getMaThuoc() + "",
                            "Khôi Phục", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();
                    btnRestore.setVisible(false);
                    this.FillDataTable(tsi.getAll());
                    isSucc = true;
                }
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (tsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Thuốc", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(tsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        List<Integer> str = tsi.getMaThuoc();
        for (int i = 0; i < tblThuoc.getRowCount(); i++) {
            for (int j = 0; j < str.size(); j++) {
                if (ckbSelectAll.isSelected()) {
                    int id = Integer.valueOf(tblThuoc.getValueAt(i, 1).toString());
                    if (str.get(j) == id) {
                        tblThuoc.setValueAt(true, i, 7);
                        lis.add((int) tblThuoc.getValueAt(i, 1));
                        getData(i, list);
                        btnDel.setVisible(true);
                    }
                } else {
                    lis.clear();
                    list.clear();
                    tblThuoc.setValueAt(false, i, 7);
                    btnDel.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.getText().isEmpty()) {
            this.FillDataTable(tsi.getAll());
        } else {
            this.FillDataTable(tsi.timKiemTenThuoc(txtSearch.getText()));
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
    private javax.swing.JComboBox<String> cbbDVT;
    private javax.swing.JCheckBox ckbSelectAll;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelDG;
    private javax.swing.JLabel jLabelDVT;
    private javax.swing.JLabel jLabelHamLuong;
    private javax.swing.JLabel jLabelTenBietDuoc;
    private javax.swing.JLabel jLabelTenHoatDuoc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblThuoc;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtHamLuong;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenBietDuoc;
    private javax.swing.JTextField txtTenHoatDuoc;
    // End of variables declaration//GEN-END:variables
}
