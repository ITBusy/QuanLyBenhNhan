package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Benh;
import java.util.List;

public interface BenhDAO {

    public List<Integer> getMaBenh();

    public List<Benh> timKiemTenBenh(String tenBenh);

}
