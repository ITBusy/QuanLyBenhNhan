package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.BenhNhan;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BenhNhanDAOImp extends SystemDAO<BenhNhan, Integer> implements BenhNhanDAO {

    @Override
    public int insert(BenhNhan bn) {
        String sql = "INSERT INTO dbo.BenhNhan(tenBN, Ngaysinh, Diachi, Gioitinh, ngheNghiep, danToc, "
                + " QuocTich, BHYTHieuLucTu, BHYTHieuLucDen, SoBHYT, nguoiThan, sdtNguoiThan, Doituong) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return XJDBC.executeUpdate(sql, bn.getTenBN(),
                bn.getNgaysinh(),
                bn.getDiachi(),
                bn.getGioitinh(),
                bn.getNgheNghiep(),
                bn.getDanToc(),
                bn.getQuocTich(),
                bn.getBHYTHieuLucTu(),
                bn.getBHYTHieuLucDen(),
                bn.getSoBHYT(),
                bn.getNguoiThan(),
                bn.getSdtNguoiThan(),
                bn.isDoituong());
    }

    @Override
    public int update(BenhNhan bn) {
        String sql = "UPDATE dbo.BenhNhan SET tenBN = ?,Ngaysinh = ?,Diachi = ?,Gioitinh = ?,ngheNghiep = ?, "
                + " danToc = ?,QuocTich = ?,BHYTHieuLucTu = ?,BHYTHieuLucDen = ?,SoBHYT = ?,nguoiThan = ?, "
                + " sdtNguoiThan = ?,Doituong = ? WHERE maBN = ?";
        return XJDBC.executeUpdate(sql, bn.getTenBN(),
                bn.getNgaysinh(),
                bn.getDiachi(),
                bn.getGioitinh(),
                bn.getNgheNghiep(),
                bn.getDanToc(),
                bn.getQuocTich(),
                bn.getBHYTHieuLucTu(),
                bn.getBHYTHieuLucDen(),
                bn.getSoBHYT(),
                bn.getNguoiThan(),
                bn.getSdtNguoiThan(),
                bn.isDoituong(),
                bn.getMaBN());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer i : list) {
            String sql = "DELETE FROM dbo.BenhNhan WHERE maBN = ?";
            if (XJDBC.executeUpdate(sql, i) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public List<BenhNhan> getAll() {
        String sql = "SELECT * FROM dbo.BenhNhan";
        return selectBySql(sql);
    }

    @Override
    public BenhNhan selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.BenhNhan WHERE maBN = ?";
        List<BenhNhan> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<BenhNhan> selectBySql(String sql, Object... args) {
        List<BenhNhan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new BenhNhan(rs.getInt("maBN"),
                            rs.getString("tenBN"),
                            rs.getDate("Ngaysinh"),
                            rs.getString("Diachi"),
                            rs.getString("Gioitinh"),
                            rs.getString("ngheNghiep"),
                            rs.getString("danToc"),
                            rs.getString("QuocTich"),
                            rs.getDate("BHYTHieuLucTu"),
                            rs.getDate("BHYTHieuLucDen"),
                            rs.getString("SoBHYT"),
                            rs.getString("nguoiThan"),
                            rs.getString("sdtNguoiThan"),
                            rs.getBoolean("Doituong")));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println("Error selectBySql: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<BenhNhan> getDSKhamBenh() {
        List<BenhNhan> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "SELECT dbo.BenhNhan.* FROM dbo.BenhNhan "
                    + " WHERE BenhNhan.maBN NOT IN (SELECT maBenhNhan FROM dbo.KhamBenh) "
                    + " OR maBN IN (SELECT dbo.BenhNhan.maBN FROM dbo.BenhNhan "
                    + " JOIN dbo.HoSoBenhAn "
                    + " ON HoSoBenhAn.MaBN = BenhNhan.maBN "
                    + " WHERE RaVien < FORMAT(GETDATE(), 'yyyy-MM-dd'))";
            rs = XJDBC.executeQuery(sql);
            while (rs.next()) {
                list.add(new BenhNhan(rs.getInt("maBN"),
                        rs.getString("tenBN"),
                        rs.getDate("Ngaysinh"),
                        rs.getString("Diachi"),
                        rs.getString("Gioitinh"),
                        rs.getString("ngheNghiep"),
                        rs.getString("danToc"),
                        rs.getString("QuocTich"),
                        rs.getDate("BHYTHieuLucTu"),
                        rs.getDate("BHYTHieuLucDen"),
                        rs.getString("SoBHYT"),
                        rs.getString("nguoiThan"),
                        rs.getString("sdtNguoiThan"),
                        rs.getBoolean("Doituong")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BenhNhanDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<BenhNhan> getDSHoSo() {
        List<BenhNhan> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "SELECT dbo.BenhNhan.* FROM dbo.BenhNhan "
                    + " WHERE maBN IN (SELECT dbo.BenhNhan.maBN FROM dbo.BenhNhan "
                    + " JOIN dbo.HoSoBenhAn "
                    + " ON HoSoBenhAn.MaBN = BenhNhan.maBN "
                    + " WHERE dbo.BenhNhan.maBN IN (SELECT MaBN FROM dbo.HoSoBenhAn) "
                    + " AND  RaVien < FORMAT(GETDATE(), 'yyyy-MM-dd'))"
                    + " OR maBN NOT IN (SELECT maBN FROM dbo.HoSoBenhAn)";
//            String sql = "SELECT dbo.BenhNhan.* FROM dbo.BenhNhan "
//                    + " WHERE (dbo.BenhNhan.maBN NOT IN (SELECT MaBN FROM dbo.HoSoBenhAn) "
//                    + " AND dbo.BenhNhan.maBN IN (SELECT maBenhNhan FROM dbo.KhamBenh)) "
//                    + " OR maBN IN (SELECT dbo.BenhNhan.maBN FROM dbo.BenhNhan "
//                    + " JOIN dbo.HoSoBenhAn "
//                    + " ON HoSoBenhAn.MaBN = BenhNhan.maBN "
//                    + " WHERE dbo.BenhNhan.maBN IN (SELECT MaBN FROM dbo.HoSoBenhAn) "
//                    + " AND dbo.BenhNhan.maBN IN (SELECT maBenhNhan FROM dbo.KhamBenh) "
//                    + " AND  RaVien < FORMAT(GETDATE(), 'yyyy-MM-dd'))";
            rs = XJDBC.executeQuery(sql);
            while (rs.next()) {
                list.add(new BenhNhan(rs.getInt("maBN"),
                        rs.getString("tenBN"),
                        rs.getDate("Ngaysinh"),
                        rs.getString("Diachi"),
                        rs.getString("Gioitinh"),
                        rs.getString("ngheNghiep"),
                        rs.getString("danToc"),
                        rs.getString("QuocTich"),
                        rs.getDate("BHYTHieuLucTu"),
                        rs.getDate("BHYTHieuLucDen"),
                        rs.getString("SoBHYT"),
                        rs.getString("nguoiThan"),
                        rs.getString("sdtNguoiThan"),
                        rs.getBoolean("Doituong")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BenhNhanDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        for (BenhNhan b : new BenhNhanDAOImp().getDSHoSo()) {
            System.out.println(b);
        }
    }

    @Override
    public int insertRetore(BenhNhan et) {
        String sql = "SET IDENTITY_INSERT dbo.BenhNhan ON "
                + " INSERT INTO dbo.BenhNhan(maBN, tenBN, Ngaysinh, Diachi, Gioitinh, ngheNghiep, danToc, "
                + " QuocTich, BHYTHieuLucTu, BHYTHieuLucDen, SoBHYT, nguoiThan, sdtNguoiThan, Doituong) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
                + " SET IDENTITY_INSERT dbo.BenhNhan OFF";
        return XJDBC.executeUpdate(sql, et.getMaBN(),
                et.getTenBN(),
                et.getNgaysinh(),
                et.getDiachi(),
                et.getGioitinh(),
                et.getNgheNghiep(),
                et.getDanToc(),
                et.getQuocTich(),
                et.getBHYTHieuLucTu(),
                et.getBHYTHieuLucDen(),
                et.getSoBHYT(),
                et.getNguoiThan(),
                et.getSdtNguoiThan(),
                et.isDoituong());
    }

    @Override
    public List<BenhNhan> timKiemTenBN(String tenBN) {
        String sql = "SELECT * FROM dbo.BenhNhan "
                + " WHERE tenBN LIKE ?";
        return selectBySql(sql, tenBN + "%");
    }

    @Override
    public List<Integer> getMaBN() {
        List<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT maBN FROM dbo.BenhNhan "
                    + " WHERE maBN NOT IN (SELECT maBN FROM dbo.PhieuXetNghiem) "
                    + " AND maBN NOT IN (SELECT maBN FROM dbo.PhongBenh) "
                    + " AND maBN NOT IN (SELECT maBN FROM dbo.DV_BenhNhan_SD) "
                    + " AND maBN NOT IN (SELECT MaBN FROM dbo.HoSoBenhAn) "
                    + " AND maBN NOT IN (SELECT maBenhNhan FROM dbo.KhamBenh) "
                    + " AND maBN NOT IN (SELECT maBN FROM dbo.PhieuThuoc) "
                    + " AND maBN NOT IN (SELECT maBN FROM dbo.BienLai)";
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
    public Integer getRowLast() {
        try {
            String sql = "SELECT TOP 1 maBN FROM dbo.BenhNhan ORDER BY maBN DESC";
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
    public boolean isBHYT(Integer id) {
        try {
            String sql = "SELECT * FROM dbo.BenhNhan WHERE maBN = ? AND DoiTuong = 1";
            ResultSet rs = XJDBC.executeQuery(sql, id);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BenhNhanDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
