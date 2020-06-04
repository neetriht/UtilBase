package com;

import com.config.PropertyUtilBase;

import java.io.IOException;
import java.util.Properties;

public class GlobalInfo {

    static Properties prop;
    public static String host; // = DB2U_url;
    public static String user; //  = "C207745";
    public static String password; // = "nhy65tgb";

    private static final String pro = "MVC.properties";

    public GlobalInfo() {
        getProperty();
    }

    public static Properties getProperty() {
        try {

            prop = PropertyUtilBase.loadPropertyFile(pro);
            password = ((String) prop.get("password")).trim();
            user = ((String) prop.get("user")).trim();
            //prop.load(CASDB2.class.getClassLoader().getResourceAsStream("com/config/DB2local.properties"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        printInfo();
        return prop;
    }

    public String getID() {
        return user;
    }

    public String getPASSWORD() {
        return password;
    }

    public static void printInfo() {
        //getProperty();
        System.out.println("Read user/password from " + pro);
        System.out.println("ID: " + user);
        System.out.println("PASSWORD: " + password);
    }


}
