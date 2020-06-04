package com.mybatis.transation;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;

public class TransactionFactory implements org.apache.ibatis.transaction.TransactionFactory {

	@Override
	public void setProperties(Properties props) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaction newTransaction(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
		// TODO Auto-generated method stub
		return null;
	}

}
