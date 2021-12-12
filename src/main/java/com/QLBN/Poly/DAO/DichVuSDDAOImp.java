package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.DichVuSD;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DichVuSDDAOImp extends SystemDAO<DichVuSD, Integer> implements DichVuSDDAO {

    @Override
    public int insert(DichVuSD et) {
        String sql = "INSERT INTO dbo.DV_BenhNhan_SD(maBN, maDV, ngaySD) "
                + " VALUES(?,?,?)";
        return XJDBC.executeUpdate(sql, et.getMaBN(),
                et.getMaDV(),
                et.getNgaySD());
    }

    @Override
    public int update(DichVuSD et) {
        String sql = "UPDATE dbo.DV_BenhNhan_SD SET maBN = ?, maDV = ?, ngaySD = ? WHERE sphieuSD = ?";
        return XJDBC.executeUpdate(sql, et.getMaBN(),
                et.getMaDV(),
                et.getNgaySD(),
                et.getSphieuSD());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.DV_BenhNhan_SD WHERE sphieuSD = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public DichVuSD selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.DV_BenhNhan_SD WHERE sphieuSD = ?";
        List<DichVuSD> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<DichVuSD> getAll() {
        String sql = "SELECT * FROM dbo.DV_BenhNhan_SD";
        return selectBySql(sql);
    }

    @Override
    protected List<DichVuSD> selectBySql(String sql, Object... args) {
        List<DichVuSD> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new DichVuSD(rs.getInt("sphieuSD"),
                            rs.getInt("maBN"),
                            rs.getInt("maDV"),
                            rs.getDate("ngaySD")));
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
    public int insertRetore(DichVuSD et) {
        String sql = "SET IDENTITY_INSERT dbo.DV_BenhNhan_SD ON "
                + " INSERT INTO dbo.DV_BenhNhan_SD(sphieuSD, maBN, maDV, ngaySD) "
                + " VALUES(?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.DV_BenhNhan_SD OFF";
        return XJDBC.executeUpdate(sql, et.getSphieuSD(),
                et.getMaBN(),
                et.getMaDV(),
                et.getNgaySD());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT sphieuSD FROM dbo.DV_BenhNhan_SD ORDER BY sphieuSD DESC";
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
    public List<DichVuSD> timKiemMaDVSD(String maDVSD) {
        String sql = "SELECT * FROM dbo.DV_BenhNhan_SD WHERE sphieuSD LIKE ?";
        return selectBySql(sql, maDVSD + "%");
    }

}
