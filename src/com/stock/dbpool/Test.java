package com.stock.dbpool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // testConn();
        //testOracle();
        //new DB2DataBase();
        //testMysql();
        //testPostgres();
        testpg();
    }

    public static void testConn() {
        String sql = " CREATE TABLE RASTDESC( " + "STOCK_CODE   	varchar(20) 		NOT NULL WITH DEFAULT,"
                + "DESC01 				varchar(65516), "
                + "UPDATE_DT 		DATE 						NOT NULL WITH DEFAULT,"
                + "primary key(STOCK_CODE , UPDATE_DT))";
        DataSourceOper sconn2 = new DataSourceOper(new MySQLDataBase());
        DataSourceOper sconn = new DataSourceOper(new DB2DataBase());
        System.out.println(sql);

        String sql2 = "select substr(stock_code,1,6), trim(desc01), char(update_dt) from rastdesc fetch first 10 rows only";
        // sconn.doQueryReturnBoolean(sql);
        try {
            ResultSet rs = sconn.executeQuery(sql2, "112");
            while (rs.next()) {
                sconn2.executeInsert("insert into RASTDESC values('" + rs.getString(1) + "','" + rs.getString(2) + "','"
                        + rs.getString(3) + "')");
                System.out.println(rs.getString(1) + " " + rs.getString(2).replace("?", "") + " " + rs.getString(3));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void testPostgres() {
        String sql01 = "select * from icetcompany";
        DataSourceOper sconn2 = new DataSourceOper(new PostgreSQLDataBase());
        // DataSourceOper sconn = new DataSourceOper(new DB2DataBase());
        System.out.println(sql01);

        try {
            ResultSet rs = sconn2.executeQuery(sql01, "113");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void testMysql() {
        String sql01 = "select * from ICETUSER";
        DataSourceOper sconn2 = new DataSourceOper(new MySQLDataBase());
        // DataSourceOper sconn = new DataSourceOper(new DB2DataBase());
        System.out.println(sql01);

        try {
            ResultSet rs = sconn2.executeQuery(sql01, "116");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void testConn2() {
        DataSourceOper sconn = new DataSourceOper(new MySQLDataBase());

        String sql = "select stock_code, desc01, update_dt from RASTDESC";
        System.out.println(sql);
        // sconn.doQueryReturnBoolean(sql);
        try {
            ResultSet rs = sconn.executeQuery(sql, "114");
            while (rs.next()) {
                // sconn2.executeInsert("insert into RASTDESC values('"+
                // rs.getString(1) +"','"+rs.getString(2)+"','"
                // +rs.getString(3)+"')");
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void testOracle() {
        DataSourceOper sconn = new DataSourceOper(new OracleDataBase());
        String sql01 = "select * from user_tablespaces";
        String sql02 = "select * from user_tables";
        String sql = "select * from user_tablespaces";
        System.out.println(sql);
        // sconn.doQueryReturnBoolean(sql);
        try {

            ResultSet rs = sconn.executeQuery(sql02, "20");
            while (rs.next()) {
                // sconn2.executeInsert("insert into RASTDESC values('"+
                // rs.getString(1) +"','"+rs.getString(2)+"','"
                // +rs.getString(3)+"')");
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public static void testpg() {
        DataSourceOper sconn = new DataSourceOper(new PGDatabase());

        String sql = "select * from rastsdd;";
        System.out.println(sql);
        // sconn.doQueryReturnBoolean(sql);
        try {
            ResultSet rs = sconn.executeQuery(sql, "114");
            while (rs.next()) {
                // sconn2.executeInsert("insert into RASTDESC values('"+
                // rs.getString(1) +"','"+rs.getString(2)+"','"
                // +rs.getString(3)+"')");
                System.out.println(rs.getString(1) + " " + rs.getDouble(2) + " " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
