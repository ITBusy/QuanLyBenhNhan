package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.PhieuThuocDAOImp;
import com.QLBN.Poly.Entity.PhieuThuoc;
import java.util.List;

public class PhieuThuocServiceImp extends SystemService<PhieuThuoc, Integer> implements PhieuThuocService {

    private PhieuThuocDAOImp PhieuThuocDAOImp;

    public PhieuThuocServiceImp() {
        PhieuThuocDAOImp = new PhieuThuocDAOImp();
    }

    @Override
    public int insert(PhieuThuoc et) {
        return PhieuThuocDAOImp.insert(et);
    }

    @Override
    public int update(PhieuThuoc et) {
        return PhieuThuocDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return PhieuThuocDAOImp.delete(list);
    }

    @Override
    public PhieuThuoc selectByID(Integer id) {
        return PhieuThuocDAOImp.selectByID(id);
    }

    @Override
    public List<PhieuThuoc> getAll() {
        return PhieuThuocDAOImp.getAll();
    }

    @Override
    public int insertRetore(PhieuThuoc et) {
        return PhieuThuocDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return PhieuThuocDAOImp.getRowLast();
    }

    @Override
    public List<PhieuThuoc> timKiemMaPT(String maPT) {
        return PhieuThuocDAOImp.timKiemMaPT(maPT);
    }

    @Override
    public PhieuThuoc getBarcodeByCombobox(Integer maBN) {
        return PhieuThuocDAOImp.getBarcodeByCombobox(maBN);
    }

}
