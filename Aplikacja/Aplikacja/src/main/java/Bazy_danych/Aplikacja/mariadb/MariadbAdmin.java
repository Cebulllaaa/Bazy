package Bazy_danych.Aplikacja.mariadb;

import java.sql.*;
public class MariadbAdmin extends Mariadb{
	//protected static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	//protected static final String DB_URL = "jdbc:mariadb://localhost:3306/aplikacja_baza";
	
	private static final String userName = "BazyUser";
	private static final String password = "123";
	
	public void estabilish_connection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,userName,password);
			stmt = conn.createStatement();
			String sql = "SELECT COUNT (*) AS total  FROM  pracownik";
			ResultSet rs3= stmt.executeQuery(sql);
			int count =0;
			while(rs3.next()) {
				count = rs3.getInt("total");
			}
			System.out.println(count);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void  close_connection() {
		try {
			conn.close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
}
