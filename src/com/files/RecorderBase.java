package com.files;

import com.GlobalConnectStatus;
import com.GlobalTimer;
import com.util.io.FileDel;
import com.util.io.FileOut;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * describe:
 *
 * @author scott dai
 * @date 2019/01/08
 */
public  class RecorderBase {

    FileOut fout = new FileOut();
    FileDel fdel = new FileDel();
    DataOutputStream doc;


    public void outputStatus(String filename, Class sc) throws IOException {

        //create the file
        fdel.checkdeleteFile(filename);
        doc = fout.getOutFile(filename);


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
