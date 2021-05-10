package com.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: neetriht scott
 * @Date: 3/5/2021 - 12:38 PM
 * @Software: IntelliJ IDEA
 * @Description:
 */

public class GetParms {


    static String config_file = "RAS.properties";
    private static String NumofGetStockThread, NumofSaveStockThread;
    private static int loadtimes, shownews, yuncailoadtimes;
    static Properties prop;


    public GetParms() {

    }

    public static void ini() {
        try {
            prop = PropertyUtilBase.loadPropertyFile(config_file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getNumber() {
        ini();
        NumofGetStockThread = (String) prop.get("NumofGetStockThread");
        NumofSaveStockThread = (String) prop.get("NumofSaveStockThread");
        System.out.println("Number of GetStockThread : " + NumofGetStockThread);
        System.out.println("Number of SaveStockThread :" + NumofSaveStockThread);
        return new String[]{NumofGetStockThread, NumofSaveStockThread};
    }

    public static int getNewsLoadTimes() {
        ini();
        loadtimes = Integer.parseInt(((String) prop.get("loadtimes")).trim());
        return loadtimes;
    }

    public static int getNewsyuncaiLoadTimes() {
        ini();
        loadtimes = Integer.parseInt(((String) prop.get("yuncailoadtimes")).trim());
        return loadtimes;
    }


    public static int getshownews() {
        ini();
        shownews = Integer.parseInt(((String) prop.get("shownews")).trim());
        return shownews;
    }
}
