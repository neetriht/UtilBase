package com.zds.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.stock.dbpool.DataSourceOper;


public class TestDatabase {

	/**
	 * @param args
	 */
	static Date dm;
	static DataSourceOper sconn;

	private static Date getMaxDate() {

		try {
			String sql = "select max(open_dt) from red_blue";
			ResultSet rs = sconn.executeQuery(sql,"115");
			rs.next();

			return rs.getDate(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// PreparedStatement ps ;
		//TestBean tb = new TestBean();
		sconn = new DataSourceOper();
		System.out.println("Check db");
		dm = getMaxDate();
		System.out.println(dm);
	}
}