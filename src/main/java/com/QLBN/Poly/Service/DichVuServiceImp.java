package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.DichVuDAOImp;
import com.QLBN.Poly.Entity.DichVu;
import java.util.List;

public class DichVuServiceImp extends SystemService<DichVu, Integer> implements DichVuService {

    private DichVuDAOImp DichVuDAOImp;

    public DichVuServiceImp() {
        DichVuDAOImp = new DichVuDAOImp();
    }

    @Override
    public int insert(DichVu et) {
        return DichVuDAOImp.insert(et);
    }

    @Override
    public int update(DichVu et) {
        return DichVuDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return DichVuDAOImp.delete(list);
    }

    @Override
    public DichVu selectByID(Integer id) {
        return DichVuDAOImp.selectByID(id);
    }

    @Override
    public List<DichVu> getAll() {
        return DichVuDAOImp.getAll();
    }

    @Override
    public int insertRetore(DichVu et) {
        return DichVuDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return DichVuDAOImp.getRowLast();
    }

    @Override
    public List<DichVu> timKiemTenDV(String tenDV) {
        return DichVuDAOImp.timKiemTenDV(tenDV);
    }

    @Override
    public List<Integer> getMaDV() {
        return DichVuDAOImp.getMaDV();
    }

}
