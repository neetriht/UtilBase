package com.files;

import com.GlobalConnectStatus;
import com.GlobalTimer;
import com.util.io.FileDel;
import com.util.io.FileOut;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * describe:
 *
 * @author scott dai
 * @date 2019/01/08
 */
public class FileRecorder {

    FileOut fout = new FileOut();
    FileDel fdel = new FileDel();
    DataOutputStream doc;
    String outfile = "/home/hadoop/scott/9_batch/report/dailyreport.txt";
    String statusfile = "/home/hadoop/scott/9_batch/report/statusreport.txt";
    String reorgfile = "/home/hadoop/scott/9_batch/report/reorgreport.txt";

    public void outputInfo(List<String[]> datasrc) throws IOException {
        //get records src
        List<String[]> insertQueue = datasrc;

        //create the file
        fdel.checkdeleteFile(outfile);
        doc = fout.getOutFile(outfile);

        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);

        //records information
        System.out.println("&&&&&&&&&&& New inserted!!!&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        fout.writeToFile(doc, GlobalTimer.getTimestamp());
        for (String[] a : insertQueue) {
            System.out.println(a[2] + ": " + a[1] + ":" + a[40]);
            fout.writeToFile(doc, new String((a[2] + ": " + a[1] + ":" + a[40]).getBytes("UTF-8")));
            // new String ((a[2] + ": " + a[1] + ":" + a[40]).getBytes("iso-8859-1"),"utf-8”)
        }
    }

    public void outputreorgInfo(List<String> info) throws IOException {
        fdel.checkdeleteFile(reorgfile);
        doc = fout.getOutFile(reorgfile);

        System.out.println("&&&&&&&&&&& New inserted!!!&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        fout.writeToFile(doc, GlobalTimer.getTimestamp());
        while (info.size() > 0) {
            String i = info.get(0);
            System.out.println(i);
            fout.writeToFile(doc, i);
        }

    }


    public void outputStatus() throws IOException {


        fdel.checkdeleteFile(statusfile);
        doc = fout.getOutFile(statusfile);
        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);
        System.out.println("&&&&&&&&&&& show status!!!&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        fout.writeToFile(doc, GlobalTimer.getTimestamp());

        Field[] f = GlobalConnectStatus.class.getDeclaredFields();  //getMethods();

        for (Field a : f) {
            if (a.getType().toString().endsWith("boolean"))
                fout.writeToFile(doc, "\t" + a.getName() + "  " + getvalue(a));
        }
        /*fout.writeToFile(doc, "Got DB2 : " + GlobalConnectStatus.DB2);
        fout.writeToFile(doc, "Got Redis: " + GlobalConnectStatus.REDIS_CLUSTER);
        fout.writeToFile(doc, "Got HBase: " + GlobalConnectStatus.HBASE);
        fout.writeToFile(doc, "Got Mongodb: " + GlobalConnectStatus.MongoDB_CLUSTER);*/

    }


    public static String getvalue(Field field) {
        boolean v = false;
        // &&
        //System.out.println(field.getType().toString());
        //field.getType().toString()
        //if (field.getType().toString().endsWith("boolean")) {
        try {
            v = (boolean) field.get(GlobalConnectStatus.class);
            // System.out.println(field.getName() + " , " + v);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Boolean.toString(v);
    }
}
