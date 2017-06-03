package jp.co.cococoa.business;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SuperBusiness {

    private Connection connection;

	public SuperBusiness() {
	}

	/**
	 * コネクションの取得
	 * @return 接続済みコネクション
	 * */
	public Connection getConnection() {
        try {
        	if((null == connection) || (null != connection && connection.isClosed())) {
                InitialContext context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/postgresql");
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
        	}

        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
	}

	/**
	 * コネクションの取得
	 * @return 接続済みコネクション
	 * */
	public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	/**
	 * コネクションのロールバック
	 * @return 接続済みコネクション
	 * */
	public void rollback() {
        try {
        	if (null != connection && !connection.isClosed()) {
                connection.rollback();
        	}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}


	/**
	 * コネクションのクローズ
	 * @return 接続済みコネクション
	 * */
	public void close() {
        try {
        	if (null != connection && !connection.isClosed()) {
                connection.close();
        	}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

}
