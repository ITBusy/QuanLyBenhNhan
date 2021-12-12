package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.BenhDAOImp;
import com.QLBN.Poly.Entity.Benh;
import java.util.List;

public class BenhServiceImp extends SystemService<Benh, Integer> implements BenhService {

    private BenhDAOImp benhDAOImp;

    public BenhServiceImp() {
        benhDAOImp = new BenhDAOImp();
    }

    @Override
    public int insert(Benh et) {
        return benhDAOImp.insert(et);
    }

    @Override
    public int update(Benh et) {
        return benhDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return benhDAOImp.delete(list);
    }

    @Override
    public Benh selectByID(Integer id) {
        return benhDAOImp.selectByID(id);
    }

    @Override
    public List<Benh> getAll() {
        return benhDAOImp.getAll();
    }

    @Override
    public int insertRetore(Benh et) {
        return benhDAOImp.insertRetore(et);
    }

    @Override
    public List<Integer> getMaBenh() {
        return benhDAOImp.getMaBenh();
    }

    @Override
    public Integer getRowLast() {
        return benhDAOImp.getRowLast();
    }

    @Override
    public List<Benh> timKiemTenBenh(String tenBenh) {
        return benhDAOImp.timKiemTenBenh(tenBenh);
    }
}
