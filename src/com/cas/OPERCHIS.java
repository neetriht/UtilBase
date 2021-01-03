package com.cas;

import com.stock.dbpool.DataSource;

import java.sql.*;
import java.util.Map;

public class OPERCHIS {


    DataSource datasource = new CHISDB2();
    Connection conn = null;
    Statement stmt = null;
    String sql = null;
    ResultSet odbcrs = null;

    public Statement getStatement(String threadid) {
        try {

            conn = datasource.getInstance().getValue();
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
            conn = datasource.getInstance().getValue();
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

        Connection short_conn = datasource.getInstance().getValue();
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

    public PreparedStatement prepareStatement(String sql, String threadid) {
        // DataSource datasource = new DB2DataBase();
        // DataSource datasource = new OracleDataBase();
        Connection short_conn = datasource.getInstance().getValue();
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