package com.cas;

import com.GlobalTimer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * describe:
 *
 * @author scott dai
 * @date 2018/11/06
 */
public class FetchData {

    public void checkTable() {
        //String db2dsql = "SELECT NAME FROM SYSIBM.SYSTABLES  WHERE NAME = '" + table_name + "';";
        UATDB2Oper db2d = new UATDB2Oper("DB2U");
        String SQL_RAP = "SELECT CNTRY_IBM, SRC_SYS, PROB_NUM, DOC_NUM, ACTV_RPT_DT, ACTV_STO_TM FROM DBUCAS1.CASTRAP WHERE CNTRY_IBM = '616' AND SRC_SYS = 'SALESFORCE'";
        String SQL_SRHR = "SELECT CNTRY_IBM, SRC_SYS, INCIDENT, CLS_TS  FROM DBUCAS1.CASTSRHR WHERE CNTRY_IBM = '616' AND SRC_SYS = 'SALESFORCE'";
        String SQL_SRTR = "SELECT CNTRY_IBM, SRC_SYS, CE_ACT_DOC_NUM FROM DBUCAS1.CASTSRTR WHERE CNTRY_IBM = '616' AND SRC_SYS = 'SALESFORCE'";
        String SQL_CDT = "SELECT CNTRY_IBM, SRC_SYS, CE_ACT_DOC_NUM, GL_STAT_CD  FROM DBUCAS1.CASTCDT WHERE CNTRY_IBM = '616' AND SRC_SYS = 'SALESFORCE'";
        ResultSet rs = db2d.executeQuery(SQL_SRTR, "111");
        try {
            while (rs.next()) {
                // printSRHR(rs);
                comp_SRTR_CDT(rs.getString(3));
                //compRAP_SRTR(rs.getString(4));
                //compRAP_SRHR(rs.getString(3), rs.getString(5) + "-" + rs.getString(6));
                //ACTV_RPT_DT, ACTV_STO_TM
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void compRAP_SRHR(String incident, String ts) {
        UATDB2Oper db2d = new UATDB2Oper("DB2U");
        String SQL_find_SRTR = "SELECT CNTRY_IBM, SRC_SYS, INCIDENT, OPEN_TS, CLS_TS FROM DBUCAS1.CASTSRHR WHERE CNTRY_IBM = '616' AND SRC_SYS = 'SALESFORCE' and INCIDENT = '" + incident + "'";
        ResultSet rs = db2d.executeQuery(SQL_find_SRTR, "222");
        try {
            if (rs.next()) {
                String opents = rs.getString(4);
                String CLS_TS = rs.getString(5);
                if (GlobalTimer.compareDate(ts, CLS_TS))
                    System.out.println(incident + " Bigger E " + ts + ": " + CLS_TS);
                else {
                    if (GlobalTimer.compareDate(opents, ts))
                        System.out.println(incident + " update open " + ts + ": " + opents);
                    else System.out.println(incident + " not update " + ts + ": " + opents + "----------------=");
                }
            }
            //else System.out.println(incident + "Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void compRAP_SRTR(String doc) {
        UATDB2Oper db2d = new UATDB2Oper("DB2U");
        String SQL_find_SRTR = "SELECT CNTRY_IBM, SRC_SYS, CE_ACT_DOC_NUM FROM DBUCAS1.CASTSRTR WHERE CNTRY_IBM = '616' AND SRC_SYS = 'SALESFORCE' and CE_ACT_DOC_NUM = '" + doc + "'";
        ResultSet rs = db2d.executeQuery(SQL_find_SRTR, "222");
        try {
            if (rs.next()) {
                System.out.println(doc + "FOUND ");
            } else System.out.println(doc + "Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printRAP(ResultSet rs) throws SQLException {
        String PROB_NUM = rs.getString(3);
        String DOC_NUM = rs.getString(4);
        String ACTV_RPT_DT = rs.getString(5);
        //String ACTV_STO_TM = rs.getString(6);

        System.out.println(DOC_NUM + " " + PROB_NUM + " " + ACTV_RPT_DT);
    }

    public void printSRHR(ResultSet rs) throws SQLException {
        String INCIDENT = rs.getString(3);
        String CLS_TS = rs.getString(4);
        System.out.println(INCIDENT + " " + CLS_TS);
    }

    public void printCDT(ResultSet rs) throws SQLException {
        String CE_ACT_DOC_NUM = rs.getString(3);
        String GL_STAT_CD = rs.getString(4);
        System.out.println(CE_ACT_DOC_NUM + " " + GL_STAT_CD);
    }

    public void printSRTR(ResultSet rs) throws SQLException {
        String DOC = rs.getString(3);
        System.out.println(DOC);
    }

    public void comp_SRTR_CDT(String doc) {

        UATDB2Oper db2d = new UATDB2Oper("DB2U");
        String SQL_find_SRTR = "SELECT CNTRY_IBM, SRC_SYS, CE_ACT_DOC_NUM, GL_STAT_CD  FROM DBUCAS1.CASTCDT WHERE CNTRY_IBM = '616' AND SRC_SYS = 'SALESFORCE' and CE_ACT_DOC_NUM = '" + doc + "'";
        ResultSet rs = db2d.executeQuery(SQL_find_SRTR, "222");
        try {
            if (rs.next()) {
                System.out.println(doc + "FOUND " + rs.getString(4));
            } else System.out.println(doc + "Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
