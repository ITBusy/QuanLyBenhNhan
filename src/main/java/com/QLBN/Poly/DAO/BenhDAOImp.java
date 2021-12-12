package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.Benh;
import com.QLBN.Poly.Utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BenhDAOImp extends SystemDAO<Benh, Integer> implements BenhDAO {

    @Override
    public int insert(Benh et) {
        String sql = "INSERT INTO dbo.Benh(tenBenh, DauHieu) "
                + " VALUES(?, ?)";
        return XJDBC.executeUpdate(sql, et.getTenBenh(), et.getDauHieu());
    }

    @Override
    public int update(Benh et) {
        String sql = "UPDATE dbo.Benh SET tenBenh = ?, DauHieu = ? WHERE maBenh = ?";
        return XJDBC.executeUpdate(sql, et.getTenBenh(), et.getDauHieu(), et.getMaBenh());
    }

    @Override
    public int delete(List<Integer> list) {
        int isSucc = 0;
        for (Integer l : list) {
            String sql = "DELETE FROM dbo.Benh WHERE maBenh = ?";
            if (XJDBC.executeUpdate(sql, l) == 1) {
                isSucc = 1;
            }
        }
        return isSucc;
    }

    @Override
    public Benh selectByID(Integer id) {
        String sql = "SELECT * FROM dbo.Benh WHERE maBenh = ?";
        List<Benh> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Benh> getAll() {
        String sql = "SELECT * FROM dbo.Benh";
        return selectBySql(sql);
    }

    @Override
    protected List<Benh> selectBySql(String sql, Object... args) {
        List<Benh> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(new Benh(rs.getInt("maBenh"),
                            rs.getString("tenBenh"),
                            rs.getString("DauHieu")));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public int insertRetore(Benh et) {
        String sql = "SET IDENTITY_INSERT dbo.Benh ON"
                + " INSERT INTO dbo.Benh(maBenh, tenBenh, DauHieu) "
                + " VALUES(?,?,?) "
                + " SET IDENTITY_INSERT dbo.Benh OFF";
        return XJDBC.executeUpdate(sql, et.getMaBenh(), et.getTenBenh(), et.getDauHieu());
    }

    @Override
    public List<Integer> getMaBenh() {
        List<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT maBenh FROM dbo.Benh "
                    + " WHERE maBenh NOT IN (SELECT maBenh FROM dbo.HoSoBenhAn) "
                    + " AND maBenh NOT IN (SELECT maBenh FROM dbo.KhamBenh)";
            ResultSet rs = XJDBC.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public Integer getRowLast() {
        try {
            String sql = "SELECT TOP 1 maBenh FROM dbo.Benh ORDER BY maBenh DESC";
            ResultSet rs = XJDBC.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<Benh> timKiemTenBenh(String tenBenh) {
        String sql = "SELECT * FROM dbo.Benh "
                + " WHERE tenBenh LIKE ?";
        return selectBySql(sql, tenBenh + "%");
    }

}
