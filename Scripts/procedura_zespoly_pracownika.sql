DROP PROCEDURE IF EXISTS zespoly_pracownika;

DELIMITER $$

CREATE PROCEDURE zespoly_pracownika (IN prac int)
BEGIN
 SELECT DISTINCT zespoł, nazwa_zespołu, status -- wyswietlam tez byle zespoly
  FROM grupy_zespołów JOIN zespoły ON id_zespołu = zespoł WHERE pracownik = prac;
END$$

DELIMITER ;

-- CALL zespoly_pracownika (4);
