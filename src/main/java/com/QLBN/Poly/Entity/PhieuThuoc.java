package com.QLBN.Poly.Entity;

import java.util.Date;

public class PhieuThuoc {

    private int sphieuthuoc;
    private int Mathuoc;
    private String Soluong;
    private String MaNV;
    private String ngayUong;
    private String soLan_Ngay;
    private String BarCode;
    private int maBN;
    private Date NgKeDon;
    private double tongTien;

    public PhieuThuoc() {
    }

    public PhieuThuoc(int sphieuthuoc, int Mathuoc, String Soluong, String MaNV, String ngayUong, String soLan_Ngay, String BarCode, int maBN, Date NgKeDon, double tongTien) {
        this.sphieuthuoc = sphieuthuoc;
        this.Mathuoc = Mathuoc;
        this.Soluong = Soluong;
        this.MaNV = MaNV;
        this.ngayUong = ngayUong;
        this.soLan_Ngay = soLan_Ngay;
        this.BarCode = BarCode;
        this.maBN = maBN;
        this.NgKeDon = NgKeDon;
        this.tongTien = tongTien;
    }

    public int getSphieuthuoc() {
        return sphieuthuoc;
    }

    public void setSphieuthuoc(int sphieuthuoc) {
        this.sphieuthuoc = sphieuthuoc;
    }

    public int getMathuoc() {
        return Mathuoc;
    }

    public void setMathuoc(int Mathuoc) {
        this.Mathuoc = Mathuoc;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String Soluong) {
        this.Soluong = Soluong;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getNgayUong() {
        return ngayUong;
    }

    public void setNgayUong(String ngayUong) {
        this.ngayUong = ngayUong;
    }

    public String getSoLan_Ngay() {
        return soLan_Ngay;
    }

    public void setSoLan_Ngay(String soLan_Ngay) {
        this.soLan_Ngay = soLan_Ngay;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public Date getNgKeDon() {
        return NgKeDon;
    }

    public void setNgKeDon(Date NgKeDon) {
        this.NgKeDon = NgKeDon;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return BarCode;
    }

}
