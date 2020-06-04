package com.cas;

import com.config.PropertyUtilBase;
import com.stock.dbpool.ConnectionManager;
import com.stock.dbpool.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class CHISDB2 extends ConnectionManager implements DataSource {
    static Properties prop;
    private static String Driver = "com.ibm.db2.jcc.DB2Driver";
    private static String url = "jdbc:db2://9.110.24.227:60000/csdw";
    private static String user = "DB2INST1";
    private static String password = "MKOijn987654321";
    private static String user1 = "hadoop";
    private static String password1 = "mko00okm";
    private static String $user$ = user;
    private static String $password$ = password;


    static CHISDB2 db2db;
    static Connection odbcconn;

    @Override
    public Connection newConn(String connName) {

        try {
            //System.setProperty("db2.jcc.charsetDecoderEncoder", "3");
            Class.forName(Driver).newInstance();
            System.out.println(url);
            System.out.println(user);
            System.out.println(password);
            odbcconn = DriverManager.getConnection(url, $user$, $password$);
            odbcconn.setAutoCommit(true);
            ConnectionManager.getPool().put(connName, odbcconn);
            return odbcconn;
        } catch (Exception e) {
            System.out.println("DB connection Errer!!!: " + user + " : " + password);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Connection getInstance(String threadid) {
        if (db2db == null)
            db2db = new CHISDB2();
        return db2db.getConnection(threadid);
    }
}
