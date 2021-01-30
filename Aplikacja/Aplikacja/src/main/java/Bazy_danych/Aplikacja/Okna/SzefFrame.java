package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import Bazy_danych.Aplikacja.mariadb.Procedures;

public class SzefFrame extends ZarzDzialuFrame {

	@Override
	protected void utworzPolecenia() {
		super.utworzPolecenia();
		polecenia.add("dodaj dzial");
		polecenia.add("dodaj pracownika");
		polecenia.add("korekta danych");
		polecenia.add("korekta historii");
		polecenia.add("zwolnij pracownika");
		polecenia.add("ZM zarzadcy dzialu");
		polecenia.add("przeniesienie zlecenia");
		polecenia.add("zamknij dzial");

		/*
		 * TODO:
		 * polecenia.add("zapytanie z parametrami");
		 */
	}

	@Override
	protected void ustawLayout() {
		setLayout(new GridLayout(8, 5));
	}

	@Override
	protected void ustawWielkosc() {
		setSize(1600, 1000);
	}

	@Override
	protected PracownikOptionsListener getNewOptionsListener() {
		return new SzefOptionsListener();
	}

	@Override
	protected void ustawTytul() {
		setTitle("Szef");
	}

	protected class SzefOptionsListener extends ZarzDzialuOptionsListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			super.actionPerformed(arg0);

			if (wykonano) {
				return;
			}

			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("dodaj dzial")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID zarzadcy");
				JTextField info1 = new JTextField("nazwa dzialu");
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

				connection.use_procedure(Procedures.DODAJ_DZIAL, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("dodaj pracownika")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("imie pracownika");
				JTextField info1 = new JTextField("nazwisko pracownika");
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

				connection.use_procedure(Procedures.DODAJ_PRACOWNIKA, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("korekta danych")) {
				ComDialog comDialog = new ComDialog(2,3);

				JTextField info0 = new JTextField("ID pracownika");
				JTextField info1 = new JTextField("nazwa atrybutu");
				JTextField info2 = new JTextField("wartosc atrybutu");
				JTextField data0 = new JTextField();
				JTextField data1 = new JTextField();
				JTextField data2 = new JTextField();

				info0.setEditable(false);
				info1.setEditable(false);
				info2.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(info1);
				comDialog.dodaj(info2);
				comDialog.dodaj(data0);
				comDialog.dodaj(data1);
				comDialog.dodaj(data2);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());
				args.add(data1.getText());
				args.add(data2.getText());

				connection.use_procedure(Procedures.KOREKTA_DANYCH, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("korekta historii")) {
				ComDialog comDialog = new ComDialog(2,3);

				JTextField info0 = new JTextField("ID pracownika");
				JTextField info1 = new JTextField("ID zespolu");
				JTextField info2 = new JTextField("nazwa atrybutu");
				JTextField info3 = new JTextField("wartosc atrybutu");
				JTextField data0 = new JTextField();
				JTextField data1 = new JTextField();
				JTextField data2 = new JTextField();
				JTextField data3 = new JTextField();

				info0.setEditable(false);
				info1.setEditable(false);
				info2.setEditable(false);
				info3.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(info1);
				comDialog.dodaj(info2);
				comDialog.dodaj(info3);
				comDialog.dodaj(data0);
				comDialog.dodaj(data1);
				comDialog.dodaj(data2);
				comDialog.dodaj(data3);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());
				args.add(data1.getText());
				args.add(data2.getText());
				args.add(data3.getText());

				connection.use_procedure(Procedures.KOREKTA_HISTORII, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zwolnij pracownika")) {
/*				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID pracownika");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());
*/
				connection.use_procedure(Procedures.ZWOLNIJ_PRACOWNIKA, null, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("ZM zarzadcy dzialu")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID nowego zarzadcy");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.ZM_ZARZADCY_DZIALU, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("przeniesienie zlecenia")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID zlecenia");
				JTextField info1 = new JTextField("ID dzialu");
				JTextField data0 = new JTextField();
				JTextField data1 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(info1);
				comDialog.dodaj(data0);
				comDialog.dodaj(data1);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());
				args.add(data1.getText());

				connection.use_procedure(Procedures.PRZENIESIENIE_ZLECENIA, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zamknij dzial")) {
				connection.use_procedure(Procedures.ZAMKNIJ_DZIAL, null, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

		}

	}

}
