package com.QLBN.Poly.Service;

import com.QLBN.Poly.Entity.BenhNhan;
import java.util.List;

public interface BenhNhanService {

    public List<BenhNhan> getDSKhamBenh();

    public List<BenhNhan> getDSHoSo();

    public List<BenhNhan> timKiemTenBN(String tenBN);

    public List<Integer> getMaBN();
    
    public boolean isBHYT(Integer id);
    
    public List<BenhNhan> ListKhamBenhHN();
    
    public List<BenhNhan> ListNoKhamBenhHN();
}
