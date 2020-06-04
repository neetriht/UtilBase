package com.stock.dbpool;

import com.config.PropertyUtilBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PGDatabase extends ConnectionManager implements DataSource {
    //    private static String Driver = "org.postgresql.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/doubleball";
//    // "jdbc:mysql://localhost:3306/chatroom?Unicode=true&characterEncoding=gb2312";
//    //private static String url_m = "jdbc:mysql://192.168.255.136:3306/db2tras";
//    private static String url_m = "jdbc:mysql://localhost:3306/WeWave";
//    private static String user = "root";
//    private static String password = "mko00okm";
//
    static PGDatabase pgbase;
    //    static Connection odbcconn;
    String config_file = "Postgres.properties";
    static Connection odbcconn;
    static Properties prop;
    private static String Driver, url, user, password;

    public PGDatabase() {
        try {
            //prop.load(DB2DataBase.class.getClassLoader().getResourceAsStream("com/config/DB2local.properties"));
            //prop = PropertyUtilBase.loadPropertyFile("com/config/DB2local.properties");
            //prop = PropertyUtilBase.loadPropertyFile("DB2host.properties");
            prop = PropertyUtilBase.loadPropertyFile(config_file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Driver = (String) prop.get("Driver");
        url = (String) prop.get("url");
        password = (String) prop.get("password");
        user = (String) prop.get("user");

        System.out.println(prop.get("Driver"));
        System.out.println("URL: " + url);
        System.out.println("U/P:" + user + " : " + password);
    }

    @Override
    public Connection newConn(String threadid) {
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

    @Override
    public Connection getInstance(String threadid) {
        if (pgbase == null) {
            System.out.println("######################  ----->  " + threadid);
            pgbase = new PGDatabase();
        }

        return pgbase.getConnection(threadid);
    }
}
