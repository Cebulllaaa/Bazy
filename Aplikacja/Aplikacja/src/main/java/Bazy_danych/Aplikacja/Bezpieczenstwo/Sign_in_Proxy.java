package Bazy_danych.Aplikacja.Bezpieczenstwo;

public class Sign_in_Proxy implements Sign_in_Interface{
	private Acces acces;
	private String login;
	private String password;
	
	public Sign_in_Proxy(String x, String y) {
		this.login =x;
		this.password = y;
	}
	
	@Override
	public int sign_in() {
		/*
		 * TODO sprawdze potem czy taka osoba istnieje w bazie i jakie ma stanowisko
		 */
		return 0;
	}
	
	@Override
	public Acces getAcces() {
		return acces;
	}
}
