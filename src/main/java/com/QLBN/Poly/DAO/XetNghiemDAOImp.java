package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Benh;
import com.QLBN.Poly.Entity.XetNghiem;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XetNghiemDAOImp extends SystemDAO<XetNghiem, Integer> implements XetNghiemDAO {

    @Override
    public int insert(XetNghiem et) {
        String sql = "INSERT INTO dbo.XetNghiem(TenXN, DonGiaBIH, DonGiaBHYT, MucDich) "
                + " VALUES(?,?,?,?)";
        return XJDBC.executeUpdate(sql, et.getTenXN(),
                et.getDonGiaBIH(),
                et.getDonGiaBHYT(),
                et.getMucDich());
    }

    @Override
    public int update(XetNghiem et) {
        String sql = "UPDATE dbo.XetNghiem SET TenXN = ?, DonGiaBIH = ?, DonGiaBHYT = ?, MucDich = ? WHERE MaXN = ?";
        return XJDBC.executeUpdate(sql, et.getTenXN(),
                et.getDonGiaBIH(),
                et.getDonGiaBHYT(),
                et.getMucDich(),
                et.getMaXN());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.XetNghiem WHERE MaXN = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public XetNghiem selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.XetNghiem WHERE MaXN = ?";
        List<XetNghiem> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<XetNghiem> getAll() {
        String sql = "SELECT * FROM dbo.XetNghiem";
        return selectBySql(sql);
    }

    @Override
    protected List<XetNghiem> selectBySql(String sql, Object... args) {
        List<XetNghiem> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new XetNghiem(rs.getInt("MaXN"),
                            rs.getString("TenXN"),
                            rs.getDouble("DonGiaBIH"),
                            rs.getDouble("DonGiaBHYT"),
                            rs.getString("MucDich")));
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
    public int insertRetore(XetNghiem et) {
        String sql = "SET IDENTITY_INSERT dbo.XetNghiem ON "
                + " INSERT INTO dbo.XetNghiem(MaXN, TenXN, DonGiaBIH, DonGiaBHYT, MucDich) "
                + " VALUES(?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.XetNghiem OFF";
        return XJDBC.executeUpdate(sql, et.getMaXN(),
                et.getTenXN(),
                et.getDonGiaBIH(),
                et.getDonGiaBHYT(),
                et.getMucDich());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT TOP 1 MaXN FROM dbo.XetNghiem ORDER BY MaXN DESC";
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
    public List<Integer> getMaXN() {
        List<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT MaXN FROM dbo.XetNghiem "
                    + " WHERE MaXN NOT IN (SELECT MaXN FROM dbo.PhieuXetNghiem)";
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
    public List<XetNghiem> timKiemTenXN(String tenXN) {
        String sql = "SELECT * FROM dbo.XetNghiem WHERE TenXN LIKE ?";
        return selectBySql(sql, tenXN + "%");
    }

}
