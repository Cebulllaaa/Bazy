DROP PROCEDURE IF EXISTS projekty_pracownika;

DELIMITER $$

CREATE PROCEDURE projekty_pracownika (IN prac int)
BEGIN
 SELECT DISTINCT projekty.id_projektu, projekty.nazwa_projektu , projekty.status -- wyswietlam tez byle projekty
  FROM grupy_zespołów JOIN zespoły ON id_zespołu = zespoł JOIN projekty ON projekty.zespol = zespoły.id_zespołu WHERE pracownik = prac;
END$$

DELIMITER ;

-- CALL projekty_pracownika (3);
