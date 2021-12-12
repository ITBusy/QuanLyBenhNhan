package com.QLBN.Poly.Utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XJDBC {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost;databaseName=QuanLyBenhNhan";
    private static String username = "sa";
    private static String password = "vanhung685864";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(dburl, username, password);
        PreparedStatement psm = null;
        if (sql.trim().startsWith("{")) {
            psm = connection.prepareCall(sql);
        } else {
            psm = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            psm.setObject(i + 1, args[i]);
        }
        return psm;
    }

    public static int executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement stmt = preparedStatement(sql, args);
            try {
                if (stmt.executeUpdate() > 0) {
                    return 1;
                }
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement stmt = preparedStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }
    public static void main(String[] args) {
        try {
            Connection conn = XJDBC.getConnection();
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(XJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
