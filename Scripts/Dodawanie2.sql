DROP PROCEDURE IF EXISTS dodaj_do_zespolu;

DELIMITER $$

CREATE PROCEDURE dodaj_do_zespolu (in zesp int, IN cz int)
BEGIN
 DECLARE c int;
 SELECT COUNT(*) FROM grupy_zespołów WHERE zespoł = zesp AND pracownik = cz INTO c;

 IF c = 0 THEN
  INSERT INTO grupy_zespołów (zespoł, pracownik, status) VALUES (zesp, cz, "Obecny");
 ELSE
  UPDATE grupy_zespołów SET status = "Obecny" WHERE zespoł = zesp AND pracownik = cz;
 END IF;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS usun_z_zespolu;

DELIMITER $$

CREATE PROCEDURE usun_z_zespolu (IN zesp int, IN cz int)
BEGIN
 UPDATE grupy_zespołów SET status = "Usuniety" WHERE zespoł = zesp AND pracownik = cz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS przenies_z_zespolu;

DELIMITER $$

CREATE PROCEDURE przenies_z_zespolu (IN zesp int, IN cz int)
BEGIN
 UPDATE grupy_zespołów SET status = "Przeniesiony" WHERE zespoł = zesp AND pracownik = cz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS projekty_zespolu;

DELIMITER $$

CREATE PROCEDURE projekty_zespolu (IN zesp int)
BEGIN
 SELECT projekty.* FROM projekty JOIN zespoły ON projekty.zespol = zespoły.id_zespołu WHERE zespoły.id_zespołu = zesp;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS sprawdz_bilans;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans (IN dz int) -- mozliwe ze nie tak jak mialo byc - rozdziele jeszcze na osobne
BEGIN
 SELECT id_zlecenia, wartosc_zlecenia, id_projektu, przydzielony_budżet, id_zespołu, zespoły.budżet, bilans_zlecen - bilans_zespolow AS bilans
  FROM zlecenia JOIN projekty ON projekty.zlecenie = zlecenia.id_zlecenia JOIN zespoły ON projekty.zespol = zespoły.id_zespołu
   JOIN (SELECT SUM(wartosc_zlecenia) AS bilans_zlecen FROM zlecenia WHERE dzial = dz) AS A
   JOIN (SELECT SUM(budżet) AS bilans_zespolow FROM zespoły WHERE dział = dz) AS B
  WHERE dzial = dz AND dział = dz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS sprawdz_bilans_zlecen;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans_zlecen (IN dz int)
BEGIN
 SELECT id_zlecenia, wartosc_zlecenia FROM zlecenia WHERE dzial = dz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS sprawdz_bilans_projektow;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans_projektow (IN dz int)
BEGIN
 SELECT id_projektu, przydzielony_budżet, zespol FROM projekty JOIN zespoły ON id_zespołu = zespol WHERE dział = dz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS sprawdz_bilans_zespolow;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans_zespolow (IN dz int)
BEGIN
 SELECT id_zespołu, budżet FROM zespoły WHERE dział = dz;
END$$

DELIMITER ;

DROP FUNCTION IF EXISTS sprawdz_bilans_budzetow;

DELIMITER $$

CREATE FUNCTION sprawdz_bilans_budzetow (dz int)
RETURNS int
DETERMINISTIC
BEGIN
 DECLARE wartosci int;
 DECLARE budzety int;

 SELECT SUM(wartosc_zlecenia) FROM zlecenia WHERE dzial = dz INTO wartosci;
 SELECT SUM(budżet) FROM zespoły WHERE dział = dz INTO budzety;

 RETURN wartosci - budzety;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS zmien_status;

DELIMITER $$

CREATE PROCEDURE zmien_status (IN pr int, IN st varchar(90), IN dz int)
BEGIN
 UPDATE projekty JOIN zespoły ON id_zespołu = zespol SET status = st WHERE id_projektu = pr AND dział = dz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS zatwierdz_budzet;

DELIMITER $$

CREATE PROCEDURE zatwierdz_budzet (IN dz int)
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

END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS ZM_zarzadcy_zespolu;

DELIMITER $$

CREATE PROCEDURE ZM_zarzadcy_zespolu (IN zesp int, IN cz int, IN dz int)
BEGIN
 DECLARE dz_zesp int;

 SELECT dział FROM zespoły WHERE id_zespołu = zesp INTO dz_zesp;

 IF dz_zesp = dz THEN
 BEGIN
  UPDATE grupy_zespołów SET status = 'Byly zarzadca' WHERE zespoł = zesp AND status = 'Zarzadca';
  UPDATE grupy_zespołów SET status = 'Zarzadca' WHERE zespoł = zesp AND pracownik = cz;
 END;
 END IF;

END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS rozwiaz_zespol;

DELIMITER $$

CREATE PROCEDURE rozwiaz_zespol (IN zesp int, IN dz int)
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

END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS korekta_danych;

DELIMITER $$

CREATE PROCEDURE korekta_danych (IN prac int, IN atr varchar(90), IN nowy varchar(90))
BEGIN
 PREPARE stmt FROM CONCAT('UPDATE pracownik SET ', atr, ' = ? WHERE id_pracownika = ?');
 EXECUTE stmt USING nowy, prac;
 DEALLOCATE PREPARE stmt;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS korekta_historii;

DELIMITER $$

CREATE PROCEDURE korekta_historii (IN prac int, IN zesp int, IN atr varchar(90), IN nowy varchar(90))
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

END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS zwolnij_pracownika;

DELIMITER $$

CREATE PROCEDURE zwolnij_pracownika (IN prac int)
BEGIN

 DELETE FROM loginy WHERE id_pracownika = prac;
 DELETE FROM hasla WHERE id_pracownika = prac;

 UPDATE grupy_zespołów SET status = 'Byly pracownik' WHERE pracownik = prac;

END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS ZM_zarzadcy_dzialu;

DELIMITER $$

CREATE PROCEDURE ZM_zarzadcy_dzialu (IN dz int, IN prac int)
BEGIN
 UPDATE działy SET zarządca_działu = prac WHERE id_działu = dz;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS przeniesienie_zlecenia;

DELIMITER $$

CREATE PROCEDURE przeniesienie_zlecenia (IN zl int, IN dz int)
BEGIN
 UPDATE projekty SET status = 'Zamkniety', przydzielony_budżet = 0 WHERE zlecenie = zl;
 UPDATE zlecenia SET dzial = dz WHERE id_zlecenia = zl;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS zatwierdz_wynagrodzenie;

DELIMITER $$

CREATE PROCEDURE zatwierdz_wynagrodzenie (IN zesp int)
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

END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS zatwierdz_czas_pracy;

DELIMITER $$

CREATE PROCEDURE zatwierdz_czas_pracy (IN zesp int)
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

END$$

DELIMITER ;

