package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.Benh;
import java.util.List;

public interface BenhService {

    public List<Integer> getMaBenh();

    public List<Benh> timKiemTenBenh(String tenBenh);
}
