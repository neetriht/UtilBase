package com;

public class GlobalEnv {


    public static boolean isLinux() {
        // TRUE: Linux
        // False: Win
        
        String osname = System.getProperty("os.name");
        System.out.println(osname);
        if (!osname.startsWith("Win")) {
            return true;
        } else {
            return false;
        }
    }

    public static void getPath(){
        System.out.println();
    }
}
