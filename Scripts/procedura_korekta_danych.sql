DROP PROCEDURE IF EXISTS korekta_danych;

DELIMITER $$

CREATE PROCEDURE korekta_danych (IN prac int, IN atr varchar(90), IN nowy varchar(90))
BEGIN
 PREPARE stmt FROM CONCAT('UPDATE pracownik SET ', atr, ' = ? WHERE id_pracownika = ?');
 EXECUTE stmt USING nowy, prac;
 DEALLOCATE PREPARE stmt;
END$$

DELIMITER ;

-- CALL korekta_danych (2, 'wypłata', '123'); -- nie zadziala
