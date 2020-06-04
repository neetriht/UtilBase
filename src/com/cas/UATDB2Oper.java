package com.cas;

import com.stock.dbpool.DataSource;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-03-08
 * Time: 15:18
 * <p>
 * Description:
 */
public class UATDB2Oper {

    DataSource datasource = new CASDB2();

    Connection conn = null;
    Statement stmt = null;
    String sql = null;
    ResultSet odbcrs = null;

    public UATDB2Oper(String env) {
        datasource = new CASDB2(env);
    }

    public UATDB2Oper(String NEW_url, String id, String passwd) {
        datasource = new CASDB2(NEW_url, id, passwd);
    }

    public UATDB2Oper() {
    }

    public UATDB2Oper(DataSource ds) {
        datasource = ds;
    }

    public Statement getStatement(String threadid) {
        try {

            conn = datasource.getInstance(threadid);
            conn.setAutoCommit(false);
            return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException ex) {

            System.err.println("sql_data.getStatement:" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet executeQuery(String sql, String threadid) {

        try {
            // System.out.println(sql);
            conn = datasource.getInstance(threadid);
            // ResultSet.CONCUR_UPDATABLE);

            stmt = conn.createStatement();

            // stmt =
            // conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            // System.out.println(conn + ": " + stmt + " : " + threadid);
            odbcrs = stmt.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(threadid + " : " + sql);
            System.err.println("sql_data.executeQuery:" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return odbcrs;
    }

    public boolean executeUpdate(String sql, String threadid) {

        Connection short_conn = datasource.getInstance(threadid);
        try {
            // conn = datasource.getInstance(threadid);
            stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(sql);

            short_conn.commit();

        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.executeUpdate:" + ex.getMessage());
            return false;
        }
        return true;
    }

    public PreparedStatement prepareStatement(String sql) {
        return prepareStatement(sql, "9988");
    }

    public PreparedStatement prepareStatement(String sql, String threadid) {
        // DataSource datasource = new DB2DataBase();
        // DataSource datasource = new OracleDataBase();
        Connection short_conn = datasource.getInstance(threadid);
        try {
            //short_conn = datasource.getInstance(threadid);
            short_conn.setAutoCommit(true);
            PreparedStatement ps = short_conn.prepareStatement(sql);
            return ps;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
