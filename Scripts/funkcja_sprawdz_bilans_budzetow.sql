DROP FUNCTION IF EXISTS sprawdz_bilans_budzetow;

DELIMITER $$

CREATE FUNCTION sprawdz_bilans_budzetow (dz int)
RETURNS int
DETERMINISTIC
BEGIN
 DECLARE wartosci int;
 DECLARE budzety int;

 SELECT SUM(wartosc_zlecenia) FROM zlecenia WHERE dzial = dz INTO wartosci;
 SELECT SUM(budżet) FROM zespoły WHERE dział = dz INTO budzety;

 RETURN wartosci - budzety;
END$$

DELIMITER ;

-- SELECT sprawdz_bilans_budzetow (1);
