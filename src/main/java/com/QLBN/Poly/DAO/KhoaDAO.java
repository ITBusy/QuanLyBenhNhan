package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Khoa;
import java.util.List;

public interface KhoaDAO {

    public List<Khoa> timKiemTenKhoa(String tenKhoa);

    public List<Integer> getMaKhoa();
}
