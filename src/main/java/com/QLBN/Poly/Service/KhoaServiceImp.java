package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.KhoaDAOImp;
import com.QLBN.Poly.Entity.Khoa;
import java.util.List;

public class KhoaServiceImp extends SystemService<Khoa, Integer> implements KhoaService {

    private KhoaDAOImp KhoaDAOImp;

    public KhoaServiceImp() {
        KhoaDAOImp = new KhoaDAOImp();
    }

    @Override
    public int insert(Khoa et) {
        return KhoaDAOImp.insert(et);
    }

    @Override
    public int update(Khoa et) {
        return KhoaDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return KhoaDAOImp.delete(list);
    }

    @Override
    public Khoa selectByID(Integer id) {
        return KhoaDAOImp.selectByID(id);
    }

    @Override
    public List<Khoa> getAll() {
        return KhoaDAOImp.getAll();
    }

    @Override
    public int insertRetore(Khoa et) {
        return KhoaDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return KhoaDAOImp.getRowLast();
    }

    @Override
    public List<Khoa> timKiemTenKhoa(String tenKhoa) {
        return KhoaDAOImp.timKiemTenKhoa(tenKhoa);
    }

    @Override
    public List<Integer> getMaKhoa() {
        return KhoaDAOImp.getMaKhoa();
    }

}
