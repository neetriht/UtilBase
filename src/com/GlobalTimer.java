package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by neetriht on 2017-06-08.
 */
public class GlobalTimer {
    public final static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat dateformatshort = new SimpleDateFormat("yyyyMMdd");
    public final static SimpleDateFormat datetimeformatshort = new SimpleDateFormat("yyyyMMddHHmmss");
    public final static SimpleDateFormat dateformatds = new SimpleDateFormat("yyMMdd_HHmmss");
    public final static SimpleDateFormat datetimeformat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
    public final static SimpleDateFormat datetimeformatjava = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
    public final static SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
    public final static SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");

    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        //DateUtils du =
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String strDate = datetimeformat.format(date);   //2016-12-16 11:53:54
        int tempDate = Integer.parseInt(strDate.substring(11, 13) + strDate.substring(14, 16) + strDate.substring(17, 19));
        int tempDateBegin = Integer.parseInt(strDateBegin.substring(0, 2) + strDateBegin.substring(3, 5) + strDateBegin.substring(6, 8));
        int tempDateEnd = Integer.parseInt(strDateEnd.substring(0, 2) + strDateEnd.substring(3, 5) + strDateEnd.substring(6, 8));

        if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotWeekend() {
        Calendar cal = Calendar.getInstance();
        boolean d = true;
        // System.out.println("Day: " + cal.DAY_OF_WEEK);
        // System.out.println("Hour: " + cal.get(cal.HOUR_OF_DAY));
        // if (a.getHours() > 0 && a.getHours() < 9)
//        if (cal.get(cal.HOUR_OF_DAY) > 0 && cal.get(cal.HOUR_OF_DAY) < 9) {
//            System.out.println("Hour: " + cal.get(cal.HOUR_OF_DAY));
//            d = false;
//        }
        // else if (a.getDay() == 7 | a.getDay() == 1)
        //else
        if (cal.get(cal.DAY_OF_WEEK) == 7 | cal.get(cal.DAY_OF_WEEK) == 1) {
            d = false;
        }
        System.out.println("Day: " + cal.get(cal.DAY_OF_WEEK));
        return d;

    }

//    public static boolean isInTime(Date date, String strTimeBegin, String strTimeEnd) {
//
//        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//        String strDate = datetimeformat.format(date);   //2016-12-16 11:53:54
//        int  tempDate=Integer.parseInt(strDate.substring(11, 13)+strDate.substring(14, 16)+strDate.substring(17, 19));
//        int  tempDateBegin=Integer.parseInt(strDateBegin.substring(0, 2)+strDateBegin.substring(3, 5)+strDateBegin.substring(6, 8));
//        int  tempDateEnd=Integer.parseInt(strDateEnd.substring(0, 2)+strDateEnd.substring(3, 5)+strDateEnd.substring(6, 8));
//
//        if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static boolean isInTime(Date schedule_Date) {
//        if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
//            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
//        }
//        if (curTime == null || !curTime.contains(":")) {
//            throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
//        }

        Date startdate = MoveTime(schedule_Date, 0, 1);
        Date enddate = MoveTime(schedule_Date, 3, 1);   //* 3 minites can run
        Date current_time = new Date();
        if (current_time.getTime() >= startdate.getTime() && enddate.getTime() > current_time.getTime()) {
            System.out.println("$^0^$ Ture: " + current_time + " " + schedule_Date + " : " + startdate + " :   " + enddate);
            return true;
        } else
            System.out.println("!^0^! False: " + current_time + " " + schedule_Date + " : " + startdate + " :   " + enddate);
        return false;
    }


    public void funclist() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dateNow);
//		cl.add(Calendar.DAY_OF_YEAR, -1);	//一天
//		cl.add(Calendar.WEEK_OF_YEAR, -1);	//一周
//		cl.add(Calendar.MONTH, -1);			//一个月
        cl.add(Calendar.MINUTE, 3);
        Date dateFrom = cl.getTime();
        System.out.println(sdf.format(dateFrom) + "到" + sdf.format(dateNow));
    }

    public static Date MoveTime(Date olddate, int change, int type) {
        //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(olddate);
        if (type == 1) {
            cl.add(Calendar.MINUTE, change);
        } else if (type == 2) {
            cl.add(Calendar.DAY_OF_YEAR, change);    //一天
        } else if (type == 3) {
            cl.add(Calendar.WEEK_OF_YEAR, change);    //一周
        } else if (type == 4) {
            cl.add(Calendar.MONTH, change);            //一个月
        } else System.out.println("wrong type");
//
//
//

        Date newdate = cl.getTime();
        // System.out.println("Change : " + newdate);
        return newdate;
    }

    public static String MoveTimeofDay(int change) {
        //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        cl.add(Calendar.DAY_OF_YEAR, change);    //一天
//		cl.add(Calendar.WEEK_OF_YEAR, -1);	//一周
//		cl.add(Calendar.MONTH, -1);			//一个月
//        cl.add(Calendar.MINUTE, change);
        Date newdate = cl.getTime();
        // System.out.println("Change : " + newdate);
        return getToday(newdate);
    }


    public static String getToday() {
        // yyyy-MM-dd
        //return "2019-03-08";
        return dateformat.format(new Date());
    }

    public static String getToday(Date d) {
        // yyyy-MM-dd
        //return "2019-03-08";
        return dateformat.format(d);
    }

    public static String getDayTime() {
        // yyyy-MM-dd-HH:mm:ss.SSS
        return datetimeformat.format(new Date());
    }

    public static String getTime() {
        // yyyy-MM-dd-HH:mm:ss.SSS
        return time.format(new Date());
    }

    public static String getShortToday() {
        // yyyy-MM-dd-HH:mm:ss.SSS
        return dateformatshort.format(new Date());
    }


    public static String getDSDate() {
        // yyyy-MM-dd-HH:mm:ss.SSS
        return dateformatds.format(new Date());
    }

    public static String getTimestamp() {
        // yyyy-MM-dd-HH:mm:ss.SSS
        return getTimestamp(0);
    }

    public static String getTimestamp(int x) {
        // yyyy-MM-dd-HH:mm:ss.SSS
        return datetimeformatjava.format(MoveTime(new Date(), x * 60, 1));
    }

    public static String formatDays(long second) {
        String useddays = "1";
        if (second != 0) {

            double diffDays = (double) second / (24 * 60 * 60 * 1000);
            //System.out.println(diffDays);
            useddays = String.valueOf((int) Math.ceil((double) Math.abs(diffDays)));
        }
        return useddays;

    }

    public static String formatSecond(long second) {
        String html = "0秒";
        if (second != 0) {
            // Double s = (Long) second;
            long s = second / 1000;
            long ss = second % 1000;
            String format;
            Object[] array;
            Integer hours = (int) (s / (60 * 60));
            Integer minutes = (int) (s / 60 - hours * 60);
            Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);
            if (hours > 0) {
                format = "%1$,d时%2$,d分%3$,d秒";
                array = new Object[]{hours, minutes, seconds};
            } else if (minutes > 0) {
                format = "%1$,d分%2$,d秒";
                array = new Object[]{minutes, seconds};
            } else {
                format = "%1$,d秒";
                array = new Object[]{seconds};
            }
            html = String.format(format, array) + ss + "毫秒";
        }

        return html;

    }

    public static boolean compareDate(String d1, String d2) {
        try {
            return compareDate(GlobalTimer.dateformat.parse(d1), GlobalTimer.dateformat.parse(d2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean compareDateTime(String d1, String d2) {
        try {
            Date dt1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(d1);//将字符串转换为date类型
            Date dt2 = new SimpleDateFormat("yyyyMMddHHmmss").parse(d2);

            return compareDate(dt1, dt2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean compareDateTime(String d1, String d2, int type) {
        System.out.println(d1);
        System.out.println(d2);
        try {
            Date dt1 = datetimeformat.parse(d1);//将字符串转换为date类型
            Date dt2 = datetimeformat.parse(d2);

            return compareDate(dt1, dt2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean compareCountDateTime(String d1, String d2, int count) {
        //count -> minites
        try {

            Date dt1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse(d1);//将字符串转换为date类型
            Date dt2 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse(d2);
            if (dt2.getTime() - dt1.getTime() > count * 60 * 1000)//比较时间大小,如果dt1大于dt2
            {
                System.out.println("#########  PASS TIME AND SHOULD STOP!!!!");
                System.out.println("#########  PASS TIME AND SHOULD STOP!!!!");
                System.out.println("#########  PASS TIME AND SHOULD STOP!!!!");
                System.out.println("#########  PASS TIME AND SHOULD STOP!!!!");
                System.out.println("#########  PASS TIME AND SHOULD STOP!!!!");
                return true;
                // System.out.println("yes");
            } else {
                // System.out.println("no");//运行输出no
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }


    public static boolean compareTime(String d1, String d2) {
        if (d1 == "" | d2 == "")
            return false;
        try {
            //System.out.println(d1 + " : " + d2);
            Date dt1 = new SimpleDateFormat("HH:mm:ss").parse(d1);//将字符串转换为date类型
            Date dt2 = new SimpleDateFormat("HH:mm:ss").parse(d2);
            if (dt1.getTime() > dt2.getTime())//比较时间大小,如果dt1大于dt2
            {
                // System.out.println("yes");
            } else {
                // System.out.println("no");//运行输出no
                return false;
            }

        } catch (ParseException e) {
            System.out.println("Happen error: " + d1 + " : " + d2);
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean compareDate(Date d1, Date d2) {
        //System.out.println(d1 + "  " + d2);
        if (d1.getTime() >= d2.getTime())
            return true;
        else return false;
    }

    public static String usedtime(String start, String stop, int type) {
        // type=1  caculate as days
        Calendar cd01 = Calendar.getInstance();
        Calendar cd02 = Calendar.getInstance();
        try {
            cd01.setTime(datetimeformat.parse(start));
            cd02.setTime(datetimeformat.parse(stop));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        long usedtime = cd02.getTimeInMillis() - cd01.getTimeInMillis();

        if (type == 1)
            return formatDays(usedtime);
        else {
            String xxx = formatSecond(usedtime);
            // System.out.println(xxx);
            return xxx;
        }
    }

    public static String usedtime(String start, String stop) {
        return usedtime(start, stop, 0);
    }


    public static String MoveForward(int n) {
        // yyyy-MM-dd
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -n);
        return dateformat.format(now.getTime());
    }


    public boolean validateDate(Date a) {
        Calendar cal = Calendar.getInstance();
        boolean d = true;
        // System.out.println("Day: " + cal.DAY_OF_WEEK);
        // System.out.println("Hour: " + cal.get(cal.HOUR_OF_DAY));
        // if (a.getHours() > 0 && a.getHours() < 9)
        if (cal.get(cal.HOUR_OF_DAY) > 0 && cal.get(cal.HOUR_OF_DAY) < 9) {
            System.out.println("Hour: " + cal.get(cal.HOUR_OF_DAY));
            d = false;
        }
        // else if (a.getDay() == 7 | a.getDay() == 1)
        else if (cal.get(cal.DAY_OF_WEEK) == 7 | cal.get(cal.DAY_OF_WEEK) == 1) {
            System.out.println("Day: " + cal.get(cal.DAY_OF_WEEK));
            d = false;
        }
        return d;
    }

//    public static boolean verifyDate(Date a) {
//
//        Calendar cal = Calendar.getInstance();
//        boolean d = true;
//        if (cal.get(cal.DAY_OF_WEEK) == 7 | cal.get(cal.DAY_OF_WEEK) == 1) {
//            System.out.println("Day: " + cal.get(cal.DAY_OF_WEEK));
//            d = false;
//        }
//        return d;
//    }
}
