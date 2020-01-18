package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String DATABASE = "localhost:3306/bioskopWeb";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";	

	private static Connection connection;

	public static void open() {
		System.out.println("connection manager otvoren");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?useSSL=false", USER_NAME, PASSWORD);
			System.out.println("uspesno otvorena konekcija");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("greska kod povezivanja");
		}
	}
	
	public static Connection getConnection() {
		System.out.println("connnnnnn");
		return connection;
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
