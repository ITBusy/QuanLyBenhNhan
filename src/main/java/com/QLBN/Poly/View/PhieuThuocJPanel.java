package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Entity.PhieuThuoc;
import com.QLBN.Poly.Entity.Thuoc;
import com.QLBN.Poly.Service.BenhNhanServiceImp;
import com.QLBN.Poly.Service.HoSoBenhAnServiceImp;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Service.PhieuThuocServiceImp;
import com.QLBN.Poly.Service.ThuocServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.XAutoCompletion;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XImages;
import com.QLBN.Poly.Utils.XOther;
import com.QLBN.Poly.Utils.XPrinting;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuThuocJPanel extends javax.swing.JPanel {

    ThuocServiceImp tsi = new ThuocServiceImp();
    BenhNhanServiceImp bnsi = new BenhNhanServiceImp();
    PhieuThuocServiceImp ptsi = new PhieuThuocServiceImp();
    NhanVienServiceImp nvsi = new NhanVienServiceImp();
    int index = -1;
    List<PhieuThuoc> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();
    List<Integer> lisPrint = new ArrayList<>();
    DefaultComboBoxModel modelTenBN;
    DefaultComboBoxModel modelTenThuoc;

    public PhieuThuocJPanel() {
        initComponents();
    }

    void init() {
        this.FillDataComboboxBenhNhan();
        this.FillDataComboboxThuoc();
        this.FillDataTable(ptsi.getAll());
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
        btnPrint.setEnabled(false);
        setTongTien();
    }

    void FillDataComboboxBenhNhan() {
        modelTenBN = (DefaultComboBoxModel) cbbTenBN.getModel();
        modelTenBN.removeAllElements();
        if (!bnsi.getAll().isEmpty()) {
            bnsi.getAll().forEach(bn -> {
                modelTenBN.addElement(bn);
            });
            cbbTenBN.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenBN);
        }
    }

    void FillDataComboboxThuoc() {
        modelTenThuoc = (DefaultComboBoxModel) cbbTenThuoc.getModel();
        modelTenThuoc.removeAllElements();
        if (!tsi.getAll().isEmpty()) {
            tsi.getAll().forEach(b -> {
                modelTenThuoc.addElement(b);
            });
            cbbTenThuoc.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenThuoc);
        }
    }

    void FillDataTable(List<PhieuThuoc> l) {
        String[] columnAdmin = {"STT", "Mã PT", "Mã Thuốc", "Số Lượng", "Mã Bác Sỹ", "Ngày Uống", "Lần/Ngày", "Barcode",
            "Mã Bệnh Nhân", "Ngày KĐ",
            "Tổng Tiền", "In Ấn", "Xóa"};
        String[] column = {"STT", "Mã PT", "Mã Thuốc", "Số Lượng", "Mã Bác Sỹ", "Ngày Uống", "Lần/Ngày", "Barcode",
            "Mã Bệnh Nhân", "Ngày KĐ", "Tổng Tiền", "In Ấn"};
        DefaultTableModel dtm = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 11:
                        return true;
                    case 12:
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 11:
                        return Boolean.class;
                    case 12:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

        };
        boolean isChecked = false;
        int i = 0;
        tblPhieuThuoc.setModel(dtm);
        DefaultTableModel model = (DefaultTableModel) tblPhieuThuoc.getModel();
        model.setRowCount(0);
        for (PhieuThuoc pt : l) {
            Object[] rowData = new Object[13];
            rowData[0] = i + 1;
            rowData[1] = pt.getSphieuthuoc();
            rowData[2] = pt.getMathuoc();
            rowData[3] = pt.getSoluong();
            rowData[4] = pt.getMaNV();
            rowData[5] = pt.getNgayUong();
            rowData[6] = pt.getSoLan_Ngay();
            rowData[7] = pt.getBarCode();
            rowData[8] = pt.getMaBN();
            rowData[9] = XDate.toString(pt.getNgKeDon(), "dd/MM/yyyy");
            rowData[10] = XOther.convertCurrency(pt.getTongTien()) + " VND";
            rowData[11] = false;
            rowData[12] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblPhieuThuoc.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblPhieuThuoc.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
    }

    void clear() {
        this.getDVT();
        this.getBarcodeByCombobox();
        XOther.isDigits('\u0000', txtSoLuong, txtNgayUong);
        txtTenBacSy.setToolTipText("");
        if (cbbTenThuoc.getSelectedItem() != null) {
            cbbTenThuoc.setSelectedIndex(0);
        }
        if (cbbTenBN.getSelectedItem() != null) {
            cbbTenBN.setSelectedIndex(0);
        }
        txtTenBacSy.setText(Auth.user.getTenNV());
        txtSoLuong.setText("");
        txtNgayUong.setText("");
        txtNgKeDon.setText(XDate.toString(new Date(), "dd/MM/yyyy"));
        txtTongTien.setText("0 VND");
        ckbSang.setSelected(true);
        ckbTrua.setSelected(false);
        ckbToi.setSelected(false);
        this.setStatus(true);
    }

    boolean checkForm() {
        if (txtSoLuong.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập số lượng");
            return false;
        } else if (txtNgayUong.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập ngày uống");
            return false;
        } else if (txtBarCode.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập barcode");
            return false;
        } else if (!txtSoLuong.getText().matches("^([1-9])+(\\d)*$")) {
            MsgBox.alert(this, "Số lượng phải là số");
            return false;
        } else if (!txtNgayUong.getText().matches("^([1-9])+(\\d)*$")) {
            MsgBox.alert(this, "Ngày uống phải là số");
            return false;
        } else if (!ckbSang.isSelected() && !ckbTrua.isSelected() && !ckbToi.isSelected()) {
            MsgBox.alert(this, "Vui lòng chọn số lần uống");
            return false;
        } else {
            return true;
        }
    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
//        btnPrint.setEnabled(!insertable);
        cbbTenBN.setEnabled(insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblPhieuThuoc.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void setModel(PhieuThuoc pt) {

        modelTenBN.setSelectedItem(bnsi.selectByID(pt.getMaBN()));
        modelTenThuoc.setSelectedItem(tsi.selectByID(pt.getMathuoc()));

        ckbSang.setSelected(false);
        ckbToi.setSelected(false);
        ckbTrua.setSelected(false);

        txtTenBacSy.setToolTipText(String.valueOf(pt.getSphieuthuoc()));
        txtNgKeDon.setText(XDate.toString(pt.getNgKeDon(), "dd/MM/yyyy"));
        txtTenBacSy.setText(nvsi.selectByID(pt.getMaNV()).getTenNV());
        cbbTenThuoc.setSelectedItem(modelTenThuoc.getSelectedItem());
        txtDVT.setText(pt.getSoluong().substring(pt.getSoluong().lastIndexOf(" ") + 1));
        txtDVT1.setText(pt.getNgayUong().substring(pt.getNgayUong().lastIndexOf(" ") + 1));
        cbbTenBN.setSelectedItem(modelTenBN.getSelectedItem());
        txtSoLuong.setText(pt.getSoluong().substring(0, pt.getSoluong().lastIndexOf(" ")));
        txtNgayUong.setText(pt.getNgayUong().substring(0, pt.getNgayUong().lastIndexOf(" ")));

        String[] arr = pt.getSoLan_Ngay().split("\\, ");
        JCheckBox[] ckb = {ckbSang, ckbTrua, ckbToi};
        for (JCheckBox ckb1 : ckb) {
            for (String arr1 : arr) {
                if (ckb1.getText().equals(arr1)) {
                    ckb1.setSelected(true);
                }
            }
        }
        txtBarCode.setText(pt.getBarCode());
        txtTongTien.setText(XOther.convertCurrency(pt.getTongTien()) + " VND");
        arr = null;
    }

    PhieuThuoc getModel() {
        PhieuThuoc pt = new PhieuThuoc();
        Thuoc k = (Thuoc) cbbTenThuoc.getSelectedItem();
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        if (!txtTenBacSy.getToolTipText().isEmpty()) {
            pt.setSphieuthuoc(Integer.parseInt(txtTenBacSy.getToolTipText()));
        }
        pt.setNgKeDon(XDate.toDate(txtNgKeDon.getText(), "dd/MM/yyyy"));
        pt.setMaNV(Auth.user.getMaNV());
        pt.setMathuoc(k.getMaThuoc());
        pt.setMaBN(bn.getMaBN());
        pt.setSoluong(txtSoLuong.getText() + " " + txtDVT.getText());
        pt.setNgayUong(txtNgayUong.getText() + " " + txtDVT1.getText());
        JCheckBox[] ckb = {ckbSang, ckbTrua, ckbToi};
        String soLan = "";
        for (int i = 0; i < ckb.length; i++) {
            if (ckb[i].isSelected()) {
                soLan += ckb[i].getText() + ", ";
            }
        }
        pt.setSoLan_Ngay(soLan.substring(0, soLan.lastIndexOf(", ")));
        pt.setBarCode(txtBarCode.getText());
        pt.setTongTien(XOther.convertDouble(txtTongTien.getText()));
        return pt;
    }

    void edit() {
        int id = (int) tblPhieuThuoc.getValueAt(index, 1);
        PhieuThuoc pt = ptsi.selectByID(id);
        if (pt != null) {
            this.setModel(pt);
            this.setStatus(false);
            tblPhieuThuoc.setRowSelectionInterval(index, index);
            tblPhieuThuoc.scrollRectToVisible(tblPhieuThuoc.getCellRect(index, 0, true));
        }
    }

    void insert() {
        if (checkForm()) {
            PhieuThuoc pt = getModel();
            if (!new HoSoBenhAnServiceImp().isDead(pt.getMaBN())) {
                if (ptsi.insert(pt) == 1) {
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Phiếu Thuốc", ptsi.getRowLast() + "", "Thêm", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();

                    this.FillDataTable(ptsi.getAll());
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
            PhieuThuoc pt = getModel();
            if (ptsi.update(pt) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Phiếu Thuốc", pt.getSphieuthuoc() + "", "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();

                this.FillDataTable(ptsi.getAll());
                this.clear();
                MsgBox.alert(this, "Cập nhật thành công");
            } else {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void setTongTien() {
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    if (txtSoLuong.getText().isEmpty() || !txtSoLuong.getText().matches("^([1-9])+(\\d)*$")) {
//                        txtTongTien.setText("0 VND");
//                    } else {
//                        try {
//                            Thuoc t = (Thuoc) cbbTenThuoc.getSelectedItem();
//                            BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
//                            if (t != null && bn != null) {
//                                if (bnsi.isBHYT(bn.getMaBN())) {
//                                    int sl = Integer.parseInt(txtSoLuong.getText());
//                                    double tongTien = ((sl * t.getDonGia()) / 100) * 30;
//                                    txtTongTien.setText(XOther.convertCurrency(tongTien) + " VND");
//                                } else {
//                                    int sl = Integer.parseInt(txtSoLuong.getText());
//                                    double tongTien = sl * t.getDonGia();
//                                    txtTongTien.setText(XOther.convertCurrency(tongTien) + " VND");
//                                }
//                            }
//                        } catch (NumberFormatException e) {
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                }
//            }
//
//        }.start();
    }

    void caretUpdateTongTien() {
        if (txtSoLuong.getText().isEmpty() || !txtSoLuong.getText().matches("^([1-9])+(\\d)*$")) {
            txtTongTien.setText("0 VND");
        } else {
            try {
                Thuoc t = (Thuoc) cbbTenThuoc.getSelectedItem();
                BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
                if (t != null && bn != null) {
                    if (bnsi.isBHYT(bn.getMaBN())) {
                        int sl = Integer.parseInt(txtSoLuong.getText());
                        double tongTien = ((sl * t.getDonGia()) / 100) * 30;
                        txtTongTien.setText(XOther.convertCurrency(tongTien) + " VND");
                    } else {
                        int sl = Integer.parseInt(txtSoLuong.getText());
                        double tongTien = sl * t.getDonGia();
                        txtTongTien.setText(XOther.convertCurrency(tongTien) + " VND");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void getDVT() {
        Thuoc t = (Thuoc) cbbTenThuoc.getSelectedItem();
        if (t != null) {
            txtDVT.setText(t.getDVT());
            txtDVT1.setText(t.getDVT());
        }
    }

    void getBarcodeByCombobox() {
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        if (bn != null) {
            PhieuThuoc pt = ptsi.getBarcodeByCombobox(bn.getMaBN());
            if (pt != null) {
                txtBarCode.setText(pt.getBarCode());
                btnRefresh.setEnabled(false);
            } else {
                txtBarCode.setText("");
            }
        }
        if (txtBarCode.getText().length() == 0) {
            btnRefresh.setEnabled(true);
        }
    }

    void getData(int row, List<PhieuThuoc> l) {// "Ngày Uống", "Lần/Ngày", "Barcode",
        int sphieuthuoc = (int) tblPhieuThuoc.getValueAt(row, 1);
        int Mathuoc = (int) tblPhieuThuoc.getValueAt(row, 2);
        String Soluong = (String) tblPhieuThuoc.getValueAt(row, 3);
        String MaNV = (String) tblPhieuThuoc.getValueAt(row, 4);
        String NgayUong = (String) tblPhieuThuoc.getValueAt(row, 5);
        String Lan_Ngay = (String) tblPhieuThuoc.getValueAt(row, 6);
        String Barcode = (String) tblPhieuThuoc.getValueAt(row, 7);
        int maBN = (int) tblPhieuThuoc.getValueAt(row, 8);
        String NgKeDon = (String) tblPhieuThuoc.getValueAt(row, 9);
        String tongTien = (String) tblPhieuThuoc.getValueAt(row, 10);

        l.add(new PhieuThuoc(sphieuthuoc, Mathuoc, Soluong, MaNV, NgayUong, Lan_Ngay, Barcode, maBN, XDate.toDate(NgKeDon, "dd/MM/yyyy"),
                XOther.convertDouble(tongTien)));
    }

    void getKeyCode(KeyEvent evt) {
        int keystrokes = evt.getModifiers() & KeyEvent.CTRL_MASK;
        int keyS = KeyEvent.VK_S;
        int keyA = KeyEvent.VK_A;
        int keyCode = evt.getKeyCode();
        if ((keyCode == keyS) && (keystrokes != 0) && btnAdd.isEnabled()) {
            this.insert();
        } else if ((keyCode == keyA) && (keystrokes != 0) && btnUpdate.isEnabled()) {
            this.update();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpSoLan_Ngay = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        cbbTenBN = new javax.swing.JComboBox<>();
        cbbTenThuoc = new javax.swing.JComboBox<>();
        txtTenBacSy = new javax.swing.JTextField();
        txtNgKeDon = new javax.swing.JTextField();
        txtDVT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNgayUong = new javax.swing.JTextField();
        txtDVT1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ckbSang = new javax.swing.JCheckBox();
        ckbToi = new javax.swing.JCheckBox();
        ckbTrua = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtBarCode = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuThuoc = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        ckbSelectAll = new javax.swing.JCheckBox();
        btnFirst = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setNextFocusableComponent(cbbTenThuoc);
        setPreferredSize(new java.awt.Dimension(1000, 493));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 153, 255)), "Cập Nhật", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tên Thuốc");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Tên Bác Sỹ");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Tên Bệnh Nhân");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Số Lượng");

        txtSoLuong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSoLuong.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSoLuongCaretUpdate(evt);
            }
        });
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Ngày Kê Đơn");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Tổng Tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setBackground(new java.awt.Color(255, 255, 255));
        txtTongTien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTongTien.setFocusable(false);

        btnAdd.setBackground(new java.awt.Color(64, 148, 94));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.setFocusPainted(false);
        btnAdd.setFocusable(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(64, 148, 94));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.setFocusPainted(false);
        btnUpdate.setFocusable(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(64, 148, 94));
        btnNew.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm Mới");
        btnNew.setFocusPainted(false);
        btnNew.setFocusable(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(64, 148, 94));
        btnPrint.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("In Ấn");
        btnPrint.setFocusPainted(false);
        btnPrint.setFocusable(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        cbbTenBN.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbbTenBN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenBNActionPerformed(evt);
            }
        });

        cbbTenThuoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbbTenThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenThuocActionPerformed(evt);
            }
        });

        txtTenBacSy.setEditable(false);
        txtTenBacSy.setBackground(new java.awt.Color(255, 255, 255));
        txtTenBacSy.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTenBacSy.setFocusable(false);

        txtNgKeDon.setEditable(false);
        txtNgKeDon.setBackground(new java.awt.Color(255, 255, 255));
        txtNgKeDon.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNgKeDon.setFocusable(false);

        txtDVT.setEditable(false);
        txtDVT.setBackground(new java.awt.Color(255, 255, 255));
        txtDVT.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtDVT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        txtDVT.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Ngày Uống");

        txtNgayUong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNgayUong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtNgayUong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNgayUongKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNgayUongKeyTyped(evt);
            }
        });

        txtDVT1.setEditable(false);
        txtDVT1.setBackground(new java.awt.Color(255, 255, 255));
        txtDVT1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtDVT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDVT1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        txtDVT1.setFocusable(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Số Lần/Ngày");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel3.setFocusable(false);

        ckbSang.setBackground(new java.awt.Color(255, 255, 255));
        ckbSang.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ckbSang.setSelected(true);
        ckbSang.setText("Sáng");
        ckbSang.setFocusPainted(false);
        ckbSang.setFocusable(false);

        ckbToi.setBackground(new java.awt.Color(255, 255, 255));
        ckbToi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ckbToi.setText("Tối");
        ckbToi.setFocusPainted(false);
        ckbToi.setFocusable(false);

        ckbTrua.setBackground(new java.awt.Color(255, 255, 255));
        ckbTrua.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ckbTrua.setText("Trưa");
        ckbTrua.setFocusPainted(false);
        ckbTrua.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(ckbSang)
                .addGap(18, 18, 18)
                .addComponent(ckbTrua)
                .addGap(18, 18, 18)
                .addComponent(ckbToi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(ckbTrua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ckbToi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ckbSang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Barcode");

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel5.setPreferredSize(new java.awt.Dimension(315, 32));

        txtBarCode.setEditable(false);
        txtBarCode.setBackground(new java.awt.Color(255, 255, 255));
        txtBarCode.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtBarCode.setBorder(null);

        btnRefresh.setBackground(new java.awt.Color(204, 204, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh_barcode_16px.png"))); // NOI18N
        btnRefresh.setBorder(null);
        btnRefresh.setFocusable(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgKeDon, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbTenThuoc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayUong, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDVT1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTenBacSy, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(cbbTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTenThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenBacSy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayUong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDVT1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgKeDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 153, 255)), "Danh Sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tblPhieuThuoc.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhieuThuoc.setFocusable(false);
        tblPhieuThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuThuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuThuoc);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
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
        jLabel10.setFocusable(false);
        jLabel10.setOpaque(true);

        btnRestore.setBackground(new java.awt.Color(64, 148, 94));
        btnRestore.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("Khôi Phục");
        btnRestore.setFocusPainted(false);
        btnRestore.setFocusable(false);
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(64, 148, 94));
        btnDel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setText("Xóa");
        btnDel.setFocusPainted(false);
        btnDel.setFocusable(false);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        ckbSelectAll.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbSelectAll.setText("Chọn Tất Cả");
        ckbSelectAll.setFocusPainted(false);
        ckbSelectAll.setFocusable(false);
        ckbSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbSelectAllActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(64, 148, 94));
        btnFirst.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<");
        btnFirst.setFocusPainted(false);
        btnFirst.setFocusable(false);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPreviuos.setBackground(new java.awt.Color(64, 148, 94));
        btnPreviuos.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPreviuos.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviuos.setText("<<");
        btnPreviuos.setFocusPainted(false);
        btnPreviuos.setFocusable(false);
        btnPreviuos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviuosActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(64, 148, 94));
        btnNext.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.setFocusPainted(false);
        btnNext.setFocusable(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(64, 148, 94));
        btnLast.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText(">|");
        btnLast.setFocusPainted(false);
        btnLast.setFocusable(false);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
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
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPreviuos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll)
                        .addGap(21, 21, 21))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRestore)
                        .addComponent(btnDel)
                        .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPreviuos)
                    .addComponent(btnFirst))
                .addGap(6, 6, 6))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHIẾU LĨNH THUỐC");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

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
        this.index = tblPhieuThuoc.getRowCount() - 1;
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
        HashMap para = new HashMap();
        para.put("maBN", lisPrint.get(0));
        para.put("icon", XImages.getPath("/Images/logo02_2.png"));
        XPrinting.print("DonThuoc.jrxml", "Đơn Thuốc", para);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblPhieuThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuThuocMouseClicked
        // chức  năng đỏ dữ liệu lên form
        this.index = tblPhieuThuoc.rowAtPoint(evt.getPoint());
        if (index >= 0) {
            this.edit();
        }

        // chức năng in ấn
        int rowPrint = tblPhieuThuoc.getSelectedRow();
        boolean isCheckPrint = (boolean) tblPhieuThuoc.getValueAt(rowPrint, 11);
        if (isCheckPrint) {
            String barcode = (String) tblPhieuThuoc.getValueAt(rowPrint, 7);
            for (int i = 0; i < tblPhieuThuoc.getRowCount(); i++) {
                String barcodeCount = (String) tblPhieuThuoc.getValueAt(i, 7);
                if (barcodeCount.equals(barcode)) {
                    tblPhieuThuoc.setValueAt(true, i, 11);
                    lisPrint.add((Integer) tblPhieuThuoc.getValueAt(i, 8));
                } else {
                    int qw = Integer.valueOf(tblPhieuThuoc.getValueAt(i, 8).toString());
                    for (int j = 0; j < lisPrint.size(); j++) {
                        if (lisPrint.get(j) == qw) {
                            lisPrint.remove(j);
                        }
                    }
                    tblPhieuThuoc.setValueAt(false, i, 11);
                }
            }
            btnPrint.setEnabled(true);
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < lisPrint.size(); i++) {
                if (!l.contains(lisPrint.get(i))) {
                    l.add(lisPrint.get(i));
                }
            }
            lisPrint.clear();
            lisPrint.addAll(l);
        } else {
            String barcode = (String) tblPhieuThuoc.getValueAt(rowPrint, 7);
            for (int i = 0; i < tblPhieuThuoc.getRowCount(); i++) {
                String barcodeCount = (String) tblPhieuThuoc.getValueAt(i, 7);
                int maBNCount = Integer.valueOf(tblPhieuThuoc.getValueAt(i, 8).toString());
                if (barcodeCount.equals(barcode)) {
                    tblPhieuThuoc.setValueAt(false, i, 11);
                    for (int j = 0; j < lisPrint.size(); j++) {
                        if (lisPrint.get(j) == maBNCount) {
                            lisPrint.remove(j);
                        }
                    }
                }
                if (lisPrint.isEmpty()) {
                    btnPrint.setEnabled(false);
                }
            }
        }

        // chức năng xóa 
        if (Auth.isManager()) {
            int row = tblPhieuThuoc.getSelectedRow();
            boolean isChecked = (boolean) tblPhieuThuoc.getValueAt(row, 12);

            if (isChecked) {
                lis.add((int) tblPhieuThuoc.getValueAt(row, 1));
                getData(row, list);
                btnDel.setVisible(true);
            } else {
                int id = Integer.valueOf(tblPhieuThuoc.getValueAt(row, 1).toString());
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
    }//GEN-LAST:event_tblPhieuThuocMouseClicked

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
                    for (PhieuThuoc pt : list) {
                        ptsi.insertRetore(new PhieuThuoc(pt.getSphieuthuoc(), pt.getMathuoc(), pt.getSoluong(),
                                pt.getMaNV(), pt.getNgayUong(), pt.getSoLan_Ngay(), pt.getBarCode(), pt.getMaBN(),
                                pt.getNgKeDon(), pt.getTongTien()));
                        id += pt.getSphieuthuoc() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(ptsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Phiếu Thuốc", id.substring(0, id.lastIndexOf(", ")),
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
                    ptsi.insertRetore(new PhieuThuoc(list.get(r).getSphieuthuoc(), list.get(r).getMathuoc(),
                            list.get(r).getSoluong(), list.get(r).getMaNV(), list.get(r).getNgayUong(),
                            list.get(r).getSoLan_Ngay(), list.get(r).getBarCode(), list.get(r).getMaBN(),
                            list.get(r).getNgKeDon(), list.get(r).getTongTien()));
                    this.FillDataTable(ptsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Phiếu Thuốc", list.get(r).getSphieuthuoc() + "",
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
                ptsi.insertRetore(new PhieuThuoc(list.get(0).getSphieuthuoc(), list.get(0).getMathuoc(),
                        list.get(0).getSoluong(), list.get(0).getMaNV(), list.get(0).getNgayUong(),
                        list.get(0).getSoLan_Ngay(), list.get(0).getBarCode(), list.get(0).getMaBN(),
                        list.get(0).getNgKeDon(), list.get(0).getTongTien()));

                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Phiếu Thuốc", list.get(0).getSphieuthuoc() + "",
                        "Khôi Phục", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                btnRestore.setVisible(false);
                this.FillDataTable(ptsi.getAll());
                isSucc = true;
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (ptsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Phiếu Thuốc", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(ptsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        for (int i = 0; i < tblPhieuThuoc.getRowCount(); i++) {
            if (ckbSelectAll.isSelected()) {
                tblPhieuThuoc.setValueAt(true, i, 12);
                lis.add((int) tblPhieuThuoc.getValueAt(i, 1));
                getData(i, list);
                btnDel.setVisible(true);
            } else {
                lis.clear();
                list.clear();
                tblPhieuThuoc.setValueAt(false, i, 12);
                btnDel.setVisible(false);
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            if (txtSearch.getText().isEmpty()) {
                this.FillDataTable(ptsi.getAll());
            } else {
                this.FillDataTable(ptsi.timKiemMaPT(txtSearch.getText()));
            }
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbbTenThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenThuocActionPerformed
        this.getDVT();
        this.caretUpdateTongTien();
    }//GEN-LAST:event_cbbTenThuocActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        String ma = null;
        String ma1 = null;
        for (int i = 0; i < 100000; i++) {
            ma = String.valueOf(1000000000 + Math.round(Math.random() * 899999999));
            ma1 = String.valueOf(1000000000 + Math.round(Math.random() * 899999999));
        }
        txtBarCode.setText(ma + ma1);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cbbTenBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenBNActionPerformed
        this.getBarcodeByCombobox();
    }//GEN-LAST:event_cbbTenBNActionPerformed

    private void txtSoLuongCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSoLuongCaretUpdate
        caretUpdateTongTien();
    }//GEN-LAST:event_txtSoLuongCaretUpdate

    private void txtSoLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyTyped
        if (txtSoLuong.getText().length() > 3) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoLuongKeyTyped

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        this.getKeyCode(evt);
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtNgayUongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayUongKeyReleased
        this.getKeyCode(evt);
    }//GEN-LAST:event_txtNgayUongKeyReleased

    private void txtNgayUongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayUongKeyTyped
        if (txtNgayUong.getText().length() > 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNgayUongKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpSoLan_Ngay;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPreviuos;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbTenBN;
    private javax.swing.JComboBox<String> cbbTenThuoc;
    private javax.swing.JCheckBox ckbSang;
    private javax.swing.JCheckBox ckbSelectAll;
    private javax.swing.JCheckBox ckbToi;
    private javax.swing.JCheckBox ckbTrua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPhieuThuoc;
    private javax.swing.JTextField txtBarCode;
    private javax.swing.JTextField txtDVT;
    private javax.swing.JTextField txtDVT1;
    private javax.swing.JTextField txtNgKeDon;
    private javax.swing.JTextField txtNgayUong;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenBacSy;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
