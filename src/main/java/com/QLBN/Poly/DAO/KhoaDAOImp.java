package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Khoa;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoaDAOImp extends SystemDAO<Khoa, Integer> implements KhoaDAO {

    @Override
    public int insert(Khoa et) {
        String sql = "INSERT INTO dbo.Khoa(Tenkhoa, KhuVuc) "
                + " VALUES(?,?)";
        return XJDBC.executeUpdate(sql, et.getTenKhoa(), et.getKhuVuc());
    }

    @Override
    public int update(Khoa et) {
        String sql = "UPDATE dbo.Khoa SET Tenkhoa = ?, KhuVuc = ? WHERE Makhoa = ?";
        return XJDBC.executeUpdate(sql, et.getTenKhoa(), et.getKhuVuc(), et.getMaKhoa());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.Khoa WHERE Makhoa = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public Khoa selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.Khoa WHERE Makhoa = ?";
        List<Khoa> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Khoa> getAll() {
        String sql = "SELECT * FROM dbo.Khoa";
        return selectBySql(sql);
    }

    @Override
    protected List<Khoa> selectBySql(String sql, Object... args) {
        List<Khoa> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new Khoa(rs.getInt("Makhoa"),
                            rs.getString("Tenkhoa"),
                            rs.getString("KhuVuc")));
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
    public int insertRetore(Khoa et) {
        String sql = "SET IDENTITY_INSERT dbo.Khoa ON "
                + " INSERT INTO dbo.Khoa(Makhoa, Tenkhoa, KhuVuc) "
                + " VALUES(?,?,?) "
                + " SET IDENTITY_INSERT dbo.Khoa OFF";
        return XJDBC.executeUpdate(sql, et.getMaKhoa(), et.getTenKhoa(), et.getKhuVuc());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT TOP 1 Makhoa FROM dbo.Khoa ORDER BY Makhoa DESC";
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
    public List<Khoa> timKiemTenKhoa(String tenKhoa) {
        String sql = "SELECT * FROM dbo.Khoa WHERE Tenkhoa LIKE ?";
        return selectBySql(sql, tenKhoa + "%");
    }

    @Override
    public List<Integer> getMaKhoa() {
        List<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Khoa "
                    + " WHERE Makhoa NOT IN (SELECT Makhoa FROM dbo.HoSoBenhAn)";
            ResultSet rs = XJDBC.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return list;
    }
}
