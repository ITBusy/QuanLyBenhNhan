package com.QLBN.Poly.Entity;

public class Thuoc {

    private int MaThuoc;
    private String TenBietDuoc;
    private String tenHoatDuoc;
    private String HamLuong;
    private String DVT;
    private double donGia;

    public Thuoc() {
    }

    public Thuoc(int MaThuoc, String TenBietDuoc, String tenHoatDuoc, String HamLuong, String DVT, double donGia) {
        this.MaThuoc = MaThuoc;
        this.TenBietDuoc = TenBietDuoc;
        this.tenHoatDuoc = tenHoatDuoc;
        this.HamLuong = HamLuong;
        this.DVT = DVT;
        this.donGia = donGia;
    }

    public int getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(int MaThuoc) {
        this.MaThuoc = MaThuoc;
    }

    public String getTenBietDuoc() {
        return TenBietDuoc;
    }

    public void setTenBietDuoc(String TenBietDuoc) {
        this.TenBietDuoc = TenBietDuoc;
    }

    public String getTenHoatDuoc() {
        return tenHoatDuoc;
    }

    public void setTenHoatDuoc(String tenHoatDuoc) {
        this.tenHoatDuoc = tenHoatDuoc;
    }

    public String getHamLuong() {
        return HamLuong;
    }

    public void setHamLuong(String HamLuong) {
        this.HamLuong = HamLuong;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return TenBietDuoc;
    }

}
