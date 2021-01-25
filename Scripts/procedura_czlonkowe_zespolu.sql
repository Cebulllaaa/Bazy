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

-- CALL czlonkowe_zespolu (6);
