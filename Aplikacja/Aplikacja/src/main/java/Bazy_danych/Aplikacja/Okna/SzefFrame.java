package Bazy_danych.Aplikacja.Okna;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class SzefFrame extends ZarzDzialuFrame {

	@Override
	protected void utworzPolecenia() {
		super.utworzPolecenia();
		polecenia.add("dodaj dzial");
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

	protected class SzefOptionsListener extends ZarzDzialuOptionsListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			super.actionPerformed(arg0);

			String akcja = arg0.getActionCommand();

			if (akcja.contentEquals("dodaj dzial")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("korekta danych")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("korekta historii")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zwolnij pracownika")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("ZM zarzadcy dzialu")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("przeniesienie zlecenia")) {
				// TODO
				return;
			}

			if (akcja.contentEquals("zamknij dzial")) {
				// TODO
				return;
			}

		}

	}

}
