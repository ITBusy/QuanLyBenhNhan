package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.PhieuThuoc;
import java.util.List;

public interface PhieuThuocService {

    public List<PhieuThuoc> timKiemMaPT(String maPT);

    public PhieuThuoc getBarcodeByCombobox(Integer maBN);
}
