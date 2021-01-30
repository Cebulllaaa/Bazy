package Bazy_danych.Aplikacja.mariadb;

import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;
import Bazy_danych.Aplikacja.Bezpieczenstwo.UI_Proxy;
public class MariadbService extends Mariadb{
	private Procedures_manager manager;
	public MariadbService(String x, String y) {
		this.login =x;
		this.password=y;
		this.IDs = new ArrayList<Integer>();
		this.acceses = new ArrayList<Acces>();
	}
	public MariadbService(ArrayList<Acces> x , ArrayList<Integer> y){
		this.IDs = new ArrayList<Integer>();
		this.acceses = new ArrayList<Acces>();
		this.acceses = x;
		this.IDs = y;
		String databse_login;
		String database_password;
		if(acceses.contains(Acces.ADMIN)) {
			estabilish_connection("BazyUser","123");
		}
		/*
		 * TODO opcje dla osob z mniejszymi uprawnieniami
		 */
		else if(acceses.contains(Acces.ZARZADCA_ZESPOLU)) {
			estabilish_connection("ZespolUser","123");
		}
		else if(acceses.contains(Acces.ZWYKLY_PRACOWNIK)) {
			estabilish_connection("PracownikUser","123");
		}
	}
	
	public void estabilish_connection(String x, String y) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,x,y);
			manager = new Procedures_manager(conn);
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
		String ID_helper;
		boolean in_base = false;
		if(login.equals("admin") && password.equals(DigestUtils.md5Hex("admin"))) {
			in_base = true;
			acceses.add(Acces.ZWYKLY_PRACOWNIK);
			acceses.add(Acces.ZARZADCA_ZESPOLU);
			acceses.add(Acces.ZARZADCA_DZIALU);
			acceses.add(Acces.ADMIN);
			IDs.add(0);
			IDs.add(0);
			IDs.add(0);
			IDs.add(0);
			
		}
		else {
		try {
			stmt = conn.createStatement();
			String sql = "SELECT pracownik.id_pracownika ,  loginy.login , hasla.hash_hasla \r\n"
					+ "FROM pracownik INNER JOIN hasla ON pracownik.id_pracownika = hasla.id_pracownika INNER JOIN loginy ON\r\n"
					+ "loginy.id_pracownika = pracownik.id_pracownika ";
			ResultSet rs3 =stmt.executeQuery(sql);
			while(rs3.next()) {
				login_helper=rs3.getNString("loginy.login");
				password_helper= rs3.getNString("hasla.hash_hasla");
				if(login_helper.equals(login) && password_helper.equals(password)) {
					in_base =true;
					ID_helper = rs3.getNString("pracownik.id_pracownika");
					try {
						IDs.add(Integer.parseInt(ID_helper));
						acceses.add(Acces.ZWYKLY_PRACOWNIK);
					}
					catch(Exception e) {
						System.out.println(e);
					}
					setAcces();
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
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
		try {
		String ID_helper;
		int ID;
		boolean z_dzialu = false;
		stmt= conn.createStatement();
		String sql = "SELECT pracownik.id_pracownika , działy.id_działu \r\n"
				+ "FROM pracownik INNER JOIN działy ON pracownik.id_pracownika = działy.zarządca_działu";
		ResultSet rs3 = stmt.executeQuery(sql);
		while(rs3.next()) {
			ID_helper = rs3.getNString("pracownik.id_pracownika");
			ID = Integer.parseInt(ID_helper);
			if(ID == IDs.get(0)) {
				acceses.add(Acces.ZARZADCA_ZESPOLU);
				setIDs("0");
				acceses.add(Acces.ZARZADCA_DZIALU);
				ID_helper = rs3.getNString("działy.id_działu");
				setIDs(ID_helper);
				z_dzialu = true;
				break; 
				}
			}
		if(!z_dzialu) {
			stmt = conn.createStatement();
			String sql2 = "SELECT pracownik.id_pracownika , zespoły.id_zespołu  \r\n"
					+ "FROM pracownik INNER JOIN zespoły ON pracownik.id_pracownika = zespoły.zarządca_zespołu";
			ResultSet rs32 = stmt.executeQuery(sql2);
			while(rs32.next()) {
				ID_helper = rs32.getNString("pracownik.id_pracownika");
				ID = Integer.parseInt(ID_helper);
				if(ID == IDs.get(0)) {
					acceses.add(Acces.ZARZADCA_ZESPOLU);
					ID_helper = rs32.getNString("zespoły.id_zespołu");
					setIDs(ID_helper);
					break;
				}
			}
		}
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("To tu");
		}
	}

	@Override
	public ArrayList<Integer> getIDs() {
		return IDs;
	}

	@Override
	protected void setIDs(String x) {
			IDs.add(Integer.parseInt(x));
		
	}
	@Override
	public UI_Proxy getUI() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void setUI() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<String> use_procedure(Procedures proc, ArrayList<String> args, ArrayList<Acces> acc, ArrayList<Integer> id) {
			return manager.use_procedure(proc,args,acc,id);
	}

}
