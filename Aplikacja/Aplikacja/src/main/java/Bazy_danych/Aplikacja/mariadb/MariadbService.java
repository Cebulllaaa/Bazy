package Bazy_danych.Aplikacja.mariadb;

import java.sql.*;
import java.util.ArrayList;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;
public class MariadbService extends Mariadb{
	public MariadbService(String x, String y) {
		this.login =x;
		this.password=y;
	}
	
	public void estabilish_connection(String x, String y) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,x,y);
		/*	stmt = conn.createStatement();
			String sql = "SELECT COUNT (*) AS total  FROM  pracownik";
			ResultSet rs3= stmt.executeQuery(sql);
			int count =0;
			while(rs3.next()) {
				count = rs3.getInt("total");
			}
			System.out.println(count); */
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Nawiazano polaczenie");
	}
	public void  close_connection() {
		try {
			conn.close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		System.out.println("Zamykam polaczenie");
	}
	@Override
	public int sign_in() {
		String login_helper;
		String password_helper;
		boolean in_base = false;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT pracownik.id_pracownika , pracownik.imie , pracownik.nazwisko, loginy.login , hasla.hash_hasla \r\n"
					+ "FROM pracownik INNER JOIN hasla ON pracownik.id_pracownika = hasla.id_pracownika INNER JOIN loginy ON\r\n"
					+ "loginy.id_pracownika = pracownik.id_pracownika ";
			ResultSet rs3 =stmt.executeQuery(sql);
			while(rs3.next()) {
				login_helper=rs3.getNString("loginy.login");
				password_helper= rs3.getNString("hasla.hash_hasla");
				if(login_helper.equals(login) && password_helper.equals(password)) {
					in_base =true;
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		if(in_base) {
			return 1;
		}
		else {
			return 0;
		}
	}
	@Override
	public ArrayList<Acces> getAcces() {
		return acceses;
	}

	@Override
	protected void setAcces() {
		// TODO Auto-generated method stub
		
	}
}
