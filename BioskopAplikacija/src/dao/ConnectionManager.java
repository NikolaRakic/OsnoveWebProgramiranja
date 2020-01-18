package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;



public class ConnectionManager {

	private static final String DATABASE_NAME = "bioskopweb.db";

	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String WINDOWS_PATH = "C:" + FILE_SEPARATOR + "Temp" + FILE_SEPARATOR + "SQLite" + FILE_SEPARATOR + DATABASE_NAME;
	private static final String LINUX_PATH = "SQLite" + FILE_SEPARATOR + DATABASE_NAME;

	private static final String PATH = WINDOWS_PATH;	

	private static Connection connection;

	public static void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + PATH);
		
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	
	public static Connection getConnection() {	
		open();
		return connection; 
			
		
	}
	
	public static void close() {
		try {
			connection.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}