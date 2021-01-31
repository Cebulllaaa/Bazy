DROP PROCEDURE IF EXISTS zamknij_dzial;

DELIMITER $$

CREATE PROCEDURE zamknij_dzial (IN dz int, IN movDZ int)
BEGIN
 DECLARE done int DEFAULT FALSE;
 DECLARE IDzl int;
 DECLARE IDprac int;
 DECLARE IDzesp int;

 DECLARE zlec CURSOR FOR SELECT id_zlecenia FROM zlecenia WHERE dzial = dz;
 DECLARE pracZesp CURSOR FOR SELECT grupy_zespołów.pracownik, grupy_zespołów.zespoł FROM grupy_zespołów JOIN zespoły
  ON grupy_zespołów.zespoł = zespoły.id_zespołu WHERE zespoły.dział = dz;

 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

 OPEN zlec;

 WHILE NOT done DO
 BEGIN
  FETCH zlec INTO IDzl;

  CALL przeniesienie_zlecenia(IDzl, movDZ); 

 END;
 END WHILE;

 CLOSE zlec;

 SET done = FALSE;

 OPEN pracZesp;

 WHILE NOT done DO
 BEGIN
  FETCH pracZesp INTO IDprac, IDzesp;

  CALL usun_z_zespolu(IDzesp, IDprac); 

 END;
 END WHILE;

 CLOSE pracZesp;

-- UPDATE działy SET zarządca_działu = 0 WHERE id_działu = dz;

END$$

DELIMITER ;

-- CALL zamknij_dzial (2, 1);
