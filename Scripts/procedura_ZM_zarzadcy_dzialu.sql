DROP PROCEDURE IF EXISTS ZM_zarzadcy_dzialu;

DELIMITER $$

CREATE PROCEDURE ZM_zarzadcy_dzialu (IN dz int, IN prac int)
BEGIN
 UPDATE działy SET zarządca_działu = prac WHERE id_działu = dz;
END$$

DELIMITER ;

-- CALL ZM_zarzadcy_dzialu (1, 1);
