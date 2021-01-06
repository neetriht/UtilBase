package com.stock.dbpool;

import com.GlobalRandom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public abstract class ConnectionManager {

    public static Hashtable<String, Connection> ConnectionPools = new Hashtable<String, Connection>();

   /* static ConnectionManager connmager = null;
    private static int CONN_NUM = 1000;
    private static int conn_base = 0;*/

    public abstract Connection newConn(String con_num);

    public static Hashtable<String, Connection> getPool() {
        if (ConnectionPools == null) {
            ConnectionPools = new Hashtable<String, Connection>();
        }
        return ConnectionPools;
    }

    public void returnConn(Map.Entry<String, Connection> conn) {
       // System.out.println("return connection:" + conn.getKey());
        ConnectionPools.put(conn.getKey(), conn.getValue());
    }

    public void releaseConnection(String conn_num, Map.Entry<String, Connection> conn) {
        ConnectionPools.remove(conn.getKey());
    }

    public synchronized Map.Entry<String, Connection> getConnection() {
        String auto_key = null;
        if (ConnectionPools == null) {
            ConnectionPools = new Hashtable<String, Connection>();
        }
        Connection conn = null;
        if (ConnectionPools.size() > 0) {

            auto_key = ConnectionPools.keys().nextElement();
            conn = ConnectionPools.get(auto_key);
            try {
                if (conn == null) {
                    //RemoveConn(auto_key);
                    conn = newConn(auto_key);
                } else {
                    if (conn.isClosed()) {
                        conn = newConn(auto_key);
                    }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            String new_auto_key = GlobalRandom.getRandomString(4, 3);
            if (!ConnectionPools.keySet().contains(new_auto_key)) {
                new_auto_key = GlobalRandom.getRandomString(4, 3);
                System.out.println("ABC new connection: " + new_auto_key);
            }
          //  System.out.println("Creante new connection: " + new_auto_key);
            conn = newConn(new_auto_key);
            auto_key = new_auto_key;
        }
        //System.out.println("Used connection: " + auto_key);
        RemoveConn(auto_key);
        return new AbstractMap.SimpleEntry<>(auto_key, conn);
    }

  /*  public Connection getConnection(String connName) {
        if (ConnectionPools == null) {
            ConnectionPools = new Hashtable<String, Connection>();
        }
        Connection conn = null;
        if (ConnectionPools.containsKey(connName)) {
            conn = ConnectionPools.get(connName);
            try {
                if (conn == null) {
                    RemoveConn(connName);
                    conn = newConn(connName);
                } else {
                    if (conn.isClosed()) {
                        RemoveConn(connName);
                        conn = newConn(connName);
                    }
                }
                // System.out.println("Use Old connection: " + connName);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("Creante new connection: " + Thread.currentThread().getId());
            conn = newConn(connName);
            //
            // poolName);
        }
        return conn;
    }*/

    private static void RemoveConn(String cname) {
        ConnectionPools.remove(cname);
        // System.out.println("Remove Connection: " + cname + " | Total connection size: " + ConnectionPools.size());
    }

    public static void CloseAllConnection() {
        System.out.println("There are: " + ConnectionPools.size() + " will be closed");
        // Enumeration<Connection> conns = ConnectionPools.elements();
        // while (conns.hasMoreElements()) {
        // Connection co = conns.nextElement();
        // if (!co.isClosed())
        // co.close();
        // RemoveConn(entry.getKey());
        // }

        // synchronized (ConnectionPools) {
        // Iterator iter = ConnectionPools.entrySet().iterator();
        // while (iter.hasNext()) {
        // Map.Entry<String, Connection> entry = (Map.Entry<String, Connection>)
        // iter.next();
        // try {
        // if (!entry.getValue().isClosed())
        // entry.getValue().close();
        // } catch (SQLException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // RemoveConn(entry.getKey());
        //
        // }
        // }
        Enumeration<String> entry = ConnectionPools.keys();
        while (entry.hasMoreElements()) {
            try {
                String key = entry.nextElement();
                Connection con = ConnectionPools.get(key);
                if (!con.isClosed())
                    con.close();
                RemoveConn(key);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Close Connection Error");
            }
        }
        //
        // for (Map.Entry<String, Connection> entry :
        // ConnectionPools.entrySet()) {
        // try {
        // if (!entry.getValue().isClosed())
        // entry.getValue().close();
        // RemoveConn(entry.getKey());
        // } catch (SQLException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // System.out.println("Close Connection Error");
        // }
        // }
        ConnectionPools.clear();
        System.out.println("Connection LEFT: " + ConnectionPools.size());
        ConnectionPools = null;
    }


//    public Connection getConnection(String threadid) {
//        if (ConnectionPools == null) {
//            ConnectionPools = new Hashtable<String, Connection>();
//        }
//        CONN_NUM++;
//        Connection conn = null;
//        if (ConnectionPools.size() > 0)
//            if (ConnectionPools.containsKey(threadid))
//                conn = ConnectionPools.get(threadid);
//                //conn = ConnectionPools.remove(ConnectionPools.keySet().toArray()[0]);
//            else
//                conn = newConn(threadid);
//        return conn;
//    }

    public synchronized void disConnection(String conn_num, Connection conn) {
        if (!ConnectionPools.containsKey(conn_num))
            ConnectionPools.put(conn_num, conn);
    }

//    public static class LoadProperties { //获取属性文件
//        public static Properties properties;
//
//        static {
//            try {
//                properties = loadPropertyFile("");
////                sIP = (String) properties.get("ip");
////                System.out.println(properties.get("port"));
////                iPort = Integer.parseInt((String) properties.get("port"));
////                dbName = (String) properties.get("dbName");
////                dbCollection = (String) properties.get("dbCollection");
////                connectionsPerHost = Integer.parseInt((String) properties.get("connectionsPerHost"));
////                threadsAllowedToBlock = Integer.parseInt((String) properties.get("threadsAllowedToBlock"));
////                maxWaitTime = Integer.parseInt((String) properties.get("maxWaitTime"));
////                connectTimeout = Integer.parseInt((String) properties.get("connectTimeout"));
////                log.info("Data source had finished initialization.");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
