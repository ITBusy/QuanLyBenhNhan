
package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.DichVu;
import java.util.List;

public interface DichVuService {
    public List<DichVu> timKiemTenDV(String tenDV);

    public List<Integer> getMaDV();
}
