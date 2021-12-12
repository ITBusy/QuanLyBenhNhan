package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.NhanVien;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAOImp extends SystemDAO<NhanVien, String> implements NhanVienDAO {

    @Override
    public int insert(NhanVien nv) {
        String sql = "INSERT INTO dbo.NhanVien(maNV, matKhau, tenNV, ngaySinh, diaChi, soDienThoai, "
                + " email, gioiTinh, vaiTro, active) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return XJDBC.executeUpdate(sql,
                nv.getMaNV(),
                nv.getMatKhau(),
                nv.getTenNV(),
                nv.getNgaysinhNV(),
                nv.getDiachi(),
                nv.getSodienthoai(),
                nv.getEmail(),
                nv.getGioitinh(),
                nv.getVaiTro(),
                nv.isActive());
    }

    @Override
    public int update(NhanVien nv) {
        String sql = "UPDATE dbo.NhanVien SET matKhau = ?, tenNV = ?, ngaySinh = ?, diaChi = ?, "
                + " soDienThoai = ?, email = ?, gioiTinh = ?, vaiTro = ? "
                + " WHERE maNV = ?";
        return XJDBC.executeUpdate(sql,
                nv.getMatKhau(),
                nv.getTenNV(),
                nv.getNgaysinhNV(),
                nv.getDiachi(),
                nv.getSodienthoai(),
                nv.getEmail(),
                nv.getGioitinh(),
                nv.getVaiTro(),
                nv.getMaNV());
    }

    @Override
    public int delete(List<String> list) {
        int isSucc = 0;
        for (String nv : list) {
            String sql = "DELETE FROM dbo.NhanVien WHERE maNV = ?";
            if (XJDBC.executeUpdate(sql, nv) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setMatKhau(rs.getString("matKhau"));
                    nv.setTenNV(rs.getString("tenNV"));
                    nv.setNgaysinhNV(rs.getDate("ngaySinh"));
                    nv.setDiachi(rs.getString("diaChi"));
                    nv.setSodienthoai(rs.getString("soDienThoai"));
                    nv.setEmail(rs.getString("email"));
                    nv.setGioitinh(rs.getString("gioiTinh"));
                    nv.setVaiTro(rs.getString("vaiTro"));
                    nv.setActive(rs.getBoolean("active"));
                    list.add(nv);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public NhanVien selectByID(String maNV) {
        String sql = "SELECT * FROM dbo.NhanVien WHERE maNV = ?";
        List<NhanVien> list = selectBySql(sql, maNV);
        return list.size() > 0 ? list.get(0) : null;

    }

    @Override
    public List<NhanVien> getAll() {
        String sql = "SELECT * FROM dbo.NhanVien WHERE active = 'true'";
        return selectBySql(sql);
    }

    @Override
    public String getRowLast() {
        try {
            String sql = "SELECT maNV FROM dbo.NhanVien ORDER BY maNV DESC";
            ResultSet rs = XJDBC.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int insertRetore(NhanVien et) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getMaNV() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT maNV FROM dbo.NhanVien "
                    + " WHERE maNV NOT IN (SELECT maNV FROM dbo.KhamBenh) "
                    + " AND maNV NOT IN (SELECT MaNV FROM dbo.BienLai) "
                    + " AND maNV NOT IN (SELECT MaNV FROM dbo.PhieuThuoc) "
                    + " AND maNV NOT IN (SELECT NguoiYeuCau FROM dbo.PhieuXetNghiem) "
                    + " AND maNV NOT IN (SELECT MaNV FROM dbo.HoSoBenhAn)";
            ResultSet rs = XJDBC.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public List<NhanVien> timKiemTen(String tenNV) {
        String sql = "SELECT * FROM dbo.NhanVien "
                + " WHERE tenNV LIKE ?";
        return selectBySql(sql, tenNV + "%");
    }

    @Override
    public int updateActive(NhanVien nv) {
        String sql = "UPDATE dbo.NhanVien SET active = ? WHERE maNV = ?";
        return XJDBC.executeUpdate(sql, nv.isActive(), nv.getMaNV());
    }

    @Override
    public List<NhanVien> taiKhoanBiKhoa() {
        String sql = "SELECT * FROM dbo.NhanVien WHERE active = 0";
        return selectBySql(sql);
    }

    @Override
    public boolean isActive(String maNV) {
        try {
            String sql = "SELECT active FROM dbo.NhanVien WHERE	maNV = ?";
            ResultSet rs = XJDBC.executeQuery(sql, maNV);
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
