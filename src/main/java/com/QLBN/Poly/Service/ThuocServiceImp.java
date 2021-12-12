package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.ThuocDAOImp;
import com.QLBN.Poly.Entity.Thuoc;
import java.util.List;

public class ThuocServiceImp extends SystemService<Thuoc, Integer> implements ThuocService {

    private ThuocDAOImp thuocDAOImp;

    public ThuocServiceImp() {
        thuocDAOImp = new ThuocDAOImp();
    }

    @Override
    public int insert(Thuoc et) {
        return thuocDAOImp.insert(et);
    }

    @Override
    public int update(Thuoc et) {
        return thuocDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return thuocDAOImp.delete(list);
    }

    @Override
    public Thuoc selectByID(Integer id) {
        return thuocDAOImp.selectByID(id);
    }

    @Override
    public List<Thuoc> getAll() {
        return thuocDAOImp.getAll();
    }

    @Override
    public int insertRetore(Thuoc et) {
        return thuocDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return thuocDAOImp.getRowLast();
    }

    @Override
    public List<Integer> getMaThuoc() {
        return thuocDAOImp.getMaThuoc();
    }

    @Override
    public List<Thuoc> timKiemTenThuoc(String tenThuoc) {
        return thuocDAOImp.timKiemTenThuoc(tenThuoc);
    }

}
