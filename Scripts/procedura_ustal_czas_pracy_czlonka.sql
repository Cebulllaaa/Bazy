DROP PROCEDURE IF EXISTS ustal_czas_pracy_czlonka;

DELIMITER $$

CREATE PROCEDURE ustal_czas_pracy_czlonka (IN zesp int, IN czas int, IN prac int)
BEGIN
 UPDATE grupy_zespołów SET godziny = czas WHERE zespoł = zesp AND (status = "Obecny" OR status = "Zarzadca") AND pracownik = prac;
END

DELIMITER ;

-- CALL ustal_czas_pracy_czlonka (6, 100, 4);
