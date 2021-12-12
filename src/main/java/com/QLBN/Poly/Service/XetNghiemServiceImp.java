package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.XetNghiemDAOImp;
import com.QLBN.Poly.Entity.XetNghiem;
import java.util.List;

public class XetNghiemServiceImp extends SystemService<XetNghiem, Integer> implements XetNghiemService {

    private XetNghiemDAOImp XetNghiemDAOImp;

    public XetNghiemServiceImp() {
        XetNghiemDAOImp = new XetNghiemDAOImp();
    }

    @Override
    public int insert(XetNghiem et) {
        return XetNghiemDAOImp.insert(et);
    }

    @Override
    public int update(XetNghiem et) {
        return XetNghiemDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return XetNghiemDAOImp.delete(list);
    }

    @Override
    public XetNghiem selectByID(Integer id) {
        return XetNghiemDAOImp.selectByID(id);
    }

    @Override
    public List<XetNghiem> getAll() {
        return XetNghiemDAOImp.getAll();
    }

    @Override
    public int insertRetore(XetNghiem et) {
        return XetNghiemDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return XetNghiemDAOImp.getRowLast();
    }

    @Override
    public List<Integer> getMaXN() {
        return XetNghiemDAOImp.getMaXN();
    }

    @Override
    public List<XetNghiem> timKiemTenXN(String tenXN) {
        return XetNghiemDAOImp.timKiemTenXN(tenXN);
    }

}
