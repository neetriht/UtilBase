package com.mybatis.transation;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;

public class ManagedTransaction implements Transaction {

	private DataSource dataSource;
	private TransactionIsolationLevel level;
	private Connection connection;
	private boolean closeConnection;

	public ManagedTransaction(Connection connection, boolean closeConnection) {
		this.connection = connection;
		this.closeConnection = closeConnection;
	}

	public ManagedTransaction(DataSource ds, TransactionIsolationLevel level, boolean closeConnection) {
		this.dataSource = ds;
		this.level = level;
		this.closeConnection = closeConnection;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		if (this.connection == null) {
			openConnection();
		}
		return this.connection;
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		if (this.closeConnection && this.connection != null) {  
//		      if (log.isDebugEnabled()) {  
//		        log.debug("Closing JDBC Connection [" + this.connection + "]");  
//		      }  
		      this.connection.close();  
		    }  
	}

	@Override
	public Integer getTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	 protected void openConnection() throws SQLException {  
		// if (log.isDebugEnabled()) {
		// log.debug("Opening JDBC Connection");
		// }
		    this.connection = this.dataSource.getConnection();  
		    if (this.level != null) {  
		      this.connection.setTransactionIsolation(this.level.getLevel());  
		    }  
		  }  

}
