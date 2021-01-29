package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class ZarzZespoluFrame extends PracownikFrame {

	@Override
	protected void utworzPolecenia() {
		super.utworzPolecenia();
		polecenia.add("budzet zespolu");
		polecenia.add("czlonkowie zespolu");
		polecenia.add("ustal wynagrodzenie");
		polecenia.add("ustal czas pracy zespolu");
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

	protected class ZarzZespoluOptionsListener extends PracownikOptionsListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			super.actionPerformed(arg0);

			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("budzet zespolu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("czlonkowie zespolu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("ustal wynagrodzenie")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("ustal czas pracy zespolu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("ustal czas pracy")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zatwierdz wynagrodzenie")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zatwierdz czas pracy")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("dodaj do zespolu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("usun z zespolu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("przenies z zespolu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("projekty zespolu")) {
				// TODO
				return;
			}

		}

	}

}
