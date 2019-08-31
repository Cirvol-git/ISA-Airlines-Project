INSERT INTO `db_isa`.`aviokompanija` (`id`, `adresa`, `ime`, `opis`) VALUES ('1', 'Postojeca Adresa Beograd', 'Nikola Tesla', 'Aerodrom u Beogradu');
INSERT INTO `db_isa`.`aviokompanija` (`id`, `adresa`, `ime`, `opis`) VALUES ('2', 'Adresa Backa Palanka', 'BP AeroTurs', 'Aerodrom u Backoj Palanci');

INSERT INTO `db_isa`.`korisnik` (`id`, `admin_leta`, `email`, `grad`, `ime`, `pass`, `prezime`, `broj_telefona`) VALUES ('5', b'0', 'korisnik4@gmail.com', 'Novi Sad', 'Marko', '123', 'Katic4', '060111222');
INSERT INTO `db_isa`.`korisnik` (`id`, `admin_leta`, `email`, `grad`, `ime`, `pass`, `prezime`, `broj_telefona`) VALUES ('4', b'0', 'korisnik3@gmail.com', 'Novi Sad', 'Marko', '123', 'Markovic3', '060111222');
INSERT INTO `db_isa`.`korisnik` (`id`, `admin_leta`, `email`, `grad`, `ime`, `pass`, `prezime`, `broj_telefona`) VALUES ('3', b'0', 'korisnik2@gmail.com', 'Novi Sad', 'Lazar', '123', 'Milic2', '060111222');
INSERT INTO `db_isa`.`korisnik` (`id`, `admin_leta`, `email`, `grad`, `ime`, `pass`, `prezime`, `broj_telefona`) VALUES ('2', b'0', 'korisnik1@gmail.com', 'Novi Sad', 'Sale', '123', 'Stanic1', '060111222');
INSERT INTO `db_isa`.`korisnik` (`id`, `admin_leta`, `email`, `grad`, `ime`, `pass`, `prezime`, `broj_telefona`, `admin_od_id`) VALUES ('1', b'1', 'admin@gmail.com', 'Novi Sad', 'Igor', '123', 'Lovric', '060222111', '1');

INSERT INTO `db_isa`.`aerodrom` (`id`,`adresa`,`grad`,`ime`) VALUES (1,'Negde u Beogradu','Beograd','Aerodrom Nikole Tesle');
INSERT INTO `db_isa`.`aerodrom` (`id`,`adresa`,`grad`,`ime`) VALUES (2,'Real adresa 1','Novi Sad','Aerodrom NS');
INSERT INTO `db_isa`.`aerodrom` (`id`,`adresa`,`grad`,`ime`) VALUES (3,'Svetozara Miletica 77','Backa Palanka','Aerodrom Backa Palanka');

INSERT INTO `db_isa`.`avio_aero` (`avio_id`,`aero_id`) VALUES (1,1);
INSERT INTO `db_isa`.`avio_aero` (`avio_id`,`aero_id`) VALUES (1,2);
INSERT INTO `db_isa`.`avio_aero` (`avio_id`,`aero_id`) VALUES (2,3);

INSERT INTO `db_isa`.`let` (`id`,`presedanja`,`redova`,`cenab`,`cenae`,`cenaf`,`duzina`,`freeb`,`freee`,`freef`,`polece_date`,`polece_time`,`prtljag`,`slece_date`,`slece_time`,`tip_leta`,`vreme`,`avio_id`,`do_dest_id`,`od_dest_id`) VALUES (1,0,5,10,5,15,300,6,12,6,'2019-08-10','23:00:00',0,'2019-08-10','23:30:00',2,30,1,2,1);

INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (1,NULL,3,b'1',NULL,b'0',NULL,2,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (2,NULL,0,b'1',NULL,b'0',NULL,3,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (3,NULL,0,b'1',NULL,b'0',NULL,1,NULL,2,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (4,NULL,0,b'1',NULL,b'0',NULL,2,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (5,NULL,0,b'1',NULL,b'0',NULL,4,NULL,4,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (6,NULL,3,b'1',NULL,b'0',NULL,4,NULL,4,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (7,NULL,0,b'1',NULL,b'0',NULL,0,NULL,1,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (8,NULL,2,b'1',NULL,b'0',NULL,1,NULL,2,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (9,NULL,5,b'1',NULL,b'0',NULL,2,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (10,NULL,4,b'1',NULL,b'0',NULL,0,NULL,1,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (11,NULL,1,b'1',NULL,b'0',NULL,1,NULL,2,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (12,NULL,3,b'1',NULL,b'0',NULL,1,NULL,2,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (13,NULL,4,b'1',NULL,b'0',NULL,4,NULL,4,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (14,NULL,1,b'1',NULL,b'0',NULL,2,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (15,NULL,1,b'1',NULL,b'0',NULL,3,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (16,NULL,2,b'1',NULL,b'0',NULL,3,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (17,NULL,5,b'1',NULL,b'0',NULL,4,NULL,4,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (18,NULL,4,b'1',NULL,b'0',NULL,2,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (19,NULL,3,b'1',NULL,b'0',NULL,3,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (20,NULL,2,b'1',NULL,b'0',NULL,2,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (21,NULL,4,b'1',NULL,b'0',NULL,1,NULL,2,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (22,NULL,5,b'1',NULL,b'0',NULL,0,NULL,1,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (23,NULL,1,b'1',NULL,b'0',NULL,0,NULL,1,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (24,NULL,5,b'1',NULL,b'0',NULL,1,NULL,2,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (25,NULL,1,b'1',NULL,b'0',NULL,4,NULL,4,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (26,NULL,2,b'1',NULL,b'0',NULL,0,NULL,1,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (27,NULL,2,b'1',NULL,b'0',NULL,4,NULL,4,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (28,NULL,4,b'1',NULL,b'0',NULL,3,NULL,3,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (29,NULL,3,b'1',NULL,b'0',NULL,0,NULL,1,0,NULL,NULL,1);
INSERT INTO `db_isa`.`rezervacija` (`id`,`ime`,`kolona`,`odobreno`,`pasos`,`potvrdjeno`,`prezime`,`red`,`rezervisano`,`tip`,`version`,`inv_od_id`,`pripada_korisniku_id`,`pripada_letu_id`) VALUES (30,NULL,5,b'1',NULL,b'0',NULL,3,NULL,3,0,NULL,NULL,1);
