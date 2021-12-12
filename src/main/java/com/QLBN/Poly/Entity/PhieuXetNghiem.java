package com.QLBN.Poly.Entity;

import java.util.Date;

public class PhieuXetNghiem {

    private int sphieuXN;
    private int MaXN;
    private int maBN;
    private Date ngayXN;
    private String kquaXN;
    private String lidoXN;
    private String NguoiYeuCau;
   

    public PhieuXetNghiem() {
    }

    public PhieuXetNghiem(int sphieuXN, int MaXN, int maBN, Date ngayXN, String kquaXN, String lidoXN, String NguoiYeuCau) {
        this.sphieuXN = sphieuXN;
        this.MaXN = MaXN;
        this.maBN = maBN;
        this.ngayXN = ngayXN;
        this.kquaXN = kquaXN;
        this.lidoXN = lidoXN;
        this.NguoiYeuCau = NguoiYeuCau;
    }

    public int getSphieuXN() {
        return sphieuXN;
    }

    public void setSphieuXN(int sphieuXN) {
        this.sphieuXN = sphieuXN;
    }

    public int getMaXN() {
        return MaXN;
    }

    public void setMaXN(int MaXN) {
        this.MaXN = MaXN;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public Date getNgayXN() {
        return ngayXN;
    }

    public void setNgayXN(Date ngayXN) {
        this.ngayXN = ngayXN;
    }

    public String getKquaXN() {
        return kquaXN;
    }

    public void setKquaXN(String kquaXN) {
        this.kquaXN = kquaXN;
    }

    public String getLidoXN() {
        return lidoXN;
    }

    public void setLidoXN(String lidoXN) {
        this.lidoXN = lidoXN;
    }

    public String getNguoiYeuCau() {
        return NguoiYeuCau;
    }

    public void setNguoiYeuCau(String NguoiYeuCau) {
        this.NguoiYeuCau = NguoiYeuCau;
    }

}
