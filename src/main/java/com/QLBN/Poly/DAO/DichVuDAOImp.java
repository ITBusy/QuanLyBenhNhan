package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.DichVu;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAOImp extends SystemDAO<DichVu, Integer> implements DichVuDAO {

    @Override
    public int insert(DichVu et) {
        String sql = "INSERT INTO dbo.DichVu(TenDV, SoLuong, Dongia, Dvtinh) "
                + " VALUES(?,?,?,?)";
        return XJDBC.executeUpdate(sql, et.getTenDV(),
                et.getSoLuong(),
                et.getDonGia(),
                et.getDonViTinh());
    }

    @Override
    public int update(DichVu et) {
        String sql = "UPDATE dbo.DichVu SET TenDV = ?, SoLuong = ?, Dongia = ?, Dvtinh = ? WHERE MaDV = ?";
        return XJDBC.executeUpdate(sql, et.getTenDV(),
                et.getSoLuong(),
                et.getDonGia(),
                et.getDonViTinh(),
                et.getMaDV());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.DichVu WHERE MaDV = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public DichVu selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.DichVu WHERE MaDV = ?";
        List<DichVu> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<DichVu> getAll() {
        String sql = "SELECT * FROM dbo.DichVu";
        return selectBySql(sql);
    }

    @Override
    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new DichVu(rs.getInt("MaDV"),
                            rs.getString("TenDV"),
                            rs.getInt("SoLuong"),
                            rs.getDouble("Dongia"),
                            rs.getString("Dvtinh")));
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
    public int insertRetore(DichVu et) {
        String sql = "SET IDENTITY_INSERT dbo.DichVu ON"
                + " INSERT INTO dbo.DichVu(MaDV, TenDV, SoLuong, Dongia, Dvtinh) "
                + " VALUES(?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.DichVu OFF";
        return XJDBC.executeUpdate(sql, et.getMaDV(),
                et.getTenDV(),
                et.getSoLuong(),
                et.getDonGia(),
                et.getDonViTinh());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT TOP 1 MaDV FROM dbo.DichVu ORDER BY MaDV DESC";
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
    public List<DichVu> timKiemTenDV(String tenDV) {
        String sql = "SELECT * FROM dbo.DichVu WHERE TenDV LIKE ?";
        return selectBySql(sql, tenDV + "%");
    }

    @Override
    public List<Integer> getMaDV() {
        List<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT MaDV FROM dbo.DichVu "
                    + " WHERE MaDV NOT IN (SELECT maDV FROM dbo.DV_BenhNhan_SD)";
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
