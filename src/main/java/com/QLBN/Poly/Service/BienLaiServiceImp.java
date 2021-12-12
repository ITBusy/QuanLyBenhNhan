package com.QLBN.Poly.Service;

import com.QLBN.Poly.DAO.BienLaiDAOImp;
import com.QLBN.Poly.Entity.BienLai;
import java.util.List;

public class BienLaiServiceImp extends SystemService<BienLai, Integer> implements BienLaiService {

    private BienLaiDAOImp BienLaiThuVienPhiDAOImp;

    public BienLaiServiceImp() {
        BienLaiThuVienPhiDAOImp = new BienLaiDAOImp();
    }

    @Override
    public int insert(BienLai et) {
        return BienLaiThuVienPhiDAOImp.insert(et);
    }

    @Override
    public int update(BienLai et) {
        return BienLaiThuVienPhiDAOImp.update(et);
    }

    @Override
    public int delete(List<Integer> list) {
        return BienLaiThuVienPhiDAOImp.delete(list);
    }

    @Override
    public BienLai selectByID(Integer id) {
        return BienLaiThuVienPhiDAOImp.selectByID(id);
    }

    @Override
    public List<BienLai> getAll() {
        return BienLaiThuVienPhiDAOImp.getAll();
    }

    @Override
    public boolean getStatusTT(Integer id, String name) {
        return BienLaiThuVienPhiDAOImp.getStatusTT(id, name);
    }

    @Override
    public int insertRetore(BienLai et) {
        return BienLaiThuVienPhiDAOImp.insertRetore(et);
    }

    @Override
    public Integer getRowLast() {
        return BienLaiThuVienPhiDAOImp.getRowLast();
    }

    @Override
    public List<BienLai> timKiemMaBL(String maBL) {
        return BienLaiThuVienPhiDAOImp.timKiemMaBL(maBL);
    }

    @Override
    public List<BienLai> PrintBienLai(String maBL) {
        return BienLaiThuVienPhiDAOImp.PrintBienLai(maBL);
    }

    @Override
    public double totalByMaBN(String maBN) {
        return BienLaiThuVienPhiDAOImp.totalByMaBN(maBN);
    }
}
