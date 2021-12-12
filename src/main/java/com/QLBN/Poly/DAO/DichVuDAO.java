package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.DichVu;
import java.util.List;

public interface DichVuDAO {

    public List<DichVu> timKiemTenDV(String tenDV);

    public List<Integer> getMaDV();
}
