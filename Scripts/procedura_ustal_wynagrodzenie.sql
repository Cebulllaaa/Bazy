DROP PROCEDURE IF EXISTS ustal_wynagrodzenie;

DELIMITER $$

CREATE PROCEDURE ustal_wynagrodzenie (IN zesp int, IN cz int, IN wyn int)
BEGIN
 UPDATE grupy_zespołów SET wynagrodzenie = wyn WHERE zespoł = zesp AND (status = "Obecny" OR status = "Zarzadca") AND pracownik = cz;
END

DELIMITER ;

-- CALL ustal_wynagrodzenie (6, 4, 44444);
