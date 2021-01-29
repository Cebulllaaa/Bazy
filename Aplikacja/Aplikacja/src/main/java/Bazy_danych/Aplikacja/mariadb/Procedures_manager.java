package Bazy_danych.Aplikacja.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;

public class Procedures_manager {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs3;
	public Procedures_manager(Connection x) {
		this.conn = x;
	}
	private ArrayList<String> result;
	public ArrayList<String> use_procedure(Procedures proc, ArrayList<String> args, ArrayList<Acces> acc, ArrayList<Integer> id){
		if(proc.equals(Procedures.DODAJ_PRACOWNIKA)) {
			if(acc.contains(Acces.ADMIN)) {
				dodaj_pracownika(args);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.DODAJ_DO_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				dodaj_do_zespolu(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		return result;
	}
	private void dodaj_pracownika(ArrayList<String> x) {
		
	}
	private void dodaj_do_zespolu(ArrayList<String> x , ArrayList<Integer> y) {
		int id_zesp = y.get(1);
		int id_prac = Integer.parseInt(x.get(0));
		System.out.println(id_prac + " " + id_zesp);
		String sql = "CALL dodaj_do_zespolu(?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			stmt.setInt(2, id_prac);
			rs3 = stmt.executeQuery();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
