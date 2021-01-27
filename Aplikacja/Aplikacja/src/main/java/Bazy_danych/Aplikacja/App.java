package Bazy_danych.Aplikacja;

import Bazy_danych.Aplikacja.mariadb.*;

public class App {
	Mariadb connection;
	public static App app;
	public static void main(String[] args) {
		app = new App();
		/*
		 *TODO Ustalenie kto loguje sie do aplikacji i wywolanie odpowiedniej funkcji
		 */
		app.admin_connection();
		app.close_connection();
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
