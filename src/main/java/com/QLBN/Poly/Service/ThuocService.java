package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.Thuoc;
import java.util.List;

public interface ThuocService {

    public List<Integer> getMaThuoc();

    public List<Thuoc> timKiemTenThuoc(String tenThuoc);
}
