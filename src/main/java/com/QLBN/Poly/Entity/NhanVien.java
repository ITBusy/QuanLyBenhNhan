package com.QLBN.Poly.Entity;

import java.util.Date;

public class NhanVien {

    private String MaNV;
    private String matKhau;
    private String TenNV;
    private Date ngaysinhNV;
    private String Diachi;
    private String sodienthoai;
    private String Email;
    private String Gioitinh;
    private String VaiTro;
    private boolean active = true;

    public NhanVien() {
    } 

    public NhanVien(String MaNV, String matKhau, String TenNV, Date ngaysinhNV, String Diachi, String sodienthoai, String Email, String Gioitinh, String VaiTro) {
        this.MaNV = MaNV;
        this.matKhau = matKhau;
        this.TenNV = TenNV;
        this.ngaysinhNV = ngaysinhNV;
        this.Diachi = Diachi;
        this.sodienthoai = sodienthoai;
        this.Email = Email;
        this.Gioitinh = Gioitinh;
        this.VaiTro = VaiTro;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public Date getNgaysinhNV() {
        return ngaysinhNV;
    }

    public void setNgaysinhNV(Date ngaysinhNV) {
        this.ngaysinhNV = ngaysinhNV;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public String getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(String VaiTro) {
        this.VaiTro = VaiTro;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return TenNV;
    }

}
