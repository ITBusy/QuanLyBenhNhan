package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.BienLai;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BienLaiDAOImp extends SystemDAO<BienLai, Integer> implements BienLaiDAO {

    @Override
    public int insert(BienLai et) {
        String sql = "INSERT INTO dbo.BienLai(MaNV, MaBN, ngayTT, TenKhoanChiPhi, TienChiPhi) "
                + " VALUES(?,?,?,?,?)";
        return XJDBC.executeUpdate(sql, et.getMaNV(),
                et.getMaBN(),
                et.getNgayTT(),
                et.getTenKhoanChiPhi(),
                et.getTienChiPhi());
    }

    @Override
    public int update(BienLai et) {
        String sql = "UPDATE dbo.BienLai SET MaNV = ?, MaBN = ?, ngayTT = ?, TenKhoanChiPhi = ?, "
                + " TienChiPhi = ? WHERE maBienLai = ?";
        return XJDBC.executeUpdate(sql, et.getMaNV(),
                et.getMaBN(),
                et.getNgayTT(),
                et.getTenKhoanChiPhi(),
                et.getTienChiPhi(),
                et.getMaBienLai());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.BienLai WHERE maBienLai = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public BienLai selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.BienLai WHERE maBienLai = ?";
        List<BienLai> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<BienLai> getAll() {
        String sql = null;
        if (Auth.isManager()) {
            sql = "SELECT * FROM dbo.BienLai";
        } else {
            sql = "SELECT * FROM dbo.BienLai WHERE ngayTT = FORMAT(GETDATE(), 'yyyy-MM-dd')";
        }
        return selectBySql(sql);
    }

    @Override
    protected List<BienLai> selectBySql(String sql, Object... args) {
        List<BienLai> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new BienLai(rs.getInt("maBienLai"),
                            rs.getString("MaNV"),
                            rs.getInt("MaBN"),
                            rs.getDate("ngayTT"),
                            rs.getString("TenKhoanChiPhi"),
                            rs.getDouble("TienChiPhi")));
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
    public boolean getStatusTT(Integer id, String name) {
        try {
            String sql = "SELECT * FROM dbo.BienLai "
                    + " WHERE MaBN = ? AND TenKhoanChiPhi = ? AND TrangThai = N'Đã Thanh Toán' AND ngayTT = FORMAT(GETDATE(),'yyyy-MM-dd')";
            ResultSet rs = XJDBC.executeQuery(sql, id, name);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public int insertRetore(BienLai et) {
        String sql = "SET IDENTITY_INSERT dbo.BienLai ON "
                + " INSERT INTO dbo.BienLai(maBienLai, MaNV, MaBN, ngayTT, TenKhoanChiPhi, TienChiPhi) "
                + " VALUES(?,?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.BienLai OFF";
        return XJDBC.executeUpdate(sql, et.getMaBienLai(),
                et.getMaNV(),
                et.getMaBN(),
                et.getNgayTT(),
                et.getTenKhoanChiPhi(),
                et.getTienChiPhi());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT maBienLai FROM dbo.BienLai ORDER BY maBienLai DESC";
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
    public List<BienLai> timKiemMaBL(String maBL) {
        String sql = "";
        if (Auth.isManager()) {
            sql = "SELECT * FROM dbo.BienLai WHERE maBienLai LIKE ?";
        } else {
            sql = "SELECT * FROM dbo.BienLai WHERE ngayTT = FORMAT(GETDATE(), 'yyyy-MM-dd') AND maBienLai LIKE ?";
        }
        return selectBySql(sql, maBL + "%");
    }

    @Override
    public List<BienLai> PrintBienLai(String maBL) {
        String sql = "SELECT * FROM dbo.BienLai WHERE maBN = ?";
        return selectBySql(sql, maBL);
    }

    @Override
    public double totalByMaBN(String maBN) {
        try {
            String sql = "SELECT SUM(TienChiPhi) FROM dbo.BienLai WHERE MaBN = ?";
            ResultSet rs = XJDBC.executeQuery(sql, maBN);
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return 0;
    }
}
