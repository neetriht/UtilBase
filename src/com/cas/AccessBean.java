package com.cas;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-09-05
 * Time: 21:48
 * <p>
 * Description:
 */
public class AccessBean {

    String G_URL = "jdbc:db2://9.190.3.29:5004/DB2DMVSB";
    String G_USER = "";
    String G_PASSWORD = "";

    public String getG_URL() {
        return G_URL;
    }

    public void setG_URL(String g_URL) {
        G_URL = g_URL;
    }

    public String getG_USER() {
        return G_USER;
    }

    public void setG_USER(String g_USER) {
        G_USER = g_USER;
    }

    public String getG_PASSWORD() {
        return G_PASSWORD;
    }

    public void setG_PASSWORD(String g_PASSWORD) {
        G_PASSWORD = g_PASSWORD;
    }


}
