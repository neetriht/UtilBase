package com.stock.dbpool;

import com.GlobalParm;
import com.config.PropertyUtilBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Properties;

public class DB2DataBase extends ConnectionManager implements DataSource {
    // @@@mysql
    // private static String Driver = "com.mysql.jdbc.Driver";
    // private static String url = "jdbc:mysql://localhost:3306/doubleball";
    // private static String user = "root";
    // private static String password = "mko00okm";
    // @@@mysql
    // private static String url = "jdbc:mysql://localhost:3306/chatroom";
    // @@@db2
    // = "com.ibm.db2.jcc.DB2Driver";
    //private static String ; //= "jdbc:db2://localhost:50000/DB2TBLUE";

    // private static String ; // = "neetriht";
    //  private static String ; // = "xsw22wsx";

    static DB2DataBase db2db = null;
    static Connection odbcconn;
    static Properties prop;
    private static String Driver, url, user, password;

    public DB2DataBase(String propvalue) {
        try {
            //prop.load(DB2DataBase.class.getClassLoader().getResourceAsStream("com/config/DB2local.properties"));
            //prop = PropertyUtilBase.loadPropertyFile("com/config/DB2local.properties");
            //prop = PropertyUtilBase.loadPropertyFile("DB2host.properties");
            prop = PropertyUtilBase.loadPropertyFile(propvalue);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(prop.get("Driver"));
        Driver = (String) prop.get("Driver");
        url = (String) prop.get("url");
        //System.out.println("URL: " + url);
        password = (String) prop.get("password");
        user = (String) prop.get("user");

    }

    public DB2DataBase() {

        try {
            //prop.load(DB2DataBase.class.getClassLoader().getResourceAsStream("com/config/DB2local.properties"));
            //prop = PropertyUtilBase.loadPropertyFile("com/config/DB2local.properties");
            //prop = PropertyUtilBase.loadPropertyFile("DB2host.properties");
            prop = PropertyUtilBase.loadPropertyFile("DB2chis.properties");

        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(prop.get("Driver"));
        Driver = (String) prop.get("Driver");
        url = (String) prop.get("url");
        //System.out.println("URL: " + url);
        password = (String) prop.get("password");
        user = (String) prop.get("user");
    }


//    public DB2DataBase() {
//        if (GlobalParm.getDb2User() != null) {
//            System.out.println("Set DB2 user/password!!!");
//            user = GlobalParm.getDb2User();
//            password = GlobalParm.getDb2Password();
//        }
//    }

//    @Override
//    public Connection getInstance(String connName) {
//        if (db2db == null)
//            db2db = new DB2DataBase();
//        return db2db.getConnection(connName);
//        // return db2db.conn();
//    }

//    @Override
//    public Connection getConnection() {
//        if (ConnectionPools == null) {
//            ConnectionPools = new Hashtable<String, Connection>();
//        }
//        return ConnectionPools;
//        return null;
//    }

    @Override
    public Map.Entry<String, Connection> getInstance() {
        // TODO Auto-generated method stub
        if (db2db == null) {
            System.out.println("######################  ----->  " + Thread.currentThread().getId());
            db2db = new DB2DataBase();
        }
        //c = db2db.pullConnection();

        return db2db.getConnection();
        //return this.getInstance("1");
    }

//    @Override
//    public void releaseConnection(Connection conn) {
//
//        returnConn(conn);
//    }

    public Connection conn() {

        try {
            Class.forName(Driver).newInstance();

            odbcconn = DriverManager.getConnection(url, GlobalParm.getDb2User(), GlobalParm.getDb2Password());
            //odbcconn = DriverManager.getConnection(url, user, password);
            odbcconn.setAutoCommit(false);
            return odbcconn;
        } catch (Exception e) {
            System.out.println("DB connection Errer!!!");
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

    @Override
    public Connection newConn(String threadid) {
        // TODO Auto-generated method stub
        try {
            Class.forName(Driver).newInstance();
            System.out.println("Info: " + user + " : " + password);
            odbcconn = DriverManager.getConnection(url, user, password);
            odbcconn.setAutoCommit(true);

            ConnectionManager.getPool().put(threadid, odbcconn);  //add(odbcconn);
            //System.out.println("Created new Connection: " + connName + " | Total connection size: " + ConnectionManager.getPool().size());
            return odbcconn;
        } catch (Exception e) {
            System.out.println("DB connection Errer!!!: " + user + " : " + password);
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Connection newConn() {
//        try {
//            Class.forName(Driver).newInstance();
//            odbcconn = DriverManager.getConnection(url, user, password);
//            odbcconn.setAutoCommit(true);
//
//            ConnectionManager.getPool().put(threadid, odbcconn);  //add(odbcconn);
//            //System.out.println("Created new Connection: " + connName + " | Total connection size: " + ConnectionManager.getPool().size());
//            return odbcconn;
//        } catch (Exception e) {
//            System.out.println("DB connection Errer!!!: " + user + " : " + password);
//            e.printStackTrace();
//            return null;
//        }
//    }

    public void pushPool() {


    }
}