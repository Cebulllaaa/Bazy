package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import Bazy_danych.Aplikacja.mariadb.Procedures;

public class ZarzDzialuFrame extends ZarzZespoluFrame {

	@Override
	protected void utworzPolecenia() {
		super.utworzPolecenia();
		polecenia.add("dodaj dostawce");
		polecenia.add("dodaj klienta");
		polecenia.add("dodaj zakup");
		polecenia.add("dodaj zlecenie");
		polecenia.add("ustal budzet projektu");
		polecenia.add("utworz projekt");
		polecenia.add("utworz zespol");
		polecenia.add("zlecenia info");
		polecenia.add("sprawdz bilans");
		polecenia.add("zmien status");
		polecenia.add("zatwierdz budzet");
		polecenia.add("ZM zarzadcy zespolu");
		polecenia.add("rozwiaz zespol");

	}

	@Override
	protected void ustawLayout() {
		setLayout(new GridLayout(6, 5));
	}

	@Override
	protected void ustawWielkosc() {
		setSize(1200, 1000);
	}

	@Override
	protected PracownikOptionsListener getNewOptionsListener() {
		return new ZarzDzialuOptionsListener();
	}

	@Override
	protected void ustawTytul() {
		setTitle("Zarzadca Dzialu");
	}

	protected class ZarzDzialuOptionsListener extends ZarzZespoluOptionsListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			super.actionPerformed(arg0);

			if (wykonano) {
				return;
			}

			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("dodaj dostawce")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("nazwa dostawcy");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(null, args, accesses, effectiveIDs); // TODO
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("dodaj klienta")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("nazwa klienta");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.DODAJ_KLIENTA, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("dodaj zakup")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("nazwa produktu");
				JTextField info1 = new JTextField("nazwa dostawcy");
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

				connection.use_procedure(Procedures.ZAKUP_PRODUKT, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("dodaj zlecenie")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("nazwa klienta");
				JTextField info1 = new JTextField("wartosc zlecenia");
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

				connection.use_procedure(Procedures.DODAJ_ZLECENIE, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("ustal budzet projektu")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID projektu");
				JTextField info1 = new JTextField("wartosc projektu");
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

				connection.use_procedure(Procedures.USTAL_BUDZET_PROJEKTU, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("utworz projekt")) {
				ComDialog comDialog = new ComDialog(2,3);

				JTextField info0 = new JTextField("nazwa projektu");
				JTextField info1 = new JTextField("ID zlecenia");
				JTextField info2 = new JTextField("ID zespolu");
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

				connection.use_procedure(Procedures.UTWORZ_PROJEKT, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("utworz zespol")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID zarzadcy");
				JTextField info1 = new JTextField("nazwa zespolu");
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

				connection.use_procedure(Procedures.UTWORZ_ZESPOL, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zlecenia info")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("ID zlecenia");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				ArrayList<String> wynik = connection.use_procedure(Procedures.ZLECENIE_INFO, args, accesses, effectiveIDs);
				// TODO
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("sprawdz bilans")) {
				ArrayList<String> wynik = connection.use_procedure(Procedures.SPRAWDZ_BILANS, null, accesses, effectiveIDs);
				// TODO
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zmien status")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID projektu");
				JTextField info1 = new JTextField("nazwa status");
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

				connection.use_procedure(Procedures.ZMIEN_STATUS, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zatwierdz budzet")) {
				connection.use_procedure(Procedures.ZATWIERDZ_BUDZET, null, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("ZM zarzadcy zespolu")) {
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("ID zespolu");
				JTextField info1 = new JTextField("ID nowego zarzadcy");
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

				connection.use_procedure(Procedures.ZM_ZARZADCY_ZESPOLU, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("rozwiaz zespol")) {
				ComDialog comDialog = new ComDialog(2,1);

				JTextField info0 = new JTextField("ID zespolu");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.ROZWIAZ_ZESPOL, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

		}

	}

}
