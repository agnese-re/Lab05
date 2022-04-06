package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	static private final String jdbcUrl = "jdbc:mysql://localhost/dizionario?user=root&password=TdP_2022";
	
	public static Connection getConnection() {
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			return connection;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, sqle);
		}
	}
}
