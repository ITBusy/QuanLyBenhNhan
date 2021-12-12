package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.PhieuXetNghiem;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.XDate;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhieuXetNghiemDAOImp extends SystemDAO<PhieuXetNghiem, Integer> implements PhieuXetNghiemDAO {

    @Override
    public int insert(PhieuXetNghiem et) {
        String sql = "INSERT INTO dbo.PhieuXetNghiem(MaXN, MaBN, NgayXN, KquaXN, LidoXN, NguoiYeuCau) "
                + " VALUES(?,?,?,?,?,?)";
        return XJDBC.executeUpdate(sql,
                et.getMaXN(),
                et.getMaBN(),
                et.getNgayXN(),
                et.getKquaXN(),
                et.getLidoXN(),
                et.getNguoiYeuCau());
    }

    @Override
    public int update(PhieuXetNghiem et) {
        String sql = "UPDATE dbo.PhieuXetNghiem SET MaXN = ?, MaBN = ?, NgayXN = ?, KquaXN = ?, LidoXN = ?,"
                + " NguoiYeuCau = ? WHERE SphieuXN = ?";
        return XJDBC.executeUpdate(sql,
                et.getMaXN(),
                et.getMaBN(),
                et.getNgayXN(),
                et.getKquaXN(),
                et.getLidoXN(),
                et.getNguoiYeuCau(),
                et.getSphieuXN());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.PhieuXetNghiem WHERE sphieuXN = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public PhieuXetNghiem selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.PhieuXetNghiem WHERE sphieuXN = ?";
        List<PhieuXetNghiem> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<PhieuXetNghiem> getAll() {
        String sql = null;
        if (Auth.isManager()) {
            sql = "SELECT * FROM dbo.PhieuXetNghiem";
        } else {
            sql = "SELECT * FROM dbo.PhieuXetNghiem WHERE ngayXN = FORMAT(GETDATE(), 'yyyy-MM-dd')";
        }
        return selectBySql(sql);
    }

    @Override
    protected List<PhieuXetNghiem> selectBySql(String sql, Object... args) {
        List<PhieuXetNghiem> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new PhieuXetNghiem(rs.getInt("sphieuXN"),
                            rs.getInt("MaXN"),
                            rs.getInt("maBN"),
                            XDate.toDate(rs.getString("ngayXN"), "yyyy-MM-dd HH:mm:ss"),
                            rs.getString("kquaXN"),
                            rs.getString("lidoXN"),
                            rs.getString("NguoiYeuCau")));
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
    public int insertRetore(PhieuXetNghiem et) {
        String sql = "SET IDENTITY_INSERT dbo.PhieuXetNghiem ON "
                + " INSERT INTO dbo.PhieuXetNghiem(sphieuXN, MaXN, MaBN, NgayXN, KquaXN, LidoXN, NguoiYeuCau) "
                + " VALUES(?,?,?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.PhieuXetNghiem OFF";
        return XJDBC.executeUpdate(sql, et.getSphieuXN(),
                et.getMaXN(),
                et.getMaBN(),
                et.getNgayXN(),
                et.getKquaXN(),
                et.getLidoXN(),
                et.getNguoiYeuCau());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT sphieuXN FROM dbo.PhieuXetNghiem ORDER BY sphieuXN DESC";
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
    public List<PhieuXetNghiem> timKiemMaPXN(String maPXN) {
        String sql = null;
        if (Auth.isManager()) {
            sql = "SELECT * FROM dbo.PhieuXetNghiem WHERE sphieuXN LIKE ?";
        } else {
            sql = "SELECT * FROM dbo.PhieuXetNghiem WHERE ngayXN = FORMAT(GETDATE(), 'yyyy-MM-dd') AND sphieuXN LIKE ?";
        }
        return selectBySql(sql, maPXN + "%");
    }
}
