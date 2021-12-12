package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Entity.NhanVien;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XOther;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NhanVienJPanel extends javax.swing.JPanel {

    boolean isSuccessfully = false;
    boolean checkForm;
    public static int checkLength = 0;
    int index = -1;
    NhanVienServiceImp nvsi = new NhanVienServiceImp();
    List<NhanVien> list = new ArrayList<>();
    List<String> lis = new ArrayList<>();

    public NhanVienJPanel() {
        initComponents();

    }

    void init() {
        this.FillDataTable(nvsi.getAll());
        this.setStatus(true);
        clear();
        if (Auth.isManager()) {
            ckbSelectAll.setVisible(true);
        } else {
            ckbSelectAll.setVisible(false);
        }
        btnXoa.setVisible(false);
        btnRestore.setVisible(false);
    }

    void insert() {
        if (checkForm()) {
            NhanVien nv = getModel();
            if (nvsi.insert(nv) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Nhân Viên", nv.getMaNV(), "Thêm", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                this.FillDataTable(nvsi.getAll());
                isSuccessfully = true;
                this.clear();
                MsgBox.alert(this, "Thêm mới thành công!");
            }
        }
    }

    void FillDataTable(List<NhanVien> list) {
        String[] columnAdmin = {"Mã NV", "Mật Khẩu", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Điện Thoại", "Email", "Giới Tính",
            "Vai Trò", "Active", "Xóa"};
        String[] column = {"Mã NV", "Mật Khẩu", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Điện Thoại", "Email", "Giới Tính",
            "Vai Trò"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 9:
                        return true;
                    case 10:
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 9:
                        return Boolean.class;
                    case 10:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        boolean isChecked = false;
        tblNhanVien.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        list.forEach(nv -> {
            model.addRow(new Object[]{
                nv.getMaNV(),
                nv.getMatKhau(),
                nv.getTenNV(),
                XDate.toString(nv.getNgaysinhNV(), "dd/MM/yyyy"),
                nv.getDiachi(),
                nv.getSodienthoai(),
                nv.getEmail(),
                nv.getGioitinh(),
                nv.getVaiTro(),
                nv.isActive(),
                isChecked
            });
        });
        tblNhanVien.setRowHeight(25);
    }

    boolean checkFormUpdate() {
        Calendar calendar = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -18); // trừ trong java
        calendar.setTime(c.getTime());
        String[] nameError = {jLabelHoTen.getText(),
            jLabelDiaChi.getText(), jLabelSDT.getText(), jLabel1Email.getText()};
        String[] title = {txtHoTen.getText(), txtDiaChi.getText(), txtSDT.getText(),
            txtEmail.getText()};
        String mess = "";
        for (int i = 0; i < title.length; i++) {
            if (title[i].isEmpty()) {
                mess += nameError[i] + ", ";
            }
        }
        if (!mess.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + mess.substring(0, mess.lastIndexOf(", ")));
            return false;
        } else if (txtHoTen.getText().matches("\\d+(.\\d+)*")) {
            MsgBox.alert(this, "Họ tên là chữ");
            return false;
        } else if (!txtSDT.getText().matches("^(09|03|07|08|05)+([0-9]{8})$")) {
            MsgBox.alert(this, "Số điện thoại không hợp lệ");
            return false;
        } else if (!txtEmail.getText().matches("^[\\w-\\+]+(\\.[\\w]+)*@[a-z]{3,}+(\\.[a-z]{3,}+)?(\\.[a-z]{2,})$")) {
            MsgBox.alert(this, "Email Không Hợp Lệ\nVD: abc@gmail.com.vn hoặc abc@gmail.com");
            return false;
        } else if (jDateChooserNgSinh.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày sinh");
            return false;
        } else if (XDate.getYear(jDateChooserNgSinh.getDate()) > XDate.getYear(calendar.getTime())) {
            MsgBox.alert(this, "Bạn chưa đủ 18 tuổi không thể đăng ký");
            return false;
        } else if ((XDate.getYear(jDateChooserNgSinh.getDate()) == XDate.getYear(calendar.getTime())
                && XDate.getMonth(jDateChooserNgSinh.getDate()) > XDate.getMonth(calendar.getTime()))
                || (XDate.getYear(jDateChooserNgSinh.getDate()) == XDate.getYear(calendar.getTime())
                && XDate.getMonth(jDateChooserNgSinh.getDate()) == XDate.getMonth(calendar.getTime())
                && XDate.getDay(jDateChooserNgSinh.getDate()) > XDate.getDay(calendar.getTime()))) {

            MsgBox.alert(this, "Bạn chưa đủ 18 tuổi không thể đăng ký");
            return false;
        } else {
            return true;
        }
    }

    boolean checkForm() {
        Calendar calendar = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -18); // trừ trong java
        calendar.setTime(c.getTime());
        String[] nameError = {jLabelMatKhau.getText(), jLabelHoTen.getText(),
            jLabelDiaChi.getText(), jLabelSDT.getText(), jLabel1Email.getText(), jLabel1MaXN.getText()};
        String[] title = {new String(txtMatKhau.getPassword()), txtHoTen.getText(), txtDiaChi.getText(), txtSDT.getText(),
            txtEmail.getText(), txtMaXacNhan.getText()};
        String mess = "";
        for (int i = 0; i < title.length; i++) {
            if (title[i].isEmpty()) {
                mess += nameError[i] + ", ";
            }
        }
        if (!mess.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + mess.substring(0, mess.lastIndexOf(", ")));
            return false;
        } else if (!new String(txtMatKhau.getPassword()).matches("[A-Z][a-z]+[0-9]+(\\w)*")) {
            MsgBox.alert(this, "Mật khẩu phải bắt đầu bằng chữ hoa.EX: Abc123");
            return false;
        } else if (checkLength == 2) {
            MsgBox.alert(this, "Mật Khẩu quá yếu");
            return false;
        } else if (txtHoTen.getText().matches("\\d+(.\\d+)*")) {
            MsgBox.alert(this, "Họ tên là chữ");
            return false;
        } else if (!txtSDT.getText().matches("^(09|03|07|08|05)+([0-9]{8})$")) {
            MsgBox.alert(this, "Số điện thoại không hợp lệ");
            return false;
        } else if (!txtEmail.getText().matches("^[\\w-\\+]+(\\.[\\w]+)*@[a-z]{3,}+(\\.[a-z]{3,}+)?(\\.[a-z]{2,})$")) {
            MsgBox.alert(this, "Email Không Hợp Lệ\nVD: abc@gmail.com.vn hoặc abc@gmail.com");
            return false;
        } else if (jDateChooserNgSinh.getDate() == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày sinh");
            return false;
        } else if (XDate.getYear(jDateChooserNgSinh.getDate()) > XDate.getYear(calendar.getTime())) {
            MsgBox.alert(this, "Bạn chưa đủ 18 tuổi không thể đăng ký");
            return false;
        } else if ((XDate.getYear(jDateChooserNgSinh.getDate()) == XDate.getYear(calendar.getTime())
                && XDate.getMonth(jDateChooserNgSinh.getDate()) > XDate.getMonth(calendar.getTime()))
                || (XDate.getYear(jDateChooserNgSinh.getDate()) == XDate.getYear(calendar.getTime())
                && XDate.getMonth(jDateChooserNgSinh.getDate()) == XDate.getMonth(calendar.getTime())
                && XDate.getDay(jDateChooserNgSinh.getDate()) > XDate.getDay(calendar.getTime()))) {

            MsgBox.alert(this, "Bạn chưa đủ 18 tuổi không thể đăng ký");
            return false;
        } else if (!new String(txtNhapLaiMatKhau.getPassword()).equals(new String(txtMatKhau.getPassword()))) {
            MsgBox.alert(this, "Mật khẩu không khớp!");
            return false;
        } else if (txtMaXacNhan.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập mã xác nhận!");
            return false;
        } else if (!txtMaXacNhan.getText().equalsIgnoreCase(XOther.IDVerify)) {
            MsgBox.alert(this, "Mã xác nhận không đúng");
            return false;
        } else {
            return true;
        }
    }

    void edit() {
        try {
            String manv = (String) tblNhanVien.getValueAt(this.index, 0);
            NhanVien model = nvsi.selectByID(manv);
            if (model != null) {
                lblLevel_1.setOpaque(false);
                lblLevel_2.setOpaque(false);
                lblLevel_3.setOpaque(false);
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
        XOther.isAlphabetic(' ', txtDiaChi, txtHoTen);
        XOther.isDigits('\u0000', txtSDT);
        Calendar calendar = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -18); // trừ trong java
        calendar.setTime(c.getTime());
        int endWith = Integer.parseInt(nvsi.getRowLast().substring(2)) + 1;
        String name = nvsi.getRowLast().substring(0, 2) + endWith;
        txtMaNV.setText(name);
        txtMatKhau.setText("");
        txtNhapLaiMatKhau.setText("");
        txtHoTen.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        jDateChooserNgSinh.setCalendar(calendar);
        txtEmail.setText("");
        rdbNu.setSelected(false);
        rdbNam.setSelected(true);
        txtMaXacNhan.setText("");
        this.setStatus(true);

        lblLevel_1.setOpaque(false);
        lblLevel_2.setOpaque(false);
        lblLevel_3.setOpaque(false);

    }

    void getData(int row, List<NhanVien> list) {
        String maNV = (String) tblNhanVien.getValueAt(row, 0);
        String matKhau = (String) tblNhanVien.getValueAt(row, 1);
        String hoTen = (String) tblNhanVien.getValueAt(row, 2);
        String ngSinh = (String) tblNhanVien.getValueAt(row, 3);
        String diaChi = (String) tblNhanVien.getValueAt(row, 4);
        String sdt = (String) tblNhanVien.getValueAt(row, 5);
        String Email = (String) tblNhanVien.getValueAt(row, 6);
        String gioitinh = (String) tblNhanVien.getValueAt(row, 7);
        String vaitro = (String) tblNhanVien.getValueAt(row, 8);
        boolean active = (boolean) tblNhanVien.getValueAt(row, 9);
        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setMatKhau(matKhau);
        nv.setTenNV(hoTen);
        nv.setNgaysinhNV(XDate.toDate(ngSinh, "dd/MM/yyyy"));
        nv.setDiachi(diaChi);
        nv.setSodienthoai(sdt);
        nv.setEmail(Email);
        nv.setGioitinh(gioitinh);
        nv.setVaiTro(vaitro);
        nv.setActive(active);
        list.add(nv);
    }

    void setModel(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtHoTen.setText(model.getTenNV());
        jDateChooserNgSinh.setDate(model.getNgaysinhNV());
        txtEmail.setText(model.getEmail());
        txtDiaChi.setText(model.getDiachi());
        txtSDT.setText(model.getSodienthoai());
        ButtonModel bm = model.getGioitinh().equals("Nam") ? rdbNam.getModel() : rdbNu.getModel();
        buttonGroupGioiTinh.setSelected(bm, true);
        cbbVaiTro.setSelectedItem(model.getVaiTro());
    }

    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setMatKhau(XOther.MaHoaPass(new String(txtMatKhau.getPassword())).trim());
        model.setTenNV(XOther.Rename(txtHoTen.getText()));
        model.setNgaysinhNV(jDateChooserNgSinh.getDate());
        model.setDiachi(XOther.Rename(txtDiaChi.getText()));
        model.setSodienthoai(txtSDT.getText().trim());
        model.setEmail(txtEmail.getText());
        model.setGioitinh(rdbNam.isSelected() ? "Nam" : "Nữ");
        model.setVaiTro(cbbVaiTro.getSelectedItem().toString());
        return model;
    }

    void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnCapNhat.setEnabled(!insertable && Auth.isManager());
        txtMatKhau.setEnabled(insertable);
        checkForm = insertable;
        txtNhapLaiMatKhau.setEnabled(insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblNhanVien.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnBack.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);

        jLabel1MaXN.setVisible(insertable);
        txtMaXacNhan.setVisible(insertable);
        btnGuiLai.setVisible(insertable);
    }

    void disableBtnResendCode() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                btnGuiLai.setEnabled(false);
                for (int i = 60; i >= 0; i--) {
                    try {
                        if (i == 0 || isSuccessfully) {
                            btnGuiLai.setEnabled(true);
                            lblTrangThai.setText("");
                            XOther.IDVerify = "";
                            break;
                        } else {
                            lblTrangThai.setText(i + " Giây");
                        }
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        System.out.println("Error getCode: " + ex.getMessage());
                    }
                }
            }
        };
        thread.start();
    }

    boolean checkPassword() {
        String input = new String(txtMatKhau.getPassword());
        switch (input) {
            case "123456":
                return true;
            case "0123456":
                return true;
            case "654321":
                return true;
            case "6543210":
                return true;
            case "0123456789":
                return true;
            case "123456789":
                return true;
            case "987654321":
                return true;
            case "9876543210":
                return true;
            default:
                return false;
        }
    }

    void update() {
        if (checkFormUpdate()) {
            NhanVien n = getModel();
            NhanVien nv = nvsi.selectByID(n.getMaNV());
            nv.setTenNV(XOther.Rename(txtHoTen.getText()));
            nv.setNgaysinhNV(jDateChooserNgSinh.getDate());
            nv.setDiachi(XOther.Rename(txtDiaChi.getText()));
            nv.setSodienthoai(txtSDT.getText().trim());
            nv.setEmail(txtEmail.getText());
            nv.setGioitinh(rdbNam.isSelected() ? "Nam" : "Nữ");
            nv.setVaiTro(cbbVaiTro.getSelectedItem().toString());
            if (nvsi.update(nv) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Nhân Viên", nv.getMaNV(), "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                MsgBox.alert(this, "Cập nhật thành công");
                FillDataTable(nvsi.getAll());
                clear();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGioiTinh = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabelMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabelMatKhau = new javax.swing.JLabel();
        jLabelNhapLaiMK = new javax.swing.JLabel();
        jLabelNgSinh = new javax.swing.JLabel();
        jLabelDiaChi = new javax.swing.JLabel();
        jLabelSDT = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNhapLaiMatKhau = new javax.swing.JPasswordField();
        jLabel1Email = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jDateChooserNgSinh = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        cbbVaiTro = new javax.swing.JComboBox<>();
        btnFirst = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        txtDiaChi = new javax.swing.JTextField();
        jLabel1MaXN = new javax.swing.JLabel();
        txtMaXacNhan = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        btnGuiLai = new javax.swing.JButton();
        jLabelHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtMatKhau = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        lblLevel_2 = new javax.swing.JLabel();
        lblLevel_1 = new javax.swing.JLabel();
        lblLevel_3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        ckbSelectAll = new javax.swing.JCheckBox();
        btnXoa = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(552, 462));

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)));

        jLabelMaNV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMaNV.setText("Mã Nhân Viên");

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNV.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelMatKhau.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMatKhau.setText("Mật Khẩu");

        jLabelNhapLaiMK.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNhapLaiMK.setText("Nhập Lại Mật Khẩu");

        jLabelNgSinh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNgSinh.setText("Ngày Sinh");

        jLabelDiaChi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDiaChi.setText("Địa Chỉ");

        jLabelSDT.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelSDT.setText("Số Điện Thoại");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Giới Tính");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Vai Trò");

        txtNhapLaiMatKhau.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtNhapLaiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhapLaiMatKhauActionPerformed(evt);
            }
        });

        jLabel1Email.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1Email.setText("Email");

        txtEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });

        jDateChooserNgSinh.setDateFormatString("dd/MM/yyyy");
        jDateChooserNgSinh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        buttonGroupGioiTinh.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdbNam.setSelected(true);
        rdbNam.setText("Nam");

        buttonGroupGioiTinh.add(rdbNu);
        rdbNu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdbNu.setText("Nữ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbNam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdbNu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdbNu)))
        );

        cbbVaiTro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Lý", "Nhân Viên", "Bác Sỹ" }));

        btnFirst.setBackground(new java.awt.Color(64, 148, 94));
        btnFirst.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(64, 148, 94));
        btnBack.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("<<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
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

        btnThem.setBackground(new java.awt.Color(64, 148, 94));
        btnThem.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(64, 148, 94));
        btnCapNhat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(64, 148, 94));
        btnLamMoi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel1MaXN.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1MaXN.setText("Mã Xác Nhận");

        txtMaXacNhan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblTrangThai.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 0, 0));
        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnGuiLai.setBackground(new java.awt.Color(64, 148, 94));
        btnGuiLai.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuiLai.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiLai.setText("Gửi Mã");
        btnGuiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiLaiActionPerformed(evt);
            }
        });

        jLabelHoTen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelHoTen.setText("Họ Tên");

        txtHoTen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        txtMatKhau.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtMatKhau.setBorder(null);
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        lblLevel_2.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_2.setOpaque(true);

        lblLevel_1.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_1.setOpaque(true);

        lblLevel_3.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_3.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblLevel_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblLevel_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblLevel_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel_1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevel_2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevel_3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1MaXN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaXacNhan))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(5, 5, 5)
                                .addComponent(btnCapNhat)
                                .addGap(5, 5, 5)
                                .addComponent(btnLamMoi)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnFirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLast))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuiLai)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserNgSinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelMaNV, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMatKhau, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNgSinh, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNhapLaiMK, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbVaiTro, javax.swing.GroupLayout.Alignment.TRAILING, 0, 330, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabelHoTen)
                                    .addComponent(jLabelSDT)
                                    .addComponent(jLabel1Email)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(13, 13, 13))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaNV)
                    .addComponent(jLabelHoTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelMatKhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelSDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Email)
                    .addComponent(jLabelNhapLaiMK))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNgSinh)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDiaChi)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1MaXN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabs.addTab("Cập Nhật", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        ckbSelectAll.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbSelectAll.setText("Chọn Tất Cả");
        ckbSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbSelectAllActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(64, 148, 94));
        btnXoa.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll)
                        .addGap(21, 21, 21))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRestore)
                        .addComponent(btnXoa)
                        .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Danh Sách", jPanel3);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabs)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnGuiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiLaiActionPerformed
        if (txtEmail.getText().isEmpty()) {
            MsgBox.alert(this, "Email trống");
        } else if (!txtEmail.getText().matches("^[\\w-\\+]+(\\.[\\w]+)*@[a-z]{3,}+(\\.[a-z]{3,}+)?(\\.[a-z]{2,})$")) {
            MsgBox.alert(this, "Email Không Hợp Lệ\nVD: abc@gmail.com.vn hoặc abc@gmail.com");
        } else {
            isSuccessfully = false;
            lblTrangThai.setEnabled(true);
            disableBtnResendCode();
            String name = txtHoTen.getText();
            XOther.sendCodeConfirm(this, txtEmail.getText(), name.substring(name.lastIndexOf(" ") + 1));
        }
    }//GEN-LAST:event_btnGuiLaiActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        if (evt.getClickCount() == 2) {
            if (Auth.isManager()) {
                this.index = tblNhanVien.rowAtPoint(evt.getPoint());
                if (this.index >= 0) {
                    this.edit();
                    tabs.setSelectedIndex(0);
                }
                list.clear();
                lis.clear();

                ckbSelectAll.setSelected(false);
                btnXoa.setVisible(false);
                for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                    tblNhanVien.setValueAt(false, i, 10);
                }
            }
        } else {
            if (Auth.isManager()) {
                int row = tblNhanVien.getSelectedRow();
                boolean isChecked = (boolean) tblNhanVien.getValueAt(row, 10);
                boolean isCheckedActive = (boolean) tblNhanVien.getValueAt(row, 9);
                boolean isExist = false;

                for (int i = 0; i < nvsi.getMaNV().size(); i++) {
                    String id = String.valueOf(tblNhanVien.getValueAt(row, 0));
                    if (nvsi.getMaNV().get(i).equals(id)) {
                        isExist = true;
                    }
                }
                if (isCheckedActive) {
                    if (isChecked) {
                        if (Auth.user.getMaNV().equals(tblNhanVien.getValueAt(row, 0))) {
                            MsgBox.alert(this, "Không thể xóa chính mình");
                            tblNhanVien.setValueAt(false, row, 10);
                            String id = (String) tblNhanVien.getValueAt(row, 0);
                            for (int j = 0; j < lis.size(); j++) {
                                if (lis.get(j).equals(id)) {
                                    lis.remove(j);
                                    list.remove(j);
                                }
                            }
                            if (lis.isEmpty()) {
                                btnXoa.setVisible(false);
                            }
                        } else {
                            if (isExist) {
                                lis.add((String) tblNhanVien.getValueAt(row, 0));
                                this.getData(row, list);
                                btnXoa.setVisible(true);
                            } else {
                                MsgBox.alert(this, "Mã nhân viên đang được sử dụng không thể xóa");
                                tblNhanVien.setValueAt(false, row, 10);
                            }
                        }
                    } else {
                        String id = (String) tblNhanVien.getValueAt(row, 0);
                        for (int j = 0; j < lis.size(); j++) {
                            if (lis.get(j).equals(id)) {
                                lis.remove(j);
                                list.remove(j);
                            }
                        }
                        if (lis.isEmpty()) {
                            btnXoa.setVisible(false);
                        }
                        ckbSelectAll.setSelected(false);
                    }
                } else {
                    if (Auth.user.getMaNV().equals(tblNhanVien.getValueAt(row, 0))) {
                        MsgBox.alert(this, "Không thể khóa tài khoản chính mình");
                        tblNhanVien.setValueAt(true, row, 9);
                    } else {
                        if (MsgBox.confirm(this, "Việc này có thể dẫn đến mất dữ liệu của tài khoản bị khóa!!!\nBạn có muốn khóa tài khoản này không")) {
                            NhanVien n = new NhanVien();
                            n.setActive(false);
                            n.setMaNV((String) tblNhanVien.getValueAt(row, 0));
                            nvsi.updateActive(n);
                            this.FillDataTable(nvsi.getAll());
                        } else {
                            tblNhanVien.setValueAt(true, row, 9);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();
        isSuccessfully = true;
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.index = tblNhanVien.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtNhapLaiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhapLaiMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhapLaiMatKhauActionPerformed

    private void txtMatKhauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyReleased
        String length = txtMatKhau.getText();
        if (length.length() != 0) {
            if ((length.length() > 0 && length.length() <= 3) || checkPassword() == true
                    || !length.matches("[A-Z][a-z]+[0-9]+(\\w)*")) {
                lblLevel_1.setOpaque(true);
                lblLevel_2.setOpaque(false);
                lblLevel_3.setOpaque(false);

                lblLevel_1.setBackground(Color.RED);
                lblLevel_2.setBackground(new Color(153, 204, 255));
                lblLevel_3.setBackground(new Color(153, 204, 255));
                checkLength = 2;
            } else if (length.length() > 3 && length.length() <= 9) {
                lblLevel_1.setOpaque(true);
                lblLevel_2.setOpaque(true);
                lblLevel_3.setOpaque(false);

                lblLevel_1.setBackground(new Color(255, 255, 0));
                lblLevel_2.setBackground(new Color(255, 255, 0));
                lblLevel_3.setBackground(new Color(153, 204, 255));
                checkLength = 0;
            } else {
                lblLevel_1.setOpaque(true);
                lblLevel_2.setOpaque(true);
                lblLevel_3.setOpaque(true);

                lblLevel_1.setBackground(new Color(0, 179, 89));
                lblLevel_2.setBackground(new Color(0, 179, 89));
                lblLevel_3.setBackground(new Color(0, 179, 89));
                checkLength = 0;
            }
        } else {
            lblLevel_1.setOpaque(false);
            lblLevel_2.setOpaque(false);
            lblLevel_3.setOpaque(false);

            lblLevel_1.setBackground(new Color(153, 204, 255));
            lblLevel_2.setBackground(new Color(153, 204, 255));
            lblLevel_3.setBackground(new Color(153, 204, 255));
        }
    }//GEN-LAST:event_txtMatKhauKeyReleased

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        txtMatKhau.requestFocus();
    }//GEN-LAST:event_jPanel4MouseClicked
    public static int r;
    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        Object[] obj = {"Khôi phục tất cả", "Khôi phục một dòng"};
        int indexes = JOptionPane.showOptionDialog(this, "Khôi phục " + list.size() + " dòng",
                "Thông Báo", 0, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
        switch (indexes) {
            case 0: {
                boolean isSucc = false;
                for (NhanVien n : list) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(n.getMaNV());
                    nv.setMatKhau(n.getMatKhau());
                    nv.setTenNV(n.getTenNV());
                    nv.setNgaysinhNV(n.getNgaysinhNV());
                    nv.setDiachi(n.getDiachi());
                    nv.setSodienthoai(n.getSodienthoai());
                    nv.setEmail(n.getEmail());
                    nv.setGioitinh(n.getGioitinh());
                    nv.setVaiTro(n.getVaiTro());
                    nv.setActive(n.isActive());
                    nvsi.insert(nv);
                    btnRestore.setVisible(false);
                    this.FillDataTable(nvsi.getAll());
                    isSucc = true;
                }
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
                break;
            }
            case 1: {
                r = list.size() - 1;
                NhanVien nv = new NhanVien();
                nv.setMaNV(list.get(r).getMaNV());
                nv.setMatKhau(list.get(r).getMatKhau());
                nv.setTenNV(list.get(r).getTenNV());
                nv.setNgaysinhNV(list.get(r).getNgaysinhNV());
                nv.setDiachi(list.get(r).getDiachi());
                nv.setSodienthoai(list.get(r).getSodienthoai());
                nv.setEmail(list.get(r).getEmail());
                nv.setGioitinh(list.get(r).getGioitinh());
                nv.setVaiTro(list.get(r).getVaiTro());
                nv.setActive(list.get(r).isActive());
                nvsi.insert(nv);
                this.FillDataTable(nvsi.getAll());
                list.remove(r);
                if (list.isEmpty()) {
                    btnRestore.setVisible(false);
                }
            }
            default:
                break;
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (nvsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Nhân Viên", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnXoa.setVisible(false);
            lis.clear();
            this.FillDataTable(nvsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        List<String> str = nvsi.getMaNV();
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            for (int j = 0; j < str.size(); j++) {
                if (ckbSelectAll.isSelected()) {
                    String id = String.valueOf(tblNhanVien.getValueAt(i, 0));
                    if (str.get(j).equals(id)) {
                        if (!Auth.user.getMaNV().equals(tblNhanVien.getValueAt(i, 0))) {
                            tblNhanVien.setValueAt(true, i, 10);
                            lis.add((String) tblNhanVien.getValueAt(i, 0));
                            getData(i, list);
                            btnXoa.setVisible(true);
                        }
                    }
                } else {
                    lis.clear();
                    list.clear();
                    tblNhanVien.setValueAt(false, i, 10);
                    btnXoa.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.getText().isEmpty()) {
            this.FillDataTable(nvsi.getAll());
        } else {
            this.FillDataTable(nvsi.timKiemTen(txtSearch.getText()));
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        if (txtSDT.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSDTKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnGuiLai;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroupGioiTinh;
    private javax.swing.JComboBox<String> cbbVaiTro;
    private javax.swing.JCheckBox ckbSelectAll;
    private com.toedter.calendar.JDateChooser jDateChooserNgSinh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel1Email;
    private javax.swing.JLabel jLabel1MaXN;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDiaChi;
    private javax.swing.JLabel jLabelHoTen;
    private javax.swing.JLabel jLabelMaNV;
    private javax.swing.JLabel jLabelMatKhau;
    private javax.swing.JLabel jLabelNgSinh;
    private javax.swing.JLabel jLabelNhapLaiMK;
    private javax.swing.JLabel jLabelSDT;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLevel_1;
    private javax.swing.JLabel lblLevel_2;
    private javax.swing.JLabel lblLevel_3;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaXacNhan;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtNhapLaiMatKhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
