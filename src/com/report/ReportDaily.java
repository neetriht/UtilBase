package com.report;

import com.GlobalConnectStatus;
import com.GlobalTimer;

import com.util.io.FileDel;
import com.util.io.FileOut;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-11-10
 * Time: 22:28
 * <p>
 * Description:
 */
public class ReportDaily {
    FileOut fout = new FileOut();
    FileDel fdel = new FileDel();
    DataOutputStream doc;
    String outfile = "/home/hadoop/scott/9_batch/report/dailyreport.txt";


    public void outputInfo(List<String[]> content) throws IOException {
        fdel.checkdeleteFile(outfile);
        doc = fout.getOutFile(outfile);
        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);
        List<String[]> insertQueue = content;
        System.out.println("&&&&&&&&&&& New inserted!!!&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        fout.writeToFile(doc, GlobalTimer.getTimestamp());
        for (String[] a : insertQueue) {
            System.out.println(a[2] + ": " + a[1] + ":" + a[40]);
            fout.writeToFile(doc, new String((a[2] + ": " + a[1] + ":" + a[40]).getBytes("UTF-8")));
            // new String ((a[2] + ": " + a[1] + ":" + a[40]).getBytes("iso-8859-1"),"utf-8”)
        }
    }

    public void outputStatus(String outputfile) throws IOException {
        fdel.checkdeleteFile(outputfile);
        doc = fout.getOutFile(outputfile);

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


    public void outputStatus(String outputfile, List<String> content) throws IOException {
        fdel.checkdeleteFile(outputfile);
        doc = fout.getOutFile(outputfile);

        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);
        String comment = "&&&&&&&&&&& show reorg log!!!&&&&&&&&&&&&&&&&&&&&&&&&&&&&";
        System.out.println(comment);

        fout.writeToFile(doc, GlobalTimer.getTimestamp());
        fout.writeToFile(doc, comment);

        if (content == null) {
            fout.writeToFile(doc, "\t There is no log existing!!!");
        } else {
            for (String a : content) {
                fout.writeToFile(doc, "\t" + a);
            }
        }

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
