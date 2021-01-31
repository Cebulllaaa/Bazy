package Bazy_danych.Aplikacja.Okna;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Bazy_danych.Aplikacja.mariadb.Procedures;

public class ZarzZespoluFrame extends PracownikFrame {

	@Override
	protected void utworzPolecenia() {
		super.utworzPolecenia();
		polecenia.add("budzet zespolu");
		polecenia.add("czlonkowie zespolu");
		polecenia.add("ustal wynagrodzenie");
		polecenia.add("ustal czas pracy czlonka");
		polecenia.add("ustal czas pracy");
		polecenia.add("zatwierdz wynagrodzenie");
		polecenia.add("zatwierdz czas pracy");
		polecenia.add("dodaj do zespolu");
		polecenia.add("usun z zespolu");
		polecenia.add("przenies z zespolu");
		polecenia.add("projekty zespolu");

	}

	@Override
	protected void ustawLayout() {
		setLayout(new GridLayout(4, 4));
	}

	@Override
	protected void ustawWielkosc() {
		setSize(800, 800);
	}

	@Override
	protected PracownikOptionsListener getNewOptionsListener() {
		return new ZarzZespoluOptionsListener();
	}

	@Override
	protected void ustawTytul() {
		setTitle("Zarzadca Zespolu");
	}

	protected class ZarzZespoluOptionsListener extends PracownikOptionsListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			super.actionPerformed(arg0);

			if (wykonano) {
				return;
			}

			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("budzet zespolu")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.BUDZET_ZESPOLU, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("czlonkowie zespolu")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.CZLONKOWIE_ZESPOLU, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("ustal wynagrodzenie")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID pracownika");
				JTextField info1 = new JTextField("wynagrodzenie");
				JTextField data0 = new JTextField();
				JTextField data1 = new JTextField();

				info0.setEditable(false);
				info1.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(info1);
				comDialog.dodaj(data0);
				comDialog.dodaj(data1);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());
				args.add(data1.getText());

				connection.use_procedure(Procedures.USTAL_WYNAGRODZENIE, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("ustal czas pracy czlonka")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("czas pracy");
				JTextField info1 = new JTextField("ID pracownika");
				JTextField data0 = new JTextField();
				JTextField data1 = new JTextField();

				info0.setEditable(false);
				info1.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(info1);
				comDialog.dodaj(data0);
				comDialog.dodaj(data1);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());
				args.add(data1.getText());

				connection.use_procedure(Procedures.USTAL_CZAS_PRACY_CZLONKA, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("ustal czas pracy")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("czas pracy");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.USTAL_CZAS_PRACY, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zatwierdz wynagrodzenie")) {
				connection.use_procedure(Procedures.ZATWIERDZ_WYNAGRODZENIE, null, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zatwierdz czas pracy")) {
				connection.use_procedure(Procedures.ZATWIERDZ_CZAS_PRACY, null, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("dodaj do zespolu")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("ID pracownika");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.DODAJ_DO_ZESPOLU, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("usun z zespolu")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("ID pracownika");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.USUN_Z_ZESPOLU, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("przenies z zespolu")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("ID pracownika");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.PRZENIES_Z_ZESPOLU, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("projekty zespolu")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.PROJEKTY_ZESPOLU, null, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

		}

		protected class ComDialog extends JDialog {
			private JPanel panel;

			public ComDialog(int rows, int cols) {
				setLayout(new BorderLayout());
				setModal(true);
				setSize(cols * 200, rows * 70);
				setLocationRelativeTo(null);
				setTitle("Podaj argumenty");

				JButton jb = new JButton("Zatwierdz");
				jb.addActionListener(new ComDialogListener());
				add(jb, BorderLayout.SOUTH);

				panel = new JPanel();
				panel.setLayout(new GridLayout(rows, cols));
				add(panel, BorderLayout.CENTER);

			}

			public void dodaj(Component comp) {
				panel.add(comp);
			}

			private class ComDialogListener implements ActionListener {
				public void actionPerformed(ActionEvent ae) {
					setVisible(false);
				}
			}
		}

	}

}
