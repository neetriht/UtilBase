package com.stock.dbpool;

import java.sql.Connection;

public interface DataSource {
    // public Connection getInstance(String threadid);

    public Connection getInstance(String threadid);

    public Connection getConnection();

    public void releaseConnection(String conn_num, Connection conn);
}
