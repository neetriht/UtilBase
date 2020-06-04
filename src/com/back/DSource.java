package com.back;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DSource {

	// @@@mysql
	// private static String Driver = "com.mysql.jdbc.Driver";
	// private static String url = "jdbc:mysql://localhost:3306/doubleball";
	// private static String user = "root";
	// private static String password = "mko00okm";
	// @@@mysql
	// private static String url = "jdbc:mysql://localhost:3306/chatroom";
	// @@@db2
	/*
	 * private static String Driver = "com.ibm.db2.jcc.DB2Driver"; private
	 * static String Driver_XA = "com.ibm.db2.jcc.DB2XADatasource"; // //
	 * "COM.ibm.db2.jdbc.app.DB2Driver"; private static String url =
	 * "jdbc:db2://localhost:50000/DB2TBLUE"; private static String user =
	 * "neetriht"; private static String password = "vfr44rfv";
	 */
	// @@@db2

	// String Driver="com.mysql.jdbc.Driver";
	// String
	// url="jdbc:mysql://localhost:3306/chatroom?Unicode=true&characterEncoding=gb2312";
	// String user="administrator";
	// String password="mmmmmm";
	private static final String JNDI_NAME = "java:jboss/MyDB2";
	static DataSource ds;
	static {

		try {
			// InitialContext envContext = ;
			ds = (DataSource) new InitialContext().lookup(JNDI_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection conn() throws SQLException {
		Connection odbcconn;
		try {

			// Class.forName(Driver).newInstance();
			// Connection odbcconn = DriverManager.getConnection(url, user,
			// password);

			odbcconn = ds.getConnection();
			/*
			 * if(!odbcconn.isValid(3)) {
			 * System.out.println("Not open Database!!!"); return null; }
			 */
		} catch (Exception e) {
			System.out.println("DRIVER ERROR!!!");
			System.out.println("Test!!!");
			System.out.print(e);
			return null;

		}
		odbcconn.setAutoCommit(false);
		return odbcconn;
	}
}