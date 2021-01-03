package com.stock.dbpool;

import com.GlobalRandom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;

public class MySQLDataBase extends ConnectionManager implements DataSource {
    // @@@mysql
    // private static String Driver = "com.mysql.jdbc.Driver";
    // private static String url = "jdbc:mysql://localhost:3306/doubleball";
    // private static String user = "root";
    // private static String password = "mko00okm";
    // @@@mysql
    // private static String url = "jdbc:mysql://localhost:3306/chatroom";
    // @@@db2
    private static String Driver = "org.postgresql.Driver";
    private static String url = "jdbc:mysql://localhost:3306/doubleball";
    // "jdbc:mysql://localhost:3306/chatroom?Unicode=true&characterEncoding=gb2312";
    //private static String url_m = "jdbc:mysql://192.168.255.136:3306/db2tras";
    private static String url_m = "jdbc:mysql://localhost:3306/WeWave";
    private static String user = "root";
    private static String password = "mko00okm";

    static MySQLDataBase mysqldb;
    static Connection odbcconn;


    public static Map.Entry<String, Connection> conn() {

        try {
            Class.forName(Driver).newInstance();

            odbcconn = DriverManager.getConnection(url_m, user, password);
            odbcconn.setAutoCommit(false);
            return new AbstractMap.SimpleEntry<String, Connection>(GlobalRandom.getRandomString(4, 3), odbcconn);
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

//    public Connection getInstance() {
//        // TODO Auto-generated method stub
//        if (mysqldb == null)
//            mysqldb = new MySQLDataBase();
//        return mysqldb.conn();
//    }

    public Map.Entry<String, Connection> getInstance() {
        if (mysqldb == null)
            mysqldb = new MySQLDataBase();
        return mysqldb.conn();
    }


//    @Override
//    public Connection getInstance(String threadid) {
//        if (mysqldb == null)
//            mysqldb = new MySQLDataBase();
//        return mysqldb.getConnection(threadid);
//    }

    //    @Override
//    public void releaseConnection(Connection conn) {
//        returnConn(conn);
//    }
//
    @Override
    public Connection newConn(String threadid) {

        //Connection c = ;
        return conn().getValue();
    }
}
