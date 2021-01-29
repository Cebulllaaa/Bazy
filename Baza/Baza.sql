-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               10.5.6-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Zrzut struktury bazy danych aplikacja_baza
DROP DATABASE IF EXISTS `aplikacja_baza`;
CREATE DATABASE IF NOT EXISTS `aplikacja_baza` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `aplikacja_baza`;

-- Zrzut struktury procedura aplikacja_baza.czlonkowe_zespolu
DROP PROCEDURE IF EXISTS `czlonkowe_zespolu`;
DELIMITER //
CREATE PROCEDURE `czlonkowe_zespolu`(IN zesp int)
BEGIN
 SELECT pracownik.id_pracownika, pracownik.imie, pracownik.nazwisko
  FROM (SELECT * FROM zespoły WHERE id_zespołu = zesp) AS Z JOIN grupy_zespołów ON Z.id_zespołu = grupy_zespołów.zespoł
   AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny")
   JOIN pracownik ON grupy_zespołów.pracownik = pracownik.id_pracownika;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Dodaj_dostawce
DROP PROCEDURE IF EXISTS `Dodaj_dostawce`;
DELIMITER //
CREATE PROCEDURE `Dodaj_dostawce`(
	IN `nazwa` VARCHAR(90)
)
BEGIN
INSERT INTO aplikacja_baza.dostawcy (dostawcy.nazwa_dostawcy)
VALUES (nazwa);
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.dodaj_do_zespolu
DROP PROCEDURE IF EXISTS `dodaj_do_zespolu`;
DELIMITER //
CREATE PROCEDURE `dodaj_do_zespolu`(in zesp int, IN cz int)
BEGIN
 DECLARE c int;
 SELECT COUNT(*) FROM grupy_zespołów WHERE zespoł = zesp AND pracownik = cz INTO c;

 IF c = 0 THEN
  INSERT INTO grupy_zespołów (zespoł, pracownik, status) VALUES (zesp, cz, "Obecny");
 ELSE
  UPDATE grupy_zespołów SET status = "Obecny" WHERE zespoł = zesp AND pracownik = cz;
 END IF;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Dodaj_dzial
DROP PROCEDURE IF EXISTS `Dodaj_dzial`;
DELIMITER //
CREATE PROCEDURE `Dodaj_dzial`(
	IN `prac` INT,
	IN `nazwa` VARCHAR(90)
)
BEGIN
INSERT INTO aplikacja_baza.działy (działy.`zarządca_działu`,działy.`nazwa_działu`)
VALUES (prac,nazwa);
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Dodaj_klienta
DROP PROCEDURE IF EXISTS `Dodaj_klienta`;
DELIMITER //
CREATE PROCEDURE `Dodaj_klienta`(
	IN `nazwa` VARCHAR(90)
)
BEGIN
INSERT INTO aplikacja_baza.klienci (klienci.nazwa_klienta)
VALUES (nazwa);
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Dodaj_pracownika
DROP PROCEDURE IF EXISTS `Dodaj_pracownika`;
DELIMITER //
CREATE PROCEDURE `Dodaj_pracownika`(
	IN `imie` VARCHAR(90),
	IN `nazwisko` VARCHAR(90)
)
BEGIN
INSERT INTO aplikacja_baza.pracownik (pracownik.imie, pracownik.nazwisko)
VALUES (imie,nazwisko);
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Dodaj_zakup
DROP PROCEDURE IF EXISTS `Dodaj_zakup`;
DELIMITER //
CREATE PROCEDURE `Dodaj_zakup`(
	IN `dz` INT,
	IN `dost` INT,
	IN `produkt` VARCHAR(90),
	IN `war` INT
)
BEGIN
INSERT INTO aplikacja_baza.zakupy (zakupy.`dział`,zakupy.dostawca,zakupy.produkt,zakupy.wartosc_zakupu)
VALUES (dz,dost,produkt,war);
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Dodaj_zlecenie
DROP PROCEDURE IF EXISTS `Dodaj_zlecenie`;
DELIMITER //
CREATE PROCEDURE `Dodaj_zlecenie`(
	IN `kl` INT,
	IN `wart` INT,
	IN `dz` INT
)
BEGIN
INSERT INTO aplikacja_baza.zlecenia (zlecenia.klient , zlecenia.wartosc_zlecenia , zlecenia.dzial)
VALUES (kl , wart , dz);
END//
DELIMITER ;

-- Zrzut struktury tabela aplikacja_baza.dostawcy
DROP TABLE IF EXISTS `dostawcy`;
CREATE TABLE IF NOT EXISTS `dostawcy` (
  `id_dostawcy` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa_dostawcy` varchar(90) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_dostawcy`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.dostawcy: ~1 rows (około)
DELETE FROM `dostawcy`;
/*!40000 ALTER TABLE `dostawcy` DISABLE KEYS */;
INSERT INTO `dostawcy` (`id_dostawcy`, `nazwa_dostawcy`) VALUES
	(1, 'Firma z narzedziami do projektowania baz danych');
/*!40000 ALTER TABLE `dostawcy` ENABLE KEYS */;

-- Zrzut struktury tabela aplikacja_baza.działy
DROP TABLE IF EXISTS `działy`;
CREATE TABLE IF NOT EXISTS `działy` (
  `id_działu` int(11) NOT NULL AUTO_INCREMENT,
  `zarządca_działu` int(90) NOT NULL DEFAULT 0,
  `nazwa_działu` varchar(90) NOT NULL DEFAULT 'Ogólny',
  PRIMARY KEY (`id_działu`),
  KEY `zarządca_działu` (`zarządca_działu`),
  CONSTRAINT `FK_działy_pracownik` FOREIGN KEY (`zarządca_działu`) REFERENCES `pracownik` (`id_pracownika`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.działy: ~1 rows (około)
DELETE FROM `działy`;
/*!40000 ALTER TABLE `działy` DISABLE KEYS */;
INSERT INTO `działy` (`id_działu`, `zarządca_działu`, `nazwa_działu`) VALUES
	(1, 1, 'Projektowania Baz Danych');
/*!40000 ALTER TABLE `działy` ENABLE KEYS */;

-- Zrzut struktury tabela aplikacja_baza.grupy_zespołów
DROP TABLE IF EXISTS `grupy_zespołów`;
CREATE TABLE IF NOT EXISTS `grupy_zespołów` (
  `zespoł` int(11) NOT NULL,
  `pracownik` int(11) NOT NULL,
  `status` varchar(90) NOT NULL DEFAULT '',
  `wynagrodzenie` int(11) NOT NULL DEFAULT 0,
  `godziny` int(11) NOT NULL DEFAULT 0,
  KEY `zespoł` (`zespoł`),
  KEY `pracownik` (`pracownik`),
  CONSTRAINT `FK_grupy_zespołów_pracownik` FOREIGN KEY (`pracownik`) REFERENCES `pracownik` (`id_pracownika`),
  CONSTRAINT `FK_grupy_zespołów_zespoły` FOREIGN KEY (`zespoł`) REFERENCES `zespoły` (`id_zespołu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.grupy_zespołów: ~2 rows (około)
DELETE FROM `grupy_zespołów`;
/*!40000 ALTER TABLE `grupy_zespołów` DISABLE KEYS */;
INSERT INTO `grupy_zespołów` (`zespoł`, `pracownik`, `status`, `wynagrodzenie`, `godziny`) VALUES
	(5, 3, 'Zarzadca', 0, 0),
	(6, 4, 'Byly pracownik', 0, 0);
/*!40000 ALTER TABLE `grupy_zespołów` ENABLE KEYS */;

-- Zrzut struktury tabela aplikacja_baza.hasla
DROP TABLE IF EXISTS `hasla`;
CREATE TABLE IF NOT EXISTS `hasla` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `hash_hasla` varchar(90) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pracownika`),
  CONSTRAINT `FK_hasla_pracownik` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.hasla: ~12 rows (około)
DELETE FROM `hasla`;
/*!40000 ALTER TABLE `hasla` DISABLE KEYS */;
INSERT INTO `hasla` (`id_pracownika`, `hash_hasla`) VALUES
	(1, '202cb962ac59075b964b07152d234b70'),
	(2, '202cb962ac59075b964b07152d234b70'),
	(3, '202cb962ac59075b964b07152d234b70'),
	(5, 'caf1a3dfb505ffed0d024130f58c5cfa'),
	(6, '202cb962ac59075b964b07152d234b70'),
	(7, '2b98e70a63b3dcdb1cc6e03d177a7784'),
	(8, 'e2fc714c4727ee9395f324cd2e7f331f'),
	(9, '7217225695fde8e615298b604e0c9c41'),
	(10, '8ed61f18a79c12b81f679f75caa36fb5'),
	(11, '0fe2f9896b9a287b112ec4d326d07f12'),
	(12, 'c18f09f0e800d5af12e222c3e6fe4da1'),
	(13, 'd8578edf8458ce06fbc5bb76a58c5ca4');
/*!40000 ALTER TABLE `hasla` ENABLE KEYS */;

-- Zrzut struktury tabela aplikacja_baza.klienci
DROP TABLE IF EXISTS `klienci`;
CREATE TABLE IF NOT EXISTS `klienci` (
  `id_klienta` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa_klienta` varchar(90) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_klienta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.klienci: ~1 rows (około)
DELETE FROM `klienci`;
/*!40000 ALTER TABLE `klienci` DISABLE KEYS */;
INSERT INTO `klienci` (`id_klienta`, `nazwa_klienta`) VALUES
	(1, 'Pierwszy klient');
/*!40000 ALTER TABLE `klienci` ENABLE KEYS */;

-- Zrzut struktury procedura aplikacja_baza.korekta_danych
DROP PROCEDURE IF EXISTS `korekta_danych`;
DELIMITER //
CREATE PROCEDURE `korekta_danych`(IN prac int, IN atr varchar(90), IN nowy varchar(90))
BEGIN
 PREPARE stmt FROM CONCAT('UPDATE pracownik SET ', atr, ' = ? WHERE id_pracownika = ?');
 EXECUTE stmt USING nowy, prac;
 DEALLOCATE PREPARE stmt;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.korekta_historii
DROP PROCEDURE IF EXISTS `korekta_historii`;
DELIMITER //
CREATE PROCEDURE `korekta_historii`(IN prac int, IN zesp int, IN atr varchar(90), IN nowy varchar(90))
BEGIN

 IF atr = 'DELETE' THEN
  DELETE FROM grupy_zespołów WHERE pracownik = prac AND zespoł = zesp;
 ELSE
 BEGIN

  PREPARE st FROM CONCAT('UPDATE grupy_zespołów SET ', atr, ' = ? WHERE pracownik = ? AND zespoł = ?'); -- nie dziala - polskie znaki
  EXECUTE st USING nowy, prac, zesp;
  DEALLOCATE PREPARE st;

 END;
 END IF;

END//
DELIMITER ;

-- Zrzut struktury funkcja aplikacja_baza.liczba_godzin_prac_lacznie
DROP FUNCTION IF EXISTS `liczba_godzin_prac_lacznie`;
DELIMITER //
CREATE FUNCTION `liczba_godzin_prac_lacznie`(prac int) RETURNS int(11)
    DETERMINISTIC
BEGIN
 DECLARE suma_godzin int;
 SELECT godziny_tygodniowo FROM pracownik WHERE id_pracownika = prac INTO suma_godzin;

 RETURN suma_godzin;

END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.liczba_godzin_prac_w_zespolach
DROP PROCEDURE IF EXISTS `liczba_godzin_prac_w_zespolach`;
DELIMITER //
CREATE PROCEDURE `liczba_godzin_prac_w_zespolach`(IN prac int)
BEGIN
 SELECT grupy_zespołów.zespoł, grupy_zespołów.godziny FROM pracownik JOIN grupy_zespołów
  ON pracownik.id_pracownika = grupy_zespołów.pracownik
  WHERE pracownik.id_pracownika = prac AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny");
END//
DELIMITER ;

-- Zrzut struktury tabela aplikacja_baza.loginy
DROP TABLE IF EXISTS `loginy`;
CREATE TABLE IF NOT EXISTS `loginy` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(90) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pracownika`),
  CONSTRAINT `FK_loginy_pracownik` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.loginy: ~12 rows (około)
DELETE FROM `loginy`;
/*!40000 ALTER TABLE `loginy` DISABLE KEYS */;
INSERT INTO `loginy` (`id_pracownika`, `login`) VALUES
	(1, 'Cebula'),
	(2, 'Ktos'),
	(3, 'Zespol A'),
	(5, 'Czolg'),
	(6, 'Omega'),
	(7, 'Alfa'),
	(8, 'Trybik'),
	(9, 'Zegar'),
	(10, 'Wrona'),
	(11, 'Pruk'),
	(12, 'Gawron'),
	(13, 'Zeus');
/*!40000 ALTER TABLE `loginy` ENABLE KEYS */;

-- Zrzut struktury widok aplikacja_baza.pracownicy
DROP VIEW IF EXISTS `pracownicy`;
-- Tworzenie tymczasowej tabeli, aby przezwyciężyć błędy z zależnościami w WIDOKU
CREATE TABLE `pracownicy` (
	`id_pracownika` INT(11) NOT NULL,
	`imie` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci',
	`nazwisko` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci',
	`login` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci',
	`hash_hasla` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Zrzut struktury tabela aplikacja_baza.pracownik
DROP TABLE IF EXISTS `pracownik`;
CREATE TABLE IF NOT EXISTS `pracownik` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(90) NOT NULL,
  `nazwisko` varchar(90) NOT NULL,
  `wypłata` int(11) NOT NULL DEFAULT 0,
  `godziny_tygodniowo` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_pracownika`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.pracownik: ~13 rows (około)
DELETE FROM `pracownik`;
/*!40000 ALTER TABLE `pracownik` DISABLE KEYS */;
INSERT INTO `pracownik` (`id_pracownika`, `imie`, `nazwisko`, `wypłata`, `godziny_tygodniowo`) VALUES
	(1, 'Bartosz', 'Cybulski', 0, 0),
	(2, 'Kazimierz', 'Jan', 0, 0),
	(3, 'Kazimierz', 'Jan', 0, 0),
	(4, 'Janusz', 'Polak', 0, 0),
	(5, 'Andrzej', 'Tomasz', 0, 0),
	(6, 'Janusz', 'Tracz', 0, 0),
	(7, 'Komisarz', 'Ryba', 0, 0),
	(8, 'Elzbieta', 'Ryba', 0, 0),
	(9, 'Halina', 'Fredrika', 0, 0),
	(10, 'Bogdan', 'Bogdarenko', 0, 0),
	(11, 'Ireneusz', 'Bogdarenko', 0, 0),
	(12, 'Gilber', 'Bogdarenko', 0, 0),
	(13, 'Jacek', 'Jacak', 0, 0);
/*!40000 ALTER TABLE `pracownik` ENABLE KEYS */;

-- Zrzut struktury tabela aplikacja_baza.projekty
DROP TABLE IF EXISTS `projekty`;
CREATE TABLE IF NOT EXISTS `projekty` (
  `id_projektu` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(90) NOT NULL,
  `zespol` int(11) NOT NULL DEFAULT 0,
  `zlecenie` int(11) NOT NULL DEFAULT 0,
  `przydzielony_budżet` int(11) NOT NULL DEFAULT 0,
  `nazwa_projektu` varchar(90) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_projektu`),
  KEY `zespol` (`zespol`),
  KEY `zlecenie` (`zlecenie`),
  CONSTRAINT `FK_projekty_zespoły` FOREIGN KEY (`zespol`) REFERENCES `zespoły` (`id_zespołu`),
  CONSTRAINT `FK_projekty_zlecenia` FOREIGN KEY (`zlecenie`) REFERENCES `zlecenia` (`id_zlecenia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.projekty: ~2 rows (około)
DELETE FROM `projekty`;
/*!40000 ALTER TABLE `projekty` DISABLE KEYS */;
INSERT INTO `projekty` (`id_projektu`, `status`, `zespol`, `zlecenie`, `przydzielony_budżet`, `nazwa_projektu`) VALUES
	(2, 'Realizowany', 5, 1, 10000, 'Pierwszy projekt'),
	(3, 'Porzucony', 6, 1, 5000, 'Projekt Janusza');
/*!40000 ALTER TABLE `projekty` ENABLE KEYS */;

-- Zrzut struktury procedura aplikacja_baza.projekty_pracownika
DROP PROCEDURE IF EXISTS `projekty_pracownika`;
DELIMITER //
CREATE PROCEDURE `projekty_pracownika`(IN prac int)
BEGIN
 SELECT DISTINCT projekty.id_projektu, projekty.nazwa_projektu , projekty.status -- wyswietlam tez byle projekty
  FROM grupy_zespołów JOIN zespoły ON id_zespołu = zespoł JOIN projekty ON projekty.zespol = zespoły.id_zespołu WHERE pracownik = prac;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.projekty_zespolu
DROP PROCEDURE IF EXISTS `projekty_zespolu`;
DELIMITER //
CREATE PROCEDURE `projekty_zespolu`(IN zesp int)
BEGIN
 SELECT projekty.* FROM projekty JOIN zespoły ON projekty.zespol = zespoły.id_zespołu WHERE zespoły.id_zespołu = zesp;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.przeniesienie_zlecenia
DROP PROCEDURE IF EXISTS `przeniesienie_zlecenia`;
DELIMITER //
CREATE PROCEDURE `przeniesienie_zlecenia`(IN zl int, IN dz int)
BEGIN
 UPDATE projekty SET status = 'Zamkniety', przydzielony_budżet = 0 WHERE zlecenie = zl;
 UPDATE zlecenia SET dzial = dz WHERE id_zlecenia = zl;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.przenies_z_zespolu
DROP PROCEDURE IF EXISTS `przenies_z_zespolu`;
DELIMITER //
CREATE PROCEDURE `przenies_z_zespolu`(IN zesp int, IN cz int)
BEGIN
 UPDATE grupy_zespołów SET status = "Przeniesiony" WHERE zespoł = zesp AND pracownik = cz;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.rozwiaz_zespol
DROP PROCEDURE IF EXISTS `rozwiaz_zespol`;
DELIMITER //
CREATE PROCEDURE `rozwiaz_zespol`(IN zesp int, IN dz int)
BEGIN

 DECLARE zesp_dz int;
 SELECT dział FROM zespoły WHERE id_zespołu = zesp INTO zesp_dz;

 IF zesp_dz = dz THEN
 BEGIN
  UPDATE projekty SET status = 'Porzucony' WHERE zespol = zesp;
  UPDATE grupy_zespołów SET status = 'Przeniesiony' WHERE zespoł = zesp
   AND (status = 'Zarzadca' OR status = 'Obecny' OR status = 'Byly zarzadca');
 END;
 END IF;

END//
DELIMITER ;

-- Zrzut struktury funkcja aplikacja_baza.sprawdz_bilans_budzetow
DROP FUNCTION IF EXISTS `sprawdz_bilans_budzetow`;
DELIMITER //
CREATE FUNCTION `sprawdz_bilans_budzetow`(dz int) RETURNS int(11)
    DETERMINISTIC
BEGIN
 DECLARE wartosci int;
 DECLARE budzety int;

 SELECT SUM(wartosc_zlecenia) FROM zlecenia WHERE dzial = dz INTO wartosci;
 SELECT SUM(budżet) FROM zespoły WHERE dział = dz INTO budzety;

 RETURN wartosci - budzety;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.sprawdz_bilans_projektow
DROP PROCEDURE IF EXISTS `sprawdz_bilans_projektow`;
DELIMITER //
CREATE PROCEDURE `sprawdz_bilans_projektow`(IN dz int)
BEGIN
 SELECT id_projektu, przydzielony_budżet, zespol FROM projekty JOIN zespoły ON id_zespołu = zespol WHERE dział = dz;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.sprawdz_bilans_zespolow
DROP PROCEDURE IF EXISTS `sprawdz_bilans_zespolow`;
DELIMITER //
CREATE PROCEDURE `sprawdz_bilans_zespolow`(IN dz int)
BEGIN
 SELECT id_zespołu, budżet FROM zespoły WHERE dział = dz;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.sprawdz_bilans_zlecen
DROP PROCEDURE IF EXISTS `sprawdz_bilans_zlecen`;
DELIMITER //
CREATE PROCEDURE `sprawdz_bilans_zlecen`(IN dz int)
BEGIN
 SELECT id_zlecenia, wartosc_zlecenia FROM zlecenia WHERE dzial = dz;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Ustal_budżet_projektu
DROP PROCEDURE IF EXISTS `Ustal_budżet_projektu`;
DELIMITER //
CREATE PROCEDURE `Ustal_budżet_projektu`(
	IN `projekt` INT,
	IN `budzet` INT,
	IN `dzial` INT
)
BEGIN
SET @dz = (SELECT zlecenia.dzial FROM zlecenia INNER JOIN projekty ON projekty.zlecenie = zlecenia.id_zlecenia WHERE 
projekty.id_projektu = projekt);
IF dzial = @dz THEN
	UPDATE projekty SET projekty.`przydzielony_budżet` = budzet WHERE projekty.id_projektu = projekt ;
	END IF;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.ustal_czas_pracy
DROP PROCEDURE IF EXISTS `ustal_czas_pracy`;
DELIMITER //
CREATE PROCEDURE `ustal_czas_pracy`(IN zesp int, IN czas int)
BEGIN
 UPDATE zespoły SET godziny_tygodniowo = czas WHERE id_zespołu = zesp;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.usun_z_zespolu
DROP PROCEDURE IF EXISTS `usun_z_zespolu`;
DELIMITER //
CREATE PROCEDURE `usun_z_zespolu`(IN zesp int, IN cz int)
BEGIN
 UPDATE grupy_zespołów SET status = "Usuniety" WHERE zespoł = zesp AND pracownik = cz;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Utworz_projekt
DROP PROCEDURE IF EXISTS `Utworz_projekt`;
DELIMITER //
CREATE PROCEDURE `Utworz_projekt`(
	IN `zesp` INT,
	IN `zl` INT,
	IN `nazwa` VARCHAR(90)
)
BEGIN
INSERT INTO aplikacja_baza.projekty (projekty.`status` , projekty.zespol , projekty.zlecenie , projekty.nazwa_projektu)
VALUES ("Realizowany",zesp,zl,nazwa);
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Utworz_zespol
DROP PROCEDURE IF EXISTS `Utworz_zespol`;
DELIMITER //
CREATE PROCEDURE `Utworz_zespol`(
	IN `cz` INT,
	IN `nazwa` VARCHAR(90),
	IN `dz` INT
)
BEGIN
INSERT INTO aplikacja_baza.`zespoły` (zespoły.`zarządca_zespołu`,zespoły.`nazwa_zespołu`,zespoły.`dział`)
VALUES (cz,nazwa,dz);
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Wygeneruj_haslo
DROP PROCEDURE IF EXISTS `Wygeneruj_haslo`;
DELIMITER //
CREATE PROCEDURE `Wygeneruj_haslo`(
	IN `prac` INT,
	IN `haslo` VARCHAR(90)
)
BEGIN
INSERT INTO aplikacja_baza.hasla (hasla.id_pracownika,hasla.hash_hasla)
VALUES (prac,MD5(haslo));
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.Wygeneruj_login
DROP PROCEDURE IF EXISTS `Wygeneruj_login`;
DELIMITER //
CREATE PROCEDURE `Wygeneruj_login`(
	IN `id_prac` INT,
	IN `login` VARCHAR(90)
)
BEGIN
INSERT INTO aplikacja_baza.loginy (loginy.id_pracownika,loginy.login)
VALUES (id_prac, login);
END//
DELIMITER ;

-- Zrzut struktury funkcja aplikacja_baza.wynagrodzenie_prac_lacznie
DROP FUNCTION IF EXISTS `wynagrodzenie_prac_lacznie`;
DELIMITER //
CREATE FUNCTION `wynagrodzenie_prac_lacznie`(prac int) RETURNS int(11)
    DETERMINISTIC
BEGIN
 DECLARE s int;
 SELECT wypłata FROM pracownik WHERE id_pracownika = prac INTO s;

 RETURN s;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.wynagrodzenie_prac_w_zespolach
DROP PROCEDURE IF EXISTS `wynagrodzenie_prac_w_zespolach`;
DELIMITER //
CREATE PROCEDURE `wynagrodzenie_prac_w_zespolach`(IN prac int)
BEGIN
 SELECT grupy_zespołów.zespoł, grupy_zespołów.wynagrodzenie FROM pracownik JOIN grupy_zespołów
  ON pracownik.id_pracownika = grupy_zespołów.pracownik
  WHERE pracownik.id_pracownika = prac AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny");
END//
DELIMITER ;

-- Zrzut struktury tabela aplikacja_baza.zakupy
DROP TABLE IF EXISTS `zakupy`;
CREATE TABLE IF NOT EXISTS `zakupy` (
  `id_zakupu` int(11) NOT NULL AUTO_INCREMENT,
  `dział` int(11) NOT NULL,
  `dostawca` int(11) NOT NULL,
  `produkt` varchar(90) NOT NULL DEFAULT '',
  `wartosc_zakupu` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_zakupu`),
  KEY `dział` (`dział`),
  KEY `dostawca` (`dostawca`),
  CONSTRAINT `FK_zakupy_dostawcy` FOREIGN KEY (`dostawca`) REFERENCES `dostawcy` (`id_dostawcy`),
  CONSTRAINT `FK_zakupy_działy` FOREIGN KEY (`dział`) REFERENCES `działy` (`id_działu`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.zakupy: ~1 rows (około)
DELETE FROM `zakupy`;
/*!40000 ALTER TABLE `zakupy` DISABLE KEYS */;
INSERT INTO `zakupy` (`id_zakupu`, `dział`, `dostawca`, `produkt`, `wartosc_zakupu`) VALUES
	(1, 1, 1, 'Najnowsze narzedzie do projektowania baz', 50000);
/*!40000 ALTER TABLE `zakupy` ENABLE KEYS */;

-- Zrzut struktury widok aplikacja_baza.zarzadcy_zespolow
DROP VIEW IF EXISTS `zarzadcy_zespolow`;
-- Tworzenie tymczasowej tabeli, aby przezwyciężyć błędy z zależnościami w WIDOKU
CREATE TABLE `zarzadcy_zespolow` (
	`id_pracownika` INT(11) NOT NULL,
	`imie` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci',
	`nazwisko` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci',
	`nazwa_zespołu` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Zrzut struktury widok aplikacja_baza.zarządcy_działów
DROP VIEW IF EXISTS `zarządcy_działów`;
-- Tworzenie tymczasowej tabeli, aby przezwyciężyć błędy z zależnościami w WIDOKU
CREATE TABLE `zarządcy_działów` (
	`id_pracownika` INT(11) NOT NULL,
	`imie` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci',
	`nazwisko` VARCHAR(90) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Zrzut struktury procedura aplikacja_baza.zatwierdz_budzet
DROP PROCEDURE IF EXISTS `zatwierdz_budzet`;
DELIMITER //
CREATE PROCEDURE `zatwierdz_budzet`(IN dz int)
BEGIN
 DECLARE koniec int DEFAULT FALSE;
 DECLARE zesp int;
 DECLARE ile int;

 DECLARE budzety CURSOR FOR SELECT id_zespołu, SUM(przydzielony_budżet) AS kwota FROM projekty JOIN zespoły ON id_zespołu = zespol GROUP BY id_zespołu;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET koniec = TRUE;

 OPEN budzety;

 WHILE NOT koniec DO

  FETCH budzety INTO zesp, ile;
  UPDATE zespoły SET budżet = ile WHERE id_zespołu = zesp;

 END WHILE;

 CLOSE budzety;

END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.zatwierdz_czas_pracy
DROP PROCEDURE IF EXISTS `zatwierdz_czas_pracy`;
DELIMITER //
CREATE PROCEDURE `zatwierdz_czas_pracy`(IN zesp int)
BEGIN
 DECLARE pozostalo int;
 DECLARE dodawanie int;
 DECLARE id_prac int;
 DECLARE i int DEFAULT 0;
 DECLARE ok boolean DEFAULT TRUE;
 DECLARE juz boolean DEFAULT FALSE;

 DECLARE pracownicy CURSOR FOR SELECT pracownik, godziny FROM grupy_zespołów
  WHERE (status = "Zarzadca" OR status = "Obecny" OR status = "Byly zarzadca") AND zespoł = zesp;

 DECLARE CONTINUE HANDLER FOR NOT FOUND SET juz = TRUE;

 SELECT zespoły.godziny_tygodniowo FROM zespoły WHERE id_zespołu = zesp INTO pozostalo;

 OPEN pracownicy;
 FETCH pracownicy INTO i, dodawanie;

 START TRANSACTION;

planowanie: WHILE NOT juz DO
 BEGIN
  SET pozostalo = pozostalo - dodawanie;

  IF pozostalo < 0 THEN
  BEGIN

   ROLLBACK;
   SET ok = FALSE;
   LEAVE planowanie;

  END;
  END IF;

  UPDATE pracownik SET godziny_tygodniowo = godziny_tygodniowo + dodawanie WHERE id_pracownika = i;

  FETCH pracownicy INTO i, dodawanie;
 END;
 END WHILE;

 IF ok THEN
  COMMIT;
 END IF;

 CLOSE pracownicy;

END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.zatwierdz_wynagrodzenie
DROP PROCEDURE IF EXISTS `zatwierdz_wynagrodzenie`;
DELIMITER //
CREATE PROCEDURE `zatwierdz_wynagrodzenie`(IN zesp int)
BEGIN
 DECLARE pozostalo int;
 DECLARE dodawanie int;
 DECLARE id_prac int;
 DECLARE i int DEFAULT 0;
 DECLARE ok boolean DEFAULT TRUE;
 DECLARE juz boolean DEFAULT FALSE;

 DECLARE pracownicy CURSOR FOR SELECT pracownik, wynagrodzenie FROM grupy_zespołów
  WHERE (status = "Zarzadca" OR status = "Obecny" OR status = "Byly zarzadca") AND zespoł = zesp;

 DECLARE CONTINUE HANDLER FOR NOT FOUND SET juz = TRUE;

 SELECT zespoły.budżet FROM zespoły WHERE id_zespołu = zesp INTO pozostalo;

 OPEN pracownicy;
 FETCH pracownicy INTO i, dodawanie;

 START TRANSACTION;

wyplacanie: WHILE NOT juz DO
 BEGIN
  SET pozostalo = pozostalo - dodawanie;

  IF pozostalo < 0 THEN
  BEGIN

   ROLLBACK;
   SET ok = FALSE;
   LEAVE wyplacanie;

  END;
  END IF;

  UPDATE pracownik SET wypłata = wypłata + dodawanie WHERE id_pracownika = i;

  FETCH pracownicy INTO i, dodawanie;
 END;
 END WHILE;

 IF ok THEN
  COMMIT;
 END IF;

 CLOSE pracownicy;

END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.zespoly_pracownika
DROP PROCEDURE IF EXISTS `zespoly_pracownika`;
DELIMITER //
CREATE PROCEDURE `zespoly_pracownika`(IN prac int)
BEGIN
 SELECT DISTINCT zespoł, nazwa_zespołu, status -- wyswietlam tez byle zespoly
  FROM grupy_zespołów JOIN zespoły ON id_zespołu = zespoł WHERE pracownik = prac;
END//
DELIMITER ;

-- Zrzut struktury tabela aplikacja_baza.zespoły
DROP TABLE IF EXISTS `zespoły`;
CREATE TABLE IF NOT EXISTS `zespoły` (
  `id_zespołu` int(11) NOT NULL AUTO_INCREMENT,
  `zarządca_zespołu` int(11) NOT NULL,
  `nazwa_zespołu` varchar(90) NOT NULL DEFAULT '',
  `dział` int(11) NOT NULL DEFAULT 1,
  `budżet` int(11) NOT NULL DEFAULT 0,
  `godziny_tygodniowo` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_zespołu`),
  KEY `zarządca_zespołu` (`zarządca_zespołu`),
  KEY `dział` (`dział`),
  CONSTRAINT `FK_zespoły_działy` FOREIGN KEY (`dział`) REFERENCES `działy` (`id_działu`),
  CONSTRAINT `FK_zespoły_pracownik` FOREIGN KEY (`zarządca_zespołu`) REFERENCES `pracownik` (`id_pracownika`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.zespoły: ~2 rows (około)
DELETE FROM `zespoły`;
/*!40000 ALTER TABLE `zespoły` DISABLE KEYS */;
INSERT INTO `zespoły` (`id_zespołu`, `zarządca_zespołu`, `nazwa_zespołu`, `dział`, `budżet`, `godziny_tygodniowo`) VALUES
	(5, 3, 'Zespol A', 1, 0, 0),
	(6, 4, 'Zespol B', 1, 0, 0);
/*!40000 ALTER TABLE `zespoły` ENABLE KEYS */;

-- Zrzut struktury tabela aplikacja_baza.zlecenia
DROP TABLE IF EXISTS `zlecenia`;
CREATE TABLE IF NOT EXISTS `zlecenia` (
  `id_zlecenia` int(11) NOT NULL AUTO_INCREMENT,
  `klient` int(11) NOT NULL,
  `wartosc_zlecenia` int(11) NOT NULL,
  `dzial` int(11) NOT NULL,
  PRIMARY KEY (`id_zlecenia`),
  KEY `klient` (`klient`),
  KEY `dzial` (`dzial`),
  CONSTRAINT `FK_zlecenia_działy` FOREIGN KEY (`dzial`) REFERENCES `działy` (`id_działu`),
  CONSTRAINT `FK_zlecenia_klienci` FOREIGN KEY (`klient`) REFERENCES `klienci` (`id_klienta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli aplikacja_baza.zlecenia: ~1 rows (około)
DELETE FROM `zlecenia`;
/*!40000 ALTER TABLE `zlecenia` DISABLE KEYS */;
INSERT INTO `zlecenia` (`id_zlecenia`, `klient`, `wartosc_zlecenia`, `dzial`) VALUES
	(1, 1, 20000, 1);
/*!40000 ALTER TABLE `zlecenia` ENABLE KEYS */;

-- Zrzut struktury procedura aplikacja_baza.Zlecenia_info
DROP PROCEDURE IF EXISTS `Zlecenia_info`;
DELIMITER //
CREATE PROCEDURE `Zlecenia_info`(
	IN `zlecenie` INT,
	IN `dz` INT
)
BEGIN
SELECT klienci.nazwa_klienta AS "Klient" , zlecenia.wartosc_zlecenia AS "Calkowita Wartosc", projekty.nazwa_projektu AS "projekty",
	projekty.`przydzielony_budżet`AS "Budzet na projekt" , projekty.`status` AS "Status", zespoły.`nazwa_zespołu` AS "zespol"
FROM zlecenia INNER JOIN projekty ON projekty.zlecenie = zlecenia.id_zlecenia INNER JOIN klienci 
	ON klienci.id_klienta = zlecenia.klient INNER JOIN zespoły ON zespoły.`id_zespołu` = projekty.zespol
WHERE zlecenia.id_zlecenia = zlecenie AND zlecenia.dzial = dz ;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.zmien_status
DROP PROCEDURE IF EXISTS `zmien_status`;
DELIMITER //
CREATE PROCEDURE `zmien_status`(IN pr int, IN st varchar(90), IN dz int)
BEGIN
 UPDATE projekty JOIN zespoły ON id_zespołu = zespol SET status = st WHERE id_projektu = pr AND dział = dz;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.ZM_zarzadcy_dzialu
DROP PROCEDURE IF EXISTS `ZM_zarzadcy_dzialu`;
DELIMITER //
CREATE PROCEDURE `ZM_zarzadcy_dzialu`(IN dz int, IN prac int)
BEGIN
 UPDATE działy SET zarządca_działu = prac WHERE id_działu = dz;
END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.ZM_zarzadcy_zespolu
DROP PROCEDURE IF EXISTS `ZM_zarzadcy_zespolu`;
DELIMITER //
CREATE PROCEDURE `ZM_zarzadcy_zespolu`(IN zesp int, IN cz int, IN dz int)
BEGIN
 DECLARE dz_zesp int;

 SELECT dział FROM zespoły WHERE id_zespołu = zesp INTO dz_zesp;

 IF dz_zesp = dz THEN
 BEGIN
  UPDATE grupy_zespołów SET status = 'Byly zarzadca' WHERE zespoł = zesp AND status = 'Zarzadca';
  UPDATE grupy_zespołów SET status = 'Zarzadca' WHERE zespoł = zesp AND pracownik = cz;
 END;
 END IF;

END//
DELIMITER ;

-- Zrzut struktury procedura aplikacja_baza.zwolnij_pracownika
DROP PROCEDURE IF EXISTS `zwolnij_pracownika`;
DELIMITER //
CREATE PROCEDURE `zwolnij_pracownika`(IN prac int)
BEGIN

 DELETE FROM loginy WHERE id_pracownika = prac;
 DELETE FROM hasla WHERE id_pracownika = prac;

 UPDATE grupy_zespołów SET status = 'Byly pracownik' WHERE pracownik = prac;

END//
DELIMITER ;

-- Zrzut struktury wyzwalacz aplikacja_baza.zespoły_after_insert
DROP TRIGGER IF EXISTS `zespoły_after_insert`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `zespoły_after_insert` AFTER INSERT ON `zespoły` FOR EACH ROW BEGIN
SET @zesp = (SELECT zespoły.id_zespołu FROM zespoły ORDER BY zespoły.id_zespołu DESC LIMIT 1);
SET @prac = (SELECT zespoły.zarządca_zespołu FROM zespoły ORDER BY zespoły.id_zespołu DESC LIMIT 1);
INSERT INTO grupy_zespołów( grupy_zespołów.`zespoł`,grupy_zespołów.pracownik,grupy_zespołów.`status`)
VALUES(@zesp, @prac, "Zarzadca");
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury widok aplikacja_baza.pracownicy
DROP VIEW IF EXISTS `pracownicy`;
-- Usuwanie tabeli tymczasowej i tworzenie ostatecznej struktury WIDOKU
DROP TABLE IF EXISTS `pracownicy`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `pracownicy` AS SELECT pracownik.id_pracownika , pracownik.imie , pracownik.nazwisko, loginy.login , hasla.hash_hasla 
FROM pracownik INNER JOIN hasla ON pracownik.id_pracownika = hasla.id_pracownika INNER JOIN loginy ON
loginy.id_pracownika = pracownik.id_pracownika ;

-- Zrzut struktury widok aplikacja_baza.zarzadcy_zespolow
DROP VIEW IF EXISTS `zarzadcy_zespolow`;
-- Usuwanie tabeli tymczasowej i tworzenie ostatecznej struktury WIDOKU
DROP TABLE IF EXISTS `zarzadcy_zespolow`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `zarzadcy_zespolow` AS SELECT pracownik.id_pracownika ,pracownik.imie , pracownik.nazwisko , zespoły.`nazwa_zespołu` 
FROM pracownik INNER JOIN zespoły ON zespoły.`zarządca_zespołu` = pracownik.id_pracownika ;

-- Zrzut struktury widok aplikacja_baza.zarządcy_działów
DROP VIEW IF EXISTS `zarządcy_działów`;
-- Usuwanie tabeli tymczasowej i tworzenie ostatecznej struktury WIDOKU
DROP TABLE IF EXISTS `zarządcy_działów`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `zarządcy_działów` AS select `pracownik`.`id_pracownika` AS `id_pracownika`,`pracownik`.`imie` AS `imie`,`pracownik`.`nazwisko` AS `nazwisko` from (`pracownik` join `działy` on(`działy`.`zarządca_działu` = `pracownik`.`id_pracownika`));

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
