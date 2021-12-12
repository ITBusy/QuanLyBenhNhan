
package com.QLBN.Poly.Entity;


public class DichVu {
    private int maDV;
    private String tenDV;
    private int soLuong;
    private double donGia;
    private String donViTinh;

    public DichVu() {
    }

    public DichVu(int maDV, String tenDV, int soLuong, double donGia, String donViTinh) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    @Override
    public String toString() {
        return tenDV;
    }
    
}
