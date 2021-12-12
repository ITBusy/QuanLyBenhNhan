
package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.PhieuThuoc;
import java.util.List;

public interface PhieuThuocDAO {
    public List<PhieuThuoc> timKiemMaPT(String maPT);
    
    public PhieuThuoc getBarcodeByCombobox(Integer maBN);
}
