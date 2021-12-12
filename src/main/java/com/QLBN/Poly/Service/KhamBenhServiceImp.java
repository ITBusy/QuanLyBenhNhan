package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.KhamBenhDAOImp;
import com.QLBN.Poly.Entity.KhamBenh;
import java.util.List;

public class KhamBenhServiceImp extends SystemService<KhamBenh, Integer> implements KhamBenhService {

    private KhamBenhDAOImp KhamBenhDAOImp;

    public KhamBenhServiceImp() {
        KhamBenhDAOImp = new KhamBenhDAOImp();
    }

    @Override
    public int insert(KhamBenh et) {
        return KhamBenhDAOImp.insert(et);
    }

    @Override
    public int update(KhamBenh et) {
        return KhamBenhDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return KhamBenhDAOImp.delete(list);
    }

    @Override
    public KhamBenh selectByID(Integer id) {
        return KhamBenhDAOImp.selectByID(id);
    }

    @Override
    public List<KhamBenh> getAll() {
        return KhamBenhDAOImp.getAll();
    }

    @Override
    public int insertRetore(KhamBenh et) {
        return KhamBenhDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return KhamBenhDAOImp.getRowLast();
    }

    @Override
    public List<KhamBenh> timKiemMaKB(String maKB) {
        return KhamBenhDAOImp.timKiemMaKB(maKB);
    }

}
