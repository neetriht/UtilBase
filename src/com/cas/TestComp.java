package com.cas;

import com.GlobalRandom;
import com.GlobalTimer;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-04-12
 * Time: 09:57
 * <p>
 * Description:
 */
public class TestComp {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // testConn();
        // testOracle();
        // testMysql();
        // testPostgres();

//        readDB2D();
//        readDB2D();
        // updateCDT();

        //readUpdateMass();
        //readUpdateStringMass();
        //searchcountry();
        //addUROL();
        getname();
        //addNomralUROL();
    }

    public static void readDB2U() throws Exception {
        UATDB2Oper db2d = new UATDB2Oper("DB2D");
        String db2dsql = "SELECT NAME,COLTYPE,LENGTH,COLNO FROM SYSIBM.SYSCOLUMNS  WHERE TBNAME = 'CASTSRH' AND TBCREATOR = 'DBDCASR' ORDER BY COLNO;";
        ResultSet rs = db2d.executeQuery(db2dsql, "111");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4));
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
        }


    }

    public static String searchcountry(String cntry_iso2) throws Exception {

        UATDB2Oper db2d = new UATDB2Oper("DB2D");
        String c = cntry_iso2;
        String db2dsql = "SELECT CNTRY_ISO2, CNTRY_DESCR, CNTRY_IBM FROM DBDCASR.CASTCTY  WHERE CNTRY_ISO2='" + c + "';";
        ResultSet rs = db2d.executeQuery(db2dsql, "111");
        if (rs.next()) {
            return rs.getString(2);
            //System.out.println(rs.getString(1) + " : " + rs.getString(2));
            //  System.out.println(rs.getString(2) + "',");
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
        } else return null;
    }

    public static void getname() throws Exception {
        //String[] cntry_iso2 = {"AO", "BW", "BI", "CD", "CV", "DJ", "ET", "MG", "ER", "MW", "LR", "MZ", "ZW", "ST", "RW", "SL", "SO", "SS", "MU", "UG", "NA", "ZA", "SC", "ZM"};
        String[] cntry_iso2 = {"AO", "BW", "BI", "CD", "CV", "DJ", "ET", "MG", "ER", "MW", "LR", "MZ", "ZW", "ST", "RW",
                "SL", "SO", "SS", "MU", "UG", "NA", "ZA", "SC", "ZM", "BE", "LU", "NL", "DK", "FI", "NO",
                "SE"};
        List<String> namelist = Collections.synchronizedList(new LinkedList<String>());
        for (String c : cntry_iso2) {
            namelist.add(searchcountry(c));
        }
        while (namelist.size() > 0)
            System.out.print("\"" + namelist.remove(0) + "\",");
    }

    public static void addUROL() throws Exception {
        String[] cntry_iso2 = {"DZ", "AT", "BJ", "BG", "BF", "CM", "CF", "TD", "CG", "HR", "CY", "CZ", "DK", "EG", "GQ", "EE", "FI", "FR", "GA", "GM", "GH", "GR", "GN", "GW", "IL", "CI", "KZ", "KE", "LV", "LY", "LT", "ML", "MT", "MR", "MA", "NE", "NG", "NO", "PK", "PL", "PT", "QA", "RO", "RU", "SA", "SN", "RS", "SK", "SI", "ES", "SE", "CH", "SY", "TZ", "TG", "TN", "TR", "UA", "HU", "AE"};

        String t = GlobalTimer.getTimestamp();
        String gmt = GlobalTimer.getTimestamp(8);
        String sqladmin = "insert into DBDCASR.CPCTUROL values('@@@','RULEADM','672','207745','2019-10-27','9999-12-31','','672','207745','" + t + "','" + gmt + "');";
        String sqlaprover = "insert into DBDCASR.CPCTUROL values('@@@','RULEAPRV','672','207745','2019-10-27','9999-12-31','','672','207745','" + t + "','" + gmt + "');";
        String sqldis = "insert into DBDCASR.CPCTUROL values('@@@','RULEDISPLY','672','207745','2019-10-27','9999-12-31','','672','207745','" + t + "','" + gmt + "');";

        UATDB2Oper db2d = new UATDB2Oper("DB2D");
//        String admin = new String(sqladmin);
//        String aprover = new String(sqlaprover);
//        String dis = new String(sqldis);
        String cntry_ibm = "";
        for (String a : cntry_iso2) {
            cntry_ibm = searchcountry(a);
            System.out.println(cntry_ibm);
            String admin = new String(sqladmin).replaceAll("@@@", cntry_ibm);
            String aprover = new String(sqlaprover).replaceAll("@@@", cntry_ibm);
            String dis = new String(sqldis).replaceAll("@@@", cntry_ibm);

            for (String sql : new String[]{admin, aprover, dis}) {
                System.out.println(sql);
                db2d.executeUpdate(sql, "234");
            }
            //d

            //UATDB2Oper db2d = new UATDB2Oper("DB2D");
        }
    }

    public static void addNomralUROL() throws Exception {
        String t = GlobalTimer.getTimestamp();
        String gmt = GlobalTimer.getTimestamp(-8);
        String cdate = GlobalTimer.getToday();
        //String[] cntry_iso2 = {"DZ", "AT", "BJ", "BG", "BF", "CM", "CF", "TD", "CG", "HR", "CY", "CZ", "DK", "EG", "GQ", "EE", "FI", "FR", "GA", "GM", "GH", "GR", "GN", "GW", "IL", "CI", "KZ", "KE", "LV", "LY", "LT", "ML", "MT", "MR", "MA", "NE", "NG", "NO", "PK", "PL", "PT", "QA", "RO", "RU", "SA", "SN", "RS", "SK", "SI", "ES", "SE", "CH", "SY", "TZ", "TG", "TN", "TR", "UA", "HU", "AE"};
        //String[] cntry_ibms = {"758"};
        //String[] ids ={"758057309", "758026286"};
        //String[] cntry_ibms = {"381", "624", "788"};
        String[] cntry_ibms = {"678", "806", "846"};
//        String[] ids ={"74408372K", "74408373O"};
        //String[] cntry_ibms = {"702"};
        //String[] ids ={"788018941"};
        String[] ids = {"702323330"};


        for (String id : ids) {
            String sqladmin = "insert into DBDCASR.CPCTUROL values('@@@','RULEADM','" + id.substring(0, 3) + "','" + id.substring(3, 9) + "','" + cdate + "','9999-12-31','','672','207745','" + t + "','" + gmt + "');";
            String sqlaprover = "insert into DBDCASR.CPCTUROL values('@@@','RULEAPRV','" + id.substring(0, 3) + "','" + id.substring(3, 9) + "','" + cdate + "','9999-12-31','','672','207745','" + t + "','" + gmt + "');";
            String sqldis = "insert into DBDCASR.CPCTUROL values('@@@','RULEDISPLY','" + id.substring(0, 3) + "','" + id.substring(3, 9) + "','" + cdate + "','9999-12-31','','672','207745','" + t + "','" + gmt + "');";

            UATDB2Oper db2d = new UATDB2Oper("DB2D");
            for (String cntry_ibm : cntry_ibms) {
                System.out.println(cntry_ibm);
                String admin = new String(sqladmin).replaceAll("@@@", cntry_ibm);
                String aprover = new String(sqlaprover).replaceAll("@@@", cntry_ibm);
                String dis = new String(sqldis).replaceAll("@@@", cntry_ibm);

                for (String sql : new String[]{admin, aprover}) {
                    System.out.println(sql);
                    db2d.executeUpdate(sql, "234");
                }
                //d

                //UATDB2Oper db2d = new UATDB2Oper("DB2D");
            }
        }
    }

    public static void readDB2D() throws Exception {
        UATDB2Oper db2d = new UATDB2Oper();
        String db2dsql = "SELECT NAME,COLTYPE,LENGTH,COLNO FROM SYSIBM.SYSCOLUMNS  WHERE TBNAME = 'CASTSRH' AND TBCREATOR = 'DBDCASR' ORDER BY COLNO;";
        ResultSet rs = db2d.executeQuery(db2dsql, "222");

        while (rs.next()) {
            System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4));
            System.out.println(rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4));
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
        }
    }

//    public static void updateCDT() {
//        UpdateData ud = new UpdateData();
//        ud.updatecdt();
//    }

    public static void readUpdateMass() throws Exception {
        UATDB2Oper db2d = new UATDB2Oper();
        String db2dsql = "SELECT DOC_NUM FROM DBDCASR.CASTRAP WHERE CNTRY_IBM = '616' AND DOC_NUM IN ('00066720','00066721','R04754223884','R04756FAC4A8','R047C110DDB6','00065835','00065847','00065852','00066645');";
        ResultSet rs = db2d.executeQuery(db2dsql, "222");

        double[] pool = {0.01, 3600, 10.1, 2.4, 7, 2, 100, 120, 33, 1.11, 9.99, 111, 99.9, 22, 11, 55.1};

        while (rs.next()) {
            String doc_num = rs.getString(1);
            System.out.println(doc_num + " : " + GlobalRandom.getRandomfloat(pool));
            String sql = "update DBDCASR.CASTRAP set LBR_DUR_MINS = " + GlobalRandom.getRandomfloat(pool) + " where doc_num = '" + doc_num + "'";
            System.out.println(sql);
            //db2d.executeUpdate(sql,"123" );
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
        }
    }

    public static void readUpdateStringMass() throws Exception {
        UATDB2Oper db2d = new UATDB2Oper();
        String db2dsql = "SELECT DOC_NUM FROM DBDCASR.CASTRAP WHERE CNTRY_IBM = '616' AND SRC_SYS = 'RETAINRAP';";
        ResultSet rs = db2d.executeQuery(db2dsql, "222");

        double[] pool = {0.01, 3600, 10.1, 2.4, 7, 2, 100, 120, 33, 1.11, 9.99, 111, 99.9, 22, 11, 55.1};
        String[] pools = {"11:54:59", "16:19:15", "17:30:00", "07:26:15", "08:02:30", "10:13:14", "09:14:27", "11:24:27", "11:27:41", "11:35:54", "11:45:00", "12:47:14"};

        while (rs.next()) {
            String doc_num = rs.getString(1);
            System.out.println(doc_num + " : " + GlobalRandom.getRandomfloat(pool));
            String sql = "update DBDCASR.CASTRAP set ACTV_STO_TM = '" + GlobalRandom.getRandomString(pools) + "' where doc_num = '" + doc_num + "'";
            System.out.println(sql);
            db2d.executeUpdate(sql, "123");
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
        }
    }


}
