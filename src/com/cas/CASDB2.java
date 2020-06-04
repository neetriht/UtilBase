package com.cas;

import com.config.PropertyUtilBase;
import com.stock.dbpool.ConnectionManager;
import com.stock.dbpool.DB2DataBase;
import com.stock.dbpool.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-03-08
 * Time: 15:12
 * <p>
 * Description:
 */
public class CASDB2 extends ConnectionManager implements DataSource {
    static Properties prop;
    private static String Driver = "com.ibm.db2.jcc.DB2Driver";
    private static String DB2D_url = "jdbc:db2://9.190.3.29:5004/DB2DMVSB";
    private static String DB2U_url = "jdbc:db2://9.190.3.29:5002/DB2UMVSB";
    private static String DB2P_url = "jdbc:db2://9.190.3.17:446/AAIRDBEP";

    private static String url_SIT01 = "jdbc:db2://sydmvsb.au.ibm.com:5002/DB2UMVSB";
    private static String url_UAT = "jdbc:db2://sydmvsb.au.ibm.com:5004/DB2DMVSB";
    private static String url_prod = "jdbc:db2://AAIR.AU.IBM.COM:446/AAIRDBEP";

    private static String crdb_prod = "jdbc:db2://b03avi11688250.ahe.boulder.ibm.com:50000/CRDBDV";
    private static String crdb_user = "db2inst1";
    private static String crdb_password = "fsnotes2018";

    private static String url; // = DB2U_url;
    private static String user; //  = "C207745";
    private static String password; // = "nhy65tgb";

    private static String user_prod = "C074083";
    private static String password_prod = "vfr45tgb";

    private static String user_uat = "C207745";
    private static String password_uat = "LOG77GOL";
//    private static String url = DB2U_url;
//    private static String user = "C207745";
//    private static String password = "MKO09IJN";

    static CASDB2 db2db;
    static Connection odbcconn;

    public CASDB2(String env) {
        if (env.equals("DB2D")) {
            url = DB2D_url;
            user = user_uat;
            password = password_uat;
        } else if (env.equals("CRDB")) {
            System.out.println("Choose CRDB!!!");
            url = crdb_prod;
            user = crdb_user;
            password = crdb_password;
        } else if (env.equals("DB2P")) {
            System.out.println("Choose DB2P!!!");
            url = DB2P_url;
            user = user_prod;
            password = password_prod;
        }  else if (env.equals("DB2U")) {
                System.out.println("Choose DB2U!!!");
                url = DB2U_url;
                user = user_uat;
                password = password_uat;

        } else {
            try {
                prop = PropertyUtilBase.loadPropertyFile("DB2U.properties");
                //prop.load(CASDB2.class.getClassLoader().getResourceAsStream("com/config/DB2U.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(prop.get("Driver"));
            Driver = ((String) prop.get("Driver")).trim();
            url = ((String) prop.get("url")).trim();
            password = ((String) prop.get("password")).trim();
            user = ((String) prop.get("user")).trim();
        }
    }

    public CASDB2(String NEW_url, String id, String passwd) {
        url = NEW_url;
        user = id;
        password = passwd;
    }


    public CASDB2() {
        // url = DB2U_url;
    }

    public Connection newConn(String threadid) {

        try {
            //System.setProperty("db2.jcc.charsetDecoderEncoder", "3");
            Class.forName(Driver).newInstance();
            System.out.println(url);
            System.out.println(user);
            System.out.println(password);
            odbcconn = DriverManager.getConnection(url, user, password);
            odbcconn.setAutoCommit(true);
            ConnectionManager.getPool().put(threadid, odbcconn);
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
            db2db = new CASDB2();
        return db2db.getConnection(threadid);
    }

//    @Override
//    public Connection getConnection() {
//        if (db2db == null)
//            db2db = new CASDB2();
//        return db2db.pullConnection();
//    }
//
//    @Override
//    public void releaseConnection(Connection conn) {
//        returnConn(conn);
//    }

}
