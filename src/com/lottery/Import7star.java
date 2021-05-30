package com.lottery;

import com.stock.dbpool.DataSourceOper;
import com.util.io.FileIn;
import com.util.io.FileOut;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * @Auther: neetriht scott
 * @Date: 5/6/2021 - 9:46 AM
 * @Software: IntelliJ IDEA
 * @Descrpition:
 */

public class Import7star  extends ImportBase{
    DataSourceOper sconn = new DataSourceOper();

    final static String URL_PATH = "http://www.17500.cn//getData//7xc.TXT";

    SimpleDateFormat ddd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

//    String output = "C:\\scott\\lotto_sql.txt";
//    String outputcsv = "C:\\scott\\lotto.csv";
    DataOutputStream dos, dos2;
    FileOut fout = new FileOut();

    public Import7star() {
//        dos = fout.getOutFile(output);
//        dos2 = fout.getOutFile(outputcsv);
    }

    public void DeleteAll() {
        try {
            String sql = "delete from SUPER_LOTTO";
            System.out.println(sql);
            // sconn.executeDelete(sql);
            // sconn.doclose();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void ReadValues() {
        try {
            System.out.println("Check db");
            dm = getMaxDate("seven_star");
            System.out.println("verify db");

            if (dm == null)
                dm = ddd.parse("2000-01-01");

            // BufferedReader br = new
            // FileIn(".//double-ball.txt").getBufferedReader();
            // BufferedReader br = new
            // FileIn().getBufferedReader(".//B.csv");
            System.out.println("Read data from url");
            System.out.println("Read ball data!!!");

            new Thread(new Import7star.GETDATA(URL_PATH)).start();

            System.out.println("Over");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void SetValue2(String v) {
        //System.out.println(v);
        // String value = "'" + v.substring(0, 39).replace(" ", "','") + "'";
        String value = v.replace(" ", ",");
        //value = value.substring(0, value.length()-1);
        // val ="'"+ value.replace(",", "','")+"'";
        String dt = value.substring(6, 16);
        //System.out.println(dt);
        //System.out.println(value);
        try {
            Date dv = ddd.parse(dt);
//            System.out.println(dt);
//            System.out.println(dm);
            if (dv.after(dm)) {
                //outputCSV(value);
                //System.out.println(value);
                InsertValue(value);
            } else {
                //System.out.println("not new");
                //Toast.makeText(activity.getApplicationContext(), "No new Data", 2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertValue(String val) {

        String sql = null;
        try {
            sql = "insert into SEVEN_STAR values(" + readyvalue(val) + ");";
            // sql = "insert into DBTLRBR.TABLE_1 values("+val+")";
            System.out.println(sql);
            sconn.executeSQL(sql);
            //fout.writeToFile(dos, sql);
            // sconn.addBatch(sql);
            sql_cnt += 1;
            // sconn.executeInsert(sql);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public String readyvalue(String val) {
        String[] b = val.split(",");
       // System.out.println(b[9]);
        if (b[9].trim().length() < 4) {
            System.out.println("have not update bonus");
            System.exit(0);
        }
        //  System.out.println(b.length);
        String value = "";
        for (String a : b) {
            value += "'" + a + "',";
        }
        value= value.substring(0, value.length()-1);
        value = value.replace("'01'", "'1'");
        value = value.replace("'02'", "'2'");
        value = value.replace("'03'", "'3'");
        value = value.replace("'04'", "'4'");
        value = value.replace("'05'", "'5'");
        value = value.replace("'06'", "'6'");
        value = value.replace("'07'", "'7'");
        value = value.replace("'08'", "'8'");
        value = value.replace("'09'", "'9'");
        // System.out.printf(value);
        return value;
    }


}
