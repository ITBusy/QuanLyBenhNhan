package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.BenhNhanDAOImp;
import com.QLBN.Poly.Entity.BenhNhan;
import java.util.List;

public class BenhNhanServiceImp extends SystemService<BenhNhan, Integer> implements BenhNhanService {

    private final BenhNhanDAOImp BenhNhanDAOImp;

    public BenhNhanServiceImp() {
        BenhNhanDAOImp = new BenhNhanDAOImp();
    }

    @Override
    public int insert(BenhNhan bn) {
        return BenhNhanDAOImp.insert(bn);
    }

    @Override
    public int update(BenhNhan bn) {
        return BenhNhanDAOImp.update(bn);
    }

    @Override
    public int delete(List<Integer> list) {
        return BenhNhanDAOImp.delete(list);
    }

    @Override
    public List<BenhNhan> getAll() {
        return BenhNhanDAOImp.getAll();
    }

    @Override
    public BenhNhan selectByID(Integer id) {
        return BenhNhanDAOImp.selectByID(id);
    }

    @Override
    public List<BenhNhan> getDSKhamBenh() {
        return BenhNhanDAOImp.getDSKhamBenh();
    }

    @Override
    public List<BenhNhan> getDSHoSo() {
        return BenhNhanDAOImp.getDSHoSo();
    }

    @Override
    public int insertRetore(BenhNhan et) {
        return BenhNhanDAOImp.insertRetore(et);
    }

    @Override
    public List<BenhNhan> timKiemTenBN(String tenBN) {
        return BenhNhanDAOImp.timKiemTenBN(tenBN);
    }

    @Override
    public List<Integer> getMaBN() {
        return BenhNhanDAOImp.getMaBN();
    }

    @Override
    public Integer getRowLast() {
        return BenhNhanDAOImp.getRowLast();
    }

    @Override
    public boolean isBHYT(Integer id) {
        return BenhNhanDAOImp.isBHYT(id);
    }

    @Override
    public List<BenhNhan> ListKhamBenhHN() {
        return BenhNhanDAOImp.ListKhamBenhHN();
    }

    @Override
    public List<BenhNhan> ListNoKhamBenhHN() {
        return BenhNhanDAOImp.ListNoKhamBenhHN();
    }

}
