DROP PROCEDURE IF EXISTS sprawdz_bilans_projektow;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans_projektow (IN dz int)
BEGIN
 SELECT id_projektu, przydzielony_budżet, zespol FROM projekty JOIN zespoły ON id_zespołu = zespol WHERE dział = dz;
END$$

DELIMITER ;

-- CALL sprawdz_bilans_projektow (1);
