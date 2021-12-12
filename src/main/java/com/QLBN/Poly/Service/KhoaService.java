package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.Khoa;
import java.util.List;

public interface KhoaService {

    public List<Khoa> timKiemTenKhoa(String tenKhoa);

    public List<Integer> getMaKhoa();
}
