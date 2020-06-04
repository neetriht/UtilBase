package com.stock.dbpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDataBase extends ConnectionManager implements DataSource {
    private static String Driver = "oracle.jdbc.driver.OracleDriver";
    private static String url = "jdbc:oracle:thin:@192.168.255.142:1521:ora1";

    private static String user = "scott";
    private static String password = "mko00okm";

    static OracleDataBase oracledb;
    static Connection odbcconn;

    public static Connection conn() {

        try {
            Class.forName(Driver).newInstance();

            odbcconn = DriverManager.getConnection(url, user, password);
            odbcconn.setAutoCommit(false);
            return odbcconn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getDriver() {
        return Driver;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public void closeconn() {
        try {
            odbcconn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Connection getInstance(String connName) {
        // TODO Auto-generated method stub
        if (oracledb == null)
            oracledb = new OracleDataBase();
        return oracledb.getConnection(connName);
    }
//
//    @Override
//    public Connection getInstance() {
//        // TODO Auto-generated method stub
////        if (oracledb == null)
////            oracledb = new OracleDataBase();
////        return oracledb.getConnection("1");
//        return this.getInstance("1");
//    }

    @Override
    public Connection newConn(String threadid) {
        try {
            Class.forName(Driver).newInstance();
            odbcconn = DriverManager.getConnection(url, user, password);
            odbcconn.setAutoCommit(true);
            ConnectionManager.getPool().put(threadid, odbcconn);
            System.out.println("Created new Connection: " + " | Total connection size: " + ConnectionManager.getPool().size());
            return odbcconn;
        } catch (Exception e) {
            System.out.println("DB connection Errer!!!");
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Connection getConnection() {
//        if (oracledb == null)
//            oracledb = new OracleDataBase();
//        return oracledb.pullConnection();
//    }
//
//    @Override
//    public void releaseConnection(Connection conn) {
//        returnConn(conn);
//    }
}
