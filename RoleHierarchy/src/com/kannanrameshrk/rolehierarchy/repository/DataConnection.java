package com.kannanrameshrk.rolehierarchy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
	private static String url="jdbc:mysql://localhost:3306/rolejob";
	private static String name="root";
	private static String pass="15410198";
	private static String driver="com.mysql.cj.jdbc.Driver";
	static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			try {
				con=DriverManager.getConnection(url,name,pass);
			} catch (SQLException e) {
				 System.out.println("Error establishing the database connection: " + e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				 System.out.println("Error closing the database connection: " + e.getMessage());
			}
			con=null;
		}
	}
}
