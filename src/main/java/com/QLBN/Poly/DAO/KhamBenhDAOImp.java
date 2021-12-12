package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.KhamBenh;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhamBenhDAOImp extends SystemDAO<KhamBenh, Integer> implements KhamBenhDAO {

    @Override
    public int insert(KhamBenh et) {
        String sql = "INSERT INTO dbo.KhamBenh(ngayKham, trieuChung, huyetAp, nhipTim, Mach, "
                + " canNang, chuanDoan, yeuCau, maNV, maBenh, maBenhNhan, CPKham) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        return XJDBC.executeUpdate(sql, et.getNgayKham(),
                et.getTrieuChung(),
                et.getHuyetAp(),
                et.getNhipTim(),
                et.getMach(),
                et.getCanNang(),
                et.getChuanDoan(),
                et.isYeuCau(),
                et.getMaNV(),
                et.getMaBenh(),
                et.getMaBenhNhan(),
                et.getCPKham());
    }

    @Override
    public int update(KhamBenh et) {
        String sql = "UPDATE dbo.KhamBenh SET ngayKham = ?, trieuChung = ?, huyetAp = ?, nhipTim = ?, "
                + " Mach = ?, canNang = ?, chuanDoan = ?, yeuCau = ?, maNV = ?, maBenh = ?, maBenhNhan = ?, "
                + " CPKham = ? WHERE soPhieuKham = ?";
        return XJDBC.executeUpdate(sql, et.getNgayKham(),
                et.getTrieuChung(),
                et.getHuyetAp(),
                et.getNhipTim(),
                et.getMach(),
                et.getCanNang(),
                et.getChuanDoan(),
                et.isYeuCau(),
                et.getMaNV(),
                et.getMaBenh(),
                et.getMaBenhNhan(),
                et.getCPKham(),
                et.getSoPhieuKham());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.KhamBenh WHERE soPhieuKham = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public KhamBenh selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.KhamBenh WHERE soPhieuKham = ?";
        List<KhamBenh> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhamBenh> getAll() {
        String sql = "SELECT * FROM dbo.KhamBenh";
        return selectBySql(sql);
    }

    @Override
    protected List<KhamBenh> selectBySql(String sql, Object... args) {
        List<KhamBenh> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new KhamBenh(rs.getInt("soPhieuKham"),
                            rs.getDate("ngayKham"),
                            rs.getString("trieuChung"),
                            rs.getString("huyetAp"),
                            rs.getInt("nhipTim"),
                            rs.getInt("Mach"),
                            rs.getInt("canNang"),
                            rs.getString("chuanDoan"),
                            rs.getBoolean("yeuCau"),
                            rs.getString("maNV"),
                            rs.getInt("maBenh"),
                            rs.getInt("maBenhNhan"),
                            rs.getDouble("CPKham")));
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
    public int insertRetore(KhamBenh et) {
        String sql = "SET IDENTITY_INSERT dbo.KhamBenh ON "
                + " INSERT INTO dbo.KhamBenh(soPhieuKham, ngayKham, trieuChung, huyetAp, nhipTim, Mach, "
                + " canNang, chuanDoan, yeuCau, maNV, maBenh, maBenhNhan, CPKham) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.KhamBenh OFF";
        return XJDBC.executeUpdate(sql, et.getSoPhieuKham(),
                et.getNgayKham(),
                et.getTrieuChung(),
                et.getHuyetAp(),
                et.getNhipTim(),
                et.getMach(),
                et.getCanNang(),
                et.getChuanDoan(),
                et.isYeuCau(),
                et.getMaNV(),
                et.getMaBenh(),
                et.getMaBenhNhan(),
                et.getCPKham());
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT soPhieuKham FROM dbo.KhamBenh ORDER BY soPhieuKham DESC";
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
    public List<KhamBenh> timKiemMaKB(String maKB) {
        String sql = "SELECT * FROM dbo.KhamBenh WHERE soPhieuKham LIKE ?";
        return selectBySql(sql, maKB + "%");
    }

}
