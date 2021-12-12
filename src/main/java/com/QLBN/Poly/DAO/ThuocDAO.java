
package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Thuoc;
import java.util.List;


public interface ThuocDAO {
    public List<Integer> getMaThuoc();

    public List<Thuoc> timKiemTenThuoc(String tenThuoc);
}
