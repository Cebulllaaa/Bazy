DROP TRIGGER IF EXISTS zlecenia_after_delete;

/* DELIMITER $$

CREATE TRIGGER zlecenia_after_delete AFTER DELETE ON zlecenia FOR EACH ROW
BEGIN

 DECLARE usuniete_id int DEFAULT OLD.id_zlecenia;
 DECLARE zostalo int;
 DECLARE klient_do_usuniecia int;

-- SELECT id_zlecenia FROM Old WHERE id_zleceina NOT IN (SELECT id_zleceina FROM New) INTO usuniete_id;
 SELECT COUNT(*) FROM
  (SELECT * FROM zlecenia AS T1 JOIN klienci AS T2 ON T1.klient = T2.id_klienta WHERE T1.id_zlecenia = usuniete_id) AS T3 INTO zostalo;

 IF zostalo = 0 THEN
 BEGIN
  SELECT id_klienta FROM klienci k2 JOIN zlecenia z ON z.klient = k2.id_klienta WHERE z.id_zlecenia = id_zlecenia INTO klient_do_usuniecia;
--  DELETE FROM zlecenia WHERE klient = (SELECT id_klienta FROM klienci k2 JOIN zlecenia z ON z.klient = k2.id_klienta WHERE z.id_zlecenia = id_zlecenia);
  DELETE FROM klienci WHERE id_klienta = (SELECT id_klienta FROM klienci k2 JOIN zlecenia z ON z.klient = k2.id_klienta WHERE z.id_zlecenia = id_zlecenia); -- = klient_do_usuniecia;
 END;
 END IF;

END; $$

DELIMITER ;
*/
