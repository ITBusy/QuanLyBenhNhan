package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.HoSoBenhAnDAOImp;
import com.QLBN.Poly.Entity.HoSoBenhAn;
import java.util.List;

public class HoSoBenhAnServiceImp extends SystemService<HoSoBenhAn, Integer> implements HoSoBenhAnService {

    private HoSoBenhAnDAOImp hoSoBenhAnDAOImp;

    public HoSoBenhAnServiceImp() {
        hoSoBenhAnDAOImp = new HoSoBenhAnDAOImp();
    }

    @Override
    public int insert(HoSoBenhAn et) {
        return hoSoBenhAnDAOImp.insert(et);
    }

    @Override
    public int update(HoSoBenhAn et) {
        return hoSoBenhAnDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return hoSoBenhAnDAOImp.delete(list);
    }

    @Override
    public HoSoBenhAn selectByID(Integer id) {
        return hoSoBenhAnDAOImp.selectByID(id);
    }

    @Override
    public List<HoSoBenhAn> getAll() {
        return hoSoBenhAnDAOImp.getAll();
    }

    @Override
    public int insertRetore(HoSoBenhAn et) {
        return hoSoBenhAnDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return hoSoBenhAnDAOImp.getRowLast();
    }

    @Override
    public List<HoSoBenhAn> timKiemTT(String sql, Object... args) {
        return hoSoBenhAnDAOImp.timKiemTT(sql, args);
    }

    @Override
    public boolean isDead(Integer maBN) {
        return hoSoBenhAnDAOImp.isDead(maBN);
    }
}
