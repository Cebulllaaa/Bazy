package Bazy_danych.Aplikacja.mariadb;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;

public abstract class Mariadb {
	protected static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	protected static final String DB_URL = "jdbc:mariadb://localhost:3306/aplikacja_baza";
	protected static String login;
	protected static String password;
	protected Connection conn ;
	protected Statement stmt ;
	protected ArrayList<Acces> acceses;
	public abstract void estabilish_connection(String x, String y);
	public abstract void close_connection();
	public abstract int sign_in();
	public abstract ArrayList<Acces> getAcces();
	protected abstract void setAcces();
	
}
