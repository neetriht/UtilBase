package com.mybatis.transation;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionException;

public class JDBCTransaction implements Transaction {

	// private static final Log log = LogFactory.getLog(JdbcTransaction.class);

	protected Connection connection;
	protected DataSource dataSource;
	protected TransactionIsolationLevel level;
	protected boolean autoCommmit;

	public JDBCTransaction(DataSource ds, TransactionIsolationLevel desiredLevel, boolean desiredAutoCommit) {
		dataSource = ds;
		level = desiredLevel;
		autoCommmit = desiredAutoCommit;
	}

	public JDBCTransaction(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		if (connection == null) {
			openConnection();
		}
		return connection;
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		if (connection != null && !connection.getAutoCommit()) {
			// if (log.isDebugEnabled()) {
			// log.debug("Committing JDBC Connection [" + connection + "]");
			// }
			connection.commit();
		}
	}

	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub
		if (connection != null && !connection.getAutoCommit()) {
			// if (log.isDebugEnabled()) {
			// log.debug("Rolling back JDBC Connection [" + connection + "]");
			// }
			connection.rollback();
		}
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		if (connection != null) {
			resetAutoCommit();
			// if (log.isDebugEnabled()) {
			// log.debug("Closing JDBC Connection [" + connection + "]");
			// }
			connection.close();
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
		connection = dataSource.getConnection();
		if (level != null) {
			connection.setTransactionIsolation(level.getLevel());
		}
		setDesiredAutoCommit(autoCommmit);
	}

	protected void setDesiredAutoCommit(boolean desiredAutoCommit) {
		try {
			if (connection.getAutoCommit() != desiredAutoCommit) {
				// if (log.isDebugEnabled()) {
				// log.debug("Setting autocommit to " + desiredAutoCommit + " on
				// JDBC Connection [" + connection + "]");
				// }
				connection.setAutoCommit(desiredAutoCommit);
			}
		} catch (SQLException e) {
			// Only a very poorly implemented driver would fail here,
			// and there's not much we can do about that.
			throw new TransactionException("Error configuring AutoCommit.  "
					+ "Your driver may not support getAutoCommit() or setAutoCommit(). " + "Requested setting: "
					+ desiredAutoCommit + ".  Cause: " + e, e);
		}
	}

	protected void resetAutoCommit() {
		try {
			if (!connection.getAutoCommit()) {
				// MyBatis does not call commit/rollback on a connection if just
				// selects were performed.
				// Some databases start transactions with select statements
				// and they mandate a commit/rollback before closing the
				// connection.
				// A workaround is setting the autocommit to true before closing
				// the connection.
				// Sybase throws an exception here.
				// if (log.isDebugEnabled()) {
				// log.debug("Resetting autocommit to true on JDBC Connection ["
				// + connection + "]");
				// }
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			// log.debug("Error resetting autocommit to true "
			// + "before closing the connection. Cause: " + e);
		}
	}
}
