
package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.XetNghiem;
import java.util.List;

public interface XetNghiemService {
    public List<Integer> getMaXN();

    public List<XetNghiem> timKiemTenXN(String tenXN);
}
