package com.stock.dbpool;

import java.sql.Connection;
import java.util.Map;

public interface DataSource {
    // public Connection getInstance(String threadid);

    public Map.Entry<String, Connection> getInstance();

    public Map.Entry<String, Connection> getConnection();

    public void releaseConnection(String conn_num, Map.Entry<String, Connection> conn);

    public void returnConn(Map.Entry<String, Connection> conn);
}
