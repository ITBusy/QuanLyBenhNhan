package com.QLBN.Poly.Entity;

import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BienLai {

    private int maBienLai;
    private String MaNV;
    private int MaBN;
    private Date ngayTT;
    private String TenKhoanChiPhi;
    private double TienChiPhi;

    public BienLai() {
    }

    public BienLai(int maBienLai, String MaNV, int MaBN, Date ngayTT, String TenKhoanChiPhi, double TienChiPhi) {
        this.maBienLai = maBienLai;
        this.MaNV = MaNV;
        this.MaBN = MaBN;
        this.ngayTT = ngayTT;
        this.TenKhoanChiPhi = TenKhoanChiPhi;
        this.TienChiPhi = TienChiPhi;
    }

    public int getMaBienLai() {
        return maBienLai;
    }

    public void setMaBienLai(int maBienLai) {
        this.maBienLai = maBienLai;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int MaBN) {
        this.MaBN = MaBN;
    }

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

    public String getTenKhoanChiPhi() {
        return TenKhoanChiPhi;
    }

    public void setTenKhoanChiPhi(String TenKhoanChiPhi) {
        this.TenKhoanChiPhi = TenKhoanChiPhi;
    }

    public double getTienChiPhi() {
        return TienChiPhi;
    }

    public void setTienChiPhi(double TienChiPhi) {
        this.TienChiPhi = TienChiPhi;
    }

    public double getTongCP(Integer id, String tenKhoan) {
        //"Khám Bệnh", "Xét Nghiệm", "Viện Phí", "Dịch Vụ"
        String sql = null;
        try {
            ResultSet rs = null;
            switch (tenKhoan) {
                case "Khám Bệnh":
                    sql = "SELECT CPKham FROM dbo.KhamBenh "
                            + " WHERE maBenhNhan = ? AND ngayKham = FORMAT(GETDATE(),'yyyy-MM-dd')";
                    break;
                case "Xét Nghiệm":
                    sql = "DECLARE @maBN INT "
                            + " SET @maBN = ? "
                            + " IF EXISTS (SELECT * FROM dbo.BenhNhan WHERE maBN = @maBN  AND Doituong = 1) "
                            + " BEGIN "
                            + "    SELECT SUM(DonGiaBHYT) FROM dbo.PhieuXetNghiem JOIN dbo.XetNghiem ON XetNghiem.MaXN = PhieuXetNghiem.MaXN "
                            + "    WHERE maBN = @maBN AND FORMAT(ngayXN,'yyyy-MM-dd') = FORMAT(GETDATE(),'yyyy-MM-dd') "
                            + " END "
                            + " ELSE "
                            + " BEGIN "
                            + "    SELECT SUM(DonGiaBIH) FROM dbo.PhieuXetNghiem JOIN dbo.XetNghiem ON XetNghiem.MaXN = PhieuXetNghiem.MaXN "
                            + "    WHERE maBN = @maBN AND FORMAT(ngayXN,'yyyy-MM-dd') = FORMAT(GETDATE(),'yyyy-MM-dd') "
                            + " END";
                    break;
                case "Viện Phí":
                    sql = "SELECT Cphidtri FROM dbo.HoSoBenhAn "
                            + " WHERE MaBN = ? AND FORMAT(VaoVien, 'yyyy-MM-dd') = FORMAT(GETDATE(),'yyyy-MM-dd')";
                    break;
                case "Dịch Vụ":
                    sql = "SELECT SUM(Dongia) Dongia FROM dbo.DV_BenhNhan_SD JOIN dbo.DichVu "
                            + " ON DichVu.MaDV = DV_BenhNhan_SD.maDV "
                            + " WHERE maBN = ? AND ngaySD = FORMAT(GETDATE(), 'yyyy-MM-dd')";
                    break;
                default:
                    break;
            }
            rs = XJDBC.executeQuery(sql, id);
            if (rs.next()) {
                return (rs.getDouble(1) + ((rs.getDouble(1) / 100) * 15));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }
}
