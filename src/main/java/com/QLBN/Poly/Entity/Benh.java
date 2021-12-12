
package com.QLBN.Poly.Entity;

public class Benh {
    private int maBenh;
    private String tenBenh;
    private String dauHieu;

    public Benh() {
    }

    public Benh(int maBenh, String tenBenh, String dauHieu) {
        this.maBenh = maBenh;
        this.tenBenh = tenBenh;
        this.dauHieu = dauHieu;
    }

    public int getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(int maBenh) {
        this.maBenh = maBenh;
    }

    public String getTenBenh() {
        return tenBenh;
    }

    public void setTenBenh(String tenBenh) {
        this.tenBenh = tenBenh;
    }

    public String getDauHieu() {
        return dauHieu;
    }

    public void setDauHieu(String dauHieu) {
        this.dauHieu = dauHieu;
    }

    @Override
    public String toString() {
        return tenBenh;
    }
    
}
