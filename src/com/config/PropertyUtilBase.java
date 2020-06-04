package com.config;

import com.GlobalTimer;

import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-11-14
 * Time: 20:44
 * <p>
 * Description:
 */
public class PropertyUtilBase {

    public static Properties loadPropertyFile(String fullFile) throws IOException {
        Properties p = new Properties();
        if (fullFile == null || "".equals(fullFile)) {
            System.out.println("#################################");
            System.out.println("Can not load the propertie file");
            System.out.println("Stop this process " + GlobalTimer.getTimestamp());
            System.out.println("#################################");
            //log.error("File path is null.");
            System.exit(1);
        } else { //加载属性文
            System.out.println(fullFile);
//            File f = new File(fullFile);
//            String path = f.getAbsolutePath();
//            System.out.println(path);
            InputStream inStream;
            if (fullFile.split("/").length == 0) {
                inStream = PropertyUtilBase.class.getClassLoader().getResourceAsStream(fullFile);
            } else {
                System.out.println("file out ##############");
                File mvc = new File(fullFile);
                inStream = new FileInputStream(mvc);
            }
            //InputStream inStream = new InputStream(mvc);
            //getResourceAsStream(path);}
            if (inStream == null)
                System.out.printf("Null");
            else
                p.load(inStream);

        }
        return p;
    }
}