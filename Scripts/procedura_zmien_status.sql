DROP PROCEDURE IF EXISTS zmien_status;

DELIMITER $$

CREATE PROCEDURE zmien_status (IN pr int, IN st varchar(90), IN dz int)
BEGIN
 UPDATE projekty JOIN zespoły ON id_zespołu = zespol SET status = st WHERE id_projektu = pr AND dział = dz;
END$$

DELIMITER ;

-- CALL zmien_status (2, 'nierealiowany', 1);
