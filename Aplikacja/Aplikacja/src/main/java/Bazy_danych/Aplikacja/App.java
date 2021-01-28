package Bazy_danych.Aplikacja;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;
import Bazy_danych.Aplikacja.Bezpieczenstwo.Sign_in_Proxy;
import Bazy_danych.Aplikacja.mariadb.Mariadb;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

public class App {
	private Mariadb connection;
	public static App app;
	private static String login;
	private static String password;
	private ArrayList<Acces> acceses;
	private ArrayList<Integer> IDs;
	public static void main(String[] args) {
		app = new App();
		/*
		 *TODO Ustalenie kto loguje sie do aplikacji i wywolanie odpowiedniej funkcji
		 * na podstawie zmiennych login i password
		 */
		login = "admin";
		password = DigestUtils.md5Hex("admin");
		app.sign_in(login, password);
	}
	private void sign_in(String x, String y) {
		acceses = new ArrayList<Acces>();
		IDs = new ArrayList<Integer>();
		int succes;
		connection = new Sign_in_Proxy(x,y);
		succes = connection.sign_in();
		if(succes ==0) {
			System.out.println("Nieprawidłowy login lub hasło");
		}
		else {
			System.out.println("Pomyslnie zalogowano do aplikacji");
			acces_view();
			UI();
		}
	}
	private void acces_view() {
		System.out.println("Wyswietlam posiadane uprawnienia");
		acceses = connection.getAcces();
		IDs = connection.getIDs();
		int i =0;
		while(acceses.size()>i) {
			System.out.println(acceses.get(i)  + " " + IDs.get(i));
			i=i+1;
		}
	}
	private void close_connection() {
		connection.close_connection();
	}
	private void UI() {
		connection = connection.getUI();
	}
}
