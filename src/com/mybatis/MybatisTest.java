package com.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisTest {

		private static SqlSessionFactory sqlSessionFactory;
	    private static Reader reader;

	    static {
	        try {
	            reader = Resources.getResourceAsReader("config/Configure.xml");
	            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static SqlSessionFactory getSession() {
	        return sqlSessionFactory;
	    }

	    public static void main(String[] args) {
	        SqlSession session = sqlSessionFactory.openSession();

	        try {
	            User user = (User) session.selectOne("config.UserMapper.GetUserByID","em10001");
				System.out.println(user.getUser_id());
				System.out.println(user.getUser_name());
				System.out.println(user.getPhone());
				System.out.println(user.getPassword());
			} finally {
	            session.close();
	        }
	    }

}
