package com.lottery;

import com.stock.dbpool.DataSourceOper;
import com.util.io.FileIn;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Auther: neetriht scott
 * @Date: 4/29/2021 - 1:21 PM
 * @Software: IntelliJ IDEA
 * @Descrpition:
 */

public abstract class ImportBase {

    int sql_cnt = 0;
    Boolean contin = true;
    Date dm;
    DataSourceOper sconn = new DataSourceOper();

    SimpleDateFormat ddd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    abstract void SetValue2(String abc);

    public Date getMaxDate(String table) throws ParseException {
        Date d = null;
        String sql = "select max(open_dt) from "+table;
        String dt = sconn.executeGetString(sql, 1);
        if (dt != null) {
            d = ddd.parse(dt);
            System.out.println(d);
        }
        return d;
    }

    class GETDATA implements Runnable {
        String value = null;
        String url;

        public GETDATA(String _url) {
            url = _url;
        }

        @Override
        public void run() {
            System.out.println(dm);
            try {
                URL u = new URL(url);
                HttpURLConnection http = (HttpURLConnection) u.openConnection();
                http.setConnectTimeout(3000);
                http.setRequestMethod("GET");

                int code = http.getResponseCode();
                System.out.println("connnect network: " + code);
                if (code == 200) {
                    System.out.println("try to start thread to read data!!!");
                    BufferedReader br = new FileIn().getBufferedReaderFromURL(u);
                    System.out.println("start thread!!!");
                    // System.out.println("start thread!!!");
                    while (((value = br.readLine()) != null) & contin) {
                        if (value.trim().length() > 0) {
                            //SetValue(value);
                            SetValue2(value);
                        }
                        else{
                            System.out.println("No more !!!");
                        }
                    }
                    br.close();
                    // System.out.println("insert into db");
                    if (sql_cnt > 0) {
                        System.out.println("new record count: " + sql_cnt);
                        // new BackUp();
                        // dor = sconn.executeBatch();
                    }
                    // sconn.doclose();

                    // System.out.println(dor.length);
                    System.out.println("insert into db done!!!");
                    System.out.println("END thread!!!");
                    //handler.sendMessage(new Message().setData(b));
                    /*
                     * while (stop && (readfile || !download_names.isEmpty())) { while
                     * (stop && !download_names.isEmpty()) { String dataset = (String)
                     * download_names.remove(0); stop = stop1 &&
                     * coper.DownLoad(cb.getOut(), cb.getIn(), _path + "\\" + dataset +
                     * ".txt", dataset, pp, _cbean); } }
                     * _cbean.getControlButtonPanel().stopEvent(); lg.LogOff(cb);
                     */
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
