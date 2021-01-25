DROP FUNCTION IF EXISTS budzet_zespolu;

DELIMITER $$

CREATE FUNCTION budzet_zespolu (zesp int)
RETURNS int
DETERMINISTIC
BEGIN
 RETURN (SELECT budżet FROM zespoły WHERE id_zespołu = zesp);
END

DELIMITER ;

-- SELECT budzet_zespolu (6);