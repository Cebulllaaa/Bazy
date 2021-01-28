DROP PROCEDURE IF EXISTS zwolnij_pracownika;

DELIMITER $$

CREATE PROCEDURE zwolnij_pracownika (IN prac int)
BEGIN

 DELETE FROM loginy WHERE id_pracownika = prac;
 DELETE FROM hasla WHERE id_pracownika = prac;

 UPDATE grupy_zespołów SET status = 'Byly pracownik' WHERE pracownik = prac;

END$$

DELIMITER ;

-- CALL zwolnij_pracownika (4);
