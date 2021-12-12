package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.HoSoBenhAn;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoSoBenhAnDAOImp extends SystemDAO<HoSoBenhAn, Integer> implements HoSoBenhAnDAO {

    @Override
    public int insert(HoSoBenhAn et) {
        String sql = "INSERT INTO dbo.HoSoBenhAn(VaoVien, RaVien, huyetAp, nhipTim, Mach, canNang, ketQuaDT, "
                + " maBenh, Makhoa, MaBN, MaNV, Cphidtri, ThoiGianTV, NguyenNhan) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return XJDBC.executeUpdate(sql, et.getVaoVien(),
                et.getRaVien(),
                et.getHuyetAp(),
                et.getNhipTim(),
                et.getMach(),
                et.getCanNang(),
                et.getKetQuaDT(),
                et.getMaBenh(),
                et.getMakhoa(),
                et.getMaBN(),
                et.getMaNV(),
                et.getCphidtri(),
                et.getTGTuVong(),
                et.getNguyenNhanTV());
    }

    @Override
    public int update(HoSoBenhAn et) {
        String sql = "UPDATE dbo.HoSoBenhAn SET VaoVien = ?, RaVien = ?, huyetAp = ?, nhipTim = ?, Mach = ?, "
                + " canNang = ?, ketQuaDT = ?, maBenh = ?, Makhoa = ?, MaBN = ?, MaNV = ?, Cphidtri = ?, "
                + " ThoiGianTV = ?, NguyenNhan = ? WHERE sPhieuHSBA = ?";
        return XJDBC.executeUpdate(sql, et.getVaoVien(),
                et.getRaVien(),
                et.getHuyetAp(),
                et.getNhipTim(),
                et.getMach(),
                et.getCanNang(),
                et.getKetQuaDT(),
                et.getMaBenh(),
                et.getMakhoa(),
                et.getMaBN(),
                et.getMaNV(),
                et.getCphidtri(),
                et.getTGTuVong(),
                et.getNguyenNhanTV(),
                et.getsPhieuHSBA());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.HoSoBenhAn WHERE sPhieuHSBA = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public HoSoBenhAn selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.HoSoBenhAn WHERE sPhieuHSBA = ?";
        List<HoSoBenhAn> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HoSoBenhAn> getAll() {
        String sql = "SELECT * FROM dbo.HoSoBenhAn";
        return selectBySql(sql);
    }

    @Override
    protected List<HoSoBenhAn> selectBySql(String sql, Object... args) {
        List<HoSoBenhAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new HoSoBenhAn(rs.getInt("sPhieuHSBA"),
                            XDate.toDate(rs.getString("VaoVien"), "yyyy-MM-dd HH:mm:ss"),
                            XDate.toDate(rs.getString("RaVien"), "yyyy-MM-dd HH:mm:ss"),
                            rs.getString("huyetAp"),
                            rs.getInt("nhipTim"),
                            rs.getInt("Mach"),
                            rs.getInt("canNang"),
                            rs.getString("ketQuaDT"),
                            rs.getInt("maBenh"),
                            rs.getInt("Makhoa"),
                            rs.getInt("MaBN"),
                            rs.getString("MaNV"),
                            rs.getDouble("Cphidtri"),
                            rs.getString("ThoiGianTV") == null ? null : XDate.toDate(rs.getString("ThoiGianTV"), "yyyy-MM-dd HH:mm:ss"),
                            rs.getString("NguyenNhan")));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public int insertRetore(HoSoBenhAn et) {
        String sql = "SET IDENTITY_INSERT dbo.HoSoBenhAn ON "
                + " INSERT INTO dbo.HoSoBenhAn(sPhieuHSBA, VaoVien, RaVien, huyetAp, nhipTim, Mach, canNang,"
                + " ketQuaDT, maBenh, Makhoa, MaBN, MaNV, Cphidtri, ThoiGianTV, NguyenNhan) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.HoSoBenhAn OFF";
        return XJDBC.executeUpdate(sql, et.getsPhieuHSBA(),
                et.getVaoVien(),
                et.getRaVien(),
                et.getHuyetAp(),
                et.getNhipTim(),
                et.getMach(),
                et.getCanNang(),
                et.getKetQuaDT(),
                et.getMaBenh(),
                et.getMakhoa(),
                et.getMaBN(),
                et.getMaNV(),
                et.getCphidtri(),
                et.getTGTuVong(),
                et.getNguyenNhanTV());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT sPhieuHSBA FROM dbo.HoSoBenhAn ORDER BY sPhieuHSBA DESC";
            ResultSet rs = XJDBC.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<HoSoBenhAn> timKiemTT(String sql, Object... args) {
        return selectBySql(sql, args);
    }

    @Override
    public boolean isDead(Integer maBN) {
        try {
            String sql = "SELECT * FROM dbo.HoSoBenhAn WHERE maBN = ? AND ketQuaDT = N'Tử Vong'";
            ResultSet rs = XJDBC.executeQuery(sql, maBN);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }
}
