package com;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * describe:
 *
 * @author scott dai
 * @date 2018/12/27
 */
public class TestFlactGlobal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //GlobalTimer.compareTime("15:00:00","15:00:01");

        new ReflexGlobal().ScanGlobalCounter(GlobalConnectStatus.class);
        new ReflexGlobal().ScanGlobalCounter(GlobalCounter.class);
//        Field[] f = GlobalConnectStatus.class.getDeclaredFields();
//        for (Field a : f) {
//            // if(a.getgetType().equals())
//            System.out.println(a.getName() + "  " + getvalue(a));
//        }
    }

//    public static String getvalue(Field field) {
//        boolean v = false;
//        // &&
//        //System.out.println(field.getType().toString());
//        //field.getType().toString()
//        if (field.getType().toString().endsWith("boolean")) {
//            try {
//                v = (boolean) field.get(GlobalConnectStatus.class);
//               // System.out.println(field.getName() + " , " + v);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        return Boolean.toString(v);
//    }
}
