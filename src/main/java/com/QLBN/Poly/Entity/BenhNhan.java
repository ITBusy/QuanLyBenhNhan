package com.QLBN.Poly.Entity;

import java.util.Date;

public class BenhNhan {

    private int maBN;
    private String tenBN;
    private Date Ngaysinh;
    private String Diachi;
    private String Gioitinh;
    private String ngheNghiep;
    private String danToc;
    private String QuocTich;
    private Date BHYTHieuLucTu;
    private Date BHYTHieuLucDen;
    private String SoBHYT;
    private String nguoiThan;
    private String sdtNguoiThan;
    private boolean Doituong;

    public BenhNhan() {
    }

    public BenhNhan(int maBN, String tenBN, Date Ngaysinh, String Diachi, String Gioitinh, String ngheNghiep, String danToc, String QuocTich, Date BHYTHieuLucTu, Date BHYTHieuLucDen, String SoBHYT, String nguoiThan, String sdtNguoiThan, boolean Doituong) {
        this.maBN = maBN;
        this.tenBN = tenBN;
        this.Ngaysinh = Ngaysinh;
        this.Diachi = Diachi;
        this.Gioitinh = Gioitinh;
        this.ngheNghiep = ngheNghiep;
        this.danToc = danToc;
        this.QuocTich = QuocTich;
        this.BHYTHieuLucTu = BHYTHieuLucTu;
        this.BHYTHieuLucDen = BHYTHieuLucDen;
        this.SoBHYT = SoBHYT;
        this.nguoiThan = nguoiThan;
        this.sdtNguoiThan = sdtNguoiThan;
        this.Doituong = Doituong;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public String getTenBN() {
        return tenBN;
    }

    public void setTenBN(String tenBN) {
        this.tenBN = tenBN;
    }

    public Date getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(Date Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public void setQuocTich(String QuocTich) {
        this.QuocTich = QuocTich;
    }

    public Date getBHYTHieuLucTu() {
        return BHYTHieuLucTu;
    }

    public void setBHYTHieuLucTu(Date BHYTHieuLucTu) {
        this.BHYTHieuLucTu = BHYTHieuLucTu;
    }

    public Date getBHYTHieuLucDen() {
        return BHYTHieuLucDen;
    }

    public void setBHYTHieuLucDen(Date BHYTHieuLucDen) {
        this.BHYTHieuLucDen = BHYTHieuLucDen;
    }

    public String getSoBHYT() {
        return SoBHYT;
    }

    public void setSoBHYT(String SoBHYT) {
        this.SoBHYT = SoBHYT;
    }

    public String getNguoiThan() {
        return nguoiThan;
    }

    public void setNguoiThan(String nguoiThan) {
        this.nguoiThan = nguoiThan;
    }

    public String getSdtNguoiThan() {
        return sdtNguoiThan;
    }

    public void setSdtNguoiThan(String sdtNguoiThan) {
        this.sdtNguoiThan = sdtNguoiThan;
    }

    public boolean isDoituong() {
        return Doituong;
    }

    public void setDoituong(boolean Doituong) {
        this.Doituong = Doituong;
    }

    @Override
    public String toString() {
        return tenBN;
    }
}
