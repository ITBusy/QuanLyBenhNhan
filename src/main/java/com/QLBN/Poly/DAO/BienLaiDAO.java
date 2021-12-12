package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.BienLai;
import java.util.List;

public interface BienLaiDAO {

    public boolean getStatusTT(Integer id, String name);
    
    public List<BienLai> timKiemMaBL(String maBL);
    
     public List<BienLai> PrintBienLai(String maBL);public double totalByMaBN(String maBN);
}
