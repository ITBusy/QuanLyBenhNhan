package com.QLBN.Poly.Entity;

import java.io.Serializable;

public class RememberMe implements Serializable {

    private String tenTk;
    private String maKhau;
    private boolean remember;
    private String ipCoputer;

    public RememberMe() {
    }

    public RememberMe(String tenTk, String maKhau, boolean remember, String ipCoputer) {
        this.tenTk = tenTk;
        this.maKhau = maKhau;
        this.remember = remember;
        this.ipCoputer = ipCoputer;
    }

    public String getTenTk() {
        return tenTk;
    }

    public void setTenTk(String tenTk) {
        this.tenTk = tenTk;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getIpCoputer() {
        return ipCoputer;
    }

    public void setIpCoputer(String ipCoputer) {
        this.ipCoputer = ipCoputer;
    }
}
