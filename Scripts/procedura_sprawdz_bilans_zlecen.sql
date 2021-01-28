DROP PROCEDURE IF EXISTS sprawdz_bilans_zlecen;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans_zlecen (IN dz int)
BEGIN
 SELECT id_zlecenia, wartosc_zlecenia FROM zlecenia WHERE dzial = dz;
END$$

DELIMITER ;

-- CALL sprawdz_bilans_zlecen (1);
