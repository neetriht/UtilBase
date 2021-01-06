package com.stock.dbpool;

import java.sql.Connection;
import java.util.Map;

public interface DataSource {

    Map.Entry<String, Connection> getInstance();

    Map.Entry<String, Connection> getConnection();

    void releaseConnection(String conn_num, Map.Entry<String, Connection> conn);

    void returnConn(Map.Entry<String, Connection> conn);
}
