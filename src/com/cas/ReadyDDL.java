package com.cas;

import com.GlobalTimer;
import com.util.io.FileDel;
import com.util.io.FileOut;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: neetrihtk
 * Date: 2018-09-29
 * Time: 17:41
 * <p>
 * Description:
 */
public class ReadyDDL {

    UATDB2Oper db2d = new UATDB2Oper("CRDB");
    UATDB2Oper newdb;

    String path = "E:\\1_CAS\\DDL\\";
    DataOutputStream doc;



    public void checkTable(UATDB2Oper ndb, String table_name, String tabschema) {
        //String db2dsql = "SELECT NAME FROM SYSIBM.SYSTABLES  WHERE NAME = '" + table_name + "';";
        String db2dsql = "SELECT TABNAME FROM SYSCAT.TABLES  WHERE TABNAME = '" + table_name + "'";
        ResultSet rs = ndb.executeQuery(db2dsql);
        try {
            if (rs.next()) {
                String tname = rs.getString(1);
                System.out.println("Find out this table: " + tname);
                getColumns(ndb, tname, tabschema);
            } else {
                System.out.println("No This table!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createTable(String DDL) {
        String line1 = "IBMSNAP_COMMITSEQ    VARCHAR(16) FOR BIT DATA NOT NULL, \n\r";
        String line2 = "IBMSNAP_INTENTSEQ    VARCHAR(16) FOR BIT DATA NOT NULL, \n\r";
        String line3 = "IBMSNAP_OPERATION    CHAR(1) FOR MIXED DATA NOT NULL,   \n\r";
        DDL += "\t\t  " + line1;
        DDL += "\t\t  " + line2;
        DDL += "\t\t  " + line3;
        return DDL;
    }

    public void getColumns(UATDB2Oper ndb, String table_name, String tabschema) {
        String DDL = "CREATE TABLE " + tabschema + "." + table_name + " ( \n\r";
        DDL = createTable(DDL);
        //String db2dsql = "SELECT NAME,COLTYPE,LENGTH, SCALE, NULLS, COLNO FROM  SYSIBM.SYSCOLUMNS  WHERE TBNAME = '" + table_name + "' AND TBCREATOR = 'DBUCAS1' ORDER BY COLNO;";
        String db2dsql = "SELECT COLNAME,TYPENAME,LENGTH, SCALE, TYPESTRINGUNITS, NULLS, COLNO FROM  SYSCAT.COLUMNS  WHERE TABNAME = '" + table_name + "'  ORDER BY COLNO";
        //System.out.println(db2dsql);
        ResultSet rs = ndb.executeQuery(db2dsql);
        try {
            while (rs.next()) {
                String col_name = rs.getString(1);
                String col_type = rs.getString(2).trim();
                String col_length = rs.getString(3);
                String col_dem = rs.getString(4);
                String col_unit = rs.getString(5);
                String col_null = rs.getString(6);

                String line = col_name + " " + col_type;
                DDL += "\t\t  ";
                if (col_type.equals("DECIMAL"))
                    line += "(" + col_length + "," + col_dem + " " + col_unit + ")";
                else
                    line += "(" + col_length + " " + col_unit + ")";

                String newline = "X" + line;

                if (col_null.equals("N")) {
                    line += " NOT NULL ";
                    newline += " WITH DEFAULT NULL ";
                }

                line += " ,\n\r";
                newline += " ,\n\r";

                //System.out.println(newline);

                DDL += line;
                //DDL += newline;

            }
            DDL = DDL.substring(0, DDL.length() - 2);
            DDL += " \n\r) IN " + tabschema + " ;";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(DDL);

        String DDL_index = createIndex(table_name, tabschema);

        System.out.println(DDL_index);

        String ALL_DDL = DDL + "\n\r" + DDL_index;
        OutputFiles(ALL_DDL, table_name);
    }

    public String settypelength(String col_type, String col_length, String col_dem) {
        String line = "";
        //col_type
        if (scale(col_type))
            line += "(" + col_length + "," + col_dem + ")";
        else if (nolength(col_type)) {
            //
        } else
            line += "(" + col_length + ")";

        String newline = "X" + line;
        return line;
    }

    private boolean nolength(String col_type) {
        boolean b = true;
        if (!col_type.contains("INT") & !col_type.contains("TIME") & !col_type.contains("DATE") & !col_type.contains("XML") & !col_type.contains("BLOB"))
            b = false;
        return b;
    }

    private boolean scale(String col_type) {
        boolean b = true;
        if (!col_type.contains("DEC"))
            b = false;
        return b;
    }

    public void readcontrol() {
        String db2dsql = "SELECT * FROM CRDB.CDAU_CONTROL ";
        ResultSet rs = db2d.executeQuery(db2dsql);
        try {
            while (rs.next()) {
                String SOURCE_DATABSE_NAME = rs.getString(1);
                String ipaddress = rs.getString(2);
                String dbport = rs.getString(3);
                String userid = rs.getString(4);
                String passwd = rs.getString(5);
                String tabschama = rs.getString(6);
                String tabname = rs.getString(7);
                String cdschema = rs.getString(8);
                String cdtabname = rs.getString(9);
                String cdtabsname = rs.getString(10);
                String cdindexname = rs.getString(11);
                String cdindexspace = rs.getString(12);
                String ddlfilename = rs.getString(13);
//                System.out.println(ipaddress
//                                + " " + dbport
////                        + " " + userid
////                        + " " + passwd
////                        + " " + tabschama
////                        + " " + tabname
////                        + " " + cdschema
////                        + " " + cdtabname
////                        + " " + cdtabsname
////                        + " " + cdindexname
////                        + " " + cdindexspace
////                        + " " + ddlfilename
//                );
                String url = "jdbc:db2://" + ipaddress + ":" + dbport + "/" + SOURCE_DATABSE_NAME;
                newdb = new UATDB2Oper(url, userid, passwd);

                checkTable(newdb, tabname, tabschama);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createIndex(String tablename, String tabschema) {
        String DDL_index = "CREATE UNIQUE INDEX " + tabschema + "." + tablename + "I ON " + tabschema + "." + tablename
                + "\n\r";

        String line1 = "( IBMSNAP_COMMITSEQ     ASC,  \n\r";
        String line2 = "  IBMSNAP_INTENTSEQ     ASC ) \n\r";

        DDL_index += "\t\t  " + line1;
        DDL_index += "\t\t  " + line2;

        DDL_index += "\t\t  IN  " + tabschema + ";";

        return DDL_index;
    }

    public void OutputFiles(String ddl, String tablename) {

        FileOut filewriter = new FileOut();
        String outputfile = path + tablename + "-DDL-" + GlobalTimer.getDSDate() + ".txt ";
        new FileDel().checkdeleteFile(outputfile);

        doc = filewriter.getOutFile(outputfile);

        try {
            filewriter.writeToFile(doc, ddl);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   /*
   BIGINT
   INTEGER
   SMALLINT

    TIME
    TIMESTMP
    TIMESTZ

    DATE
    BLOB

    DISTINCT
    --------------------
    DECIMAL

    ---------------
    CHAR
    CLOB
    FLOAT
    VARCHAR
    VARG
    LONGVAR
    GRAPHIC

    ROWID*/
}