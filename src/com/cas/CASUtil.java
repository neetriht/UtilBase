package com.cas;

import com.GlobalCas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CASUtil {

    public List<String> getiso2(String code) throws SQLException {
        UATDB2Oper db2d = new UATDB2Oper("DB2D");
        if (code.length() > 0) {

            System.out.println(code);

            List<String> cntry_iso = Collections.synchronizedList(new LinkedList<String>());
            String db2dsql = "SELECT CNTRY_ISO2, CNTRY_DESCR, CNTRY_IBM FROM DBDCASR.CASTCTY  WHERE CNTRY_IBM in (" + code + ");";
            System.out.println(db2dsql);
            ResultSet rs = db2d.executeQuery(db2dsql);
            while (rs.next()) {
                // System.out.println(rs.getString(1));
                cntry_iso.add(rs.getString(1));
            }

            return cntry_iso;
//                return rs.getString(2);
//                //System.out.println(rs.getString(1) + " : " + rs.getString(2));
//                //  System.out.println(rs.getString(2) + "',");
////            System.out.println(rs.getString(2));
////            System.out.println(rs.getString(3));
////            System.out.println(rs.getString(4));
//            } else return null;
        }
        return null;
    }

    public List<String> getShort(String[] code) throws SQLException {
        return this.getiso2(GlobalCas.onebyone(code));
    }

    public List<String> getShort(String code) throws SQLException {
        return this.getiso2(GlobalCas.addquotes(code));
    }
}
