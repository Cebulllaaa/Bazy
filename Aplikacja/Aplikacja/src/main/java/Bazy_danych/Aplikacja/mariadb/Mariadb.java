package Bazy_danych.Aplikacja.mariadb;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;
import Bazy_danych.Aplikacja.Bezpieczenstwo.UI_Proxy;

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
	public abstract ArrayList<Integer> getIDs();
	protected abstract void setAcces();
	protected abstract void setIDs(String x);
	protected ArrayList<Integer> IDs;
	public abstract UI_Proxy getUI();
	protected abstract void setUI();
	public abstract ArrayList<String> use_procedure(Procedures proc, ArrayList<String> args, ArrayList<Acces> acc, ArrayList<Integer> id);
}
