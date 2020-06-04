package com.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.alibaba.druid.pool.DruidDataSource;


public class DruidDemo {

    public void druidTest() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///stu");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        try {
            // 获得连接:
            conn = dataSource.getConnection();
            // 编写SQL：
            String sql = "select * from student";
            pstmt = conn.prepareStatement(sql);
            // 执行sql:
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "   " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.realease(rs, pstmt, conn);
        }

    }

    public void realease(ResultSet rs, PreparedStatement ps, Connection con) {

        try {
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
