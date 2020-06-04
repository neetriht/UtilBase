package com;

/**
 * Created by neetriht on 2017-11-02.
 */

public class GlobalParm {

    public static String DB2_USER;

    public static String getDb2Password() {
        return DB2_PASSWORD;
    }

    public static void setDb2Password(String db2Password) {
        DB2_PASSWORD = db2Password;
    }

    public static String getDb2User() {
        return DB2_USER;
    }

    public static void setDb2User(String db2User) {
        DB2_USER = db2User;
    }

    public static String DB2_PASSWORD;


//
//    String extract_time = "extract_time";
//    public static Properties pps = new Properties();
//
//    public String getExtract_time() {
//        return extract_time;
//    }
//
//    public void setExtract_time(String extract_time) {
//        this.extract_time = extract_time;
//    }
//
//    public void init() throws ServletException {
//        System.out.println("=== start application and get parms!!!");
//        System.out.println("=== " + GlobalTimer.getDayTime());
//        getProperties();
//        //boolean bool = pingLink();
//        // 取得Application对象
//        ServletContext application = this.getServletContext();
//        System.out.println(application.getContextPath());
//        //System.out.println(new File("\\").getAbsolutePath());
//        // 设置Application属性
//        //application.setAttribute("bool", bool);
//    }
//
//    public void getProperties() {
//
//        try {
//            System.out.println(new File("/").getAbsolutePath());
//            System.out.println(new File("\\").getAbsolutePath());
//            pps.load((FileInputStream) getClass().getResourceAsStream("app.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        pps.getProperty(extract_time);
//
//        Enumeration enum1 = pps.propertyNames();
//        while (enum1.hasMoreElements()) {
//            String strKey = (String) enum1.nextElement();
//            String strValue = pps.getProperty(strKey);
//            System.out.println(strKey + "=" + strValue);
//
//            switch (strKey)
//            {
//                case "db2_user": break;
//                case "db2_password":
//            }
//
//
//        }
//    }
//
//    public boolean pingLink() {
//        boolean bool = true;
//        Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
//        Process process = null; // 声明处理类对象
//        String line = null; // 返回行信息
//        InputStream is = null; // 输入流
//        InputStreamReader isr = null; // 字节流
//        BufferedReader br = null;
//        String ip = "www.baidu.com";
//        boolean res = false;// 结果
//        try {
//            process = runtime.exec("ping " + ip); // PING
//            is = process.getInputStream(); // 实例化输入流
//            isr = new InputStreamReader(is);// 把输入流转换成字节流
//            br = new BufferedReader(isr);// 从字节中读取文本
//            while ((line = br.readLine()) != null) {
//                if (line.contains("TTL")) {
//                    res = true;
//                    break;
//                }
//            }
//            is.close();
//            isr.close();
//            br.close();
//            if (res) {
////             Log.print("ping www.baidu.com通...已经连接外网");
//            } else {
//                bool = false;
////             Log.print("ping www.baidu.com不通...无法连接外网");
//            }
//        } catch (IOException e) {
////            Log.print(e.getMessage());
//        }
//        return bool;
//    }

}
