
package com.QLBN.Poly.Entity;

public class Khoa {
    private int maKhoa;
    private String tenKhoa;
    private String KhuVuc;

    public Khoa() {
    }

    public Khoa(int maKhoa, String tenKhoa, String KhuVuc) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.KhuVuc = KhuVuc;
    }

    public int getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(int maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getKhuVuc() {
        return KhuVuc;
    }

    public void setKhuVuc(String KhuVuc) {
        this.KhuVuc = KhuVuc;
    }

    @Override
    public String toString() {
        return tenKhoa;
    }
    
}
