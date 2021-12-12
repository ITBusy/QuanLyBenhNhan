
package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.HoSoBenhAn;
import java.util.List;

public interface HoSoBenhAnDAO {
    public List<HoSoBenhAn> timKiemTT(String sql, Object ...args);
    public boolean isDead(Integer maBN);
}
