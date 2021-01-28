DROP PROCEDURE IF EXISTS projekty_zespolu;

DELIMITER $$

CREATE PROCEDURE projekty_zespolu (IN zesp int)
BEGIN
 SELECT projekty.* FROM projekty JOIN zespoły ON projekty.zespol = zespoły.id_zespołu WHERE zespoły.id_zespołu = zesp;
END$$

DELIMITER ;

-- CALL projekty_zespolu (6);
