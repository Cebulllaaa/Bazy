DROP PROCEDURE IF EXISTS sprawdz_bilans;

DELIMITER $$

CREATE PROCEDURE sprawdz_bilans (IN dz int) -- mozliwe ze nie tak jak mialo byc - rozdziele jeszcze na osobne
BEGIN
 SELECT id_zlecenia, wartosc_zlecenia, id_projektu, przydzielony_budżet, id_zespołu, zespoły.budżet, bilans_zlecen - bilans_zespolow AS bilans
  FROM zlecenia JOIN projekty ON projekty.zlecenie = zlecenia.id_zlecenia JOIN zespoły ON projekty.zespol = zespoły.id_zespołu
   JOIN (SELECT SUM(wartosc_zlecenia) AS bilans_zlecen FROM zlecenia WHERE dzial = dz) AS A
   JOIN (SELECT SUM(budżet) AS bilans_zespolow FROM zespoły WHERE dział = dz) AS B
  WHERE dzial = dz AND dział = dz;
END

DELIMITER ;

-- CALL sprawdz_bilans (1);