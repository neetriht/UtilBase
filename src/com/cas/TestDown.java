package com.cas;

import com.GlobalRandom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-07-31
 * Time: 11:03
 * <p>
 * Description:
 */
public class TestDown {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // testConn();
        // testOracle();
        // testMysql();
        // testPostgres();

//        readDB2U();
//        readDB2D();
        // updateCDT();

        //readUpdateMass();
        downfile();
    }

    public static void downfile() {
        try {
            //readDB2P();
            dowork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDB2P() throws Exception {
        UATDB2Oper db2d = new UATDB2Oper("DB2P");
        String db2dsql = "SELECT EMP_CNTRY, EMP_NUM, EMP_NAME FROM DBPCASR.CASTEMP;";
        String sql2 = "SELECT USER_CNTRY, USER_NUM,USER_NM  FROM DBPCASR.CPCT";
        String sql3 = "SELECT USER_CNTRY, USER_NUM, USER_EMAIL FROM DBPCASR.CPCTUSER;";
        String SQL4 = "SELECT NAME,COLTYPE,LENGTH,COLNO FROM SYSIBM.SYSCOLUMNS  WHERE TBNAME = 'CASTSRHR' AND TBCREATOR = 'DBPCASR' ORDER BY COLNO;";
        //String SQL5 = "SELECT NAME,COLTYPE,LENGTH,COLNO FROM SYSIBM.SYSCOLUMNS  WHERE TBNAME = 'CASTSRH' AND TBCREATOR = 'DBPCASR' ORDER BY COLNO;";
        ResultSet rs = db2d.executeQuery(db2dsql);
        while (rs.next()) {
            System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3));
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
        }
    }

    public static void dowork() {
        UATDB2Oper db2d = new UATDB2Oper("DB2U");
        String sql = "update DBUCAS1.CASTARAS SET EMP_ORG =?, EMP_BU =?, EMP_TYPE =?, EMP_GEO =?, EMP_BAND=?, EMP_TERR =?, EMP_SKIL =?, SITE_PARTY_ID =?, MVS_PROD_TYPE =?, EFF_DT =?, MVS_MKTG_ID=?, MVS_COMPONENT_ID =?, PID_RLS_LVL =?, CAUSE_CD =? WHERE DOC_NUM=2";
        PreparedStatement ps;
        ps = db2d.prepareStatement(sql);
        try {
            ps.setString(1, GlobalRandom.getRandomString(30, 5));
            ps.setString(2, GlobalRandom.getRandomString(8, 5));
            ps.setString(3, GlobalRandom.getRandomString(5, 5));
            ps.setString(4, GlobalRandom.getRandomString(10, 5));
            ps.setString(5, GlobalRandom.getRandomString(2, 5));
            ps.setString(6, GlobalRandom.getRandomString(30, 5));
            ps.setString(7, GlobalRandom.getRandomString(5, 5));
            ps.setString(8, GlobalRandom.getRandomString(10, 5));
            ps.setString(9, GlobalRandom.getRandomString(20, 5));
            ps.setString(10, "2019-09-01");
            ps.setString(11, GlobalRandom.getRandomString(60, 5));
            ps.setString(12, GlobalRandom.getRandomString(60, 5));
            ps.setString(13, GlobalRandom.getRandomString(30, 5));
            ps.setString(14, GlobalRandom.getRandomString(30, 5));
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
