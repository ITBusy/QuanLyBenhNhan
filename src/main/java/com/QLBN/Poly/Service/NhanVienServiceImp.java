package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.NhanVienDAOImp;
import com.QLBN.Poly.Entity.NhanVien;
import java.util.List;

public class NhanVienServiceImp extends SystemService<NhanVien, String> implements NhanVienService {

    private final NhanVienDAOImp nhanVienDAOImp;

    public NhanVienServiceImp() {
        nhanVienDAOImp = new NhanVienDAOImp();
    }

    @Override
    public int insert(NhanVien nv) {
        return nhanVienDAOImp.insert(nv);
    }

    @Override
    public int update(NhanVien nv) {
        return nhanVienDAOImp.update(nv);
    }

    @Override
    public int delete(List<String> list) {
        return nhanVienDAOImp.delete(list);
    }

    @Override
    public NhanVien selectByID(String maNV) {
        return nhanVienDAOImp.selectByID(maNV);
    }

    @Override
    public List<NhanVien> getAll() {
        return nhanVienDAOImp.getAll();
    }

    @Override
    public String getRowLast() {
        return nhanVienDAOImp.getRowLast();
    }

    @Override
    public int insertRetore(NhanVien et) {
        return nhanVienDAOImp.insertRetore(et);
    }

    @Override
    public List<String> getMaNV() {
        return nhanVienDAOImp.getMaNV();
    }

    @Override
    public List<NhanVien> timKiemTen(String tenNV) {
        return nhanVienDAOImp.timKiemTen(tenNV);
    }

    @Override
    public int updateActive(NhanVien nv) {
        return nhanVienDAOImp.updateActive(nv);
    }

    @Override
    public List<NhanVien> taiKhoanBiKhoa() {
        return nhanVienDAOImp.taiKhoanBiKhoa();
    }

    @Override
    public boolean isActive(String maNV) {
        return nhanVienDAOImp.isActive(maNV);
    }

}
