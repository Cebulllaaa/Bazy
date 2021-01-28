DROP PROCEDURE IF EXISTS ZM_zarzadcy_zespolu;

DELIMITER $$

CREATE PROCEDURE ZM_zarzadcy_zespolu (IN zesp int, IN cz int, IN dz int)
BEGIN
 DECLARE dz_zesp int;

 SELECT dział FROM zespoły WHERE id_zespołu = zesp INTO dz_zesp;

 IF dz_zesp = dz THEN
 BEGIN
  UPDATE grupy_zespołów SET status = 'Byly zarzadca' WHERE zespoł = zesp AND status = 'Zarzadca';
  UPDATE grupy_zespołów SET status = 'Zarzadca' WHERE zespoł = zesp AND pracownik = cz;
 END;
 END IF;

END$$

DELIMITER ;

-- CALL ZM_zarzadcy_zespolu (5, 3, 1);
