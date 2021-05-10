package com;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-04-22
 * Time: 22:10
 * <p>
 * Description:
 */
public class TestGlobal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //GlobalTimer.compareTime("15:00:00","15:00:01");
        //System.out.println(GlobalTimer.getTime());
//        GlobalInfo g =new  GlobalInfo();
//        System.out.println(g.getID());
//        System.out.println(g.getPASSWORD());

//        String a = GlobalCas.addquotes("672832616");
//        System.out.println(a);
//        List<String> o =  GlobalFileIO.read("C:\\scott\\list.txt");
//        while(o.size()>0)
//            System.out.println(o.remove(0));
       // System.out.println(GlobalTimer.getDayTime());
     //  boolean a = GlobalTimer.compareCountDateTime("2019-12-09-15:55:01","2019-12-09-15:58:55",3);
     //   System.out.println(a);

      int ss =   Integer.parseInt(GlobalTimer.usedtime("2021-03-20-15:55:02", "2021-03-26-15:55:01", 1));
        System.out.println(ss);
    }
}
