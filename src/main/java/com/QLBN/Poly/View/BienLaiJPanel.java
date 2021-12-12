package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Entity.BienLai;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Service.BenhNhanServiceImp;
import com.QLBN.Poly.Service.BienLaiServiceImp;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XAutoCompletion;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XImages;
import com.QLBN.Poly.Utils.XOther;
import com.QLBN.Poly.Utils.XPrinting;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BienLaiJPanel extends javax.swing.JPanel {

    NhanVienServiceImp nvsi = new NhanVienServiceImp();
    BenhNhanServiceImp bnsi = new BenhNhanServiceImp();
    BienLaiServiceImp blsi = new BienLaiServiceImp();
    int index = -1;
    List<BienLai> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();

    public BienLaiJPanel() {
        initComponents();
    }

    void init() {
        this.FillComboboxBenhNhan();
        this.FillComboboxTenKhoanCP();
        this.FillDataTable(blsi.getAll());
        this.clear();
        this.setStatus(true);
        if (Auth.isLogin()) {
            txtMaNV.setText(Auth.user.getTenNV());
        }
        if (Auth.isManager()) {
            ckbSelectAll.setVisible(true);
        } else {
            ckbSelectAll.setVisible(false);
        }
        btnRestore.setVisible(false);
        btnDel.setVisible(false);
    }

    void FillComboboxBenhNhan() {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbbTenBenhNhan.getModel();
        comboBoxModel.removeAllElements();
        if (!bnsi.getAll().isEmpty()) {
            bnsi.getAll().forEach(bn -> {
                comboBoxModel.addElement(bn);
            });
            cbbTenBenhNhan.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenBenhNhan);
        }

    }

    void FillComboboxTenKhoanCP() {
        String[] arr = {"Khám Bệnh", "Xét Nghiệm", "Viện Phí", "Dịch Vụ"};
        List<String> li = Arrays.asList(arr);

        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbbTenKhoanCP.getModel();
        comboBoxModel.removeAllElements();
        li.forEach(l -> {
            comboBoxModel.addElement(l);
        });
        cbbTenKhoanCP.setSelectedIndex(0);
    }

    void FillDataTable(List<BienLai> l) {
        String[] columnIndex = {"STT", "Mã BL", "Mã NV", "Mã BN", "Ngày TT", "Tên Khoản CP", "Tiền CP", "Xóa"};
        String[] column = {"STT", "Mã BL", "Mã NV", "Mã BN", "Ngày TT", "Tên Khoản CP", "Tiền CP"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(Auth.isManager() ? columnIndex : column, 0) {
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
        tblBienLai.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblBienLai.getModel();
        model.setRowCount(0);
        for (BienLai bl : l) {
            Object[] rowData = new Object[8];
            rowData[0] = i + 1;
            rowData[1] = bl.getMaBienLai();
            rowData[2] = bl.getMaNV();
            rowData[3] = bl.getMaBN();
            rowData[4] = XDate.toString(bl.getNgayTT(), "dd/MM/yyyy");
            rowData[5] = bl.getTenKhoanChiPhi();
            rowData[6] = XOther.convertCurrency(bl.getTienChiPhi()) + " VND";
            rowData[7] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblBienLai.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblBienLai.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
//        btnUpdate.setEnabled(!insertable);
        btnInPhieu.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblBienLai.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void clear() {
        txtMaNV.setText(Auth.user.getTenNV());
        txtMaNV.setToolTipText("");
        txtNgayTT.setText(XDate.toString(new Date(), "dd/MM/yyyy"));
        txtTienChiPhi.setText("");
        cbbTenBenhNhan.setSelectedIndex(0);
        cbbTenKhoanCP.setSelectedIndex(0);
        this.setStatus(true);

    }

    boolean checkForm() {
        if (txtTienChiPhi.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập tiền chi phí");
            return false;
        } else {
            return true;
        }
    }

    void setModel(BienLai bl) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenBenhNhan.getModel();

        model.setSelectedItem(bnsi.selectByID(bl.getMaBN()));

        txtMaNV.setToolTipText(String.valueOf(bl.getMaBienLai()));
        txtMaNV.setText(nvsi.selectByID(bl.getMaNV()).getTenNV());
        txtNgayTT.setText(XDate.toString(bl.getNgayTT(), "dd/MM/yyyy"));
        txtTienChiPhi.setText(XOther.convertCurrency(bl.getTienChiPhi()) + " VND");
        cbbTenBenhNhan.setSelectedItem(model.getSelectedItem());
        cbbTenKhoanCP.setSelectedItem(bl.getTenKhoanChiPhi());
    }

    BienLai getModel() {
        BienLai bl = new BienLai();
        BenhNhan bn = (BenhNhan) cbbTenBenhNhan.getSelectedItem();
        if (!txtMaNV.getToolTipText().isEmpty()) {
            bl.setMaBienLai(Integer.parseInt(txtMaNV.getToolTipText()));
        }
        bl.setMaNV(Auth.user.getMaNV());
        bl.setNgayTT(XDate.toDate(txtNgayTT.getText(), "dd/MM/yyyy"));
        bl.setTienChiPhi(XOther.convertDouble(txtTienChiPhi.getText()));
        bl.setMaBN(bn.getMaBN());
        bl.setTenKhoanChiPhi(cbbTenKhoanCP.getSelectedItem().toString());
        return bl;
    }

    void edit() {
        int id = (int) tblBienLai.getValueAt(index, 1);
        BienLai bl = blsi.selectByID(id);
        if (bl != null) {
            this.setModel(bl);
            this.setStatus(false);
            tblBienLai.setRowSelectionInterval(index, index);
            tblBienLai.scrollRectToVisible(tblBienLai.getCellRect(index, 0, true));
        }
    }

    void insert() {
        if (checkForm()) {
            double cp = XOther.convertDouble(txtTienChiPhi.getText());
            BenhNhan bn = (BenhNhan) cbbTenBenhNhan.getSelectedItem();
            if (cp > 0) {
                BienLai bl = getModel();
                if (blsi.getStatusTT(bl.getMaBN(), bl.getTenKhoanChiPhi())) {
                    MsgBox.alert(this,
                            "Không thể thêm mới bởi vì bệnh nhân " + bn.getTenBN().toUpperCase()
                            + " đã thanh toán chi phí "
                            + cbbTenKhoanCP.getSelectedItem().toString().toUpperCase());
                } else {
                    if (blsi.insert(bl) == 1) {
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Biên Lai", blsi.getRowLast() + "", "Thêm", new Date()));
                        ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                        ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                        ManagerSecurityDAO.list.clear();

                        this.FillDataTable(blsi.getAll());
                        this.clear();
                        MsgBox.alert(this, "Thêm mới thành công");
                    } else {
                        MsgBox.alert(this, "Thêm mới thất bại");
                    }
                }
            } else {
                if (bn != null && cbbTenKhoanCP.getSelectedItem() != null) {
                    MsgBox.alert(this,
                            "Không thể thêm mới bởi vì bệnh nhân " + bn.getTenBN().toUpperCase()
                            + " chưa phát sinh chi phí "
                            + cbbTenKhoanCP.getSelectedItem().toString().toUpperCase() + " nào");
                }
            }
        }
    }

    void getData(int row, List<BienLai> l) {
        int maBienLai = (int) tblBienLai.getValueAt(row, 1);
        String MaNV = (String) tblBienLai.getValueAt(row, 2);
        int MaBN = (int) tblBienLai.getValueAt(row, 3);
        String ngàyTT = (String) tblBienLai.getValueAt(row, 4);
        String TenKhoanChiPhi = (String) tblBienLai.getValueAt(row, 5);
        String TienChiPhi = (String) tblBienLai.getValueAt(row, 6);

        l.add(new BienLai(maBienLai, MaNV, MaBN, XDate.toDate(ngàyTT, "dd/MM/yyyy"), TenKhoanChiPhi,
                XOther.convertDouble(TienChiPhi)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTienChiPhi = new javax.swing.JTextField();
        cbbTenBenhNhan = new javax.swing.JComboBox<>();
        btnInPhieu = new javax.swing.JButton();
        txtMaNV = new javax.swing.JTextField();
        cbbTenKhoanCP = new javax.swing.JComboBox<>();
        txtNgayTT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBienLai = new javax.swing.JTable();
        ckbSelectAll = new javax.swing.JCheckBox();
        btnDel = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)), "Cập Nhật", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Tên Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tên Bệnh Nhân");

        btnAdd.setBackground(new java.awt.Color(64, 148, 94));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        btnNext.setBackground(new java.awt.Color(64, 148, 94));
        btnNext.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");

        btnPreviuos.setBackground(new java.awt.Color(64, 148, 94));
        btnPreviuos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPreviuos.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviuos.setText("<<");

        btnFirst.setBackground(new java.awt.Color(64, 148, 94));
        btnFirst.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Ngày Thanh Toán");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Tên Khoản Chi Phí");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Tiền Chi Phí");

        txtTienChiPhi.setEditable(false);
        txtTienChiPhi.setBackground(new java.awt.Color(255, 255, 255));
        txtTienChiPhi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTienChiPhi.setText("0 VND");

        cbbTenBenhNhan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbbTenBenhNhan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenBenhNhanActionPerformed(evt);
            }
        });

        btnInPhieu.setBackground(new java.awt.Color(64, 148, 94));
        btnInPhieu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnInPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnInPhieu.setText("In Phiếu");
        btnInPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuActionPerformed(evt);
            }
        });

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNV.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbbTenKhoanCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenKhoanCPActionPerformed(evt);
            }
        });

        txtNgayTT.setEditable(false);
        txtNgayTT.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayTT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(12, 12, 12)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInPhieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPreviuos)
                        .addGap(12, 12, 12)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast))
                    .addComponent(txtTienChiPhi)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbbTenBenhNhan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTT)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbbTenKhoanCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTenBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTenKhoanCP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnNew)
                    .addComponent(btnFirst)
                    .addComponent(btnPreviuos)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(btnInPhieu))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BIÊN LAI THU VIỆN PHÍ");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 204)), "Danh Sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tblBienLai.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBienLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBienLaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBienLai);

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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
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
                        .addGap(20, 20, 20))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnInPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPhieuActionPerformed
        BenhNhan bn = (BenhNhan) cbbTenBenhNhan.getSelectedItem();
        if (bn != null) {
//            PrintBienLai bienLai = new PrintBienLai(null, true);
//        bienLai.getData(blsi.PrintBienLai(bn.getMaBN()+""),blsi.totalByMaBN(bn.getMaBN()+""), bn.getTenBN());
//        bienLai.setLocationRelativeTo(null);
//        bienLai.setVisible(true);


            InputStream imgInputStream
                    = this.getClass().getResourceAsStream("/Images/logo02_1.png");
            HashMap para = new HashMap();
            para.put("maBN", bn.getMaBN());
            para.put("maNV", Auth.user.getMaNV());
            para.put("logo", XImages.getPath("/Images/logo02_2.png"));

            XPrinting.print("BienLai.jrxml", "Biên Lai Khoản Phí", para);
        }
    }//GEN-LAST:event_btnInPhieuActionPerformed

    private void tblBienLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBienLaiMouseClicked
        this.index = tblBienLai.rowAtPoint(evt.getPoint());
        this.edit();

        if (Auth.isManager()) {
            int row = tblBienLai.getSelectedRow();
            boolean isChecked = (boolean) tblBienLai.getValueAt(row, 7);

            if (isChecked) {
                lis.add((int) tblBienLai.getValueAt(row, 1));
                getData(row, list);
                btnDel.setVisible(true);
            } else {
                int id = Integer.valueOf(tblBienLai.getValueAt(row, 1).toString());
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
    }//GEN-LAST:event_tblBienLaiMouseClicked

    private void cbbTenBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenBenhNhanActionPerformed
        BenhNhan bn = (BenhNhan) cbbTenBenhNhan.getSelectedItem();
        if (bn != null && cbbTenKhoanCP.getSelectedItem() != null) {
//            System.out.println(bn.getMaBN());
            txtTienChiPhi.setText(XOther.convertCurrency(
                    new BienLai().getTongCP(bn.getMaBN(),
                            cbbTenKhoanCP.getSelectedItem().toString())) + " VND");
        }
    }//GEN-LAST:event_cbbTenBenhNhanActionPerformed

    private void cbbTenKhoanCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenKhoanCPActionPerformed
        BenhNhan bn = (BenhNhan) cbbTenBenhNhan.getSelectedItem();
        if (bn != null && cbbTenKhoanCP.getSelectedItem() != null) {
            txtTienChiPhi.setText(XOther.convertCurrency(
                    new BienLai().getTongCP(bn.getMaBN(),
                            cbbTenKhoanCP.getSelectedItem().toString())) + " VND");
        }
    }//GEN-LAST:event_cbbTenKhoanCPActionPerformed

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
                    for (BienLai bl : list) {
                        blsi.insertRetore(new BienLai(bl.getMaBienLai(), bl.getMaNV(), bl.getMaBN(),
                                bl.getNgayTT(), bl.getTenKhoanChiPhi(), bl.getTienChiPhi()));
                        id += bl.getMaBienLai() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(blsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Biên Lai", id.substring(0, id.lastIndexOf(", ")),
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
                    blsi.insertRetore(new BienLai(list.get(r).getMaBienLai(), list.get(r).getMaNV(),
                            list.get(r).getMaBN(), list.get(r).getNgayTT(), list.get(r).getTenKhoanChiPhi(),
                            list.get(r).getTienChiPhi()));
                    this.FillDataTable(blsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Biên Lai", list.get(r).getMaBienLai() + "",
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
                blsi.insertRetore(new BienLai(list.get(0).getMaBienLai(), list.get(0).getMaNV(),
                        list.get(0).getMaBN(), list.get(0).getNgayTT(), list.get(0).getTenKhoanChiPhi(),
                        list.get(0).getTienChiPhi()));

                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Biên Lai", list.get(0).getMaBienLai() + "",
                        "Khôi Phục", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                btnRestore.setVisible(false);
                this.FillDataTable(blsi.getAll());
                isSucc = true;
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (blsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Biên Lai", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(blsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        for (int i = 0; i < tblBienLai.getRowCount(); i++) {
            if (ckbSelectAll.isSelected()) {
                tblBienLai.setValueAt(true, i, 7);
                lis.add((int) tblBienLai.getValueAt(i, 1));
                getData(i, list);
                btnDel.setVisible(true);
            } else {
                lis.clear();
                list.clear();
                tblBienLai.setValueAt(false, i, 7);
                btnDel.setVisible(false);
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            if (txtSearch.getText().isEmpty()) {
                this.FillDataTable(blsi.getAll());
            } else {
                this.FillDataTable(blsi.timKiemMaBL(txtSearch.getText()));
            }
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInPhieu;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPreviuos;
    private javax.swing.JButton btnRestore;
    private javax.swing.JComboBox<String> cbbTenBenhNhan;
    private javax.swing.JComboBox<String> cbbTenKhoanCP;
    private javax.swing.JCheckBox ckbSelectAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBienLai;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayTT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTienChiPhi;
    // End of variables declaration//GEN-END:variables
}
