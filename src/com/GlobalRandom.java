package com;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-06-11
 * Time: 13:31
 * <p>
 * Description:
 */
public class GlobalRandom {



    public static String getRandom(String[] str) {
        Random random = new Random();
        int number = random.nextInt(str.length);
        //将产生的数字通过length次承载到sb中
        return str[number];
    }

    public static String fillRandom(String val, int length, int type) {
        return val + getRandomString(length, type);
    }

    public static String fillRandomLen(String val, int length, int type) {
        int used = val.length();
        return val + getRandomString(length - used, type);
    }

    public static double getRandomfloat(double[] pool) {
        double ram = 0;
        Random random = new Random();
        int index = random.nextInt(pool.length - 1);
        return pool[index];
    }

    public static String getRandomString(String[] pool) {
        String ram = "";
        Random random = new Random();
        int index = random.nextInt(pool.length - 1);
        return pool[index];
    }

    public static String getRandomString(int length, int type) {
        //1,3, 9
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        String strALFuppter = "QWERTYUIOPASDFGHJKLZXCVBNM";

        String strALF = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM";
        String num = "1234567890";
        String numno0 = "123456789";
        String num9 = "9";
        String strALFNUM = strALFuppter + num;
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次

        String pool = num;

        switch (type) {
            case 1:
                pool = strALFuppter;
                break;
            case 2:
                pool = num;
                break;
            case 3:
                pool = numno0;
                break;
            case 4:
                pool = strALFNUM;
                break;
            case 5:
                pool = str;
                break;
            case 9:
                pool = num9;
                break;
        }

        for (int i = 0; i < length; ++i) {
            //产生0-61的数字
            int number = random.nextInt(pool.length());
            //将产生的数字通过length次承载到sb中
            sb.append(pool.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

}
