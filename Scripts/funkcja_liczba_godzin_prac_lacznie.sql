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

-- SELECT liczba_godzin_prac_lacznie (4);