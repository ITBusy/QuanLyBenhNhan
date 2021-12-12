package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.DichVuSDDAOImp;
import com.QLBN.Poly.Entity.DichVuSD;
import java.util.List;

public class DichVuSDServiceImp extends SystemService<DichVuSD, Integer> implements DichVuSDService {

    private DichVuSDDAOImp DichVuSDDAOImp;

    public DichVuSDServiceImp() {
        DichVuSDDAOImp = new DichVuSDDAOImp();
    }

    @Override
    public int insert(DichVuSD et) {
        return DichVuSDDAOImp.insert(et);
    }

    @Override
    public int update(DichVuSD et) {
        return DichVuSDDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return DichVuSDDAOImp.delete(list);
    }

    @Override
    public DichVuSD selectByID(Integer id) {
        return DichVuSDDAOImp.selectByID(id);
    }

    @Override
    public List<DichVuSD> getAll() {
        return DichVuSDDAOImp.getAll();
    }

    @Override
    public int insertRetore(DichVuSD et) {
        return DichVuSDDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return DichVuSDDAOImp.getRowLast();
    }

    @Override
    public List<DichVuSD> timKiemMaDVSD(String maDVSD) {
        return DichVuSDDAOImp.timKiemMaDVSD(maDVSD);
    }
}
