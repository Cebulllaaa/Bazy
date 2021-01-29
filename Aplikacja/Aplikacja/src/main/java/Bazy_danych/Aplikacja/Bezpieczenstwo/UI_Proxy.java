package Bazy_danych.Aplikacja.Bezpieczenstwo;

import java.util.ArrayList;

import Bazy_danych.Aplikacja.mariadb.Mariadb;
import Bazy_danych.Aplikacja.mariadb.MariadbService;
import Bazy_danych.Aplikacja.mariadb.Procedures;

public class UI_Proxy extends Mariadb{
	private MariadbService service;
	public UI_Proxy(ArrayList<Acces> x , ArrayList<Integer> y) {
		this.acceses = new ArrayList<Acces>();
		this.IDs = new ArrayList<Integer>();
		this.acceses =x;
		this.IDs =y;
		service = new MariadbService(acceses,IDs);
	}
	@Override
	public void estabilish_connection(String x, String y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close_connection() {
		service.close_connection();
		
	}

	@Override
	public int sign_in() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Acces> getAcces() {
		
		return acceses;
	}

	@Override
	public ArrayList<Integer> getIDs() {
		return IDs;
	}

	@Override
	protected void setAcces() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setIDs(String x) {
		// TODO Auto-generated method stub
		
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
		if(acc.equals(acceses)) {
			return service.use_procedure(proc,args,acc,id);
		}
		else {
			System.out.println("Blad zgodnosci");
			return null;
		}
	}

}
