package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class PracownikFrame extends JFrame {

	protected LinkedList<String> polecenia = new LinkedList<>();
	protected LinkedList<JButton> opcje = new LinkedList<>();
	protected JDialog dialog;
	protected PracownikOptionsListener ol;

	public PracownikFrame() {
		ol = getNewOptionsListener();

		utworzPolecenia();

		for (String pol : polecenia) {
			JButton button = new JButton(pol);
			button.addActionListener(null);
			add(button);
		}

		ustawWielkosc();
		ustawLayout();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	protected void utworzPolecenia() {
		polecenia.add("liczba godzin pracownika");
		polecenia.add("wynagrodzenie pracownika");
		polecenia.add("zespoly pracownika");
		polecenia.add("dane pracownika");
		polecenia.add("historia pracownika");

	}

	protected void ustawLayout() {
		setLayout(new GridLayout(1, 5));
	}

	protected void ustawWielkosc() {
		setSize(1000, 200);
	}

	protected PracownikOptionsListener getNewOptionsListener() {
		return new PracownikOptionsListener();
	}

	protected class PracownikOptionsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("liczba godzin pracownika")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("wynagrodzenie pracownika")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zespoly pracownika")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("dane pracownika")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("historia pracownika")) {
				// TODO
				return;
			}

		}

	}

}
