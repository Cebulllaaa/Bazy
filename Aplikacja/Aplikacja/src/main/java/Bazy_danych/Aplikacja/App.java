package Bazy_danych.Aplikacja;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;
import Bazy_danych.Aplikacja.Bezpieczenstwo.Sign_in_Proxy;
import Bazy_danych.Aplikacja.mariadb.Mariadb;
import Bazy_danych.Aplikacja.mariadb.Procedures;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import org.apache.commons.codec.digest.DigestUtils;

public class App {
	private Mariadb connection;
	public static App app;
	private static String login;
	private static String password;
	private ArrayList<Acces> acceses;
	private ArrayList<Integer> IDs;
	private static boolean isLogging = false;
	private ArrayList<String> arg;
	public static void main(String[] args) {
		app = new App();
		LogInDialog lid = new LogInDialog();
		lid.setVisible(true);
		while (!isLogging) {
			try {
				Thread.sleep(200);
			}
			catch (InterruptedException ix) {
				ix.printStackTrace();
			}
		}
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
		ArrayList<Integer> u = new ArrayList<Integer>();
		u.add(0);
		u.add(15);
		arg = new ArrayList<String>();
		arg.add("44");
		connection.use_procedure(Procedures.DODAJ_DO_ZESPOLU, arg, acceses, u);

	}

	private static class LogInDialog extends JDialog {

		private JTextField inLogin;
		private JTextField inPassword;

		public LogInDialog() {
			setLayout(new GridLayout(3, 1));

			inLogin = new JTextField("login");
			inPassword = new JTextField("haslo");

			add(inLogin);
			add(inPassword);

			JButton button = new JButton("Ok");
			button.addActionListener(new OkListener());
			add(button);

			setTitle("Logowanie");
			setSize(200, 100);
			setLocationRelativeTo(null);

		}

		private class OkListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				login = inLogin.getText();
				password = DigestUtils.md5Hex(inPassword.getText());
				isLogging = true;

				setVisible(false);

			}

		}

	}

}
