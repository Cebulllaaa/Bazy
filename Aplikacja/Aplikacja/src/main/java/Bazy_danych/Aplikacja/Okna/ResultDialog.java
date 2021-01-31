package Bazy_danych.Aplikacja.Okna;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ResultDialog extends JFrame {

	private ArrayList<String> rekordy;
	private int wysokosc;
	private int szerokosc;
	private boolean wykracza;

	public ResultDialog(ArrayList<String> rek) {
		rekordy = rek;
		wysokosc = rekordy.size();

		if (wysokosc > 30) {
			wykracza = true;
			wysokosc = 30;
		}

		szerokosc = 0;
		for (String rekord : rekordy) {
			szerokosc = Math.max(szerokosc, rekord.length());
		}

		szerokosc = Math.min(szerokosc, 160);
		szerokosc = Math.max(szerokosc, 10);

		setSize(wysokosc * 30, szerokosc * 10);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // TODO

	}

	//

}
