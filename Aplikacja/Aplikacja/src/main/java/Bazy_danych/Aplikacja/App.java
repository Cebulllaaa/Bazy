package Bazy_danych.Aplikacja;

import Bazy_danych.Aplikacja.Bezpieczenstwo.*;
import Bazy_danych.Aplikacja.mariadb.*;

public class App {
	private Mariadb connection;
	public static App app;
	private static String login;
	private static String password;
	private Sign_in_Interface sign;
	public static void main(String[] args) {
		app = new App();
		/*
		 *TODO Ustalenie kto loguje sie do aplikacji i wywolanie odpowiedniej funkcji
		 * na podstawie zmiennych login i password
		 */
		login = "Cebula";
		password = "123";
		app.sign_in(login, password);
		app.admin_connection();
		app.close_connection();
	}
	private void sign_in(String x, String y) {
		int succes;
		sign = new Sign_in_Proxy(x,y);
		succes = sign.sign_in();
		if(succes ==0) {
			System.out.println("Nieprawidłowy login lub hasło");
		}
		else {
			System.out.println("Pomyslnie zalogowano do aplikacji");
		}
	}
	private void admin_connection() {
		connection = new MariadbAdmin();
		connection.estabilish_connection();
	}
	private void zarzadcadzialu_connection() {
		
	}
	private void zarzadcazespolu_connection() {
		
	}
	private void pracownik_connection() {
		
	}
	private void close_connection() {
		connection.close_connection();
	}
}
