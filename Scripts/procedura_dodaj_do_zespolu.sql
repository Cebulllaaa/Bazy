DROP PROCEDURE IF EXISTS dodaj_do_zespolu;

DELIMITER $$

CREATE PROCEDURE dodaj_do_zespolu (in zesp int, IN cz int)
BEGIN
 DECLARE c int;
 SELECT COUNT(*) FROM grupy_zespołów WHERE zespoł = zesp AND pracownik = cz INTO c;

 IF c = 0 THEN
  INSERT INTO grupy_zespołów (zespoł, pracownik, status) VALUES (zesp, cz, "Obecny");
 ELSE
  UPDATE grupy_zespołów SET status = "Obecny" WHERE zespoł = zesp AND pracownik = cz;
 END IF;
END$$

DELIMITER ;

-- CALL dodaj_do_zespolu (6, 4);
