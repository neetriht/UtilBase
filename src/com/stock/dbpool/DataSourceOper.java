package com.stock.dbpool;

import java.sql.*;
import java.util.Map;

public class DataSourceOper {

    //DataSource datasource = new DB2DataBase();
    //DataSource datasource = new DB2DataBase();
    // DataSource datasource = new OracleDataBase();
    DataSource datasource = new PGDatabase();
    Connection conn = null;
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

            //conn = datasource.getConnection();
            conn = datasource.getInstance().getValue();
            //
            conn.setAutoCommit(false);
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return st;

        } catch (SQLException ex) {

            System.err.println("sql_data.getStatement:" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Boolean detect() {
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
        try {
            // Connection conn = datasource.getInstance("000");
            //conn.setAutoCommit(false);
            //stmt = conn.createStatement();
            //stmt.executeQuery("SELECT * FROM RASTSDD");
            return !conn.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            datasource.returnConn(conntry);
        }
    }

    public String doQueryReturnBoolean(String sql) {
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
        //  Connection short_conn = datasource.getInstance("876");
        try {
            // conn = datasource.getConnection();
            stmt = short_conn.createStatement();
            odbcrs = stmt.executeQuery(sql);

            return "1";
        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.executeInsert:" + ex.getMessage());
            return "0";
        } finally {
            datasource.returnConn(conntry);
        }
    }


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

    public ResultSet executeQuery(String sql, String threadid) {
        ResultSet odbcrs;

        Map.Entry<String, Connection> conntry = datasource.getInstance();
        try {
            //System.out.println(threadid + ":  " + sql);
            // DataSource datasource = new OracleDataBase();
            conn = conntry.getValue();
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
        return odbcrs;
    }


    public boolean executeDelete(String sql) {
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
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
            datasource.returnConn(conntry);
        }

        return true;
    }

//    //public boolean checkExisting(String sql) {
//        return checkExisting(sql, "9985");
//    }

    public boolean checkExisting(String sql) {
        // System.out.println("Thread id is : " + threadid);
        boolean existing = true;
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();

        if (short_conn != null) {
            try {
                Statement stmt = short_conn.createStatement();
                // stmt =
                // conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                // System.out.println(sql + ": " +
                // Thread.currentThread().getId());
                // System.out.println(sql + ": " + conn.isValid(5));
                // System.out.println(sql + ": " + stmt.isClosed());
                ResultSet odbcrs = stmt.executeQuery(sql);

                if (!odbcrs.isClosed()) {
                    if (odbcrs.next()) {
                        //closeResultSet(odbcrs, conn);
                        return true;
                    }
                    // closeResultSet(odbcrs, conn);
                }
            } catch (SQLException ex) {
                System.out.println(sql + " Pools : " + ConnectionManager.ConnectionPools.size());
                System.err.println("sql_data.checkExisting:" + ex.getMessage() + ": " + Thread.currentThread().getId());
                ex.printStackTrace();
                return false;
            } finally {
                datasource.returnConn(conntry);
            }
        }
        return false;
    }

//    public boolean executeInsert(String sql) {
//        return executeInsert(sql);
//    }

    public boolean executeInsert(String sql) {
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
        // String t = conntry.getKey();
        //Connection short_conn = conntry.getValue();
        try {

            //conn = datasource.getInstance(threadid);
            stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(sql);
            short_conn.commit();

            //  return true;
        } catch (Exception ex) {
            System.out.println(sql);
            System.err.println("sql_data.executeInsert:" + ex.getMessage());
            return false;
        } finally {
            datasource.returnConn(conntry);
        }
        return true;
    }

    /**
     * @webmethod
     */
//   public boolean executeUpdate(String sql) {
//        return executeUpdate(sql, "9987");
//    }
    public boolean executeUpdate(String sql) {
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
        // Connection short_conn = datasource.getInstance();
        try {
            // conn = datasource.getInstance(threadid);
            stmt = short_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(sql);
            short_conn.commit();

        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.executeUpdate:" + ex.getMessage());
            return false;
        } finally {
            datasource.returnConn(conntry);
        }
        return true;
    }

//    public PreparedStatement prepareStatement(String sql) {
//        return prepareStatement(sql);
//    }

    public PreparedStatement prepareStatement(String sql) {
        // DataSource datasource = new DB2DataBase();
        // DataSource datasource = new OracleDataBase();
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
        //Connection short_conn = datasource.getInstance(threadid);
        try {
            //short_conn = datasource.getInstance(threadid);
            short_conn.setAutoCommit(true);
            PreparedStatement ps = short_conn.prepareStatement(sql);
            return ps;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            datasource.returnConn(conntry);
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

        // return true;
    }

    public void doclose() throws Exception {
        if (odbcrs != null)
            odbcrs.close();
        if (stmt != null)
            stmt.close();

    }

    public void addBatch(String sql) {
        try {
            if (stmt == null)
                stmt = this.getStatement();
            stmt.addBatch(sql);
        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.addBatch:" + ex.getMessage());
        }
    }

    public int[] executeBatch() {
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
        // Connection short_conn = datasource.getInstance(threadid);
        try {
            // System.out.println("AAA");
            if (stmt == null)
                stmt = this.getStatement();
            int[] a = stmt.executeBatch();
            short_conn.commit();
            return a;
        } catch (SQLException ex) {

            System.err.println("sql_data.executeBatch:" + ex.getMessage());
            return null;
        } finally {
            datasource.returnConn(conntry);
        }

    }

    /*
     * public void doclose() throws Exception { // stmt.close(); conn.close();
     *
     * }
     */

    public boolean validateQuery(String sql) {
        Map.Entry<String, Connection> conntry = datasource.getInstance();
        Connection short_conn = conntry.getValue();
        //Connection short_conn = datasource.getInstance("888");
        try {
            stmt = short_conn.createStatement();
            odbcrs = stmt.executeQuery(sql);
            odbcrs.next();
            if (odbcrs.getString(1) != null) {
                //closeResultSet(odbcrs, conn);
                return true;
            } else {
                //closeResultSet(odbcrs, conn);
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(sql);
            System.err.println("sql_data.validateQuery:" + ex.getMessage());
            return false;
        } finally {
            datasource.returnConn(conntry);
        }

    }

//    private void closeResultSet(ResultSet rs, Connection c) {
//        try {
//            if (!rs.isClosed())
//                rs.close();
//
////            if (!c.isClosed())
////                c.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
