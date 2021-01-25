DROP PROCEDURE IF EXISTS historia_pracownika;

DELIMITER $$

CREATE PROCEDURE historia_pracownika (IN prac int)
BEGIN
 SELECT DISTINCT zespoły.id_zespołu, zespoły.nazwa_zespołu, projekty.id_projektu, projekty.nazwa_projektu
  FROM (SELECT * FROM pracownik WHERE id_pracownika = prac) AS P JOIN grupy_zespołów ON P.id_pracownika = grupy_zespołów.pracownik
   JOIN zespoły ON zespoły.id_zespołu = grupy_zespołów.zespoł JOIN projekty ON projekty.zespol = zespoły.id_zespołu;
END

DELIMITER ;

-- CALL historia_pracownika (4);
