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

-- SELECT wynagrodzenie_prac_lacznie (1);
