package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.PhieuThuoc;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhieuThuocDAOImp extends SystemDAO<PhieuThuoc, Integer> implements PhieuThuocDAO {

    @Override
    public int insert(PhieuThuoc et) {
        String sql = "INSERT INTO dbo.PhieuThuoc(Mathuoc, Soluong, MaNV, ngayUong, soLan_Ngay, BarCode, "
                + " maBN, NgKeDon, tongTien) "
                + " VALUES(?,?,?,?,?,?,?,?,?)";
        return XJDBC.executeUpdate(sql,
                et.getMathuoc(),
                et.getSoluong(),
                et.getMaNV(),
                et.getNgayUong(),
                et.getSoLan_Ngay(),
                et.getBarCode(),
                et.getMaBN(),
                et.getNgKeDon(),
                et.getTongTien());
    }

    @Override
    public int update(PhieuThuoc et) {
        String sql = "UPDATE dbo.PhieuThuoc SET Mathuoc = ?, Soluong = ?, MaNV = ?, ngayUong = ?, soLan_Ngay = ?, "
                + " BarCode = ?, maBN = ?, NgKeDon = ?,"
                + " tongTien = ? WHERE sphieuthuoc = ?";
        return XJDBC.executeUpdate(sql,
                et.getMathuoc(),
                et.getSoluong(),
                et.getMaNV(),
                et.getNgayUong(),
                et.getSoLan_Ngay(),
                et.getBarCode(),
                et.getMaBN(),
                et.getNgKeDon(),
                et.getTongTien(),
                et.getSphieuthuoc());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.PhieuThuoc WHERE sphieuthuoc = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public PhieuThuoc selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.PhieuThuoc WHERE sphieuthuoc = ?";
        List<PhieuThuoc> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<PhieuThuoc> getAll() {
        String sql = null;
        if (Auth.isManager()) {
            sql = "SELECT * FROM dbo.PhieuThuoc";
        } else {
            sql = "SELECT * FROM dbo.PhieuThuoc WHERE NgKeDon = FORMAT(GETDATE(), 'yyyy-MM-dd')";
        }
        return selectBySql(sql);
    }

    @Override
    protected List<PhieuThuoc> selectBySql(String sql, Object... args) {
        List<PhieuThuoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new PhieuThuoc(rs.getInt("sphieuthuoc"),
                            rs.getInt("Mathuoc"),
                            rs.getString("Soluong"),
                            rs.getString("MaNV"),
                            rs.getString("ngayUong"),
                            rs.getString("soLan_Ngay"),
                            rs.getString("BarCode"),
                            rs.getInt("maBN"),
                            rs.getDate("NgKeDon"),
                            rs.getDouble("tongTien")));
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
    public int insertRetore(PhieuThuoc et) {
        String sql = "SET IDENTITY_INSERT dbo.PhieuThuoc ON "
                + " INSERT INTO dbo.PhieuThuoc(sphieuthuoc, Mathuoc, Soluong, MaNV, ngayUong, soLan_Ngay, BarCode, "
                + " maBN, NgKeDon, tongTien) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.PhieuThuoc OFF";
        return XJDBC.executeUpdate(sql, et.getSphieuthuoc(),
                et.getMathuoc(),
                et.getSoluong(),
                et.getMaNV(),
                et.getNgayUong(),
                et.getSoLan_Ngay(),
                et.getBarCode(),
                et.getMaBN(),
                et.getNgKeDon(),
                et.getTongTien());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT sphieuthuoc FROM dbo.PhieuThuoc ORDER BY sphieuthuoc DESC";
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
    public List<PhieuThuoc> timKiemMaPT(String maPT) {
        String sql = null;
        if (Auth.isManager()) {
            sql = "SELECT * FROM dbo.PhieuThuoc WHERE sphieuthuoc LIKE ?";
        } else {
            sql = "SELECT * FROM dbo.PhieuThuoc WHERE NgKeDon = FORMAT(GETDATE(), 'yyyy-MM-dd') AND sphieuthuoc LIKE ?";
        }
        return selectBySql(sql, maPT + "%");
    }

    @Override
    public PhieuThuoc getBarcodeByCombobox(Integer maBN) {
        PhieuThuoc pt = new PhieuThuoc();
        try {
            String sql = "SELECT DISTINCT BarCode FROM dbo.PhieuThuoc "
                    + " WHERE maBN = ? AND NgKeDon = FORMAT(GETDATE(), 'yyyy-MM-dd')";
            ResultSet rs = XJDBC.executeQuery(sql, maBN);
            if (rs.next()) {
                pt.setBarCode(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error getBarcode: " + ex.getMessage());
        }
        return pt;
    }

}
