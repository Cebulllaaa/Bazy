package Bazy_danych.Aplikacja.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Bazy_danych.Aplikacja.Bezpieczenstwo.Acces;

public class Procedures_manager {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs3;
	public Procedures_manager(Connection x) {
		this.conn = x;
	}
	private ArrayList<String> result;
	public ArrayList<String> use_procedure(Procedures proc, ArrayList<String> args, ArrayList<Acces> acc, ArrayList<Integer> id){
		if(proc.equals(Procedures.DODAJ_PRACOWNIKA)) {
			if(acc.contains(Acces.ADMIN)) {
				dodaj_pracownika(args);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.DODAJ_DO_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				dodaj_do_zespolu(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.LICZBA_GODZIN_PRACOWNIKA)) {
			if(acc.contains(Acces.ZWYKLY_PRACOWNIK)) {
				liczba_godzin_pracownika(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.WYNAGRODZENIE_PRACOWNIKA)) {
			if(acc.contains(Acces.ZWYKLY_PRACOWNIK)) {
				wynagrodzenie_pracownika(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZESPOLY_PRACOWNIKA)) {
			if(acc.contains(Acces.ZWYKLY_PRACOWNIK)) {
				zespoly_pracownika(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.PROJEKTY_PRACOWNIKA)) {
			if(acc.contains(Acces.ZWYKLY_PRACOWNIK)) {
				projekty_pracownika(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.DANE_PRACOWNIKA)) {
			if(acc.contains(Acces.ZWYKLY_PRACOWNIK)) {
				dane_pracownika(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.HISTORIA_PRACOWNIKA)) {
			if(acc.contains(Acces.ZWYKLY_PRACOWNIK)) {
				historia_pracownika(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.BUDZET_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				budzet_zespolu(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.CZLONKOWIE_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				czlonkowie_zespolu(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.USTAL_WYNAGRODZENIE)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				ustal_wynagrodzenie(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.USTAL_CZAS_PRACY_CZLONKA)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				ustal_czas_pracy_czlonka(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.USTAL_CZAS_PRACY)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				ustal_czas_pracy(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.BUDZET_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				budzet_zespolu(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZATWIERDZ_WYNAGRODZENIE)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				zatwierdz_wynagrodzenie(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZATWIERDZ_CZAS_PRACY)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				zatwierdz_czas_pracy(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.USUN_Z_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				usun_z_zespolu(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.PRZENIES_Z_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				przenies_z_zespolu(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.PROJEKTY_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_ZESPOLU)) {
				projekty_zespolu(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.DODAJ_KLIENTA)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				dodaj_klienta(args);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.DODAJ_ZLECENIE)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				dodaj_zlecenie(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZAKUP_PRODUKT)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				zakup_produkt(args);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.UTWORZ_PROJEKT)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				utworz_projekt(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.USTAL_BUDZET_PROJEKTU)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				ustal_budzet_projektu(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.SPRAWDZ_BILANS)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				sprawdz_bilans(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZLECENIE_INFO)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				zlecenie_info(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZMIEN_STATUS)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				zmien_status(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZATWIERDZ_BUDZET)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				zatwierdz_budzet(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZM_ZARZADCY_ZESPOLU)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				zm_zarzadcy_zespolu(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.UTWORZ_ZESPOL)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				utworz_zespol(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ROZWIAZ_ZESPOL)) {
			if(acc.contains(Acces.ZARZADCA_DZIALU)) {
				rozwiaz_zespol(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.KOREKTA_DANYCH)) {
			if(acc.contains(Acces.ADMIN)) {
				korekta_danych(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.KOREKTA_HISTORII)) {
			if(acc.contains(Acces.ADMIN)) {
				korekta_historii(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.DODAJ_DZIAL)) {
			if(acc.contains(Acces.ADMIN)) {
				dodaj_dzial(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZWOLNIJ_PRACOWNIKA)) {
			if(acc.contains(Acces.ADMIN)) {
				zwolnij_pracownika(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZM_ZARZADCY_DZIALU)) {
			if(acc.contains(Acces.ADMIN)) {
				zm_zarzadcy_dzialu(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.PRZENIESIENIE_ZLECENIA)) {
			if(acc.contains(Acces.ADMIN)) {
				przeniesienie_zlecenia(args,id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.ZAMKNIJ_DZIAL)) {
			if(acc.contains(Acces.ADMIN)) {
				zamknij_dzial(id);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.BACKUP_WCZYT)) {
			if(acc.contains(Acces.ADMIN)) {
				wczyt_backup();
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.BACKUP_WYK)) {
			if(acc.contains(Acces.ADMIN)) {
				wyk_backup();
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		else if(proc.equals(Procedures.DYNAMICZNE)) {
			if(acc.contains(Acces.ADMIN)) {
				dynamiczne(args);
			}
			else {
				System.out.println("Brak odpowiednich uprawnien");
			}
		}
		return result;
	}
	private void dodaj_pracownika(ArrayList<String> x) {
		System.out.println("Uruchamiam procedure dodajaca pracownika");
		int id_prac = 0;
		String imie = x.get(0);
		String nazwisko = x.get(1);
		String login = x.get(2);
		String haslo = x.get(3);
		String sql = "SELECT COUNT (*) AS total FROM pracownik";
		try {
			stmt = conn.prepareStatement(sql);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				id_prac = rs3.getInt("total");
				id_prac = id_prac+1;
			}
			sql = "CALL  Dodaj_pracownika(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, imie);
			stmt.setString(2, nazwisko);
			rs3 = stmt.executeQuery();
			
			sql = "CALL Wygeneruj_login(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			stmt.setString(2, login);
			rs3 = stmt.executeQuery();
			
			sql = "CALL Wygeneruj_haslo(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			stmt.setString(2, haslo);
			rs3 = stmt.executeQuery();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	private void dodaj_do_zespolu(ArrayList<String> x , ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure dodajaca pracownika do zespolu");
		int id_zesp = y.get(1);
		int id_prac = Integer.parseInt(x.get(0));
		System.out.println(id_prac + " " + id_zesp);
		String sql = "CALL dodaj_do_zespolu(?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			stmt.setInt(2, id_prac);
			rs3 = stmt.executeQuery();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void liczba_godzin_pracownika(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca liczbe godzin");
		int id_prac = x.get(0);
		String sql = "CALL liczba_godzin_prac_w_zespolach(?)";
		String zesp;
		String godz;
		result = new ArrayList<String>();
		result.add("Zespol;Godziny");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				zesp = rs3.getString("zespoł");
				godz = rs3.getString("godziny");
				result.add(zesp+";"+godz);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void wynagrodzenie_pracownika(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca wynagrodzenie");
		int id_prac = x.get(0);
		String sql = "CALL wynagrodzenie_prac_w_zespolach(?)";
		String zesp;
		String wyn;
		result = new ArrayList<String>();
		result.add("Zespol;Wynagrodzenie");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				zesp = rs3.getString("zespoł");
				wyn = rs3.getString("wynagrodzenie");
				result.add(zesp+";"+wyn);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	private void zespoly_pracownika(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca zespoly pracownika");
		int id_prac = x.get(0);
		String sql = "CALL zespoly_pracownika(?)";
		String zesp;
		String nazwa;
		String status;
		result = new ArrayList<String>();
		result.add("ID;Nazwa;Status");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				zesp = rs3.getString("zespoł");
				nazwa = rs3.getString("nazwa_zespołu");
				status = rs3.getString("status");
				result.add(zesp+";"+nazwa+";"+status);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void projekty_pracownika(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca projekty pracownika");
		int id_prac = x.get(0);
		String sql = "CALL projekty_pracownika(?)";
		String projekt;
		String nazwa;
		String status;
		result = new ArrayList<String>();
		result.add("ID;Nazwa;Status");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				projekt = rs3.getString("id_projektu");
				nazwa = rs3.getString("nazwa_projektu");
				status = rs3.getString("status");
				result.add(projekt+";"+nazwa+";"+status);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void dane_pracownika(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca dane pracownika");
		int id_prac = x.get(0);
		String sql = "CALL Dane_pracownika(?)";
		String imie;
		String nazwisko;
		result = new ArrayList<String>();
		result.add("imie;nazwisko");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				imie = rs3.getString("imie");
				nazwisko = rs3.getString("nazwisko");
				result.add(imie+";"+nazwisko);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void historia_pracownika(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca historie pracownika");
		int id_prac = x.get(0);
		String sql = "CALL historia_pracownika(?)";
		String zespol;
		String status;
		String projekt;
		String statusp;
		result = new ArrayList<String>();
		result.add("Zespol;Status w zespole; Projekt; Status Projektu");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_prac);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				zespol = rs3.getString("nazwa_zespołu");
				status = rs3.getString("status");
				projekt = rs3.getString("nazwa_projektu");
				statusp = rs3.getString("status_projektu");
				result.add(zespol+";"+status+";"+projekt+";"+statusp);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void budzet_zespolu(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca budzet zespolu");
		int id_zesp = x.get(1);
		String sql = "CALL Budzet_zespolu(?)";
		String budzet;
		result = new ArrayList<String>();
		result.add("Budzet");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				budzet = rs3.getString("budżet");
				result.add(budzet);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void czlonkowie_zespolu(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca czlonkow zespolu");
		int id_zesp = x.get(1);
		String sql = "CALL czlonkowie_zespolu(?)";
		String id_prac;
		String imie;
		String nazwisko;
		result = new ArrayList<String>();
		result.add("ID;Imie;Nazwisko");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				id_prac = rs3.getString("id_pracownika");
				imie = rs3.getString("imie");
				nazwisko = rs3.getString("nazwisko");
				result.add(id_prac+";"+imie+";"+nazwisko);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void ustal_wynagrodzenie(ArrayList<String> y, ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure ustalajaca wynagrodzenie w zespole");
	}
	private void ustal_czas_pracy_czlonka(ArrayList<String> y, ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure ustalajaca czas pracy czlonka zespolu");
	}
	private void ustal_czas_pracy(ArrayList<String> y, ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure ustalajaca czas pracy zespolu");
		int id_zesp = x.get(1);
		int czas = Integer.parseInt(y.get(0));
		String sql = "CALL ustal_czas_pracy(?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			stmt.setInt(2, czas);
			rs3 = stmt.executeQuery();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
				
	}
	private void zatwierdz_wynagrodzenie(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure zatwierdzajaca wynagrodzenie w zespole");
	}
	private void zatwierdz_czas_pracy(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure zatwierdzajaca czas pracy w zespole");
	}
	private void usun_z_zespolu(ArrayList<String> x , ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure usuwajaca czlonka z zespolu");
		int id_zesp = y.get(1);
		int czlonek =Integer.parseInt(x.get(0));
		String sql = "CALL usun_z_zespolu(??)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			stmt.setInt(2, czlonek);
			rs3 = stmt.executeQuery();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	private void przenies_z_zespolu(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure przenoszaca czlonka z zespolu");
		int id_zesp = y.get(1);
		int czlonek = Integer.parseInt(x.get(0));
		String sql = "CALL przenies_z_zespolu(?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			stmt.setInt(2, czlonek);
			rs3 = stmt.executeQuery();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void projekty_zespolu(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca projekty zespolu");
		int id_zesp = x.get(1);
		String sql = "CALL projekty_zespolu(?)";
		result = new ArrayList<String>();
		String projekt;
		String status;
		String zespol;
		String zlecenie;
		String budzet;
		String nazwap;
		result.add("ID;status;zespol;zlecenie;budzet;nazwa projektu");
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_zesp);
			rs3 = stmt.executeQuery();
			while(rs3.next()) {
				projekt = rs3.getString("id_projektu");
				status = rs3.getString("status");
				zespol = rs3.getString("zespol");
				zlecenie = rs3.getString("zlecenie");
				budzet = rs3.getString("przydzielony_budżet");
				nazwap =rs3.getString("nazwa_projektu");
				result.add(projekt+";"+status+";"+zespol+";"+zlecenie+";"+budzet+";"+nazwap);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void dodaj_klienta(ArrayList<String>x) {
		System.out.println("Uruchamiam procedure dodajaca klienta");
	}
	private void dodaj_zlecenie(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure dodajaca zlecenie");
	}
	private void zakup_produkt(ArrayList<String> x) {
		System.out.println("Uruchamiam procedure zakupujaca nowy produkt");
	}
	private void utworz_projekt(ArrayList<String> x ,ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure tworzaca nowy projekt");
	}
	private void ustal_budzet_projektu(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure ustalajaca budzet projektu");
	}
	private void sprawdz_bilans(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure wyswietlajaca bilans dzialu");
	}
	private void zlecenie_info(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure wyswietlajaca informacje o zleceniu");
	}
	private void zmien_status(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure zmieniajaca status projektu");
	}
	private void zatwierdz_budzet(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure zatwierdzjaca budzet zespolow w dziale");
	}
	private void zm_zarzadcy_zespolu(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure zmieniajaca zarzadce w dziale");
	}
	private void utworz_zespol(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure tworzaca nowy zespol");
	}
	private void rozwiaz_zespol(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure rozwiazujaca zespol");
	}
	private void korekta_danych(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure poprawiajaca dane pracownika");
	}
	private void korekta_historii(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure poprawiajaca historie pracownika");
	}
	private void dodaj_dzial(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure dodajaca nowy dzial");
	}
	private void zwolnij_pracownika(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure zwalniajaca pracownika");
	}
	private void zm_zarzadcy_dzialu(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure zmieniajaca zarzadce dzialu");
	}
	private void przeniesienie_zlecenia(ArrayList<String> x, ArrayList<Integer> y) {
		System.out.println("Uruchamiam procedure przenoszaca zlecenie do innego dzialu");
	}
	private void zamknij_dzial(ArrayList<Integer> x) {
		System.out.println("Uruchamiam procedure zamykajaca dzial");
	}
	private void wczyt_backup() {
		System.out.println("Wczytuje backup bazy danych");
	}
	private void dynamiczne(ArrayList<String> arg) {
		System.out.println("Rozpoczynam budowe dynamicznego zapytania");
	}
	private void wyk_backup() {
		System.out.println("Wykonuje backup bazy danych");
	}
}
