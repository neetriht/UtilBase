package com.stock.dbpool;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSourceOper {

    // DataSource datasource = new DB2DataBase();
    // DataSource datasource = new OracleDataBase();
    DataSource datasource = new PGDatabase();
    Statement stmt = null;
    String sql = null;
    ResultSet odbcrs = null;

    public DataSourceOper() {
        //datasource = new DB2DataBase(propvalue);
    }

    public DataSourceOper(String propvalue) {
        datasource = new DB2DataBase(propvalue);
    }

    public DataSourceOper(DataSource ds) {
        datasource = ds;
    }

    public Statement getStatement() {
        try {
            Connection conn = datasource.getInstance().getValue();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return st;
        } catch (SQLException ex) {
            System.err.println("sql_data.getStatement:" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public int executeUpdate(PreparedStatement tt) {
        try {
            return tt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.executeUpdate:" + ex.getMessage());
            return -1;
        }
    }

    public Boolean detect() {
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            return !short_conn.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            datasource.returnConn(connectionpair);
        }
    }


//    public String doQueryReturnBoolean(String sql) {
//        String value;
//        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
//        Connection short_conn = connectionpair.getValue();
//        //  Connection short_conn = datasource.getInstance("876");
//        try {
//            // conn = datasource.getConnection();
//            Statement stmt = short_conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            value =  "1";
//        } catch (SQLException ex) {
//            System.out.println(sql);
//            System.err.println("sql_data.executeInsert:" + ex.getMessage());
//            value =  "0";
//        } finally {
//            datasource.returnConn(connectionpair);
//        }
//        return value;
//    }


//    public ResultSet HttpexecuteQuery(String sql) {
//        return executeQuery(sql, "159");
//    }

//    public void closeStatement() {
//        try {
//            if (!stmt.isClosed())
//                stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public void returnConnection() {
//        returnConnection(conn);
//    }
//
//    public void returnConnection(Connection c) {
//        try {
//            if (odbcrs != null)
//                if (!odbcrs.isClosed())
//                    odbcrs.close();
//            if (stmt != null)
//                if (!stmt.isClosed())
//                    stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//       // datasource.releaseConnection(c);
//    }

  /*  public int checkClosed(String sql) {
        ResultSet odbcrs;
        int rt = 0;
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        try {
            conn = connectionpair.getValue();
            stmt = conn.createStatement();
            odbcrs = stmt.executeQuery(sql);
            if (odbcrs != null & odbcrs.next() & !odbcrs.isClosed()) {
                String cc = odbcrs.getString(2);
                if (cc != null) {
                    if (cc.equals("C")) {
                        rt = 2;
                    } else {
                       *//* String onboard = rs.getString(3);
                        if (onboard == null)
                            return 3;*//*
                        rt = 1;
                    }
                } else
                    rt = 1;
            }
        } catch (Exception ex) {
            System.err.println("sql_data.executeQuery:" + ex.getMessage());
            ex.printStackTrace();
            return 0;
        } finally {
            datasource.returnConn(connectionpair);
        }
        return rt;
    }*/

    public int checkBackInt(String sql, ICheckTask checktask) {
        int value;
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection conn = connectionpair.getValue();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            value = checktask.checkWay(rs);
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("sql_data.executeQuery:" + ex.getMessage());
            ex.printStackTrace();
            return 0;
        } finally {
            datasource.returnConn(connectionpair);
        }
        return value;
    }

    public Map.Entry<String, Connection> getConnection() {
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        return connectionpair;
    }


    public void releaseConnection(Map.Entry<String, Connection> CONNECTIONPAIR) {
        datasource.returnConn(CONNECTIONPAIR);
    }

  /*  public ResultSet executeQuery(String sql, String threadid) {
        ResultSet odbcrs;

        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        try {
            //System.out.println(threadid + ":  " + sql);
            // DataSource datasource = new OracleDataBase();
            conn = connectionpair.getValue();
            // stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            // ResultSet.CONCUR_UPDATABLE);

            stmt = conn.createStatement();

            // stmt =
            // conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            // System.out.println(conn + ": " + stmt + " : " + threadid);
            odbcrs = stmt.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(threadid + " : " + sql);
            System.err.println("sql_data.executeQuery:" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }

        // new AbstractMap.SimpleEntry<>(auto_key, conn);
        return odbcrs;
    }*/


    /* public boolean executeDelete(String sql) {
         Map.Entry<String, Connection> connectionpair = datasource.getInstance();
         Connection short_conn = connectionpair.getValue();
         //Connection short_conn = datasource.getInstance("999");
         try {
             System.out.println(sql);
             // conn = datasource.getConnection();
             stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             int xxx = stmt.executeUpdate(sql);
             System.out.println(xxx);
             short_conn.commit();
         } catch (SQLException ex) {
             System.out.println(sql);
             System.err.println("sql_data.executeDelete:" + ex.getMessage());
             return false;
         } finally {
             datasource.returnConn(connectionpair);
         }

         return true;
     }
     private void closeResultSet(ResultSet rs, Connection c) {
        try {
            if (!rs.isClosed())
                rs.close();

//            if (!c.isClosed())
//                c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    public boolean checkExisting(String sql) {
        boolean existing = false;
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            Statement stmt = short_conn.createStatement();
            ResultSet odbcrs = stmt.executeQuery(sql);
            if (odbcrs.next()) {
                existing = true;
            }
        } catch (SQLException ex) {
            System.out.println(sql + " Pools : " + ConnectionManager.ConnectionPools.size());
            System.err.println("sql_data.checkExisting:" + ex.getMessage() + ": " + Thread.currentThread().getId());
            ex.printStackTrace();
            return false;
        } finally {
            datasource.returnConn(connectionpair);
        }
        return existing;
    }


    public boolean executeSQL(String sql) {
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            Statement stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(sql);
            short_conn.commit();
            stmt.close();
        } catch (Exception ex) {
            System.out.println(sql);
            System.err.println("sql_data.executeInsert:" + ex.getMessage());
            return false;
        } finally {
            datasource.returnConn(connectionpair);
        }
        return true;
    }

//    public boolean executeUpdate(String sql) {
//        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
//        Connection short_conn = connectionpair.getValue();
//        // Connection short_conn = datasource.getInstance();
//        try {
//            // conn = datasource.getInstance(threadid);
//            Statement stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            stmt.executeUpdate(sql);
//            short_conn.commit();
//            stmt.close();
//        } catch (SQLException ex) {
//            System.out.println(sql);
//            System.err.println("sql_data.executeUpdate:" + ex.getMessage());
//            return false;
//        } finally {
//            datasource.returnConn(connectionpair);
//        }
//        return true;
//    }

    public PreparedStatement prepareStatement(String sql) {
        // DataSource datasource = new DB2DataBase();
        // DataSource datasource = new OracleDataBase();
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        //Connection short_conn = datasource.getInstance(threadid);
        try {
            //short_conn = datasource.getInstance(threadid);
            short_conn.setAutoCommit(true);
            PreparedStatement ps = short_conn.prepareStatement(sql);
            return ps;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }


    public void doclose() throws Exception {
        if (odbcrs != null)
            odbcrs.close();
        if (stmt != null)
            stmt.close();

    }

/*    public void addBatch(String sql) {
        try {
            if (stmt == null)
                stmt = this.getStatement();
            stmt.addBatch(sql);
        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.addBatch:" + ex.getMessage());
        }
    }*/

    public int[] executeBatch() {
        int[] a;
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        // Connection short_conn = datasource.getInstance(threadid);
        try {
            Statement stmt = short_conn.createStatement();
            a = stmt.executeBatch();
            short_conn.commit();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("sql_data.executeBatch:" + ex.getMessage());
            return null;
        } finally {
            datasource.returnConn(connectionpair);
        }
        return a;
    }

    public boolean validateQuery(String sql) {
        boolean value = true;
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            Statement stmt = short_conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            if (rs.getString(1) != null) {
                value = true;
            } else {
                value = false;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.validateQuery:" + ex.getMessage());
            return false;
        } finally {
            datasource.returnConn(connectionpair);
        }
        return value;
    }

    public String executeGetString(String sql, int column) {
        String value = "";
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            Statement stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getString(1));
                value = rs.getString(column);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            datasource.returnConn(connectionpair);
        }
        return value;
    }

    public int executeGetInteger(String sql, int column) {
        int value = 0;
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            Statement stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                // System.out.println(rs.getString(1));
                value = rs.getInt(column);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            datasource.returnConn(connectionpair);
        }
        return value;
    }

    public List executeGetStringGroup(String sql, int column) {
        List<String> value = new ArrayList<String>();
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            Statement stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value.add(rs.getString(column));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            datasource.returnConn(connectionpair);
        }
        return value;
    }

    public List executeGetStringGroup(String sql, int... column) {
        List<String[]> value = new ArrayList<>();
        Map.Entry<String, Connection> connectionpair = datasource.getInstance();
        Connection short_conn = connectionpair.getValue();
        try {
            Statement stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            List<String> bean = new ArrayList<>();
            while (rs.next()) {
                for (int a : column)
                    bean.add(rs.getString(a));
                value.add(bean.toArray(new String[bean.size()]));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            datasource.returnConn(connectionpair);
        }
        return value;
    }

}
