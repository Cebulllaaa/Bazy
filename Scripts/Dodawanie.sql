DROP PROCEDURE IF EXISTS liczba_godzin_prac_w_zespolach;

DELIMITER $$

CREATE PROCEDURE liczba_godzin_prac_w_zespolach (IN prac int)
BEGIN
 SELECT grupy_zespołów.zespoł, grupy_zespołów.godziny FROM pracownik JOIN grupy_zespołów
  ON pracownik.id_pracownika = grupy_zespołów.pracownik
  WHERE pracownik.id_pracownika = prac AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny");
END$$

DELIMITER ;

DROP FUNCTION IF EXISTS liczba_godzin_prac_lacznie;

DELIMITER $$

CREATE FUNCTION liczba_godzin_prac_lacznie (prac int)
RETURNS int
DETERMINISTIC
BEGIN
 DECLARE suma_godzin int;
 SELECT godziny_tygodniowo FROM pracownik WHERE id_pracownika = prac INTO suma_godzin;

 RETURN suma_godzin;

END$$

DELIMITER ;

DROP FUNCTION IF EXISTS wynagrodzenie_prac_lacznie;

DELIMITER $$

CREATE FUNCTION wynagrodzenie_prac_lacznie (prac int)
RETURNS int
DETERMINISTIC
BEGIN
 DECLARE s int;
 SELECT wypłata FROM pracownik WHERE id_pracownika = prac INTO s;

 RETURN s;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS wynagrodzenie_prac_w_zespolach;

DELIMITER $$

CREATE PROCEDURE wynagrodzenie_prac_w_zespolach (IN prac int)
BEGIN
 SELECT grupy_zespołów.zespoł, grupy_zespołów.wynagrodzenie FROM pracownik JOIN grupy_zespołów
  ON pracownik.id_pracownika = grupy_zespołów.pracownik
  WHERE pracownik.id_pracownika = prac AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny");
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS zespoly_pracownika;

DELIMITER $$

CREATE PROCEDURE zespoly_pracownika (IN prac int)
BEGIN
 SELECT DISTINCT zespoł, nazwa_zespołu, status -- wyswietlam tez byle zespoly
  FROM grupy_zespołów JOIN zespoły ON id_zespołu = zespoł WHERE pracownik = prac;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS projekty_pracownika;

DELIMITER $$

CREATE PROCEDURE projekty_pracownika (IN prac int)
BEGIN
 SELECT DISTINCT projekty.id_projektu, projekty.nazwa_projektu , projekty.status -- wyswietlam tez byle projekty
  FROM grupy_zespołów JOIN zespoły ON id_zespołu = zespoł JOIN projekty ON projekty.zespol = zespoły.id_zespołu WHERE pracownik = prac;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS dane_pracownika;

DELIMITER $$

CREATE PROCEDURE dane_pracownika (IN prac int)
BEGIN
 SELECT imie, nazwisko, wypłata, godziny_tygodniowo FROM pracownik WHERE id_pracownika = prac;
-- nie wyswietla id, dla bezpieczenstwa
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS historia_pracownika;

DELIMITER $$

CREATE PROCEDURE historia_pracownika (IN prac int)
BEGIN
 SELECT DISTINCT zespoły.id_zespołu, zespoły.nazwa_zespołu, projekty.id_projektu, projekty.nazwa_projektu
  FROM (SELECT * FROM pracownik WHERE id_pracownika = prac) AS P JOIN grupy_zespołów ON P.id_pracownika = grupy_zespołów.pracownik
   JOIN zespoły ON zespoły.id_zespołu = grupy_zespołów.zespoł JOIN projekty ON projekty.zespol = zespoły.id_zespołu;
END$$

DELIMITER ;

DROP FUNCTION IF EXISTS budzet_zespolu;

DELIMITER $$

CREATE FUNCTION budzet_zespolu (zesp int)
RETURNS int
DETERMINISTIC
BEGIN
 RETURN (SELECT budżet FROM zespoły WHERE id_zespołu = zesp);
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS czlonkowe_zespolu;

DELIMITER $$

CREATE PROCEDURE czlonkowe_zespolu (IN zesp int)
BEGIN
 SELECT pracownik.id_pracownika, pracownik.imie, pracownik.nazwisko
  FROM (SELECT * FROM zespoły WHERE id_zespołu = zesp) AS Z JOIN grupy_zespołów ON Z.id_zespołu = grupy_zespołów.zespoł
   AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny")
   JOIN pracownik ON grupy_zespołów.pracownik = pracownik.id_pracownika;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS ustal_wynagrodzenie;

DELIMITER $$

CREATE PROCEDURE ustal_wynagrodzenie (IN zesp int, IN cz int, IN wyn int)
BEGIN
 UPDATE grupy_zespołów SET wynagrodzenie = wyn WHERE zespoł = zesp AND (status = "Obecny" OR status = "Zarzadca") AND pracownik = cz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS ustal_czas_pracy_czlonka;

DELIMITER $$

CREATE PROCEDURE ustal_czas_pracy_czlonka (IN zesp int, IN czas int, IN prac int)
BEGIN
 UPDATE grupy_zespołów SET godziny = czas WHERE zespoł = zesp AND (status = "Obecny" OR status = "Zarzadca") AND pracownik = prac;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS ustal_czas_pracy;

DELIMITER $$

CREATE PROCEDURE ustal_czas_pracy (IN zesp int, IN czas int)
BEGIN
 UPDATE zespoły SET godziny_tygodniowo = czas WHERE id_zespołu = zesp;
END$$

DELIMITER $$


