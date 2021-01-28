DROP PROCEDURE IF EXISTS zatwierdz_budzet;

DELIMITER $$

CREATE PROCEDURE zatwierdz_budzet (IN dz int)
BEGIN
 DECLARE koniec int DEFAULT FALSE;
 DECLARE zesp int;
 DECLARE ile int;

 DECLARE budzety CURSOR FOR SELECT id_zespołu, SUM(przydzielony_budżet) AS kwota FROM projekty JOIN zespoły ON id_zespołu = zespol GROUP BY id_zespołu;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET koniec = TRUE;

 OPEN budzety;

 WHILE NOT koniec DO

  FETCH budzety INTO zesp, ile;
  UPDATE zespoły SET budżet = ile WHERE id_zespołu = zesp;

 END WHILE;

 CLOSE budzety;

END$$

DELIMITER $$

-- CALL zatwierdz_budzet (1);
