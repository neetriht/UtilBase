package com;

import java.util.LinkedList;
import java.util.List;

public class GlobalCas {

        //
    public static String addquotes(String cntrylist) {

        List<String> a = split(cntrylist);
        String s = "";
        while (a.size() > 0)
            s = s + ",'" + a.remove(0) + "'";

        return s.substring(1, s.length());
    }


    public static String onebyone(String[] cntrylist) {
        String c = "";
        for (String a : cntrylist) {
            c = c + ",'" + a + "'";
        }
        c = c.substring(1, c.length());
        return c;
    }

    public static List<String> split(String cntrylist) {
        if (cntrylist.length() % 3 != 0)
            return null;
        else {
            List<String> l = new LinkedList<String>();
            while (cntrylist.length() > 2) {
                l.add(cntrylist.substring(0, 3));
                cntrylist = cntrylist.substring(3, cntrylist.length());
                //System.out.println(cntrylist);
            }
            return l;
        }
    }

}
