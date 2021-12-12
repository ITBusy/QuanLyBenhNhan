package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Benh;
import com.QLBN.Poly.Entity.XetNghiem;
import java.util.List;

public interface XetNghiemDAO {
    public List<Integer> getMaXN();

    public List<XetNghiem> timKiemTenXN(String tenXN);
}
