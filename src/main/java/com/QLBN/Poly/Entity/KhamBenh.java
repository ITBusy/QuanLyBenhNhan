package com.QLBN.Poly.Entity;

import java.util.Date;

public class KhamBenh {

    private int soPhieuKham;
    private Date ngayKham;
    private String trieuChung;
    private String huyetAp;
    private int nhipTim;
    private int Mach;
    private int canNang;
    private String chuanDoan;
    private boolean yeuCau;
    private String maNV;
    private int maBenh;
    private int maBenhNhan;
    private double CPKham;

    public KhamBenh() {
    }

    public KhamBenh(int soPhieuKham, Date ngayKham, String trieuChung, String huyetAp, int nhipTim, int Mach, int canNang, String chuanDoan, boolean yeuCau, String maNV, int maBenh, int maBenhNhan, double CPKham) {
        this.soPhieuKham = soPhieuKham;
        this.ngayKham = ngayKham;
        this.trieuChung = trieuChung;
        this.huyetAp = huyetAp;
        this.nhipTim = nhipTim;
        this.Mach = Mach;
        this.canNang = canNang;
        this.chuanDoan = chuanDoan;
        this.yeuCau = yeuCau;
        this.maNV = maNV;
        this.maBenh = maBenh;
        this.maBenhNhan = maBenhNhan;
        this.CPKham = CPKham;
    }

    public int getSoPhieuKham() {
        return soPhieuKham;
    }

    public void setSoPhieuKham(int soPhieuKham) {
        this.soPhieuKham = soPhieuKham;
    }

    public Date getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getHuyetAp() {
        return huyetAp;
    }

    public void setHuyetAp(String huyetAp) {
        this.huyetAp = huyetAp;
    }

    public int getNhipTim() {
        return nhipTim;
    }

    public void setNhipTim(int nhipTim) {
        this.nhipTim = nhipTim;
    }

    public int getMach() {
        return Mach;
    }

    public void setMach(int Mach) {
        this.Mach = Mach;
    }

    public int getCanNang() {
        return canNang;
    }

    public void setCanNang(int canNang) {
        this.canNang = canNang;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public boolean isYeuCau() {
        return yeuCau;
    }

    public void setYeuCau(boolean yeuCau) {
        this.yeuCau = yeuCau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(int maBenh) {
        this.maBenh = maBenh;
    }

    public int getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(int maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public double getCPKham() {
        return CPKham;
    }

    public void setCPKham(double CPKham) {
        this.CPKham = CPKham;
    }

}
