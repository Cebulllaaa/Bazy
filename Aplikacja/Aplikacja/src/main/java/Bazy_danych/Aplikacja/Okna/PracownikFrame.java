package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;
import Bazy_danych.Aplikacja.mariadb.Mariadb;
import Bazy_danych.Aplikacja.mariadb.Procedures;

public class PracownikFrame extends JFrame {

	protected LinkedList<String> polecenia = new LinkedList<>();
	protected LinkedList<JButton> opcje = new LinkedList<>();
	protected JDialog dialog;
	protected PracownikOptionsListener ol;
	protected boolean wykonano;
	protected ArrayList<Acces> accesses;
	protected ArrayList<Integer> IDs;
	protected ArrayList<Integer> effectiveIDs;
	protected Mariadb connection;
	private IDDialog idd;
	private AlarmDialog ad;

	public PracownikFrame() {
		ol = getNewOptionsListener();
		idd = new IDDialog();
		ad = new AlarmDialog();

		ustawMenuBar();
		utworzPolecenia();

		for (String pol : polecenia) {
			JButton button = new JButton(pol);
			button.addActionListener(ol);
			add(button);
		}

		ustawWielkosc();
		ustawLayout();
		ustawTytul();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	protected void ustawMenuBar() {
		;
	}

	protected void utworzPolecenia() {
		polecenia.add("liczba godzin pracownika");
		polecenia.add("wynagrodzenie pracownika");
		polecenia.add("zespoly pracownika");
		polecenia.add("projekty pracownika");
		polecenia.add("dane pracownika");
		polecenia.add("historia pracownika");

	}

	protected void ustawLayout() {
		setLayout(new GridLayout(1, 6));
	}

	protected void ustawWielkosc() {
		setSize(1200, 200);
	}

	protected void ustawTytul() {
		setTitle("Pracownik");
	}

	protected PracownikOptionsListener getNewOptionsListener() {
		return new PracownikOptionsListener();
	}

	public void setAccesses(ArrayList<Acces> acc, ArrayList<Integer> IDs) {
		accesses = acc;
		this.IDs = IDs;
		effectiveIDs = (ArrayList<Integer>) IDs.clone();
	}

	public void setConnection(Mariadb conn) {
		connection = conn;
	}

	protected class PracownikOptionsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			wykonano = false;
			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("Ok")) {
				ad.setVisible(false);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("Zatwierdz")) {
				idd.setVisible(false);
				wykonano = true;
				return;
			}

			for (int i=0; i < accesses.size(); i++) {
				if (IDs.get(i) == 0) {
					idd.setInfo(accesses.get(i).toString());
					idd.setVisible(true);

					Integer in = null;

					try {
						in = Integer.parseInt(idd.getInput());

						if (in.compareTo(0) <= 0) {
							throw new NumberFormatException();
						}

					}
					catch (NumberFormatException nfx) {
						ad.setVisible(true);
						return;
					}

					effectiveIDs.set(i, in);
				}
			}

			wykonano = false;

			if (akcja.contentEquals("liczba godzin pracownika")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.LICZBA_GODZIN_PRACOWNIKA, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("wynagrodzenie pracownika")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.WYNAGRODZENIE_PRACOWNIKA, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zespoly pracownika")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.ZESPOLY_PRACOWNIKA, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("projekty pracownika")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.PROJEKTY_PRACOWNIKA, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("dane pracownika")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.DANE_PRACOWNIKA, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("historia pracownika")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.HISTORIA_PRACOWNIKA, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

		}

	}

	private class IDDialog extends JDialog {
		private JTextField outInfo;
		private JTextField inID;
		private JButton confirmButton;
		private String info;

		public IDDialog() {
			outInfo = new JTextField();
			inID = new JTextField();
			confirmButton = new JButton("Zatwierdz");
			info = "Podaj ID dla dostepu: ";

			outInfo.setEditable(false);
			confirmButton.addActionListener(ol);

			setLayout(new GridLayout(3, 1));
			setSize(300, 100);
			setLocationRelativeTo(null);
			setModal(true);

			add(outInfo);
			add(inID);
			add(confirmButton);

		}

		public void setInfo(String accessInfo) {
			outInfo.setText(info + accessInfo);
		}

		public String getInput() {
			return inID.getText();
		}

	}

	private class AlarmDialog extends JDialog {
		public AlarmDialog() {
			setSize(200, 100);
			setTitle("Blad");
			setLayout(new GridLayout(2, 1));
			setLocationRelativeTo(null);

			JTextField info = new JTextField("Blad: podano niewlasciwe ID");
			info.setEditable(false);

			JButton ok = new JButton("Ok");
			ok.addActionListener(ol);

			add(info);
			add(ok);

		}
	}

}
