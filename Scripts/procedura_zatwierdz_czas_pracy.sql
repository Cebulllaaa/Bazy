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

-- CALL zatwierdz_czas_pracy (6);
