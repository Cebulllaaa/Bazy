DROP PROCEDURE IF EXISTS sprawdz_bilans_zespolow;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans_zespolow (IN dz int)
BEGIN
 SELECT id_zespołu, budżet FROM zespoły WHERE dział = dz;
END$$

DELIMITER ;

-- CALL sprawdz_bilans_zespolow (1);
