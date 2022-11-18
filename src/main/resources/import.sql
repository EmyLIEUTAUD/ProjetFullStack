-- Ajout des centres dans la bdd --
COPY Centre(gid,nom,arrete_pref_numero,xy_precis,id_adr,num_adresse,adresse,cp,com_insee,comnom,lat_coor1,long_coor1,structure_siren,structure_type,structure_rais,structure_num,structure_voie,structure_cp,structure_insee,structure_com,_userid_creation,_userid_modification,_edit_datemaj,lieu_accessibilite,horaires_lundi,horaires_mardi,horaires_mercredi,horaires_jeudi,horaires_vendredi,horaires_samedi,horaires_dimanche,rdv,date_fermeture,date_ouverture,rdv_site_web,rdv_tel,rdv_tel2,rdv_modalites,rdv_consultation_prevaccination,centre_svi_repondeur,centre_fermeture,reserve_professionels_sante,centre_type) FROM 'D:\Polytech\5A\Fullstack\Projet\covid-api\src\main\resources\centres-vaccination.csv' DELIMITER ';' CSV HEADER;

-- Ajout d'un admin dans la bdd --
INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('admin1', 'admin1', 'admin1@gmail.com', '$2y$10$SRk2GBReHHv/hacAZzFTwuf1zG37Ze5Ah7OFVbqyZ8C0PU.OstOEq');
INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (1, 'ADMIN');
INSERT INTO Admin (identifiant, gid) VALUES (1, 2727);

-- Ajout d'un deuxième admin dans la bdd --
INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('admin2', 'admin2', 'admin2@gmail.com', '$2y$10$qtdmVIOF.LIVScjL6k35geDDAULs9vcy.RkLCmkwGrbeNaqNWZEYq');
INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (2, 'ADMIN');
INSERT INTO Admin (identifiant, gid) VALUES (2, 1072);

-- Ajout d'un médecin dans la bdd --
INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('medecin1', 'medecin1', 'medecin1@gmail.com', '$2y$10$kT/2RiZtCFCnczlJxp4a/.d6qirzp/4OdvBTAKLP4uMy3KhY1QK1W');
INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (3, 'MEDECIN');
INSERT INTO Medecin (identifiant, gid) VALUES (3, 2727);

-- Ajout d'un deuxième medecin dans la bdd --
INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('medecin2', 'medecin2', 'medecin2@gmail.com', '$2y$10$FJdOT5ypWkshAsE6ac1HtOEd03qwI2nPGQERhhXQBgD0Zh7KvIY/C');
INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (4, 'MEDECIN');
INSERT INTO Medecin (identifiant, gid) VALUES (4, 188);

-- Ajout d'un personne public dans la bdd --
INSERT INTO Personne (nom, prenom, mail) VALUES ('public1', 'public1', 'public1@gmail.com');
INSERT INTO Public (identifiant, dose) VALUES (5, 2)