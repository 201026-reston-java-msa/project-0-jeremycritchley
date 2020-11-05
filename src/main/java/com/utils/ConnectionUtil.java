package com.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	
private static Connection connection;
		
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				FileInputStream fis = new FileInputStream("connection.properties");
				Properties prop = new Properties();
				prop.load(fis);
				connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
					//Class.forName("org.postgresql.Driver");
					/*String url = "jdbc:postgresql://lallah.db.elephantsql.com:5432/iewkljsf",
							username= "iewkljsf",
							password= "nBG7He5aybzPUMcSjAZ6ueA6PCMuqT-l";*/
					//connection = DriverManager.getConnection(url, username, password);			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 

		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
