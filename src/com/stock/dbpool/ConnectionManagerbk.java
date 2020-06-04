package com.stock.dbpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public abstract class ConnectionManagerbk {

    //private static Hashtable<String, Connection> ConnectionPools = new Hashtable<String, Connection>();
    public static Vector<Connection> ConnectionPool = new Vector<Connection>();
    static ConnectionManager connmager = null;
    private static int CONN_NUM = 1000;
    //private static int conn_base = 0;


    //public abstract Connection newConn(String connName);

    public abstract Connection newConn();

//    public int getConnNum() {
//        return conn_base++;
//    }

    public static Vector<Connection> getPool() {
        if (ConnectionPool == null) {
            ConnectionPool = new Vector<Connection>();
        }
        return ConnectionPool;
    }

    public void returnConn(Connection conn) {


        ConnectionPool.add(conn);
    }


    public synchronized Connection pullConnection() {
        if (ConnectionPool == null) {
            ConnectionPool = new Vector<Connection>();
        }
        Connection conn = null;
        if (ConnectionPool.size() > 0) {
            conn = ConnectionPool.remove(0);
            try {
                if (conn == null) {
                    conn = newConn();
                } else {
                    if (conn.isClosed()) {
                        conn = newConn();
                    }
                }
                // System.out.println("Use Old connection: " + connName);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("Creante new connection: " + Thread.currentThread().getId());
            conn = newConn();
            //
            // poolName);
        }
        return conn;
    }

//    private static void RemoveConn(String cname) {
//        ConnectionPools.remove(cname);
//        // System.out.println("Remove Connection: " + cname + " | Total connection size: " + ConnectionPools.size());
//    }

    public static void CloseAllConnection() {
        System.out.println("There are: " + ConnectionPool.size() + " will be closed");
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


    /*    Enumeration<String> entry = ConnectionPools.keys();
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
        }*/

        while (ConnectionPool.size() > 0) {
            Connection con = ConnectionPool.remove(0);
            try {
                if (!con.isClosed())
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
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

        System.out.println("Connection LEFT: " + ConnectionPool.size());
        ConnectionPool = null;
    }


//    public synchronized void disConnection(String conn_num, Connection conn) {
//        if (!ConnectionPools.containsKey(conn_num))
//            ConnectionPools.put(conn_num, conn);
//    }

}
