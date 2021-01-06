package com.stock.dbpool;

import com.config.PropertyUtilBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;

public class PGDatabase extends ConnectionManager implements DataSource {

    static PGDatabase pgbase;
    private static int dbclient_num = 0;
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
    public Connection newConn(String con_num) {
        try {
            Class.forName(Driver).newInstance();
            dbclient_num++;
            System.out.println("==========> Info: " + user + " : " + password + " and client number: " + dbclient_num);
            odbcconn = DriverManager.getConnection(url, user, password);
            odbcconn.setAutoCommit(true);
            ConnectionManager.getPool().put(con_num, odbcconn);  //add(odbcconn);
            //System.out.println("Created new Connection: " + connName + " | Total connection size: " + ConnectionManager.getPool().size());
            return odbcconn;
        } catch (Exception e) {
            System.out.println("DB connection Errer!!!: " + user + " : " + password);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map.Entry<String, Connection> getInstance() {
        if (pgbase == null) {
            System.out.println("######################  ----->  " + Thread.currentThread().getId());
            pgbase = new PGDatabase();
        }
        return pgbase.getConnection();
    }
}
