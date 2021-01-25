DROP PROCEDURE IF EXISTS liczba_godzin_prac_w_zespolach;

DELIMITER $$

CREATE PROCEDURE liczba_godzin_prac_w_zespolach (IN prac int)
BEGIN
 SELECT grupy_zespołów.zespoł, grupy_zespołów.godziny FROM pracownik JOIN grupy_zespołów
  ON pracownik.id_pracownika = grupy_zespołów.pracownik
  WHERE pracownik.id_pracownika = prac AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny");
END$$

DELIMITER ;

-- CALL liczba_godzin_prac_w_zespolach (4);