package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalStock {

    public static List<String> itemnames = new ArrayList<String>();
    public static List<String[]> itmesvalues = new ArrayList<String[]>();
    public static Map<String, String[]> fininfo = new HashMap<String, String[]>();

    public static String composeNumChar2(int num) {

        if (num < 10)
            return "00" + Integer.toString(num);
        else if (num < 100)
            return "0" + Integer.toString(num);
        else
            return Integer.toString(num);
    }

    public static String composeNumChar3(int stock_num) {

        if (stock_num < 10)
            return "000" + Integer.toString(stock_num);
        else if (stock_num < 100)
            return "00" + Integer.toString(stock_num);
        else if (stock_num < 1000)
            return "0" + Integer.toString(stock_num);
        else
            return Integer.toString(stock_num);
    }

    public static String composeNumChar5(int stock_num) {

        if (stock_num < 10)
            return "00000" + Integer.toString(stock_num);
        else if (stock_num < 100)
            return "0000" + Integer.toString(stock_num);
        else if (stock_num < 1000)
            return "000" + Integer.toString(stock_num);
        else if (stock_num < 10000)
            return "00" + Integer.toString(stock_num);
        else
            return Integer.toString(stock_num);
    }

    public static String AssortCode(String stockcode) {

        String xx = "sh";
        //System.out.println(stockcode);
        switch (stockcode.substring(0, 2)) {
            case "60": {
                xx = "sh" + stockcode;
                break;
            }
            case "00": {
                xx = "sz" + stockcode;
                break;
            }
            case "30": {
                xx = "sz" + stockcode;
                break;
            }
            case "68": {
                xx = "sh" + stockcode;
                break;
            }
            case "50": {
                xx = "sh" + stockcode;
                break;
            }
            case "51": {
                xx = "sh" + stockcode;
                break;
            }
            case "15": {
                xx = "sz" + stockcode;
                break;
            }
            case "39": {
                xx = "sz" + stockcode;
                break;
            }
            case "16": {
                xx = "jj" + stockcode;
                break;
            }
        }
        return xx;
    }
}
