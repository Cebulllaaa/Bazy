DROP PROCEDURE IF EXISTS rozwiaz_zespol;

DELIMITER $$

CREATE PROCEDURE rozwiaz_zespol (IN zesp int, IN dz int)
BEGIN

 DECLARE zesp_dz int;
 SELECT dział FROM zespoły WHERE id_zespołu = zesp INTO zesp_dz;

 IF zesp_dz = dz THEN
 BEGIN
  UPDATE projekty SET status = 'Porzucony' WHERE zespol = zesp;
  UPDATE grupy_zespołów SET status = 'Przeniesiony' WHERE zespoł = zesp
   AND (status = 'Zarzadca' OR status = 'Obecny' OR status = 'Byly zarzadca');
 END;
 END IF;

END$$

DELIMITER ;

-- CALL rozwiaz_zespol (5, 1);
