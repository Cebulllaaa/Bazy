DROP PROCEDURE IF EXISTS dane_pracownika;

DELIMITER $$

CREATE PROCEDURE dane_pracownika (IN prac int)
BEGIN
 SELECT imie, nazwisko, wypłata, godziny_tygodniowo FROM pracownik WHERE id_pracownika = prac;
-- nie wyswietla id, dla bezpieczenstwa
END

DELIMITER ;

-- CALL dane_pracownika (4);
