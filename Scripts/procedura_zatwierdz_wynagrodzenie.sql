DROP PROCEDURE IF EXISTS zatwierdz_wynagrodzenie;

DELIMITER $$

CREATE PROCEDURE zatwierdz_wynagrodzenie (IN zesp int)
BEGIN
 DECLARE pozostalo int;
 DECLARE dodawanie int;
 DECLARE id_prac int;
 DECLARE i int DEFAULT 0;
 DECLARE ok bool DEFAULT TRUE;

 SELECT zespoły.budżet FROM zespoły WHERE id_zespołu = zesp INTO pozostalo;
-- SELECT wynagrodzenie FROM grupy_zespołów WHERE zespoł = zesp AND pracownik = (SELECT grupy_zespołów.pracownik FROM grupy_zespołów WHERE (status = "Zarzadca" OR status = "Obecny") AND zespoł = zesp) AND (status = "Zarzadca" OR status = "Obecny");

 START TRANSACTION;
-- SELECT grupy_zespołów.pracownik FROM grupy_zespołów WHERE (status = "Zarzadca" OR status = "Obecny") AND zespoł = zesp;
wyplacanie: WHILE i IN (SELECT grupy_zespołów.pracownik FROM grupy_zespołów WHERE (status = "Zarzadca" OR status = "Obecny") AND zespoł = zesp) DO
 BEGIN
-- 	 SELECT i;
-- SELECT wynagrodzenie FROM grupy_zespołów WHERE zespoł = zesp AND pracownik = i AND (status = "Zarzadca" OR status = "Obecny");
  SELECT wynagrodzenie FROM grupy_zespołów WHERE zespoł = zesp AND pracownik = i AND (status = "Zarzadca" OR status = "Obecny") INTO dodawanie; -- zachowuje dodawana pensje
  SET pozostalo = pozostalo - dodawanie;
-- SELECT 1;
  IF pozostalo < 0 THEN
  BEGIN

   ROLLBACK;
   SET ok = FALSE;
   LEAVE wyplacanie;

  END;
  END IF;
-- SELECT 2;
  UPDATE pracownik SET wypłata = wypłata + dodawanie WHERE id_pracownika = i;
 END;
 END WHILE;

 IF ok THEN
-- BEGIN
--  SELECT 1;
  COMMIT;
-- END;
 END IF;

END$$

DELIMITER ;

CALL zatwierdz_wynagrodzenie (5);
