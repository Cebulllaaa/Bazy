package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Bazy_danych.Aplikacja.mariadb.Procedures;

public class SzefFrame extends ZarzDzialuFrame {

	protected JMenuBar menuBar;
	protected JMenu opcjeMenu;

	@Override
	protected void ustawMenuBar() {
		menuBar = new JMenuBar();
		opcjeMenu = new JMenu("Opcje");

		BackupRestore br = new BackupRestore();

		JMenuItem backup = new JMenuItem("Backup");
		backup.addActionListener(br);

		JMenuItem restore = new JMenuItem("Restore");
		restore.addActionListener(br);

		opcjeMenu.add(backup);
		opcjeMenu.add(restore);

		menuBar.add(opcjeMenu);

		setJMenuBar(menuBar);

	}

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
		polecenia.add("zapytanie z parametrami");

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
				info1.setEditable(false);

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
				ComDialog comDialog = new ComDialog(2,2);

				JTextField info0 = new JTextField("dzial docelowy");
				JTextField data0 = new JTextField();

				info0.setEditable(false);

				comDialog.dodaj(info0);
				comDialog.dodaj(data0);

				comDialog.setVisible(true);

				ArrayList<String> args = new ArrayList<>();

				args.add(data0.getText());

				connection.use_procedure(Procedures.ZAMKNIJ_DZIAL, args, accesses, effectiveIDs);
				wykonano = true;
				return;
			}

			if (akcja.contentEquals("zapytanie z parametrami")) {
				ComDialog comDialog = new ComDialog(2,3);

				JTextField info0 = new JTextField("kolumna");
				JTextField info1 = new JTextField("tabela");
				JTextField info2 = new JTextField("warunek");
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

				ArrayList<String> wynik = connection.use_procedure(Procedures.DYNAMICZNE, args, accesses, effectiveIDs);
System.out.println(wynik);
				ResultFrame rf = new ResultFrame(wynik);
				rf.setTitle(akcja);
				rf.setVisible(true);
				wykonano = true;
				return;
			}

		}

	}

	private class BackupRestore implements ActionListener {
		private String sciezka_do_bazy = "sciezka.sql";
		private PathDialog pd = new PathDialog();
		private InfoDialog id = new InfoDialog();

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String akcja = arg0.getActionCommand();

			Runtime runtime = Runtime.getRuntime();

			if (akcja.contentEquals("Backup")) {
				try {
					pd.setVisible(true);
					Process proc = runtime.exec("mysqldump -B aplikacja_baza -R --add-drop-database -u BazyUser -p123 -r " + sciezka_do_bazy);
//System.out.println("Backupuje");
					int wynik = proc.waitFor();
//System.out.println("Backup zrobiony");

					if (wynik == 0) {
						id.setText("Utworzono backup");
					}
					else {
						id.setText("Blad: nie udalo sie utworzyc backupu");
					}

					id.setVisible(true);

				}
				catch (IOException iox) {
					iox.printStackTrace();
				} catch (InterruptedException ix) {
					ix.printStackTrace();
				}

				return;

			}
			
			if (akcja.contentEquals("Restore")) {
				try {
					pd.setVisible(true);
					Process proc = runtime.exec(new String[]{"mysql", "-uBazyUser", "-p123", "-e", "source " + sciezka_do_bazy});
//System.out.println("Odtwarzam"); //+"mariadb -u BazyUser -p123 -e \"source " + sciezka_do_bazy + "\"");
					int wynik = proc.waitFor();
//System.out.println("Odtworzylem");

					if (wynik == 0) {
						id.setText("Odtworzono backup");
					}
					else {
						id.setText("Blad: nie udalo sie odtworzyc backupu");
					}

					id.setVisible(true);

				}
				catch (IOException iox) {
					iox.printStackTrace();
				} catch (InterruptedException ix) {
					ix.printStackTrace();
				}

				return;
			}

		}

		private class PathDialog extends JDialog {
			private JTextField pathField = new JTextField(sciezka_do_bazy);

			public PathDialog() {
				setTitle("Podaj sciezke");
				setSize(300, 100);
				setModal(true);
				setLayout(new GridLayout(3, 1));
				setLocationRelativeTo(null);

				JTextField infoField = new JTextField("Podaj sciezke do pliku z baza");
				infoField.setEditable(false);

				JButton but = new JButton("Zatwierdz");
				but.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						sciezka_do_bazy = pathField.getText();
						setVisible(false);
					}
				});

				add(infoField);
				add(pathField);
				add(but);

			}

		}

		private class InfoDialog extends JDialog {

			private JTextField info = new JTextField();

			public InfoDialog() {
				setTitle("Wynik");
				setSize(400, 100);
				setModal(true);
				setLayout(new GridLayout(2, 1));
				setLocationRelativeTo(null);

				info.setEditable(false);
				add(info);

				JButton but = new JButton("Ok");
				but.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				add(but);

			}

			public void setText(String tekst) {
				info.setText(tekst);
			}

		}

	}

}
