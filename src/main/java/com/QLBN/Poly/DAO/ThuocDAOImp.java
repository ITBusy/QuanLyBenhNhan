package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Thuoc;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThuocDAOImp extends SystemDAO<Thuoc, Integer> implements ThuocDAO {

    @Override
    public int insert(Thuoc et) {
        String sql = "INSERT INTO dbo.Thuoc(TenBietDuoc, tenHoatDuoc, HamLuong, DVT, donGia) "
                + " VALUES(?,?,?,?,?)";
        return XJDBC.executeUpdate(sql, et.getTenBietDuoc(),
                et.getTenHoatDuoc(),
                et.getHamLuong(),
                et.getDVT(),
                et.getDonGia());
    }

    @Override
    public int update(Thuoc et) {
        String sql = "UPDATE dbo.Thuoc SET TenBietDuoc = ?, tenHoatDuoc = ?, HamLuong = ?, DVT = ?, donGia = ? WHERE MaThuoc = ?";
        return XJDBC.executeUpdate(sql, et.getTenBietDuoc(),
                et.getTenHoatDuoc(),
                et.getHamLuong(),
                et.getDVT(),
                et.getDonGia(),
                et.getMaThuoc());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.Thuoc WHERE MaThuoc = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public Thuoc selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.Thuoc WHERE MaThuoc = ?";
        List<Thuoc> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Thuoc> getAll() {
        String sql = "SELECT * FROM dbo.Thuoc";
        return selectBySql(sql);
    }

    @Override
    protected List<Thuoc> selectBySql(String sql, Object... args) {
        List<Thuoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new Thuoc(rs.getInt("MaThuoc"),
                            rs.getString("TenBietDuoc"),
                            rs.getString("tenHoatDuoc"),
                            rs.getString("HamLuong"),
                            rs.getString("DVT"),
                            rs.getDouble("donGia")
                    ));
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
    public int insertRetore(Thuoc et) {
        String sql = "SET IDENTITY_INSERT dbo.Thuoc ON "
                + " INSERT INTO dbo.Thuoc(MaThuoc, TenBietDuoc, tenHoatDuoc, HamLuong, DVT, donGia) "
                + " VALUES(?,?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.Thuoc OFF";
        return XJDBC.executeUpdate(sql, et.getMaThuoc(),
                et.getTenBietDuoc(),
                et.getTenHoatDuoc(),
                et.getHamLuong(),
                et.getDVT(),
                et.getDonGia());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT TOP 1 MaThuoc FROM dbo.Thuoc ORDER BY MaThuoc DESC";
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
    public List<Integer> getMaThuoc() {
        List<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Thuoc "
                    + " WHERE MaThuoc NOT IN (SELECT Mathuoc FROM dbo.PhieuThuoc)";
            ResultSet rs = XJDBC.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Thuoc> timKiemTenThuoc(String tenThuoc) {
        String sql = "SELECT * FROM dbo.Thuoc "
                + " WHERE TenBietDuoc LIKE ?";
        return selectBySql(sql, tenThuoc + "%");
    }

}
