package Bazy_danych.Aplikacja.mariadb;

import java.sql.Connection;
import java.sql.Statement;

public abstract class Mariadb {
	protected static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	protected static final String DB_URL = "jdbc:mariadb://localhost:3306/aplikacja_baza";
	protected Connection conn ;
	protected Statement stmt ;
	public abstract void estabilish_connection();
	public abstract void close_connection();
	
}
