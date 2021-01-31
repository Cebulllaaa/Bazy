package Bazy_danych.Aplikacja.Okna;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ResultFrame extends JFrame {

	private ArrayList<String> rekordy;
	private int wysokosc;
	private int szerokosc;
	private JPanel zbior;
	private ArrayList<String[]> wartosci;
	private ArrayList<JTextField[]> pokazane;
	private int gornyIndeks;
	private int dolnyIndeks;

	public ResultFrame(ArrayList<String> rek) {
		rekordy = rek;
		wysokosc = rekordy.size();

		if (wysokosc > 30) {
			wysokosc = 30;
		}

		szerokosc = 0;
		for (String rekord : rekordy) {
			szerokosc = Math.max(szerokosc, rekord.length());
		}

		szerokosc = Math.min(szerokosc, 160);
		szerokosc = Math.max(szerokosc, 10);

		setSize(szerokosc * 10, wysokosc * 30 + 100);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JButton wroc = new JButton("Wroc");
		wroc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		add(wroc, BorderLayout.SOUTH);

		String[] tytuly = rekordy.get(0).split(";");
		JPanel kolumny = new JPanel();
		kolumny.setLayout(new GridLayout(1, tytuly.length));
//System.out.println(tytuly.length);
//System.out.println(wysokosc);
		for (String tytul : tytuly) {
			JTextField title = new JTextField(tytul);
			title.setEditable(false);
			kolumny.add(title);
		}

		add(kolumny, BorderLayout.NORTH);

		wartosci = new ArrayList<>();
		zbior = new JPanel(new GridLayout(wysokosc - 1, tytuly.length));
		pokazane = new ArrayList<>();

		for (int i=1; i < rekordy.size(); i++) {
			wartosci.add(rekordy.get(i).split(";"));
//System.out.println(wartosci.get(i-1));
		}
//System.out.println(wartosci);
		for (int i=0; i < wysokosc - 1; i++) {
			String[] pola = wartosci.get(i);
			JTextField[] poleInfo = new JTextField[pola.length];

			for (int j=0; j < pola.length; j++) {
				poleInfo[j] = new JTextField(pola[j]);
				zbior.add(poleInfo[j]);
			}

			pokazane.add(poleInfo);

		}

		gornyIndeks = 0;
		dolnyIndeks = gornyIndeks + wysokosc - 2;

		JPanel informacje = new JPanel(new BorderLayout());
		informacje.add(zbior, BorderLayout.CENTER);

		JButton up = new JButton("do gory");
		JButton down = new JButton("do dolu");

		Przesuwanie moving = new Przesuwanie();
		up.addActionListener(moving);
		down.addActionListener(moving);

		informacje.add(up, BorderLayout.NORTH);
		informacje.add(down, BorderLayout.SOUTH);

		add(informacje, BorderLayout.CENTER);

	}

	private class Przesuwanie implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String action = ae.getActionCommand();

			if (action.contentEquals("do gory")) {
				goUp();
				return;
			}

			if (action.contentEquals("do dolu")) {
				goDown();
				return;
			}

		}

		private void goUp() {
			if (gornyIndeks > 0) {
				gornyIndeks--;
				dolnyIndeks--;
			}
			ustawPola();
		}

		private void goDown() {
			if (dolnyIndeks < wartosci.size() - 1) {
				gornyIndeks++;
				dolnyIndeks++;
			}
			ustawPola();
		}

		private void ustawPola() {
			for (int i = 0; i < dolnyIndeks - gornyIndeks; i++) {
				JTextField[] row = pokazane.get(i);
				String[] titles = wartosci.get(i + gornyIndeks);

				for (int j=0; j < row.length ; j++) {
					row[j].setText(titles[j]);
				}

			}

		}

	}

}
