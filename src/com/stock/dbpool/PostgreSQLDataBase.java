package com.stock.dbpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 * Created by neetriht on 2017-07-10.
 */
public class PostgreSQLDataBase extends ConnectionManager implements DataSource {

    static PostgreSQLDataBase postgredb;
    static Connection odbcconn;

    private static String Driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/pg2tice";

    private static String user = "postgres";
    private static String password = "mko00okm";

    @Override
    public Connection newConn(String con_num) {
        try {
            Class.forName(Driver).newInstance();
            odbcconn = DriverManager.getConnection(url, user, password);
            odbcconn.setAutoCommit(true);
            ConnectionManager.getPool().put(con_num, odbcconn);
            //System.out.println("Created new Connection: " + connName + " | Total connection size: " + ConnectionManager.getPool().size());
            return odbcconn;
        } catch (Exception e) {
            System.out.println("DB connection Errer!!!");
            e.printStackTrace();
            return null;
        }
    }
//
//    @Override
//    public Connection getInstance() {
//        return this.getInstance("1");
//    }
//

    @Override
    public Map.Entry<String, Connection> getInstance() {
        if (postgredb == null)
            postgredb = new PostgreSQLDataBase();
        return postgredb.getConnection();
    }

//    @Override
//    public void releaseConnection(Connection conn) {
//        returnConn(conn);
//    }
}
