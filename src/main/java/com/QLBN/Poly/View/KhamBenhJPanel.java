package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.Benh;
import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Entity.KhamBenh;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Service.BenhNhanServiceImp;
import com.QLBN.Poly.Service.BenhServiceImp;
import com.QLBN.Poly.Service.HoSoBenhAnServiceImp;
import com.QLBN.Poly.Service.KhamBenhServiceImp;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.XAutoCompletion;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XImages;
import com.QLBN.Poly.Utils.XOther;
import com.QLBN.Poly.Utils.XPrinting;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class KhamBenhJPanel extends javax.swing.JPanel {

    BenhNhanServiceImp bnsi = new BenhNhanServiceImp();
    BenhServiceImp bsi = new BenhServiceImp();
    KhamBenhServiceImp kbsi = new KhamBenhServiceImp();
    NhanVienServiceImp nvsi = new NhanVienServiceImp();
    int index = -1;
    KhamBenh k;
    List<KhamBenh> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();

    public KhamBenhJPanel() {
        initComponents();
    }

    void init() {
        this.FillDataComboboxBenh();
        this.FillDataTable(kbsi.getAll());
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
            bnsi.getDSKhamBenh().forEach(bn -> {
                model.addElement(bn);
            });
            cbbTenBN.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenBN);
        }
    }

    void FillDataComboboxBenh() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenBenh.getModel();
        model.removeAllElements();
        if (!bsi.getAll().isEmpty()) {
            bsi.getAll().forEach(b -> {
                model.addElement(b);
            });
            cbbTenBenh.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenBenh);
        }
    }

    void FillDataTable(List<KhamBenh> l) {
        //ngayKham, trieuChung, huyetAp, nhipTim, Mach, canNang, chuanDoan, yeuCau, maNV, maBenh, maBenhNhan, CPKham
        String[] columnAdmin = {"STT", "Mã KB", "Mã BN", "Mã Bệnh", "Mã BS", "Ngày Khám", "Triệu Chứng",
            "Huyết Áp", "Nhịp Tim", "Mạch", "Cân Nặng", "Chuẩn Đoán", "Chi Phí", "Yêu Cầu", "Xóa"};
        String[] column = {"STT", "Mã KB", "Mã BN", "Mã Bệnh", "Mã BS", "Ngày Khám", "Triệu Chứng",
            "Huyết Áp", "Nhịp Tim", "Mạch", "Cân Nặng", "Chuẩn Đoán", "Chi Phí", "Yêu Cầu"};
        DefaultTableModel dtm = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 14;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 14 ? Boolean.class : String.class;
            }

        };
        boolean isChecked = false;
        int i = 0;
        tblKhamBenh.setModel(dtm);
        DefaultTableModel model = (DefaultTableModel) tblKhamBenh.getModel();
        model.setRowCount(0);
        for (KhamBenh kb : l) {
            Object[] rowData = new Object[15];
            rowData[0] = i + 1;
            rowData[1] = kb.getSoPhieuKham();
            rowData[2] = kb.getMaBenhNhan();
            rowData[3] = kb.getMaBenh();
            rowData[4] = kb.getMaNV();
            rowData[5] = XDate.toString(kb.getNgayKham(), "dd/MM/yyyy");
            rowData[6] = kb.getTrieuChung();
            rowData[7] = kb.getHuyetAp();
            rowData[8] = kb.getNhipTim();
            rowData[9] = kb.getMach();
            rowData[10] = kb.getCanNang();
            rowData[11] = kb.getChuanDoan();
            rowData[12] = XOther.convertCurrency(kb.getCPKham());
            rowData[13] = kb.isYeuCau() ? "Nhập viện" : "Không";
            rowData[14] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblKhamBenh.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhamBenh.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
    }

    void clear() {
        XOther.isAlphabetic(' ', txtChuanDoan);
        XOther.isDigits('\u0000', txtCanNang, txtMach, txtNhipTim);
        XOther.isDigits('/', txtHuyetAp);
        XOther.isDigits('.', txtChiPhi);
        this.FillDataComboboxBenhNhan();
        txtTenBacSy.setToolTipText("");
        if (cbbTenBenh.getSelectedItem() != null) {
            cbbTenBenh.setSelectedIndex(0);
        }
        if (cbbTenBN.getSelectedItem() != null) {
            cbbTenBN.setSelectedIndex(0);
        }
        txtTenBacSy.setText(Auth.user.getTenNV());
        txtChiPhi.setText("");
        rdbNhapVien.setSelected(true);
        txtTrieuChung.setText("");
        txtCanNang.setText("");
        txtChuanDoan.setText("");
        txtHuyetAp.setText("");
        txtMach.setText("");
        txtNhipTim.setText("");
        txtNgKham.setText(XDate.toString(new Date(), "dd/MM/yyyy"));
        this.setStatus(true);
    }

    boolean checkForm() {
        String[] nameEr = {jLabelTrieuChung.getText(), jLabelMach.getText(), jLabelCanNang.getText(),
            jLabelNhipTim.getText(), jLabelHuyetAp.getText(), jLabelChuanDoan.getText(), jLabelChiPhi.getText()};
        String[] Err = {txtTrieuChung.getText(), txtMach.getText(), txtCanNang.getText(), txtMach.getText(),
            txtHuyetAp.getText(), txtChuanDoan.getText(), txtChiPhi.getText()};
        String msg = "";

        String[] num = {jLabelMach.getText(), jLabelCanNang.getText(), jLabelNhipTim.getText()};
        String[] arr = {txtMach.getText(), txtCanNang.getText(), txtNhipTim.getText()};
        String ms = "";
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].matches("^([0-9])*$")) {
                ms += num[i] + ", ";
            }
        }
        for (int i = 0; i < Err.length; i++) {
            if (Err[i].isEmpty()) {
                msg += nameEr[i] + ", ";
            }
        }
        if (!msg.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + msg.substring(0, msg.lastIndexOf(", ")));
            return false;
        } else if (txtTrieuChung.getText().matches("^([0-9])*$") || txtChuanDoan.getText().matches("^([0-9])*$")) {
            MsgBox.alert(this, (txtTrieuChung.getText().matches("^([0-9])*$") ? "Triệu chứng" : "Chuẩn đoán") + " phải là chữ");
            return false;
        } else if (!ms.isEmpty()) {
            MsgBox.alert(this, ms.substring(0, ms.lastIndexOf(", ")) + " phải là sô");
            return false;
        } else if (!txtHuyetAp.getText().matches("([0-9]{1,3}/[0-9]{1,3})")) {
            MsgBox.alert(this, "Huyết áp phải là sô");
            return false;
        } else if (!txtChiPhi.getText().matches("\\d+(.+\\d)*")) {
            MsgBox.alert(this, "Chi phí phải là số");
            return false;
        } else {
            return true;
        }
    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnPrint.setEnabled(!insertable && rdbNhapVien.isSelected());
        cbbTenBN.setEnabled(insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblKhamBenh.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void setModel(KhamBenh kb) {
        BenhNhan bn = bnsi.selectByID(kb.getMaBenhNhan());
        DefaultComboBoxModel modelBenh = (DefaultComboBoxModel) cbbTenBenh.getModel();
        DefaultComboBoxModel modelBN = (DefaultComboBoxModel) cbbTenBN.getModel();
        modelBN.setSelectedItem(bn);
        modelBenh.setSelectedItem(bsi.selectByID(kb.getMaBenh()));

        txtTenBacSy.setText(nvsi.selectByID(kb.getMaNV()).getTenNV());
        txtTenBacSy.setToolTipText(String.valueOf(kb.getSoPhieuKham()));
        txtNgKham.setText(XDate.toString(kb.getNgayKham(), "dd/MM/yyyy"));
        txtTrieuChung.setText(kb.getTrieuChung());
        cbbTenBenh.setSelectedItem(modelBenh.getSelectedItem());
        cbbTenBN.setSelectedItem(modelBN.getSelectedItem());
        txtChiPhi.setText(XOther.convertString(kb.getCPKham()));
        txtChuanDoan.setText(kb.getChuanDoan());
        txtMach.setText(kb.getMach() + "");
        txtCanNang.setText(kb.getCanNang() + "");
        txtMach.setText(kb.getMach() + "");
        txtNhipTim.setText(kb.getNhipTim() + "");
        txtHuyetAp.setText(kb.getHuyetAp());
        ButtonModel bm = kb.isYeuCau() ? rdbNhapVien.getModel() : rdbKhong.getModel();
        buttonGroupYCNV.setSelected(bm, true);

    }

    KhamBenh getModel() {
        KhamBenh kb = new KhamBenh();
        Benh b = (Benh) cbbTenBenh.getSelectedItem();
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        if (!txtTenBacSy.getToolTipText().isEmpty()) {
            kb.setSoPhieuKham(Integer.parseInt(txtTenBacSy.getToolTipText()));
        }
        kb.setNgayKham(XDate.toDate(txtNgKham.getText(), "dd/MM/yyyy"));
        kb.setTrieuChung(txtTrieuChung.getText());
        kb.setMaNV(Auth.user.getMaNV());
        kb.setMaBenh(b.getMaBenh());
        kb.setMaBenhNhan(bn.getMaBN());
        kb.setCPKham(Double.parseDouble(txtChiPhi.getText()));
        kb.setYeuCau(rdbNhapVien.isSelected());
        kb.setMach(Integer.parseInt(txtMach.getText()));
        kb.setCanNang(Integer.parseInt(txtCanNang.getText()));
        kb.setNhipTim(Integer.parseInt(txtMach.getText()));
        kb.setHuyetAp(txtHuyetAp.getText());
        kb.setChuanDoan(txtChuanDoan.getText());
        return kb;
    }

    void edit() {
        int id = (int) tblKhamBenh.getValueAt(index, 1);
        k = kbsi.selectByID(id);
        if (k != null) {
            this.setModel(k);
            this.setStatus(false);
            tabs.setSelectedIndex(0);
            tblKhamBenh.setRowSelectionInterval(index, index);
            tblKhamBenh.scrollRectToVisible(tblKhamBenh.getCellRect(index, 0, true));
        }
    }

    void insert() {
        if (checkForm()) {
            KhamBenh kb = getModel();
            if (!new HoSoBenhAnServiceImp().isDead(kb.getMaBenhNhan())) {
                if (kbsi.insert(kb) == 1) {
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Khám Bệnh", kbsi.getRowLast() + "", "Thêm", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();

                    this.FillDataTable(kbsi.getAll());
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
            KhamBenh kb = getModel();
            if (kbsi.update(kb) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Khám Bệnh", kb.getSoPhieuKham() + "", "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();

                this.FillDataTable(kbsi.getAll());
                this.clear();
                MsgBox.alert(this, "Cập nhật thành công");
            } else {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void getData(int row, List<KhamBenh> l) {
        int soPhieuKham = (int) tblKhamBenh.getValueAt(row, 1);
        int maBenhNhan = (int) tblKhamBenh.getValueAt(row, 2);
        int maBenh = (int) tblKhamBenh.getValueAt(row, 3);
        String maNV = (String) tblKhamBenh.getValueAt(row, 4);
        String ngayKham = (String) tblKhamBenh.getValueAt(row, 5);
        String trieuChung = (String) tblKhamBenh.getValueAt(row, 6);
        String huyetAp = (String) tblKhamBenh.getValueAt(row, 7);
        int nhipTim = (int) tblKhamBenh.getValueAt(row, 8);
        int Mach = (int) tblKhamBenh.getValueAt(row, 9);
        int canNang = (int) tblKhamBenh.getValueAt(row, 10);
        String chuanDoan = (String) tblKhamBenh.getValueAt(row, 11);
        String CPKham = (String) tblKhamBenh.getValueAt(row, 12);
        String yeuCau = (String) tblKhamBenh.getValueAt(row, 13);

        l.add(new KhamBenh(soPhieuKham, XDate.toDate(ngayKham, "dd/MM/yyyy"), trieuChung, huyetAp, nhipTim, Mach,
                canNang, chuanDoan, yeuCau.equals("Nhập Viện"), maNV, maBenh, maBenhNhan, XOther.convertDouble(CPKham)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupYCNV = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabelTrieuChung = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTrieuChung = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtNgKham = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtNgSinh = new javax.swing.JTextField();
        cbbTenBN = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTenBacSy = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbbTenBenh = new javax.swing.JComboBox<>();
        jLabelMach = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtMach = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabelNhipTim = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtHuyetAp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabelCanNang = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtCanNang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabelChuanDoan = new javax.swing.JLabel();
        txtChuanDoan = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rdbNhapVien = new javax.swing.JRadioButton();
        rdbKhong = new javax.swing.JRadioButton();
        jLabelChiPhi = new javax.swing.JLabel();
        txtChiPhi = new javax.swing.JTextField();
        jLabelHuyetAp = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtNhipTim = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhamBenh = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        ckbSelectAll = new javax.swing.JCheckBox();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KHÁM BỆNH");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)), "Thông Tin Bệnh Nhân", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(413, 245));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Địa Chỉ");

        txtDiaChi.setEditable(false);
        txtDiaChi.setBackground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelTrieuChung.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTrieuChung.setText("Triệu Chứng");

        txtTrieuChung.setColumns(20);
        txtTrieuChung.setRows(5);
        jScrollPane1.setViewportView(txtTrieuChung);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Ngày Khám");

        txtNgKham.setEditable(false);
        txtNgKham.setBackground(new java.awt.Color(255, 255, 255));
        txtNgKham.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Tên Bệnh Nhân");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Ngày Sinh");

        txtNgSinh.setEditable(false);
        txtNgSinh.setBackground(new java.awt.Color(255, 255, 255));
        txtNgSinh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbbTenBN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenBNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(txtDiaChi)
                    .addComponent(txtNgKham)
                    .addComponent(txtNgSinh)
                    .addComponent(cbbTenBN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTrieuChung)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel18)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgKham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTrieuChung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)), "Thông Tin Bệnh", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Tên Bác Sỹ");

        txtTenBacSy.setEditable(false);
        txtTenBacSy.setBackground(new java.awt.Color(255, 255, 255));
        txtTenBacSy.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tên Bệnh");

        cbbTenBenh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelMach.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMach.setText("Mạch");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        txtMach.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMach.setBorder(null);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Lần/Phút");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtMach, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelNhipTim.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNhipTim.setText("Nhịp Tim");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel7.setPreferredSize(new java.awt.Dimension(401, 30));

        txtHuyetAp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtHuyetAp.setToolTipText("160/160");
        txtHuyetAp.setBorder(null);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("mmHg");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtHuyetAp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHuyetAp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jLabelCanNang.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCanNang.setText("Cân Nặng");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        txtCanNang.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCanNang.setBorder(null);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Kg");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtCanNang, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCanNang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelChuanDoan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelChuanDoan.setText("Chuẩn Đoán");

        txtChuanDoan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Yêu Cầu");

        buttonGroupYCNV.add(rdbNhapVien);
        rdbNhapVien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdbNhapVien.setText("Nhập Viện");

        buttonGroupYCNV.add(rdbKhong);
        rdbKhong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdbKhong.setText("Không");

        jLabelChiPhi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelChiPhi.setText("Chi Phí Khám Bệnh");

        txtChiPhi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelHuyetAp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelHuyetAp.setText("Huyết Áp");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        txtNhipTim.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNhipTim.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Lần/Phút");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(txtNhipTim, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtNhipTim, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbbTenBenh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelChuanDoan)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtChuanDoan)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenBacSy)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdbNhapVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbKhong)
                                .addGap(73, 73, 73)
                                .addComponent(txtChiPhi))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(174, 174, 174)
                                .addComponent(jLabelChiPhi)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMach)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelNhipTim)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(1, 1, 1))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelHuyetAp)
                                            .addComponent(jLabelCanNang))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTenBacSy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbTenBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelMach)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelCanNang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jLabelNhipTim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelHuyetAp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelChuanDoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChuanDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabelChiPhi))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbNhapVien)
                    .addComponent(rdbKhong)
                    .addComponent(txtChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        btnAdd.setBackground(new java.awt.Color(64, 148, 94));
        btnAdd.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(64, 148, 94));
        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(64, 148, 94));
        btnNew.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

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

        btnPrint.setBackground(new java.awt.Color(64, 148, 94));
        btnPrint.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("In Phiếu");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPreviuos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPreviuos)
                    .addComponent(btnFirst)
                    .addComponent(btnPrint))
                .addGap(38, 38, 38))
        );

        tabs.addTab("Cập Nhật", jPanel1);

        tblKhamBenh.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhamBenh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhamBenhMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhamBenh);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Tìm Kiếm:");

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jLabel11.setOpaque(true);

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
                        .addComponent(btnRestore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll)
                        .addGap(23, 23, 23))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRestore)
                        .addComponent(btnDel)
                        .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Danh Sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabs))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
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
        this.index = tblKhamBenh.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
//        GiayNhapVienJDialog dialog = new GiayNhapVienJDialog(null, true);
//        dialog.init(bnsi.selectByID(k.getMaBenhNhan()).getTenBN(),
//                bnsi.selectByID(k.getMaBenhNhan()).getNgaysinh(),
//                bnsi.selectByID(k.getMaBenhNhan()).getGioitinh(),
//                bnsi.selectByID(k.getMaBenhNhan()).getSoBHYT(),
//                XDate.toString(k.getNgayKham(), "dd/MM/yyyy"),
//                k.getChuanDoan(),
//                nvsi.selectByID(k.getMaNV()).getTenNV());
//        dialog.setVisible(true);
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        if (bn != null) {
            HashMap para = new HashMap();
            para.put("maBN", bn.getMaBN());
            para.put("logo", XImages.getPath("/Images/logo02_2.png"));
            para.put("ConDau", XImages.getPath("/Images/ConDau.png"));
            para.put("ChuKy", XImages.getPath("/Images/chuKyPNG.png"));
            XPrinting.print("GiayNhapVien.jrxml", "Giấy Vào Viện", para);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void cbbTenBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenBNActionPerformed
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        if (bn != null) {
            txtNgSinh.setText(XDate.toString(bn.getNgaysinh(), "dd/MM/yyyy"));
            txtDiaChi.setText(bn.getDiachi());
        }
    }//GEN-LAST:event_cbbTenBNActionPerformed

    private void tblKhamBenhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhamBenhMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblKhamBenh.rowAtPoint(evt.getPoint());
            this.edit();

            list.clear();
            lis.clear();

            ckbSelectAll.setSelected(false);
            btnDel.setVisible(false);
            if (Auth.isManager()) {
                for (int i = 0; i < tblKhamBenh.getRowCount(); i++) {
                    tblKhamBenh.setValueAt(false, i, 14);
                }
            }
        } else {
            if (Auth.isManager()) {
                int row = tblKhamBenh.getSelectedRow();
                boolean isChecked = (boolean) tblKhamBenh.getValueAt(row, 14);

                if (isChecked) {
                    lis.add((int) tblKhamBenh.getValueAt(row, 1));
                    getData(row, list);
                    btnDel.setVisible(true);
                } else {
                    int id = Integer.valueOf(tblKhamBenh.getValueAt(row, 1).toString());
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
        }
    }//GEN-LAST:event_tblKhamBenhMouseClicked

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
                    for (KhamBenh kb : list) {
                        kbsi.insertRetore(new KhamBenh(kb.getSoPhieuKham(), kb.getNgayKham(), kb.getTrieuChung(),
                                kb.getHuyetAp(), kb.getNhipTim(), kb.getMach(), kb.getCanNang(),
                                kb.getChuanDoan(), kb.isYeuCau(), kb.getMaNV(), kb.getMaBenh(),
                                kb.getMaBenhNhan(), kb.getCPKham()));
                        id += kb.getSoPhieuKham() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(kbsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Khám Bệnh", id.substring(0, id.lastIndexOf(", ")),
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
                    kbsi.insertRetore(new KhamBenh(list.get(r).getSoPhieuKham(), list.get(r).getNgayKham(),
                            list.get(r).getTrieuChung(), list.get(r).getHuyetAp(), list.get(r).getNhipTim(),
                            list.get(r).getMach(), list.get(r).getCanNang(), list.get(r).getChuanDoan(),
                            list.get(r).isYeuCau(), list.get(r).getMaNV(), list.get(r).getMaBenh(),
                            list.get(r).getMaBenhNhan(), list.get(r).getCPKham()));
                    this.FillDataTable(kbsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Khám Bệnh", list.get(r).getSoPhieuKham() + "",
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
                kbsi.insertRetore(new KhamBenh(list.get(0).getSoPhieuKham(), list.get(0).getNgayKham(),
                        list.get(0).getTrieuChung(), list.get(0).getHuyetAp(), list.get(0).getNhipTim(),
                        list.get(0).getMach(), list.get(0).getCanNang(), list.get(0).getChuanDoan(),
                        list.get(0).isYeuCau(), list.get(0).getMaNV(), list.get(0).getMaBenh(),
                        list.get(0).getMaBenhNhan(), list.get(0).getCPKham()));

                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Khám Bệnh", list.get(0).getSoPhieuKham() + "",
                        "Khôi Phục", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                btnRestore.setVisible(false);
                this.FillDataTable(kbsi.getAll());
                isSucc = true;
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (kbsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Khám Bệnh", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(kbsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        for (int i = 0; i < tblKhamBenh.getRowCount(); i++) {
            if (ckbSelectAll.isSelected()) {
                tblKhamBenh.setValueAt(true, i, 14);
                lis.add((int) tblKhamBenh.getValueAt(i, 1));
                getData(i, list);
                btnDel.setVisible(true);
            } else {
                lis.clear();
                list.clear();
                tblKhamBenh.setValueAt(false, i, 14);
                btnDel.setVisible(false);
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed


    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.getText().isEmpty()) {
            this.FillDataTable(kbsi.getAll());
        } else {

            this.FillDataTable(kbsi.timKiemMaKB(txtSearch.getText()));
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
    private javax.swing.ButtonGroup buttonGroupYCNV;
    private javax.swing.JComboBox<String> cbbTenBN;
    private javax.swing.JComboBox<String> cbbTenBenh;
    private javax.swing.JCheckBox ckbSelectAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCanNang;
    private javax.swing.JLabel jLabelChiPhi;
    private javax.swing.JLabel jLabelChuanDoan;
    private javax.swing.JLabel jLabelHuyetAp;
    private javax.swing.JLabel jLabelMach;
    private javax.swing.JLabel jLabelNhipTim;
    private javax.swing.JLabel jLabelTrieuChung;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdbKhong;
    private javax.swing.JRadioButton rdbNhapVien;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblKhamBenh;
    private javax.swing.JTextField txtCanNang;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtChuanDoan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHuyetAp;
    private javax.swing.JTextField txtMach;
    private javax.swing.JTextField txtNgKham;
    private javax.swing.JTextField txtNgSinh;
    private javax.swing.JTextField txtNhipTim;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenBacSy;
    private javax.swing.JTextArea txtTrieuChung;
    // End of variables declaration//GEN-END:variables
}
