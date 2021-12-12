package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.NhanVien;
import java.util.List;

public interface NhanVienService {

    public List<String> getMaNV();

    public List<NhanVien> timKiemTen(String tenNV);

    public int updateActive(NhanVien nv);

    public List<NhanVien> taiKhoanBiKhoa();

    public boolean isActive(String maNV);
}
