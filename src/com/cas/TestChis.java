package com.cas;

import com.GlobalRandom;
import com.GlobalTimer;
import com.stock.dbpool.DataSourceOper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestChis {

    static PreparedStatement ps;
    static float COUNTER_ONETIME = 0, allcount = 0, TOTAL = 2000000;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub


        //countall();
        //addwork();
        checkdata();
    }

    public static void addwork() {
        try {
            dowork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void countall() throws SQLException {
        OPERCHIS db2d = new OPERCHIS();
        String SQL = "SELECT COUNT(*) FROM db2inst1.or_test";

        ResultSet rs = db2d.executeQuery(SQL);
        while (rs.next()) {
            System.out.println("Total count: " + rs.getString(1));
        }
    }


    public static void dowork() throws SQLException {

        OPERCHIS sconn = new OPERCHIS();
        String insersql = "insert into db2inst1.or_test values(?,?)";
        ps = sconn.prepareStatement(insersql);

        try {
            while (allcount < TOTAL) {
                subdata(1000);
                adddata();

                Thread.sleep(5);
            }
            subdata(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void checkdata() throws SQLException {
        OPERCHIS db2d = new OPERCHIS();
        //String SQL = "SELECT * FROM hadoop.RASTGAMB";
        String SQL = "SELECT * FROM db2inst1.RASTSTOK";
        ResultSet rs = db2d.executeQuery(SQL);
        while (rs.next()) {
            System.out.println("Total count: " + rs.getString(1));
            System.out.println("Total count: " + rs.getString(2));
            System.out.println("Total count: " + rs.getString(3));
            System.out.println("Total count: " + rs.getString(4));
            System.out.println("Total count: " + rs.getString(5));
        }
    }

    public static void subdata(int top) throws SQLException {
        if (COUNTER_ONETIME > top) {
            System.out.println("SUB ONE TIME.!!!  === " + GlobalTimer.getTimestamp());
            countall();
            ps.executeBatch();
            COUNTER_ONETIME = 0;
        }
    }

    public static void adddata() throws SQLException {

        ps.setString(1, GlobalRandom.getRandomString(5, 5));
        ps.setString(2, GlobalRandom.getRandomString(9, 5));
        ps.addBatch();
        COUNTER_ONETIME++;
        allcount++;


    }

}
