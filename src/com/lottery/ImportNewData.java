package com.lottery;


import com.stock.dbpool.DataSourceOper;
import com.util.io.FileIn;
import com.util.io.FileOut;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ImportNewData extends ImportBase {

    final static String URL_PATH = "http://www.17500.cn//getData//ssq.TXT";

    String output = "C:\\scott\\redblue_sql.txt";
    String outputcsv = "C:\\scott\\redblue.csv";
    DataOutputStream dos, dos2;
    FileOut fout = new FileOut();

    public ImportNewData() {
        dos = fout.getOutFile(output);
        dos2 = fout.getOutFile(outputcsv);
    }

    public void DeleteAll() {
        try {
            String sql = "delete from red_blue";
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
            dm = getMaxDate("red_blue");
            System.out.println("verify db");

            if (dm == null)
                dm = ddd.parse("2000-01-01");

            // BufferedReader br = new
            // FileIn(".//double-ball.txt").getBufferedReader();
            // BufferedReader br = new
            // FileIn().getBufferedReader(".//B.csv");
            System.out.println("Read data from url");
            System.out.println("Read ball data!!!");

            new Thread(new GETDATA(URL_PATH)).start();

            System.out.println("Over");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void SetValue(String v) {

        // String value = "'" + v.substring(0, 39).replace(" ", "','") + "'";
        String value = "'" + v.replace(" ", "','") + "'";
        value = value.replace("'01'", "'1'");
        value = value.replace("'02'", "'2'");
        value = value.replace("'03'", "'3'");
        value = value.replace("'04'", "'4'");
        value = value.replace("'05'", "'5'");
        value = value.replace("'06'", "'6'");
        value = value.replace("'07'", "'7'");
        value = value.replace("'08'", "'8'");
        value = value.replace("'09'", "'9'");
        System.out.println(value);
        // val ="'"+ value.replace(",", "','")+"'";
        String dt = value.substring(11, 21);

        // System.out.println(dt);
        try {
            Date dv = ddd.parse(dt);
            if (dv.after(dm)) {
                System.out.println(dv);
                InsertValue(value);
            } else {
                //System.out.println("not new");
                //Toast.makeText(activity.getApplicationContext(), "No new Data", 2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SetValue2(String v) {

        // String value = "'" + v.substring(0, 39).replace(" ", "','") + "'";
        String value = v.replace(" ", ",");
        // val ="'"+ value.replace(",", "','")+"'";
        String dt = value.substring(8, 18);
        //System.out.println(value);
        try {
            Date dv = ddd.parse(dt);
            //System.out.println(dt);
            if (dv.after(dm)) {
                //outputCSV(value);
                InsertValue(value);
            } else {
                // System.out.println("not new");
                //Toast.makeText(activity.getApplicationContext(), "No new Data", 2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void outputCSV(String val) {

        //System.out.println(val);
        try {
            if (dos2 != null)
                //   dos2 = fout.getOutFile(outputcsv);
                fout.writeToFile(dos2, val);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void InsertValue(String val) {

        String sql = null;
        try {
            sql = "insert into red_blue values(" + readyvalue(val) + ");";
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

        if (b[16].trim().length() < 4) {
            System.out.println("have not update bonus");
            System.exit(0);
        }

        String value = "";
        for (String a : b) {
            value += "'" + a + "',";
        }
        value = value.substring(0, value.length() - 1);
        value = value.replace("'01'", "'1'");
        value = value.replace("'02'", "'2'");
        value = value.replace("'03'", "'3'");
        value = value.replace("'04'", "'4'");
        value = value.replace("'05'", "'5'");
        value = value.replace("'06'", "'6'");
        value = value.replace("'07'", "'7'");
        value = value.replace("'08'", "'8'");
        value = value.replace("'09'", "'9'");
        //System.out.println(value);
        return value;
    }

    public void setstop() {
        contin = false;
    }

//    private Date getMaxDate() {
//        try {
//            String sql = "select max(open_dt) from redblue";
//            return ddd.parse(rboper.getMaxDate(sql));
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return null;
//        }
//
//    }

//    class GETDATA implements Runnable {
//        String value = null;
//        String url;
//
//        public GETDATA(String _url) {
//            url = _url;
//        }
//
//        @Override
//        public void run() {
//            System.out.println(dm);
//            try {
//                URL u = new URL(url);
//                HttpURLConnection http = (HttpURLConnection) u.openConnection();
//                http.setConnectTimeout(3000);
//                http.setRequestMethod("GET");
//
//                int code = http.getResponseCode();
//                System.out.println("connnect network: " + code);
//                if (code == 200) {
//                    System.out.println("try to start thread to read data!!!");
//                    BufferedReader br = new FileIn().getBufferedReaderFromURL(u);
//                    System.out.println("start thread!!!");
//                    // System.out.println("start thread!!!");
//                    while (((value = br.readLine()) != null) & contin) {
//                        if (value.trim().length() > 0) {
//                            // SetValue(value);
//                            SetValue2(value);
//                        }
//                    }
//                    br.close();
//                    // System.out.println("insert into db");
//                    if (sql_cnt > 0) {
//                        System.out.println("new record count: " + sql_cnt);
//                        // new BackUp();
//                        // dor = sconn.executeBatch();
//                    }
//                    // sconn.doclose();
//
//                    // System.out.println(dor.length);
//                    System.out.println("insert into db done!!!");
//                    System.out.println("END thread!!!");
//                    //handler.sendMessage(new Message().setData(b));
//                    /*
//                     * while (stop && (readfile || !download_names.isEmpty())) { while
//                     * (stop && !download_names.isEmpty()) { String dataset = (String)
//                     * download_names.remove(0); stop = stop1 &&
//                     * coper.DownLoad(cb.getOut(), cb.getIn(), _path + "\\" + dataset +
//                     * ".txt", dataset, pp, _cbean); } }
//                     * _cbean.getControlButtonPanel().stopEvent(); lg.LogOff(cb);
//                     */
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
