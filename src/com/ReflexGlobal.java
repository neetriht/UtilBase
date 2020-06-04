package com;

import java.lang.reflect.Field;

/**
 * describe:
 *
 * @author scott dai
 * @date 2019/01/07
 */
public class ReflexGlobal {


    public void ScanGlobalCounter(Class sc) {
        // TODO Auto-generated method stub
        //GlobalTimer.compareTime("15:00:00","15:00:01");

        Field[] f = sc.getDeclaredFields();
        //GlobalConnectStatus.class = sc
        String[][] c = new String[f.length][2];

        for (Field a : f) {
            // if(a.getgetType().equals())
            System.out.println(a.getName() + "  " + getvalue(a));
        }
    }

    public static String getvalue(Field field) {
        boolean v = false;
        // &&
        //System.out.println(field.getType().toString());
        //field.getType().toString()
        if (field.getType().toString().endsWith("boolean")) {
            try {
                v = (boolean) field.get(GlobalConnectStatus.class);
                // System.out.println(field.getName() + " , " + v);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return Boolean.toString(v);
    }
}
