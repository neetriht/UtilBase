package com;

public class GlobalStock {

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


}
