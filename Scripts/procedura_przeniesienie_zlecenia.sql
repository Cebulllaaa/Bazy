DROP PROCEDURE IF EXISTS przeniesienie_zlecenia;

DELIMITER $$

CREATE PROCEDURE przeniesienie_zlecenia (IN zl int, IN dz int)
BEGIN
 UPDATE projekty SET status = 'Zamkniety', przydzielony_budżet = 0 WHERE zlecenie = zl;
 UPDATE zlecenia SET dzial = dz WHERE id_zlecenia = zl;
END$$

DELIMITER ;

-- CALL przeniesienie_zlecenia (1, 1);
