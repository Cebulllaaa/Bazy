DROP PROCEDURE IF EXISTS usun_z_zespolu;

DELIMITER $$

CREATE PROCEDURE usun_z_zespolu (IN zesp int, IN cz int)
BEGIN
 UPDATE grupy_zespołów SET status = "Usuniety" WHERE zespoł = zesp AND pracownik = cz;
END$$

DELIMITER ;

-- CALL usun_z_zespolu (6, 4);
