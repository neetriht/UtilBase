package com.zds.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import com.stock.dbpool.DataSourceOper;


public class TestDatabase {

    /**
     * @param args
     */
    static Date dm;
    static DataSourceOper sconn;

    private static Date getMaxDate() {
        Date s = null;
        try {
            String sql = "select max(open_dt) from red_blue";

            Map.Entry<String, Connection> c = sconn.getConnection();
            Statement st = c.getValue().createStatement();
            ResultSet rs = st.executeQuery(sql);
            //ResultSet rs = sconn.executeQuery(sql,"115");
            rs.next();
            s = rs.getDate(1);
            rs.close();
            st.close();
            sconn.releaseConnection(c);
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // PreparedStatement ps ;
        //TestBean tb = new TestBean();
        sconn = new DataSourceOper();
        System.out.println("Check db");
        dm = getMaxDate();
        System.out.println(dm);
    }
}