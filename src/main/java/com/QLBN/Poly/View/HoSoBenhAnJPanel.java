package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.Benh;
import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Entity.Khoa;
import com.QLBN.Poly.Service.BenhNhanServiceImp;
import com.QLBN.Poly.Service.HoSoBenhAnServiceImp;
import com.QLBN.Poly.Service.KhoaServiceImp;
import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Entity.HoSoBenhAn;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Service.BenhServiceImp;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XAutoCompletion;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XOther;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HoSoBenhAnJPanel extends javax.swing.JPanel {

    BenhNhanServiceImp bnsi = new BenhNhanServiceImp();
    KhoaServiceImp ksi = new KhoaServiceImp();
    NhanVienServiceImp nvsi = new NhanVienServiceImp();
    HoSoBenhAnServiceImp hsbasi = new HoSoBenhAnServiceImp();
    BenhServiceImp bsi = new BenhServiceImp();
    int index = -1;
    List<HoSoBenhAn> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();

    public HoSoBenhAnJPanel() {
        initComponents();
    }

    void init() {
        this.FillDataComboboxKhoa();
        this.FillDataComboboxBenh();
        this.FillDataTable(hsbasi.getAll());
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
        jpnSearchOptions.setVisible(false);
    }

    void FillDataComboboxBenhNhan() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenBN.getModel();
        model.removeAllElements();
        if (!bnsi.getDSHoSo().isEmpty()) {
            bnsi.getDSHoSo().forEach(bn -> {
                model.addElement(bn);
            });
            cbbTenBN.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenBN);
        }
    }

    void FillDataComboboxKhoa() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenKhoa.getModel();
        model.removeAllElements();
        if (!ksi.getAll().isEmpty()) {
            ksi.getAll().forEach(k -> {
                model.addElement(k);
            });
            cbbTenKhoa.setSelectedIndex(0);
            XAutoCompletion.enable(cbbTenKhoa);
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

    void FillDataTable(List<HoSoBenhAn> l) {
        String[] columnAdmin = {"STT", "Mã HSBA", "Vào Viện", "Ra Viện", "Huyết Áp", "Nhịp Tim", "Mạch",
            "Cân Nặng", "Kết Quả ĐT", "Mã Bệnh", "Mã Khoa", "Mã BN", "Mã Bác Sỹ", "Chi Phí ĐT",
            "Thời Gian TV", "Nguyên Nhân", "Xóa"};
        String[] column = {"STT", "Mã HSBA", "Vào Viện", "Ra Viện", "Huyết Áp", "Nhịp Tim", "Mạch",
            "Cân Nặng", "Kết Quả ĐT", "Mã Bệnh", "Mã Khoa", "Mã BN", "Mã Bác Sỹ", "Chi Phí ĐT",
            "Thời Gian TV", "Nguyên Nhân"};
        DefaultTableModel dtm = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 16;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 16 ? Boolean.class : String.class;
            }

        };
        boolean isChecked = false;
        int i = 0;
        tblHSBA.setModel(dtm);
        DefaultTableModel model = (DefaultTableModel) tblHSBA.getModel();
        model.setRowCount(0);
        for (HoSoBenhAn kb : l) {
            Object[] rowData = new Object[17];
            rowData[0] = i + 1;
            rowData[1] = kb.getsPhieuHSBA();
            rowData[2] = XDate.toString(kb.getVaoVien(), "dd/MM/yyyy HH:mm:ss");
            rowData[3] = XDate.toString(kb.getRaVien(), "dd/MM/yyyy HH:mm:ss");
            rowData[4] = kb.getHuyetAp();
            rowData[5] = kb.getNhipTim();
            rowData[6] = kb.getMach();
            rowData[7] = kb.getCanNang();
            rowData[8] = kb.getKetQuaDT();
            rowData[9] = kb.getMaBenh();
            rowData[10] = kb.getMakhoa();
            rowData[11] = kb.getMaBN();
            rowData[12] = kb.getMaNV();
            rowData[13] = XOther.convertCurrency(kb.getCphidtri());
            rowData[14] = kb.getTGTuVong() == null ? null : XDate.toString(kb.getTGTuVong(), "dd/MM/yyyy HH:mm:ss");
            rowData[15] = kb.getNguyenNhanTV();
            rowData[16] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblHSBA.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblHSBA.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
    }

    void clear() {
        XOther.isDigits('\u0000', txtCanNang, txtMach, txtNhipTim);
        XOther.isDigits('/', txtHuyetAp);
        XOther.isDigits('.', txtChiPhiDT);
        txtVaoVien.setText(XDate.toString(new Date(), "dd/MM/yyyy  HH:mm:ss"));
        Calendar calendar = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        c.setTime(XDate.toDate(txtVaoVien.getText(), "dd/MM/yyyy  HH:mm:ss"));
        c.add(Calendar.DATE, 5);
        calendar.setTime(c.getTime());

        this.FillDataComboboxBenhNhan();

        txtTenBacSy.setToolTipText("");
        if (cbbTenKhoa.getSelectedItem() != null) {
            cbbTenKhoa.setSelectedIndex(0);
        }
        if (cbbTenBN.getSelectedItem() != null) {
            cbbTenBN.setSelectedIndex(0);
        }
        if (cbbTenBenh.getSelectedItem() != null) {
            cbbTenBenh.setSelectedIndex(0);
        }
        txtTenBacSy.setText(Auth.user.getTenNV());
        txtChiPhiDT.setText("");
        txtMach.setText("");
        txtHuyetAp.setText("");
        txtNhipTim.setText("");
        txtCanNang.setText("");
        rdbKhongThayDoi.setSelected(true);
        rdbDoBenh.setSelected(true);

        jDateChooserRaVien.setCalendar(calendar);
        jDateChooserRaVien.setMinSelectableDate(XDate.toDate(txtVaoVien.getText(), "dd/MM/yyyy  HH:mm:ss"));
        this.setStatus(true);
        this.setStatusTV(false);
    }

    boolean checkThoiGianTuVong() {
        Date tgtv = jDateChooserThoiGianTuVong.getDate();
        Date vv = XDate.toDate(txtVaoVien.getText(), "dd/MM/yyyy HH:mm:ss");
        Date rv = jDateChooserRaVien.getDate();

        if (tgtv == null) {
            MsgBox.alert(this, "Vui lòng nhập thời gian tử vong");
            return false;
        } else if (XDate.getYear(tgtv) < XDate.getYear(vv)) {
            MsgBox.alert(this, "Thời gian tử vong không hợp lệ \n (thời gian tử vong không thể nhỏ hơn thời gian vào viện)");
            return false;
        } else if ((XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) < XDate.getMonth(vv))
                || (XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) == XDate.getMonth(vv)
                && XDate.getDay(tgtv) < XDate.getDay(vv))
                || (XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) == XDate.getMonth(vv)
                && XDate.getDay(tgtv) == XDate.getDay(vv)
                && XDate.getHours(tgtv) < XDate.getHours(vv))
                || (XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) == XDate.getMonth(vv)
                && XDate.getDay(tgtv) == XDate.getDay(vv)
                && XDate.getHours(tgtv) == XDate.getHours(vv)
                && XDate.getMinutes(tgtv) < XDate.getMinutes(vv))
                || (XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) == XDate.getMonth(vv)
                && XDate.getDay(tgtv) == XDate.getDay(vv)
                && XDate.getHours(tgtv) == XDate.getHours(vv)
                && XDate.getMinutes(tgtv) == XDate.getMinutes(vv)
                && XDate.getSeconds(tgtv) < XDate.getSeconds(vv))) {
            MsgBox.alert(this, "Thời gian tử vong không hợp lệ \n (thời gian tử vong không thể nhỏ hơn thời gian vào viện)");
            return false;
        } else if (XDate.getYear(tgtv) > XDate.getYear(rv)) {
            MsgBox.alert(this, "Thời gian tử vong không hợp lệ \n (thời gian tử vong không thể lớn hơn thời gian ra viện)");
            return false;
        } else if ((XDate.getYear(tgtv) == XDate.getYear(rv)
                && XDate.getMonth(tgtv) > XDate.getMonth(rv))
                || (XDate.getYear(tgtv) == XDate.getYear(rv)
                && XDate.getMonth(tgtv) == XDate.getMonth(rv)
                && XDate.getDay(tgtv) > XDate.getDay(rv))
                || (XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) == XDate.getMonth(vv)
                && XDate.getDay(tgtv) == XDate.getDay(vv)
                && XDate.getHours(tgtv) > XDate.getHours(vv))
                || (XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) == XDate.getMonth(vv)
                && XDate.getDay(tgtv) == XDate.getDay(vv)
                && XDate.getHours(tgtv) == XDate.getHours(vv)
                && XDate.getMinutes(tgtv) > XDate.getMinutes(vv))
                || (XDate.getYear(tgtv) == XDate.getYear(vv)
                && XDate.getMonth(tgtv) == XDate.getMonth(vv)
                && XDate.getDay(tgtv) == XDate.getDay(vv)
                && XDate.getHours(tgtv) == XDate.getHours(vv)
                && XDate.getMinutes(tgtv) == XDate.getMinutes(vv)
                && XDate.getSeconds(tgtv) > XDate.getSeconds(vv))) {
            MsgBox.alert(this, "Thời gian tử vong không hợp lệ \n (thời gian tử vong không thể lớn hơn thời gian ra viện)");
            return false;
        } else {
            return true;
        }
    }

    boolean checkForm() {
        Date dateNBD = XDate.toDate(txtVaoVien.getText(), "dd/MM/yyyy HH:mm:ss");
        Date dateNKT = jDateChooserRaVien.getDate();

        String[] num = {jLabelMach.getText(), jLabelCanNang.getText(), jLabelNhipTim.getText()};
        String[] arr = {txtMach.getText(), txtCanNang.getText(), txtMach.getText()};
        String ms = "";
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].matches("^([0-9])*$")) {
                ms += num[i] + ", ";
            }
        }
        String[] name = {jLabelMach.getText(), jLabelCanNang.getText(), jLabelNhipTim.getText(),
            jLabelHuyetAp.getText(), jLabelChiPhi.getText()};
        String[] err = {txtMach.getText(), txtCanNang.getText(), txtNhipTim.getText(),
            txtHuyetAp.getText(), txtChiPhiDT.getText()};
        String msg = "";
        for (int i = 0; i < err.length; i++) {
            if (err[i].isEmpty()) {
                msg += name[i] + ", ";
            }
        }

        if (!msg.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + msg.substring(0, msg.lastIndexOf(", ")));
            return false;
        } else if (dateNBD == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày vào viện");
            return false;
        } else if (dateNKT == null) {
            MsgBox.alert(this, "Vui lòng nhập ngày ra viện");
            return false;
        } else if (!ms.isEmpty()) {
            MsgBox.alert(this, ms.substring(0, ms.lastIndexOf(", ")) + " phải là sô");
            return false;
        } else if (!txtHuyetAp.getText().matches("([0-9]{1,3}/[0-9]{1,3})")) {
            MsgBox.alert(this, "Huyết áp phải là sô");
            return false;
        } else if (XDate.getYear(dateNKT) < XDate.getYear(dateNBD)) {
            MsgBox.alert(this, "Ngày kết thúc không hợp lệ!(" + XDate.toString(dateNBD, "dd/MM/yyyy HH:mm:ss") + ")");
            return false;
        } else if ((XDate.getYear(dateNKT) == XDate.getYear(dateNBD)
                && XDate.getMonth(dateNKT) < XDate.getMonth(dateNBD))
                || (XDate.getYear(dateNKT) == XDate.getYear(dateNBD)
                && XDate.getMonth(dateNKT) == XDate.getMonth(dateNBD)
                && XDate.getDay(dateNKT) < XDate.getDay(dateNBD))) {
            MsgBox.alert(this, "Ngày kết thúc không hợp lệ!(" + XDate.toString(dateNBD, "dd/MM/yyyy HH:mm:ss") + ")");
            return false;
        } else if (!txtChiPhiDT.getText().matches("\\d+(.+\\d)*")) {
            MsgBox.alert(this, "Chi phí điều trị phải là số");
            return false;
        } else {
            return true;
        }
    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        cbbTenBN.setEnabled(insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblHSBA.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void setStatusTV(boolean st) {
        jLabelThoiGianTV.setVisible(st);
        jDateChooserThoiGianTuVong.setVisible(st);
        jLabelNguyenNhan.setVisible(st);
        rdbDoBenh.setVisible(st);
        rdbDoDieuTri.setVisible(st);
    }

    void setModel(HoSoBenhAn hs) {
        DefaultComboBoxModel modelKhoa = (DefaultComboBoxModel) cbbTenKhoa.getModel();
        DefaultComboBoxModel modelBenhNhan = (DefaultComboBoxModel) cbbTenBN.getModel();
        DefaultComboBoxModel modelBenh = (DefaultComboBoxModel) cbbTenBenh.getModel();

        modelBenhNhan.setSelectedItem(bnsi.selectByID(hs.getMaBN()));
        modelBenh.setSelectedItem(bsi.selectByID(hs.getMaBenh()));
        modelKhoa.setSelectedItem(ksi.selectByID(hs.getMakhoa()));

        txtTenBacSy.setToolTipText(String.valueOf(hs.getsPhieuHSBA()));
        jDateChooserRaVien.setDate(hs.getRaVien());
        txtVaoVien.setText(XDate.toString(hs.getVaoVien(), "dd/MM/yyyy HH:mm:ss"));
        ButtonModel bm = null;
        switch (hs.getKetQuaDT()) {
            case "Đã Khỏi":
                bm = rdbDaKhoi.getModel();
                this.setStatusTV(false);
                break;
            case "Nặng Hơn":
                bm = rdbNangHon.getModel();
                this.setStatusTV(false);
                break;
            case "Tử Vong":
                bm = rdbTuVong.getModel();
                this.setStatusTV(true);
                jDateChooserThoiGianTuVong.setDate(hs.getTGTuVong());
                ButtonModel bm1 = hs.getNguyenNhanTV().equals(rdbDoBenh.getText()) ? rdbDoBenh.getModel() : rdbDoDieuTri.getModel();
                bgpNguyenNhan.setSelected(bm1, true);
                break;
            case "Đỡ":
                rdbDo.getModel();
                this.setStatusTV(false);
                break;
            case "Không Thay Đổi":
                bm = rdbKhongThayDoi.getModel();
                this.setStatusTV(false);
                break;
            case "Đang Điều Trị":
                bm = rdbDangDieuTri.getModel();
                this.setStatusTV(false);
                break;
            default:
                break;
        }

        bgpKetQuaDT.setSelected(bm, true);
        txtTenBacSy.setText(nvsi.selectByID(hs.getMaNV()).getTenNV());
        cbbTenKhoa.setSelectedItem(modelKhoa.getSelectedItem());
        cbbTenBN.setSelectedItem(modelBenhNhan.getSelectedItem());
        cbbTenBenh.setSelectedItem(modelBenh.getSelectedItem());
        txtChiPhiDT.setText(XOther.convertString(hs.getCphidtri()));
        txtCanNang.setText(String.valueOf(hs.getCanNang()));
        txtHuyetAp.setText(hs.getHuyetAp());
        txtNhipTim.setText(String.valueOf(hs.getNhipTim()));
        txtMach.setText(String.valueOf(hs.getMach()));
    }

    void setTTBenhNhan(BenhNhan bn) {
        txtNgSinh.setText(XDate.toString(bn.getNgaysinh(), "dd/MM/yyyy"));
        txtTu.setText(bn.getBHYTHieuLucTu() != null ? XDate.toString(bn.getBHYTHieuLucTu(), "dd/MM/yyyy") : null);
        txtDen.setText(bn.getBHYTHieuLucDen() != null ? XDate.toString(bn.getBHYTHieuLucDen(), "dd/MM/yyyy") : null);
        txtDoiTuong.setText(bn.isDoituong() ? "BHYT" : "Thường");
        txtSoBHYT.setText(bn.getSoBHYT());
        txtDiaChi.setText(bn.getDiachi());
        txtGioiTinh.setText(bn.getGioitinh());
        txtDanToc.setText(bn.getDanToc());
        txtQuocTich.setText(bn.getQuocTich());
        txtNgThan.setText(bn.getNguoiThan());
        txtSDTNgThan.setText(bn.getSdtNguoiThan());
    }

    HoSoBenhAn getModel() {
        HoSoBenhAn hs = new HoSoBenhAn();
        Khoa k = (Khoa) cbbTenKhoa.getSelectedItem();
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        Benh b = (Benh) cbbTenBenh.getSelectedItem();

        if (!txtTenBacSy.getToolTipText().isEmpty()) {
            hs.setsPhieuHSBA(Integer.parseInt(txtTenBacSy.getToolTipText()));
        }
        hs.setVaoVien(XDate.toDate(txtVaoVien.getText(), "dd/MM/yyyy HH:mm:ss"));
        hs.setRaVien(jDateChooserRaVien.getDate());
        hs.setMaNV(Auth.user.getMaNV());
        hs.setMakhoa(k.getMaKhoa());
        hs.setMaBN(bn.getMaBN());
        hs.setMaBenh(b.getMaBenh());
        hs.setCphidtri(Double.parseDouble(txtChiPhiDT.getText()));
        JRadioButton[] rdb = {rdbDaKhoi, rdbNangHon, rdbTuVong, rdbDo, rdbKhongThayDoi, rdbDangDieuTri};
        for (JRadioButton rdb1 : rdb) {
            if (rdb1.isSelected()) {
                hs.setKetQuaDT(rdb1.getText());
            }
        }
        hs.setHuyetAp(txtHuyetAp.getText());
        hs.setCanNang(Integer.parseInt(txtCanNang.getText()));
        hs.setMach(Integer.parseInt(txtMach.getText()));
        hs.setNhipTim(Integer.parseInt(txtNhipTim.getText()));
        if (rdbTuVong.isSelected()) {
            hs.setTGTuVong(jDateChooserThoiGianTuVong.getDate());
            hs.setNguyenNhanTV(rdbDoBenh.isSelected() ? "Do Bệnh" : "Do Điều Trị");
        } else {
            hs.setTGTuVong(null);
            hs.setNguyenNhanTV(null);
        }
        return hs;
    }

    void edit() {
        int id = (int) tblHSBA.getValueAt(index, 1);
        HoSoBenhAn hsba = hsbasi.selectByID(id);
        if (hsba != null) {
            this.setModel(hsba);
            this.setStatus(false);
            tabs.setSelectedIndex(0);
        }
    }

    void insert() {
        if (checkForm()) {
            HoSoBenhAn hs = getModel();
            if (!hsbasi.isDead(hs.getMaBN())) {
                if (hsbasi.insert(hs) == 1) {
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Hồ Sơ Bệnh Án", hsbasi.getRowLast() + "", "Thêm", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();

                    this.FillDataTable(hsbasi.getAll());
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
            HoSoBenhAn hs = getModel();
            if (hsbasi.update(hs) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Hồ Sơ Bệnh Án", hs.getsPhieuHSBA() + "", "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();

                this.FillDataTable(hsbasi.getAll());
                this.clear();
                MsgBox.alert(this, "Cập nhật thành công");
            } else {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void getData(int row, List<HoSoBenhAn> l) {
        int sPhieuHSBA = (int) tblHSBA.getValueAt(row, 1);
        String VaoVien = (String) tblHSBA.getValueAt(row, 2);
        String RaVien = (String) tblHSBA.getValueAt(row, 3);
        String huyetAp = (String) tblHSBA.getValueAt(row, 4);
        int nhipTim = (int) tblHSBA.getValueAt(row, 5);
        int Mach = (int) tblHSBA.getValueAt(row, 6);
        int canNang = (int) tblHSBA.getValueAt(row, 7);
        String ketQuaDT = (String) tblHSBA.getValueAt(row, 8);
        int maBenh = (int) tblHSBA.getValueAt(row, 9);
        int Makhoa = (int) tblHSBA.getValueAt(row, 10);
        int MaBN = (int) tblHSBA.getValueAt(row, 11);
        String MaNV = (String) tblHSBA.getValueAt(row, 12);
        String Cphidtri = (String) tblHSBA.getValueAt(row, 13);
        String TGTuVong = (String) tblHSBA.getValueAt(row, 14);
        String NguyenNhanTV = (String) tblHSBA.getValueAt(row, 15);

        l.add(new HoSoBenhAn(sPhieuHSBA, XDate.toDate(VaoVien, "dd/MM/yyyy HH:mm:ss"),
                XDate.toDate(RaVien, "dd/MM/yyyy HH:mm:ss"), huyetAp, nhipTim, Mach, canNang, ketQuaDT, maBenh,
                Makhoa, MaBN, MaNV, XOther.convertDouble(Cphidtri),
                TGTuVong == null ? null : XDate.toDate(TGTuVong, "dd/MM/yyyy HH:mm:ss"),
                NguyenNhanTV == null ? null : NguyenNhanTV));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpKetQuaDT = new javax.swing.ButtonGroup();
        bgpNguyenNhan = new javax.swing.ButtonGroup();
        bgpSearch = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbbTenBN = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtNgSinh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDoiTuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoBHYT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSDTNgThan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDanToc = new javax.swing.JTextField();
        txtNgThan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDen = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtQuocTich = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbbTenKhoa = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtVaoVien = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabelMach = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtMach = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabelCanNang = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtCanNang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtHuyetAp = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtNhipTim = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabelHuyetAp = new javax.swing.JLabel();
        jLabelNhipTim = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        rdbDaKhoi = new javax.swing.JRadioButton();
        rdbNangHon = new javax.swing.JRadioButton();
        rdbTuVong = new javax.swing.JRadioButton();
        rdbDo = new javax.swing.JRadioButton();
        rdbKhongThayDoi = new javax.swing.JRadioButton();
        jLabelChiPhi = new javax.swing.JLabel();
        txtChiPhiDT = new javax.swing.JTextField();
        jLabelThoiGianTV = new javax.swing.JLabel();
        jDateChooserThoiGianTuVong = new com.toedter.calendar.JDateChooser();
        jLabelNguyenNhan = new javax.swing.JLabel();
        rdbDoBenh = new javax.swing.JRadioButton();
        rdbDoDieuTri = new javax.swing.JRadioButton();
        jDateChooserRaVien = new com.toedter.calendar.JDateChooser();
        rdbDangDieuTri = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        txtTenBacSy = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbbTenBenh = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jpnSearchOptions = new javax.swing.JPanel();
        ckbKhoa = new javax.swing.JCheckBox();
        ckbBenhNhan = new javax.swing.JCheckBox();
        ckbMaHSBA = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHSBA = new javax.swing.JTable();
        ckbSelectAll = new javax.swing.JCheckBox();
        btnDel = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));

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

        btnNext.setBackground(new java.awt.Color(64, 148, 94));
        btnNext.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)), "HÀNH CHÍNH", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Tên Bệnh Nhân");

        cbbTenBN.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbbTenBN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenBNActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Ngày Sinh");

        txtNgSinh.setEditable(false);
        txtNgSinh.setBackground(new java.awt.Color(255, 255, 255));
        txtNgSinh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Đối Tượng");

        txtDoiTuong.setEditable(false);
        txtDoiTuong.setBackground(new java.awt.Color(255, 255, 255));
        txtDoiTuong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Số BHYT");

        txtSoBHYT.setEditable(false);
        txtSoBHYT.setBackground(new java.awt.Color(255, 255, 255));
        txtSoBHYT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Địa Chỉ");

        txtDiaChi.setEditable(false);
        txtDiaChi.setBackground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Giới Tính");

        txtGioiTinh.setEditable(false);
        txtGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        txtGioiTinh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Từ");

        txtTu.setEditable(false);
        txtTu.setBackground(new java.awt.Color(255, 255, 255));
        txtTu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("SĐT Người Thân");

        txtSDTNgThan.setEditable(false);
        txtSDTNgThan.setBackground(new java.awt.Color(255, 255, 255));
        txtSDTNgThan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Dân Tộc");

        txtDanToc.setEditable(false);
        txtDanToc.setBackground(new java.awt.Color(255, 255, 255));
        txtDanToc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtNgThan.setEditable(false);
        txtNgThan.setBackground(new java.awt.Color(255, 255, 255));
        txtNgThan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Người Thân");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Đến");

        txtDen.setEditable(false);
        txtDen.setBackground(new java.awt.Color(255, 255, 255));
        txtDen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Quốc Tịch");

        txtQuocTich.setEditable(false);
        txtQuocTich.setBackground(new java.awt.Color(255, 255, 255));
        txtQuocTich.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(cbbTenBN, 0, 247, Short.MAX_VALUE)
                    .addComponent(txtDoiTuong, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(txtNgSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDen, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtNgThan, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(txtDanToc, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuocTich, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoBHYT, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addComponent(txtSDTNgThan, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(36, 36, 36)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addGap(36, 36, 36))
                            .addComponent(txtSDTNgThan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(36, 36, 36))
                            .addComponent(txtSoBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel13)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgThan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDoiTuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(36, 36, 36)))))
                .addGap(6, 6, 6))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)), "QUẢN LÝ BỆNH NHÂN", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel16.setText("Tên Khoa");

        cbbTenKhoa.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbbTenKhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel17.setText("Vào Viện");

        txtVaoVien.setEditable(false);
        txtVaoVien.setBackground(new java.awt.Color(255, 255, 255));
        txtVaoVien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel18.setText("Ra Viện");

        jLabelMach.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelMach.setText("Mạch");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        txtMach.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMach.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Lần/Phút");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtMach, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelCanNang.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCanNang.setText("Cân Nặng");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        txtCanNang.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCanNang.setBorder(null);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Kg");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtCanNang, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCanNang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel7.setPreferredSize(new java.awt.Dimension(401, 30));

        txtHuyetAp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtHuyetAp.setToolTipText("160/160");
        txtHuyetAp.setBorder(null);

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("mmHg");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtHuyetAp, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHuyetAp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        txtNhipTim.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNhipTim.setBorder(null);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Lần/Phút");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(txtNhipTim, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtNhipTim, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelHuyetAp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelHuyetAp.setText("Huyết Áp");

        jLabelNhipTim.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNhipTim.setText("Nhịp Tim");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel23.setText("Kết Quả ĐT");

        bgpKetQuaDT.add(rdbDaKhoi);
        rdbDaKhoi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbDaKhoi.setText("Đã Khỏi");
        rdbDaKhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDaKhoiActionPerformed(evt);
            }
        });

        bgpKetQuaDT.add(rdbNangHon);
        rdbNangHon.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbNangHon.setText("Nặng Hơn");
        rdbNangHon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNangHonActionPerformed(evt);
            }
        });

        bgpKetQuaDT.add(rdbTuVong);
        rdbTuVong.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbTuVong.setText("Tử Vong");
        rdbTuVong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbTuVongActionPerformed(evt);
            }
        });

        bgpKetQuaDT.add(rdbDo);
        rdbDo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbDo.setText("Đỡ");
        rdbDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDoActionPerformed(evt);
            }
        });

        bgpKetQuaDT.add(rdbKhongThayDoi);
        rdbKhongThayDoi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbKhongThayDoi.setSelected(true);
        rdbKhongThayDoi.setText("Không Thay Đổi");
        rdbKhongThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbKhongThayDoiActionPerformed(evt);
            }
        });

        jLabelChiPhi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabelChiPhi.setText("Chi Phí");

        txtChiPhiDT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabelThoiGianTV.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabelThoiGianTV.setText("Thời Gian Tử Vong");

        jDateChooserThoiGianTuVong.setDateFormatString("dd/MM/yyyy HH:mm:ss");

        jLabelNguyenNhan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabelNguyenNhan.setText("Nguyên Nhân");

        bgpNguyenNhan.add(rdbDoBenh);
        rdbDoBenh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbDoBenh.setText("Do Bệnh");

        bgpNguyenNhan.add(rdbDoDieuTri);
        rdbDoDieuTri.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbDoDieuTri.setText("Do Điều Trị");

        jDateChooserRaVien.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        jDateChooserRaVien.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        bgpKetQuaDT.add(rdbDangDieuTri);
        rdbDangDieuTri.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rdbDangDieuTri.setText("Đang Điều Trị");
        rdbDangDieuTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDangDieuTriActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel27.setText("Tên Bác Sỹ");

        txtTenBacSy.setEditable(false);
        txtTenBacSy.setBackground(new java.awt.Color(255, 255, 255));
        txtTenBacSy.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel29.setText("Tên Bệnh");

        cbbTenBenh.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbbTenBenh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTenKhoa, 0, 339, Short.MAX_VALUE)
                    .addComponent(txtVaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabelChiPhi))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateChooserRaVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(txtChiPhiDT, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMach)
                                    .addComponent(jLabelNhipTim))
                                .addGap(0, 272, Short.MAX_VALUE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCanNang)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelHuyetAp)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenBacSy))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelThoiGianTV)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jDateChooserThoiGianTuVong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabelNguyenNhan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdbDoBenh)
                                .addGap(97, 97, 97))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbbTenBenh, 0, 153, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdbDaKhoi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbNangHon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbTuVong)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbDo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbKhongThayDoi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbDangDieuTri))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rdbDoDieuTri)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabelMach)
                    .addComponent(jLabelCanNang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTenKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabelNhipTim))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelHuyetAp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserRaVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelChiPhi))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(6, 6, 6)
                                .addComponent(cbbTenBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdbDaKhoi)
                                    .addComponent(rdbNangHon)
                                    .addComponent(rdbTuVong)
                                    .addComponent(rdbDo)
                                    .addComponent(rdbKhongThayDoi)
                                    .addComponent(rdbDangDieuTri))))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelThoiGianTV, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserThoiGianTuVong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtChiPhiDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenBacSy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNguyenNhan)
                        .addComponent(rdbDoBenh)
                        .addComponent(rdbDoDieuTri)))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPreviuos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(btnPreviuos)
                    .addComponent(btnFirst)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew))
                .addGap(6, 6, 6))
        );

        tabs.addTab("Cập Nhật", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 255)));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jpnSearchOptions.setBackground(new java.awt.Color(255, 255, 255));

        bgpSearch.add(ckbKhoa);
        ckbKhoa.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbKhoa.setText("Khoa");

        bgpSearch.add(ckbBenhNhan);
        ckbBenhNhan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbBenhNhan.setText("Bệnh Nhân");

        bgpSearch.add(ckbMaHSBA);
        ckbMaHSBA.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbMaHSBA.setSelected(true);
        ckbMaHSBA.setText("Mã HSBA");

        javax.swing.GroupLayout jpnSearchOptionsLayout = new javax.swing.GroupLayout(jpnSearchOptions);
        jpnSearchOptions.setLayout(jpnSearchOptionsLayout);
        jpnSearchOptionsLayout.setHorizontalGroup(
            jpnSearchOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSearchOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ckbBenhNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckbMaHSBA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckbKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jpnSearchOptionsLayout.setVerticalGroup(
            jpnSearchOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ckbKhoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbBenhNhan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbMaHSBA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblHSBA.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHSBA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHSBAMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHSBA);

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
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
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
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searchHSBA1.png"))); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jLabel10.setOpaque(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tìm Kiếm:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jpnSearchOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
                        .addComponent(btnRestore)
                        .addGap(12, 12, 12)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jpnSearchOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRestore)
                                    .addComponent(btnDel)
                                    .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 355, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabs.addTab("Danh Sách", jPanel2);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("HỒ SƠ BỆNH ÁN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 531, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (rdbTuVong.isSelected()) {
            if (checkThoiGianTuVong()) {
                insert();
            }
        } else {
            insert();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (rdbTuVong.isSelected()) {
            if (checkThoiGianTuVong()) {
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
        this.index = tblHSBA.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblHSBAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHSBAMouseClicked
        jpnSearchOptions.setVisible(false);
        if (evt.getClickCount() == 2) {
            this.index = tblHSBA.rowAtPoint(evt.getPoint());
            this.edit();
            list.clear();
            lis.clear();

            ckbSelectAll.setSelected(false);
            btnDel.setVisible(false);
            if (Auth.isManager()) {
                for (int i = 0; i < tblHSBA.getRowCount(); i++) {
                    tblHSBA.setValueAt(false, i, 16);
                }
            }
        } else {
            if (Auth.isManager()) {
                int row = tblHSBA.getSelectedRow();
                boolean isChecked = (boolean) tblHSBA.getValueAt(row, 16);

                if (isChecked) {
                    lis.add((int) tblHSBA.getValueAt(row, 1));
                    getData(row, list);
                    btnDel.setVisible(true);
                } else {
                    int id = Integer.valueOf(tblHSBA.getValueAt(row, 1).toString());
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
    }//GEN-LAST:event_tblHSBAMouseClicked

    private void rdbDaKhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDaKhoiActionPerformed
        this.setStatusTV(false);
    }//GEN-LAST:event_rdbDaKhoiActionPerformed

    private void rdbNangHonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNangHonActionPerformed
        this.setStatusTV(false);
    }//GEN-LAST:event_rdbNangHonActionPerformed

    private void rdbTuVongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbTuVongActionPerformed
        this.setStatusTV(true);
    }//GEN-LAST:event_rdbTuVongActionPerformed

    private void rdbDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDoActionPerformed
        this.setStatusTV(false);
    }//GEN-LAST:event_rdbDoActionPerformed

    private void rdbKhongThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbKhongThayDoiActionPerformed
        this.setStatusTV(false);
    }//GEN-LAST:event_rdbKhongThayDoiActionPerformed

    private void rdbDangDieuTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDangDieuTriActionPerformed
        this.setStatusTV(false);
    }//GEN-LAST:event_rdbDangDieuTriActionPerformed

    private void cbbTenBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenBNActionPerformed
        BenhNhan bn = (BenhNhan) cbbTenBN.getSelectedItem();
        if (bn != null) {
            this.setTTBenhNhan(bn);
        } else {
            txtNgSinh.setText("");
            txtTu.setText("");
            txtDen.setText("");
            txtDoiTuong.setText("");
            txtSoBHYT.setText("");
            txtDiaChi.setText("");
            txtGioiTinh.setText("");
            txtDanToc.setText("");
            txtQuocTich.setText("");
            txtNgThan.setText("");
            txtSDTNgThan.setText("");
        }
    }//GEN-LAST:event_cbbTenBNActionPerformed

    int r;

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        jpnSearchOptions.setVisible(false);
        if (list.size() > 1) {
            Object[] obj = {"Khôi phục tất cả", "Khôi phục một dòng"};
            int indexes = JOptionPane.showOptionDialog(this, "Khôi phục " + list.size() + " dòng",
                    "Thông Báo", 0, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
            switch (indexes) {
                case 0: {
                    boolean isSucc = false;
                    String id = "";
                    for (HoSoBenhAn hs : list) {
                        hsbasi.insertRetore(new HoSoBenhAn(hs.getsPhieuHSBA(), hs.getVaoVien(), hs.getRaVien(),
                                hs.getHuyetAp(), hs.getNhipTim(), hs.getMach(), hs.getCanNang(),
                                hs.getKetQuaDT(), hs.getMaBenh(), hs.getMakhoa(), hs.getMaBN(), hs.getMaNV(),
                                hs.getCphidtri(), hs.getTGTuVong(), hs.getNguyenNhanTV()));
                        id += hs.getsPhieuHSBA() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(hsbasi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Hồ Sơ Bệnh Án", id.substring(0, id.lastIndexOf(", ")),
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
                    hsbasi.insertRetore(new HoSoBenhAn(list.get(r).getsPhieuHSBA(), list.get(r).getVaoVien(),
                            list.get(r).getRaVien(), list.get(r).getHuyetAp(), list.get(r).getNhipTim(),
                            list.get(r).getMach(), list.get(r).getCanNang(), list.get(r).getKetQuaDT(),
                            list.get(r).getMaBenh(), list.get(r).getMakhoa(), list.get(r).getMaBN(),
                            list.get(r).getMaNV(), list.get(r).getCphidtri(), list.get(r).getTGTuVong(),
                            list.get(r).getNguyenNhanTV()));
                    this.FillDataTable(hsbasi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Hồ Sơ Bệnh Án", list.get(r).getsPhieuHSBA() + "",
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
                hsbasi.insertRetore(new HoSoBenhAn(list.get(0).getsPhieuHSBA(), list.get(0).getVaoVien(),
                        list.get(0).getRaVien(), list.get(0).getHuyetAp(), list.get(0).getNhipTim(),
                        list.get(0).getMach(), list.get(0).getCanNang(), list.get(0).getKetQuaDT(),
                        list.get(0).getMaBenh(), list.get(0).getMakhoa(), list.get(0).getMaBN(),
                        list.get(0).getMaNV(), list.get(0).getCphidtri(), list.get(0).getTGTuVong(),
                        list.get(0).getNguyenNhanTV()));

                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Hồ Sơ Bệnh Án", list.get(0).getsPhieuHSBA() + "",
                        "Khôi Phục", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();
                btnRestore.setVisible(false);
                this.FillDataTable(hsbasi.getAll());
                isSucc = true;
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        this.clear();
        jpnSearchOptions.setVisible(false);
        if (hsbasi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Hồ Sơ Bệnh Án", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(hsbasi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        jpnSearchOptions.setVisible(false);
        lis.clear();
        for (int i = 0; i < tblHSBA.getRowCount(); i++) {
            if (ckbSelectAll.isSelected()) {
                tblHSBA.setValueAt(true, i, 16);
                lis.add((int) tblHSBA.getValueAt(i, 1));
                getData(i, list);
                btnDel.setVisible(true);
            } else {
                lis.clear();
                list.clear();
                tblHSBA.setValueAt(false, i, 16);
                btnDel.setVisible(false);
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (ckbKhoa.isSelected()) {
            String sql = "SELECT HoSoBenhAn.* FROM dbo.HoSoBenhAn "
                    + " JOIN dbo.Khoa ON Khoa.Makhoa = HoSoBenhAn.Makhoa "
                    + " WHERE Tenkhoa LIKE ?";
            this.FillDataTable(hsbasi.timKiemTT(sql, txtSearch.getText() + "%"));
        } else if (ckbBenhNhan.isSelected()) {
            String sql = "SELECT HoSoBenhAn.* FROM dbo.HoSoBenhAn "
                    + " JOIN dbo.BenhNhan ON BenhNhan.maBN = HoSoBenhAn.MaBN "
                    + " WHERE tenBN LIKE ?";
            this.FillDataTable(hsbasi.timKiemTT(sql, txtSearch.getText() + "%"));
        } else {
            String sql = "SELECT * FROM dbo.HoSoBenhAn "
                    + " WHERE sPhieuHSBA LIKE ?";
            this.FillDataTable(hsbasi.timKiemTT(sql, txtSearch.getText() + "%"));
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    int s = 0;
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        s++;
        if (s % 2 == 0) {
            jpnSearchOptions.setVisible(false);
        } else {
            jpnSearchOptions.setVisible(true);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        jpnSearchOptions.setVisible(false);
        if (Auth.isManager()) {
            if (tabs.getSelectedIndex() == 0) {
                ckbSelectAll.setSelected(false);
                for (int i = 0; i < tblHSBA.getRowCount(); i++) {
                    tblHSBA.setValueAt(false, i, 16);
                }
                btnDel.setVisible(false);
                btnRestore.setVisible(false);
                lis.clear();
                list.clear();
                ckbMaHSBA.setSelected(true);
            }
        }
    }//GEN-LAST:event_tabsMouseClicked

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        jpnSearchOptions.setVisible(false);
    }//GEN-LAST:event_txtSearchMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        jpnSearchOptions.setVisible(false);
    }//GEN-LAST:event_jPanel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpKetQuaDT;
    private javax.swing.ButtonGroup bgpNguyenNhan;
    private javax.swing.ButtonGroup bgpSearch;
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
    private javax.swing.JComboBox<String> cbbTenBenh;
    private javax.swing.JComboBox<String> cbbTenKhoa;
    private javax.swing.JCheckBox ckbBenhNhan;
    private javax.swing.JCheckBox ckbKhoa;
    private javax.swing.JCheckBox ckbMaHSBA;
    private javax.swing.JCheckBox ckbSelectAll;
    private com.toedter.calendar.JDateChooser jDateChooserRaVien;
    private com.toedter.calendar.JDateChooser jDateChooserThoiGianTuVong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCanNang;
    private javax.swing.JLabel jLabelChiPhi;
    private javax.swing.JLabel jLabelHuyetAp;
    private javax.swing.JLabel jLabelMach;
    private javax.swing.JLabel jLabelNguyenNhan;
    private javax.swing.JLabel jLabelNhipTim;
    private javax.swing.JLabel jLabelThoiGianTV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnSearchOptions;
    private javax.swing.JRadioButton rdbDaKhoi;
    private javax.swing.JRadioButton rdbDangDieuTri;
    private javax.swing.JRadioButton rdbDo;
    private javax.swing.JRadioButton rdbDoBenh;
    private javax.swing.JRadioButton rdbDoDieuTri;
    private javax.swing.JRadioButton rdbKhongThayDoi;
    private javax.swing.JRadioButton rdbNangHon;
    private javax.swing.JRadioButton rdbTuVong;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHSBA;
    private javax.swing.JTextField txtCanNang;
    private javax.swing.JTextField txtChiPhiDT;
    private javax.swing.JTextField txtDanToc;
    private javax.swing.JTextField txtDen;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDoiTuong;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtHuyetAp;
    private javax.swing.JTextField txtMach;
    private javax.swing.JTextField txtNgSinh;
    private javax.swing.JTextField txtNgThan;
    private javax.swing.JTextField txtNhipTim;
    private javax.swing.JTextField txtQuocTich;
    private javax.swing.JTextField txtSDTNgThan;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoBHYT;
    private javax.swing.JTextField txtTenBacSy;
    private javax.swing.JTextField txtTu;
    private javax.swing.JTextField txtVaoVien;
    // End of variables declaration//GEN-END:variables
}
