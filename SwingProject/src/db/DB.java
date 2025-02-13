package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost";
	public static final String username = "scott";
	public static final String password = "tiger";
	
	public static Statement stmt;
	public static Connection conn;
	
	// DB connect
	public static void init() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("해당 드라이버가 존재하지 않음");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("접속 정보 확인 요청");
			e.printStackTrace();
		}
	}
	
	// DQL : select
	public static ResultSet getResult(String sql) {
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// DML : update, delete
	public static void executeSql(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
