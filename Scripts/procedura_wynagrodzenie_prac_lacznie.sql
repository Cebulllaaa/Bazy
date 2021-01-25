DROP PROCEDURE IF EXISTS wynagrodzenie_prac_w_zespolach;

DELIMITER $$

CREATE PROCEDURE wynagrodzenie_prac_w_zespolach (IN prac int)
BEGIN
 SELECT grupy_zespołów.zespoł, grupy_zespołów.wynagrodzenie FROM pracownik JOIN grupy_zespołów
  ON pracownik.id_pracownika = grupy_zespołów.pracownik
  WHERE pracownik.id_pracownika = prac AND (grupy_zespołów.status = "Zarzadca" OR grupy_zespołów.status = "Obecny");
END$$

DELIMITER ;

-- CALL wynagrodzenie_prac_w_zespolach (4);
