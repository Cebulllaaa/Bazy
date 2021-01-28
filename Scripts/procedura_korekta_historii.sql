DROP PROCEDURE IF EXISTS korekta_historii;

DELIMITER $$

CREATE PROCEDURE korekta_historii (IN prac int, IN zesp int, IN atr varchar(90), IN nowy varchar(90))
BEGIN

 IF atr = 'DELETE' THEN
  DELETE FROM grupy_zespołów WHERE pracownik = prac AND zespoł = zesp;
 ELSE
 BEGIN

  PREPARE st FROM CONCAT('UPDATE grupy_zespołów SET ', atr, ' = ? WHERE pracownik = ? AND zespoł = ?');
  EXECUTE st USING nowy, prac, zesp;
  DEALLOCATE PREPARE st;

 END;
 END IF;

END$$

DELIMITER ;

-- CALL korekta_historii (1, 1, 1, 1);
