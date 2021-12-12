package com.QLBN.Poly.Entity;

import java.util.Date;

public class HoSoBenhAn {

    private int sPhieuHSBA;
    private Date VaoVien;
    private Date RaVien;
    private String huyetAp;
    private int nhipTim;
    private int Mach;
    private int canNang;
    private String ketQuaDT;
    private int maBenh;
    private int Makhoa;
    private int MaBN;
    private String MaNV;
    private double Cphidtri;
    private Date TGTuVong;
    private String NguyenNhanTV;

    public HoSoBenhAn() {
    }

    public HoSoBenhAn(int sPhieuHSBA, Date VaoVien, Date RaVien, String huyetAp, int nhipTim, int Mach, int canNang, String ketQuaDT, int maBenh, int Makhoa, int MaBN, String MaNV, double Cphidtri, Date TGTuVong, String NguyenNhanTV) {
        this.sPhieuHSBA = sPhieuHSBA;
        this.VaoVien = VaoVien;
        this.RaVien = RaVien;
        this.huyetAp = huyetAp;
        this.nhipTim = nhipTim;
        this.Mach = Mach;
        this.canNang = canNang;
        this.ketQuaDT = ketQuaDT;
        this.maBenh = maBenh;
        this.Makhoa = Makhoa;
        this.MaBN = MaBN;
        this.MaNV = MaNV;
        this.Cphidtri = Cphidtri;
        this.TGTuVong = TGTuVong;
        this.NguyenNhanTV = NguyenNhanTV;
    }

    public int getsPhieuHSBA() {
        return sPhieuHSBA;
    }

    public void setsPhieuHSBA(int sPhieuHSBA) {
        this.sPhieuHSBA = sPhieuHSBA;
    }

    public Date getVaoVien() {
        return VaoVien;
    }

    public void setVaoVien(Date VaoVien) {
        this.VaoVien = VaoVien;
    }

    public Date getRaVien() {
        return RaVien;
    }

    public void setRaVien(Date RaVien) {
        this.RaVien = RaVien;
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

    public String getKetQuaDT() {
        return ketQuaDT;
    }

    public void setKetQuaDT(String ketQuaDT) {
        this.ketQuaDT = ketQuaDT;
    }

    public int getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(int maBenh) {
        this.maBenh = maBenh;
    }

    public int getMakhoa() {
        return Makhoa;
    }

    public void setMakhoa(int Makhoa) {
        this.Makhoa = Makhoa;
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int MaBN) {
        this.MaBN = MaBN;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public double getCphidtri() {
        return Cphidtri;
    }

    public void setCphidtri(double Cphidtri) {
        this.Cphidtri = Cphidtri;
    }

    public Date getTGTuVong() {
        return TGTuVong;
    }

    public void setTGTuVong(Date TGTuVong) {
        this.TGTuVong = TGTuVong;
    }

    public String getNguyenNhanTV() {
        return NguyenNhanTV;
    }

    public void setNguyenNhanTV(String NguyenNhanTV) {
        this.NguyenNhanTV = NguyenNhanTV;
    }

}
