package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.PhieuXetNghiemDAOImp;
import com.QLBN.Poly.Entity.PhieuXetNghiem;
import java.util.List;

public class PhieuXetNghiemServiceImp extends SystemService<PhieuXetNghiem, Integer> implements PhieuXetNghiemService {

    private PhieuXetNghiemDAOImp PhieuXetNghiemDAOImp;

    public PhieuXetNghiemServiceImp() {
        PhieuXetNghiemDAOImp = new PhieuXetNghiemDAOImp();
    }

    @Override
    public int insert(PhieuXetNghiem et) {
        return PhieuXetNghiemDAOImp.insert(et);
    }

    @Override
    public int update(PhieuXetNghiem et) {
        return PhieuXetNghiemDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return PhieuXetNghiemDAOImp.delete(list);
    }

    @Override
    public PhieuXetNghiem selectByID(Integer id) {
        return PhieuXetNghiemDAOImp.selectByID(id);
    }

    @Override
    public List<PhieuXetNghiem> getAll() {
        return PhieuXetNghiemDAOImp.getAll();
    }

    @Override
    public int insertRetore(PhieuXetNghiem et) {
        return PhieuXetNghiemDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return PhieuXetNghiemDAOImp.getRowLast();
    }

    @Override
    public List<PhieuXetNghiem> timKiemMaPXN(String maPXN) {
return PhieuXetNghiemDAOImp.timKiemMaPXN(maPXN);
    }
}
