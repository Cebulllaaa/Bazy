package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class ZarzDzialuFrame extends ZarzZespoluFrame {

	@Override
	protected void utworzPolecenia() {
		super.utworzPolecenia();
		polecenia.add("dodaj dostawce");
		polecenia.add("dodaj klienta");
		polecenia.add("dodaj pracownika");
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

	protected class ZarzDzialuOptionsListener extends ZarzZespoluOptionsListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			super.actionPerformed(arg0);

			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("dodaj dostawce")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("dodaj klienta")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("dodaj pracownika")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("dodaj zakup")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("dodaj zlecenie")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("ustal budzet projektu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("utworz projekt")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("utworz zespol")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zlecenia info")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("sprawdz bilans")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zmien status")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zatwierdz budzet")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("ZM zarzadcy zespolu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("rozwiaz zespol")) {
				// TODO
				return;
			}

		}

	}

}
