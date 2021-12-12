package com.QLBN.Poly.Entity;

public class XetNghiem {

    private int MaXN;
    private String TenXN;
    private double DonGiaBIH;
    private double DonGiaBHYT;
    private String MucDich;

    public XetNghiem() {
    }

    public XetNghiem(int MaXN, String TenXN, double DonGiaBIH, double DonGiaBHYT, String MucDich) {
        this.MaXN = MaXN;
        this.TenXN = TenXN;
        this.DonGiaBIH = DonGiaBIH;
        this.DonGiaBHYT = DonGiaBHYT;
        this.MucDich = MucDich;
    }

    public int getMaXN() {
        return MaXN;
    }

    public void setMaXN(int MaXN) {
        this.MaXN = MaXN;
    }

    public String getTenXN() {
        return TenXN;
    }

    public void setTenXN(String TenXN) {
        this.TenXN = TenXN;
    }

    public double getDonGiaBIH() {
        return DonGiaBIH;
    }

    public void setDonGiaBIH(double DonGiaBIH) {
        this.DonGiaBIH = DonGiaBIH;
    }

    public double getDonGiaBHYT() {
        return DonGiaBHYT;
    }

    public void setDonGiaBHYT(double DonGiaBHYT) {
        this.DonGiaBHYT = DonGiaBHYT;
    }

    public String getMucDich() {
        return MucDich;
    }

    public void setMucDich(String MucDich) {
        this.MucDich = MucDich;
    }

    @Override
    public String toString() {
        return TenXN;
    }

}
