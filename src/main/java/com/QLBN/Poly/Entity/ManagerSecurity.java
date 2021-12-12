package com.QLBN.Poly.Entity;

import java.io.Serializable;
import java.util.Date;

public class ManagerSecurity implements Serializable {

    private String MaNV;
    private String MatKhau;
    private String MatKhauCu;
    private String nguoiThucHien;
    private String ChucVu;
    private String Bang;
    private String ID_Bang;
    private String NoiDung;
    private String Email;
    private Date thoiGian;
    private Khoa khoa;
    private DichVuSD dichVuSD;
    private HoSoBenhAn hoSoBenhAn;
    private Benh benh;
    private BenhNhan benhNhan;
    private BienLai bienLai;
    private DichVu dichVu;
    private KhamBenh khamBenh;
    private PhieuThuoc phieuThuoc;
    private Thuoc thuoc;
    private PhieuXetNghiem phieuXetNghiem;
    private XetNghiem xetNghiem;

    public ManagerSecurity() {
    }

    // Quan Ly Dang Nhap
    public ManagerSecurity(String MaNV, String MatKhau, Date thoiGian) {
        this.MaNV = MaNV;
        this.MatKhau = MatKhau;
        this.thoiGian = thoiGian;
    }

    // Quan Ly Dang Ky
    public ManagerSecurity(String MaNV, String MatKhau, String Email, Date thoiGian) {
        this.MaNV = MaNV;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.thoiGian = thoiGian;
    }

    //Quan Ly Thay Doi/Quen Mat Khau
    public ManagerSecurity(String MaNV, String MatKhau, String MatKhauCu, String Email, Date thoiGian) {
        this.MaNV = MaNV;
        this.MatKhau = MatKhau;
        this.MatKhauCu = MatKhauCu;
        this.Email = Email;
        this.thoiGian = thoiGian;
    }

    //Quan Ly Tac Vu Nguoi Dung
    public ManagerSecurity(String MaNV, String nguoiThucHien, String ChucVu, String Bang, String ID_Bang, String NoiDung, Date thoiGian) {
        this.MaNV = MaNV;
        this.nguoiThucHien = nguoiThucHien;
        this.ChucVu = ChucVu;
        this.Bang = Bang;
        this.ID_Bang = ID_Bang;
        this.NoiDung = NoiDung;
        this.thoiGian = thoiGian;
    }

    // Khôi phục
    public ManagerSecurity(Khoa khoa) {
        this.khoa = khoa;
    }

    public ManagerSecurity(DichVuSD dichVuSD) {
        this.dichVuSD = dichVuSD;
    }

    public ManagerSecurity(HoSoBenhAn hoSoBenhAn) {
        this.hoSoBenhAn = hoSoBenhAn;
    }

    public ManagerSecurity(Benh benh) {
        this.benh = benh;
    }

    public ManagerSecurity(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public ManagerSecurity(BienLai bienLai) {
        this.bienLai = bienLai;
    }

    public ManagerSecurity(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public ManagerSecurity(KhamBenh khamBenh) {
        this.khamBenh = khamBenh;
    }

    public ManagerSecurity(PhieuThuoc phieuThuoc) {
        this.phieuThuoc = phieuThuoc;
    }

    public ManagerSecurity(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public ManagerSecurity(PhieuXetNghiem phieuXetNghiem) {
        this.phieuXetNghiem = phieuXetNghiem;
    }

    public ManagerSecurity(XetNghiem xetNghiem) {
        this.xetNghiem = xetNghiem;
    }

    // Getter and Setter
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getMatKhauCu() {
        return MatKhauCu;
    }

    public void setMatKhauCu(String MatKhauCu) {
        this.MatKhauCu = MatKhauCu;
    }

    public String getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(String nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getBang() {
        return Bang;
    }

    public void setBang(String Bang) {
        this.Bang = Bang;
    }

    public String getID_Bang() {
        return ID_Bang;
    }

    public void setID_Bang(String ID_Bang) {
        this.ID_Bang = ID_Bang;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public DichVuSD getDichVuSD() {
        return dichVuSD;
    }

    public void setDichVuSD(DichVuSD dichVuSD) {
        this.dichVuSD = dichVuSD;
    }

    public HoSoBenhAn getHoSoBenhAn() {
        return hoSoBenhAn;
    }

    public void setHoSoBenhAn(HoSoBenhAn hoSoBenhAn) {
        this.hoSoBenhAn = hoSoBenhAn;
    }

    public Benh getBenh() {
        return benh;
    }

    public void setBenh(Benh benh) {
        this.benh = benh;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public BienLai getBienLai() {
        return bienLai;
    }

    public void setBienLai(BienLai bienLai) {
        this.bienLai = bienLai;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public KhamBenh getKhamBenh() {
        return khamBenh;
    }

    public void setKhamBenh(KhamBenh khamBenh) {
        this.khamBenh = khamBenh;
    }

    public PhieuThuoc getPhieuThuoc() {
        return phieuThuoc;
    }

    public void setPhieuThuoc(PhieuThuoc phieuThuoc) {
        this.phieuThuoc = phieuThuoc;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public PhieuXetNghiem getPhieuXetNghiem() {
        return phieuXetNghiem;
    }

    public void setPhieuXetNghiem(PhieuXetNghiem phieuXetNghiem) {
        this.phieuXetNghiem = phieuXetNghiem;
    }

    public XetNghiem getXetNghiem() {
        return xetNghiem;
    }

    public void setXetNghiem(XetNghiem xetNghiem) {
        this.xetNghiem = xetNghiem;
    }

}
