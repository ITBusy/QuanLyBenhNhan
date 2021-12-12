package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.HoSoBenhAn;
import java.util.List;

public interface HoSoBenhAnService {

    public List<HoSoBenhAn> timKiemTT(String sql, Object... args);

    public boolean isDead(Integer maBN);
}
