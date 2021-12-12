package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Service.BenhNhanServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XOther;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BenhNhanJPanel extends javax.swing.JPanel {
    
    BenhNhanServiceImp bnsi = new BenhNhanServiceImp();
    int index = -1;
    List<BenhNhan> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();
    
    public BenhNhanJPanel() {
        initComponents();
    }
    
    void init() {
        FillDataTable(bnsi.getAll());
        clear();
        if (Auth.isManager()) {
            ckbSelectAll.setVisible(true);
        } else {
            ckbSelectAll.setVisible(false);
        }
        btnRestore.setVisible(false);
        btnDel.setVisible(false);
        this.setStatus(true);
    }
    
    void FillDataTable(List<BenhNhan> l) {
        String[] columnAdmin = {"STT", "Mã BN", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Giới Tính",
            "Nghề Nghiệp", "Dân Tộc", "Quốc Tịch", "Đối Tượng", "Thời Hạn Từ", "Thời Hạn Đến", "Số BHYT",
            "Người Thân", "SDT Người Thân", "Xóa"};
        String[] column = {"STT", "Mã BN", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Giới Tính",
            "Nghề Nghiệp", "Dân Tộc", "Quốc Tịch", "Đối Tượng", "Thời Hạn Từ", "Thời Hạn Đến", "Số BHYT",
            "Người Thân", "SDT Người Thân"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 15;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 15 ? Boolean.class : String.class;
            }
        };
        boolean isChecked = false;
        tblBenhNhan.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblBenhNhan.getModel();
        model.setRowCount(0);
        int i = 0;
        for (BenhNhan bn : l) {
            Object[] rowData = new Object[16];
            rowData[0] = i + 1;
            rowData[1] = bn.getMaBN();
            rowData[2] = bn.getTenBN();
            rowData[3] = XDate.toString(bn.getNgaysinh(), "dd/MM/yyyy");
            rowData[4] = bn.getDiachi();
            rowData[5] = bn.getGioitinh();
            rowData[6] = bn.getNgheNghiep();
            rowData[7] = bn.getDanToc();
            rowData[8] = bn.getQuocTich();
            rowData[9] = bn.isDoituong() ? "BHYT" : "Thường";
            rowData[10] = bn.getBHYTHieuLucTu() == null ? null : XDate.toString(bn.getBHYTHieuLucTu(), "dd/MM/yyyy");
            rowData[11] = bn.getBHYTHieuLucDen() == null ? null : XDate.toString(bn.getBHYTHieuLucDen(), "dd/MM/yyyy");
            rowData[12] = bn.getSoBHYT();
            rowData[13] = bn.getNguoiThan();
            rowData[14] = bn.getSdtNguoiThan();
            rowData[15] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblBenhNhan.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblBenhNhan.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
    }
    
    void clear() {
        XOther.isAlphabetic(' ', txtHoTen, txtDanToc, txtDiaChi, txtQuocTich, txtNguoiThan, txtNgheNghiep);
        XOther.isDigits('\u0000', txtSDTNguoiThan);
        txtHoTen.setToolTipText("");
        txtHoTen.setText("");
        jDateChooserNgSinh.setDate(new Date());
        jDateChooserBHYTTu.setDate(null);
        jDateChooserBHYTDen.setDate(null);
        rdbNam.isSelected();
        txtNgheNghiep.setText("");
        txtDiaChi.setText("");
        txtSDTNguoiThan.setText("");
        rdbKhong.isSelected();
        txtNguoiThan.setText("");
        txtSoBHYT.setText("");
        txtQuocTich.setText("");
        txtDanToc.setText("");
        
        this.setStatus(true);
        this.setStutusBHYT(false);
    }
    
    void setModel(BenhNhan bn) {
        txtHoTen.setToolTipText(String.valueOf(bn.getMaBN()));
        txtHoTen.setText(bn.getTenBN());
        jDateChooserNgSinh.setDate(bn.getNgaysinh());
        txtDiaChi.setText(bn.getDiachi());
        ButtonModel bm = bn.getGioitinh().equals(rdbNam.getText()) ? rdbNam.getModel() : rdbNu.getModel();
        buttonGroupGioiTinh.setSelected(bm, true);
        txtNgheNghiep.setText(bn.getNgheNghiep());
        txtSDTNguoiThan.setText(bn.getSdtNguoiThan());
        ButtonModel bmDT = bn.isDoituong() ? rdbBHYT.getModel() : rdbKhong.getModel();
        buttonGroupDoiTuong.setSelected(bmDT, true);
        txtNguoiThan.setText(bn.getNguoiThan());
        txtDanToc.setText(bn.getDanToc());
        txtQuocTich.setText(bn.getQuocTich());
        
        if (bn.isDoituong()) {
            setStutusBHYT(true);
            jDateChooserBHYTTu.setDate(bn.getBHYTHieuLucTu());
            jDateChooserBHYTDen.setDate(bn.getBHYTHieuLucDen());
            txtSoBHYT.setText(bn.getSoBHYT());
        } else {
            setStutusBHYT(false);
        }
    }
    
    BenhNhan getModel() {
        BenhNhan bn = new BenhNhan();
        if (!txtHoTen.getToolTipText().isEmpty()) {
            bn.setMaBN(Integer.parseInt(txtHoTen.getToolTipText()));
        }
        bn.setTenBN(XOther.Rename(txtHoTen.getText()));
        bn.setNgaysinh(jDateChooserNgSinh.getDate());
        bn.setDiachi(XOther.Rename(txtDiaChi.getText()));
        bn.setGioitinh(rdbNam.isSelected() ? "Nam" : "Nữ");
        bn.setNgheNghiep(XOther.Rename(txtNgheNghiep.getText()));
        bn.setSdtNguoiThan(txtSDTNguoiThan.getText().trim());
        bn.setDoituong(rdbBHYT.isSelected());
        bn.setNguoiThan(XOther.Rename(txtNguoiThan.getText()));
        if (rdbBHYT.isSelected()) {
            if (checkBHYT()) {
                bn.setBHYTHieuLucTu(jDateChooserBHYTTu.getDate());
                bn.setBHYTHieuLucDen(jDateChooserBHYTDen.getDate());
                bn.setSoBHYT(txtSoBHYT.getText());
            }
        } else {
            bn.setBHYTHieuLucTu(null);
            bn.setBHYTHieuLucDen(null);
            bn.setSoBHYT(null);
        }
        bn.setDanToc(XOther.Rename(txtDanToc.getText()));
        bn.setQuocTich(XOther.Rename(txtQuocTich.getText()));
        return bn;
    }
    
    boolean checkForm() {
        Date dateNs = jDateChooserNgSinh.getDate();
        Date date = new Date();
        String[] NameError = {jLabelHoTen.getText(), jLabelDiaChi.getText(), jLabelNghiepNghiep.getText(),
            jLabelDanToc.getText(), jLabelQuocTich.getText(), jLabelNguoiThan.getText(),
            jLabelSDTNguoiThan.getText()};
        String[] Error = {txtHoTen.getText(), txtDiaChi.getText(), txtNgheNghiep.getText(),
            txtDanToc.getText(), txtQuocTich.getText(), txtNguoiThan.getText(), txtSDTNguoiThan.getText()};
        String msg = "";
        for (int i = 0; i < Error.length; i++) {
            if (Error[i].isEmpty()) {
                msg += NameError[i] + ", ";
            }
        }
        String[] Name = {jLabelHoTen.getText(), jLabelNghiepNghiep.getText(),
            jLabelDanToc.getText(), jLabelQuocTich.getText(), jLabelNguoiThan.getText()};
        String[] Err = {txtHoTen.getText(), txtNgheNghiep.getText(),
            txtDanToc.getText(), txtQuocTich.getText(), txtNguoiThan.getText()};
        String msgx = "";
        for (int i = 0; i < Err.length; i++) {
            if (Err[i].matches("^([0-9])*$")) {
                msgx += Name[i] + ", ";
            }
        }
        if (!msg.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + msg.substring(0, msg.lastIndexOf(", ")));
            return false;
        } else if (dateNs == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày sinh");
            return false;
        } else if ((XDate.getYear(dateNs) == XDate.getYear(date) && XDate.getMonth(dateNs) > XDate.getMonth(date))
                || (XDate.getYear(dateNs) == XDate.getYear(date) && XDate.getMonth(dateNs) == XDate.getMonth(date)
                && XDate.getDay(dateNs) > XDate.getDay(date))) {
            MsgBox.alert(this, "Ngày sinh không hợp lệ! (Hiện tại: " + XDate.toString(date, "dd/MM/yyyy") + ")");
            return false;
        } else if (!msgx.isEmpty()) {
            MsgBox.alert(this, msgx.substring(0, msgx.lastIndexOf(", ")) + ": phải là số");
            return false;
        } else if (!txtSDTNguoiThan.getText().matches("^(09|03|07|08|05)+([0-9]{8})$")) {
            MsgBox.alert(this, "Số điện thoại không hợp lệ");
            return false;
        } else {
            return true;
        }
    }
//  } else if (!txtSDTNguoiThan.getText().matches("^([A-Z][0-9]{2,})$")) {
//            MsgBox.alert(this, "Số buồng phải bắt đầu bằng A-Z và số");
//            return false;

    boolean checkBHYT() {
        Date tu = jDateChooserBHYTTu.getDate();
        Date den = jDateChooserBHYTDen.getDate();
        Date tuoi = jDateChooserNgSinh.getDate();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        if (tu != null) {
            c.setTime(tu);
            c.add(Calendar.YEAR, 1);
//            jDateChooserBHYTDen.setMinSelectableDate(c.getTime());
        }
        
        if (tu == null || den == null || txtSoBHYT.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập "
                    + ((tu == null && den == null && txtSoBHYT.getText().isEmpty())
                    ? "thời hạn BHYT và số BHYT"
                    : (tu == null || den == null ? "thời hạn BHYT" : "số BHYT")));
            return false;
        } else if (XDate.getYear(tu) > XDate.getYear(date)) {
            MsgBox.alert(this, "Ngày bắt đầu BHYT không quá thời gian hiện tại");
            return false;
        } else if (XDate.getYear(tu) < XDate.getYear(tuoi)) {
            MsgBox.alert(this, "Ngày bắt đầu BHYT không thể nhỏ hơn ngày sinh");
            return false;
        } else if ((XDate.getYear(tu) == XDate.getYear(date)
                && XDate.getMonth(tu) > XDate.getMonth(date))
                || (XDate.getYear(tu) == XDate.getYear(date))
                && XDate.getMonth(tu) == XDate.getMonth(date)
                && XDate.getDay(tu) > XDate.getDay(date)) {
            MsgBox.alert(this, "Ngày bắt đầu BHYT không quá thời gian hiện tại");
            return false;
        } else if ((XDate.getYear(tu) == XDate.getYear(tuoi)
                && XDate.getMonth(tu) < XDate.getMonth(tuoi))
                || (XDate.getYear(tu) == XDate.getYear(tuoi))
                && XDate.getMonth(tu) == XDate.getMonth(tuoi)
                && XDate.getDay(tu) < XDate.getDay(tuoi)) {
            MsgBox.alert(this, "Ngày bắt đầu BHYT không thể nhỏ hơn ngày sinh");
            return false;
        } else if (XDate.getYear(den) < XDate.getYear(c.getTime())) {
            MsgBox.alert(this, "Ngày kết thúc BHYT không hợp lệ");
            return false;
        } else if (XDate.getYear(den) < XDate.getYear(date)) {
            MsgBox.alert(this, "BHYT đã hết hạn");
            return false;
        } else if ((XDate.getYear(den) == XDate.getYear(date)
                && XDate.getMonth(den) < XDate.getMonth(date))
                || (XDate.getYear(den) == XDate.getYear(date))
                && XDate.getMonth(den) == XDate.getMonth(date)
                && XDate.getDay(den) < XDate.getDay(date)) {
            MsgBox.alert(this, "BHYT đã hết hạn");
            return false;
        } else if ((XDate.getYear(den) == XDate.getYear(c.getTime())
                && XDate.getMonth(den) < XDate.getMonth(c.getTime()))
                || (XDate.getYear(den) == XDate.getYear(c.getTime()))
                && XDate.getMonth(den) == XDate.getMonth(c.getTime())
                && XDate.getDay(den) < XDate.getDay(c.getTime())) {
            MsgBox.alert(this, "Ngày kết thúc BHYT không hợp lệ");
            return false;
        } else if (!txtSoBHYT.getText().matches("([A-Z]{2,}[0-9]{13})")) {
            MsgBox.alert(this, "Số BHYT không đúng");
            return false;
        } else {
            return true;
        }
    }
    
    void insert() {
        if (checkForm()) {
            BenhNhan bn = getModel();
            if (bnsi.insert(bn) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Bệnh Nhân", bnsi.getRowLast() + "", "Thêm", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                this.FillDataTable(bnsi.getAll());
                this.clear();
                MsgBox.alert(this, "Thêm mới thành công!");
            } else {
                MsgBox.alert(this, "Thêm mới thất bại!");
            }
        }
    }
    
    void update() {
        if (checkForm()) {
            BenhNhan bn = getModel();
            if (bnsi.update(bn) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Bệnh Nhân", bn.getMaBN() + "", "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                this.FillDataTable(bnsi.getAll());
                this.clear();
                MsgBox.alert(this, "Cập nhật thành công!");
            } else {
                MsgBox.alert(this, "Cập nhật thất bại!");
            }
        }
    }
    
    void Edit() {
        int maBN = (int) tblBenhNhan.getValueAt(this.index, 1);
        BenhNhan bn = bnsi.selectByID(maBN);
        if (bn != null) {
            this.setModel(bn);
            this.setStatus(false);
        }
    }
    
    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        
        boolean first = this.index > 0;
        boolean last = this.index < tblBenhNhan.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }
    
    void setStutusBHYT(boolean st) {
        jDateChooserBHYTTu.setVisible(st);
        jDateChooserBHYTDen.setVisible(st);
        txtSoBHYT.setVisible(st);
        
        jLabelTu.setVisible(st);
        jLabelDen.setVisible(st);
        jLabel1SoBHYT.setVisible(st);
    }
    
    void getData(int row, List<BenhNhan> l) {
        int maBN = (int) tblBenhNhan.getValueAt(row, 1);
        String tenBN = (String) tblBenhNhan.getValueAt(row, 2);
        String Ngaysinh = (String) tblBenhNhan.getValueAt(row, 3);
        String Diachi = (String) tblBenhNhan.getValueAt(row, 4);
        String Gioitinh = (String) tblBenhNhan.getValueAt(row, 5);
        String ngheNghiep = (String) tblBenhNhan.getValueAt(row, 6);
        String danToc = (String) tblBenhNhan.getValueAt(row, 7);
        String QuocTich = (String) tblBenhNhan.getValueAt(row, 8);
        String dt = (String) tblBenhNhan.getValueAt(row, 9);
        String BHYTHieuLucTu = (String) tblBenhNhan.getValueAt(row, 10);
        String BHYTHieuLucDen = (String) tblBenhNhan.getValueAt(row, 11);
        String SoBHYT = (String) tblBenhNhan.getValueAt(row, 12);
        String nguoiThan = (String) tblBenhNhan.getValueAt(row, 13);
        String sdtNguoiThan = (String) tblBenhNhan.getValueAt(row, 14);
        l.add(new BenhNhan(maBN, tenBN,
                XDate.toDate(Ngaysinh, "dd/MM/yyyy"),
                Diachi,
                Gioitinh,
                ngheNghiep,
                danToc,
                QuocTich,
                BHYTHieuLucTu == null ? null : XDate.toDate(BHYTHieuLucTu, "dd/MM/yyyy"),
                BHYTHieuLucDen == null ? null : XDate.toDate(BHYTHieuLucDen, "dd/MM/yyyy"),
                SoBHYT == null ? null : SoBHYT,
                nguoiThan,
                sdtNguoiThan,
                dt.equals("BHYT")));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGioiTinh = new javax.swing.ButtonGroup();
        buttonGroupDoiTuong = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabelHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabelDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabelNghiepNghiep = new javax.swing.JLabel();
        txtNgheNghiep = new javax.swing.JTextField();
        jLabelSDTNguoiThan = new javax.swing.JLabel();
        txtSDTNguoiThan = new javax.swing.JTextField();
        jLabelNguoiThan = new javax.swing.JLabel();
        txtNguoiThan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jDateChooserNgSinh = new com.toedter.calendar.JDateChooser();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        rdbBHYT = new javax.swing.JRadioButton();
        rdbKhong = new javax.swing.JRadioButton();
        jLabelDanToc = new javax.swing.JLabel();
        txtDanToc = new javax.swing.JTextField();
        jLabelQuocTich = new javax.swing.JLabel();
        txtQuocTich = new javax.swing.JTextField();
        jLabelTu = new javax.swing.JLabel();
        jDateChooserBHYTTu = new com.toedter.calendar.JDateChooser();
        jLabelDen = new javax.swing.JLabel();
        jDateChooserBHYTDen = new com.toedter.calendar.JDateChooser();
        jLabel1SoBHYT = new javax.swing.JLabel();
        txtSoBHYT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBenhNhan = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        ckbSelectAll = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(690, 444));

        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));

        jLabelHoTen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelHoTen.setText("Họ Tên");

        txtHoTen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtHoTen.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        txtHoTen.setMinimumSize(new java.awt.Dimension(0, 0));
        txtHoTen.setVerifyInputWhenFocusTarget(false);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Ngày Sinh");

        jLabelDiaChi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDiaChi.setText("Địa Chỉ");

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Giới Tính");

        jLabelNghiepNghiep.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNghiepNghiep.setText("Nghề Nghiệp");

        txtNgheNghiep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNgheNghiep.setToolTipText("");

        jLabelSDTNguoiThan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelSDTNguoiThan.setText("SĐT Người Thân");

        txtSDTNguoiThan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSDTNguoiThan.setToolTipText("");
        txtSDTNguoiThan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTNguoiThanKeyTyped(evt);
            }
        });

        jLabelNguoiThan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNguoiThan.setText("Người Thân");

        txtNguoiThan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNguoiThan.setVerifyInputWhenFocusTarget(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Đối Tượng");

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

        btnLast.setBackground(new java.awt.Color(64, 148, 94));
        btnLast.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
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

        btnPreviuos.setBackground(new java.awt.Color(64, 148, 94));
        btnPreviuos.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPreviuos.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviuos.setText("<<");
        btnPreviuos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviuosActionPerformed(evt);
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

        jDateChooserNgSinh.setDateFormatString("dd/MM/yyyy");
        jDateChooserNgSinh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jDateChooserNgSinh.setMaxSelectableDate(new Date());

        buttonGroupGioiTinh.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdbNam.setSelected(true);
        rdbNam.setText("Nam");

        buttonGroupGioiTinh.add(rdbNu);
        rdbNu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdbNu.setText("Nữ");

        buttonGroupDoiTuong.add(rdbBHYT);
        rdbBHYT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rdbBHYT.setText("BHYT");
        rdbBHYT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbBHYTActionPerformed(evt);
            }
        });

        buttonGroupDoiTuong.add(rdbKhong);
        rdbKhong.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rdbKhong.setSelected(true);
        rdbKhong.setText("Không");
        rdbKhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbKhongActionPerformed(evt);
            }
        });

        jLabelDanToc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDanToc.setText("Dân Tộc");

        txtDanToc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelQuocTich.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelQuocTich.setText("Quốc Tịch");

        txtQuocTich.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelTu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTu.setText("Từ");

        jDateChooserBHYTTu.setDateFormatString("dd/MM/yyyy");
        jDateChooserBHYTTu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelDen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDen.setText("Đến");

        jDateChooserBHYTDen.setDateFormatString("dd/MM/yyyy");
        jDateChooserBHYTDen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel1SoBHYT.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1SoBHYT.setText("Số BHYT");

        txtSoBHYT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(114, 114, 114))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdbBHYT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbKhong)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jDateChooserBHYTTu, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserBHYTDen, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1SoBHYT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoBHYT))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                            .addComponent(jDateChooserNgSinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNghiepNghiep, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdbNam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdbNu)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNgheNghiep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDanToc, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                            .addComponent(txtQuocTich, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                            .addComponent(txtSDTNguoiThan, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                            .addComponent(jLabelSDTNguoiThan)
                            .addComponent(jLabelNguoiThan)
                            .addComponent(jLabelDanToc)
                            .addComponent(jLabelQuocTich)
                            .addComponent(txtNguoiThan, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPreviuos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast)))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoTen)
                    .addComponent(jLabelDanToc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelQuocTich))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelDiaChi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelNguoiThan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNguoiThan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbNam)
                    .addComponent(rdbNu))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNghiepNghiep)
                    .addComponent(jLabelSDTNguoiThan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgheNghiep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDTNguoiThan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbKhong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserBHYTDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1SoBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserBHYTTu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPreviuos)
                    .addComponent(btnFirst))
                .addContainerGap())
        );

        tabs.addTab("Cập Nhật", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));

        tblBenhNhan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBenhNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBenhNhanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBenhNhan);

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
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
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Danh Sách", jPanel2);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("THÔNG TIN BỆNH NHÂN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (rdbBHYT.isSelected()) {
            if (checkBHYT()) {
                insert();
            }
        } else {
            insert();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (rdbBHYT.isSelected()) {
            if (checkBHYT()) {
                update();
            }
        } else {
            update();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.index = 0;
        this.Edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviuosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviuosActionPerformed
        this.index--;
        this.Edit();
    }//GEN-LAST:event_btnPreviuosActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.index++;
        this.Edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.index = tblBenhNhan.getRowCount() - 1;
        this.Edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblBenhNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBenhNhanMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblBenhNhan.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.Edit();
                tabs.setSelectedIndex(0);
            }
            list.clear();
            lis.clear();
            
            ckbSelectAll.setSelected(false);
            btnDel.setVisible(false);
            if (Auth.isManager()) {
                for (int i = 0; i < tblBenhNhan.getRowCount(); i++) {
                    tblBenhNhan.setValueAt(false, i, 15);
                }
            }
        } else {
            if (Auth.isManager()) {
                int row = tblBenhNhan.getSelectedRow();
                boolean isChecked = (boolean) tblBenhNhan.getValueAt(row, 15);
                boolean isExist = false;
                
                for (int i = 0; i < bnsi.getMaBN().size(); i++) {
                    int id = Integer.valueOf(tblBenhNhan.getValueAt(row, 1).toString());
                    if (bnsi.getMaBN().get(i) == id) {
                        isExist = true;
                    }
                }
                if (isChecked) {
                    if (isExist) {
                        lis.add((int) tblBenhNhan.getValueAt(row, 1));
                        getData(row, list);
                        btnDel.setVisible(true);
                    } else {
                        MsgBox.alert(this, "Mã bệnh nhân đang được điều trị không thể xóa");
                        tblBenhNhan.setValueAt(false, row, 15);
                    }
                } else {
                    int id = Integer.valueOf(tblBenhNhan.getValueAt(row, 1).toString());
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
    }//GEN-LAST:event_tblBenhNhanMouseClicked

    private void rdbBHYTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbBHYTActionPerformed
        setStutusBHYT(true);
    }//GEN-LAST:event_rdbBHYTActionPerformed

    private void rdbKhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbKhongActionPerformed
        setStutusBHYT(false);
    }//GEN-LAST:event_rdbKhongActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (!txtSearch.getText().isEmpty()) {
            this.FillDataTable(bnsi.timKiemTenBN(txtSearch.getText()));
        } else {
            this.FillDataTable(bnsi.getAll());
        }
    }//GEN-LAST:event_txtSearchKeyReleased
    
    public static int r;

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        if (list.size() > 1) {
            Object[] obj = {"Khôi phục tất cả", "Khôi phục một dòng"};
            int indexes = JOptionPane.showOptionDialog(this, "Khôi phục " + list.size() + " dòng",
                    "Thông Báo", 0, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
            switch (indexes) {
                case 0: {
                    boolean isSucc = false;
                    String id = "";
                    for (BenhNhan bn : list) {
                        bnsi.insertRetore(new BenhNhan(bn.getMaBN(), bn.getTenBN(), bn.getNgaysinh(), bn.getDiachi(),
                                bn.getGioitinh(), bn.getNgheNghiep(), bn.getDanToc(), bn.getQuocTich(),
                                bn.getBHYTHieuLucTu(), bn.getBHYTHieuLucDen(), bn.getSoBHYT(), bn.getNguoiThan(),
                                bn.getSdtNguoiThan(), bn.isDoituong()));
                        id += bn.getMaBN() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(bnsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Bệnh Nhân", id.substring(0, id.lastIndexOf(", ")),
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
                    bnsi.insertRetore(new BenhNhan(list.get(r).getMaBN(), list.get(r).getTenBN(), list.get(r).getNgaysinh(),
                            list.get(r).getDiachi(), list.get(r).getGioitinh(), list.get(r).getNgheNghiep(),
                            list.get(r).getDanToc(), list.get(r).getQuocTich(), list.get(r).getBHYTHieuLucTu(),
                            list.get(r).getBHYTHieuLucDen(), list.get(r).getSoBHYT(), list.get(r).getNguoiThan(),
                            list.get(r).getSdtNguoiThan(), list.get(r).isDoituong()));
                    this.FillDataTable(bnsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Bệnh Nhân", list.get(r).getMaBN() + "",
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
//                for (BenhNhan bn : list) {
                bnsi.insertRetore(new BenhNhan(list.get(0).getMaBN(), list.get(0).getTenBN(),
                        list.get(0).getNgaysinh(), list.get(0).getDiachi(),
                        list.get(0).getGioitinh(), list.get(0).getNgheNghiep(),
                        list.get(0).getDanToc(), list.get(0).getQuocTich(),
                        list.get(0).getBHYTHieuLucTu(), list.get(0).getBHYTHieuLucDen(),
                        list.get(0).getSoBHYT(), list.get(0).getNguoiThan(),
                        list.get(0).getSdtNguoiThan(), list.get(0).isDoituong()));
                
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Bệnh Nhân", list.get(0).getMaBN() + "",
                        "Khôi Phục", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                btnRestore.setVisible(false);
                this.FillDataTable(bnsi.getAll());
                isSucc = true;
//                }
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (bnsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Bệnh Nhân", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(bnsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        List<Integer> str = bnsi.getMaBN();
        for (int i = 0; i < tblBenhNhan.getRowCount(); i++) {
            for (int j = 0; j < str.size(); j++) {
                if (ckbSelectAll.isSelected()) {
                    int id = Integer.valueOf(tblBenhNhan.getValueAt(i, 1).toString());
                    if (str.get(j) == id) {
                        tblBenhNhan.setValueAt(true, i, 15);
                        lis.add((int) tblBenhNhan.getValueAt(i, 1));
                        getData(i, list);
                        btnDel.setVisible(true);
                    }
                } else {
                    lis.clear();
                    list.clear();
                    tblBenhNhan.setValueAt(false, i, 15);
                    btnDel.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        if (Auth.isManager()) {
            if (tabs.getSelectedIndex() == 0) {
                ckbSelectAll.setSelected(false);
                for (int i = 0; i < tblBenhNhan.getRowCount(); i++) {
                    tblBenhNhan.setValueAt(false, i, 15);
                    btnDel.setVisible(false);
                    btnRestore.setVisible(false);
                    lis.clear();
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_tabsMouseClicked

    private void txtSDTNguoiThanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTNguoiThanKeyTyped
        if(txtSDTNguoiThan.getText().length() > 9){
            evt.consume();
        }
    }//GEN-LAST:event_txtSDTNguoiThanKeyTyped


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
    private javax.swing.ButtonGroup buttonGroupDoiTuong;
    private javax.swing.ButtonGroup buttonGroupGioiTinh;
    private javax.swing.JCheckBox ckbSelectAll;
    private com.toedter.calendar.JDateChooser jDateChooserBHYTDen;
    private com.toedter.calendar.JDateChooser jDateChooserBHYTTu;
    private com.toedter.calendar.JDateChooser jDateChooserNgSinh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel1SoBHYT;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelDanToc;
    private javax.swing.JLabel jLabelDen;
    private javax.swing.JLabel jLabelDiaChi;
    private javax.swing.JLabel jLabelHoTen;
    private javax.swing.JLabel jLabelNghiepNghiep;
    private javax.swing.JLabel jLabelNguoiThan;
    private javax.swing.JLabel jLabelQuocTich;
    private javax.swing.JLabel jLabelSDTNguoiThan;
    private javax.swing.JLabel jLabelTu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbBHYT;
    private javax.swing.JRadioButton rdbKhong;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblBenhNhan;
    private javax.swing.JTextField txtDanToc;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtNgheNghiep;
    private javax.swing.JTextField txtNguoiThan;
    private javax.swing.JTextField txtQuocTich;
    private javax.swing.JTextField txtSDTNguoiThan;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoBHYT;
    // End of variables declaration//GEN-END:variables
}
