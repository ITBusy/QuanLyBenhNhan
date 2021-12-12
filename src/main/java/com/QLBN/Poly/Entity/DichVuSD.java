package com.QLBN.Poly.Entity;

import java.util.Date;

public class DichVuSD {

    private int sphieuSD;
    private int maBN;
    private int maDV;
    private Date ngaySD;

    public DichVuSD() {
    }

    public DichVuSD(int sphieuSD, int maBN, int maDV, Date ngaySD) {
        this.sphieuSD = sphieuSD;
        this.maBN = maBN;
        this.maDV = maDV;
        this.ngaySD = ngaySD;
    }

    public int getSphieuSD() {
        return sphieuSD;
    }

    public void setSphieuSD(int sphieuSD) {
        this.sphieuSD = sphieuSD;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public Date getNgaySD() {
        return ngaySD;
    }

    public void setNgaySD(Date ngaySD) {
        this.ngaySD = ngaySD;
    }

}
