DROP PROCEDURE IF EXISTS przenies_z_zespolu;

DELIMITER $$

CREATE PROCEDURE przenies_z_zespolu (IN zesp int, IN cz int)
BEGIN
 UPDATE grupy_zespołów SET status = "Przeniesiony" WHERE zespoł = zesp AND pracownik = cz;
END$$

DELIMITER ;

-- CALL przenies_z_zespolu (6, 4);
