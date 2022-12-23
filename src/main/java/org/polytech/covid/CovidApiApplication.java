package org.polytech.covid;

import java.io.File;
import java.sql.DriverManager;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CovidApiApplication implements CommandLineRunner {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {

		SpringApplication.run(CovidApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Ajout des centres

		String centresVaccinationPath = new File("./src/main/resources/centres-vaccination.csv").getAbsolutePath();
		String importCentres = "COPY Centre(gid,nom,arrete_pref_numero,xy_precis,id_adr,num_adresse,adresse,cp,com_insee,comnom,lat_coor1,long_coor1,structure_siren,structure_type,structure_rais,structure_num,structure_voie,structure_cp,structure_insee,structure_com,_userid_creation,_userid_modification,_edit_datemaj,lieu_accessibilite,horaires_lundi,horaires_mardi,horaires_mercredi,horaires_jeudi,horaires_vendredi,horaires_samedi,horaires_dimanche,rdv,date_fermeture,date_ouverture,rdv_site_web,rdv_tel,rdv_tel2,rdv_modalites,rdv_consultation_prevaccination,centre_svi_repondeur,centre_fermeture,reserve_professionels_sante,centre_type) FROM '"
				+ centresVaccinationPath + "' DELIMITER ';' CSV HEADER;";
		int rawCentres = jdbcTemplate.update(importCentres);
		if (rawCentres > 0) {
			System.out.println("Les centres ont bien été importés");
		}

		// Ajout d'un premier admin

		String importPersonneAdmin1 = "INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('admin1', 'admin1', 'admin1@gmail.com', '$2y$10$SRk2GBReHHv/hacAZzFTwuf1zG37Ze5Ah7OFVbqyZ8C0PU.OstOEq');";
		int rawPersonneAdmin1 = jdbcTemplate.update(importPersonneAdmin1);
		if (rawPersonneAdmin1 > 0) {
			System.out.println("La personne Admin1 a bien été importée");
		}

		String importRoleAdmin1 = "INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (2, 'ADMIN');";
		int rawRoleAdmin1 = jdbcTemplate.update(importRoleAdmin1);
		if (rawRoleAdmin1 > 0) {
			System.out.println("Le role ADMIN de Admin1 a bien été importé");
		}

		String importAdminAdmin1 = "INSERT INTO Admin (identifiant, gid) VALUES (2, 2727);";
		int rawAdminAdmin1 = jdbcTemplate.update(importAdminAdmin1);
		if (rawAdminAdmin1 > 0) {
			System.out.println("Admin1 a bien été ajouté dans la table Admin");
		}

		// Ajout d'un deuxième admin

		String importPersonneAdmin2 = "INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('admin2', 'admin2', 'admin2@gmail.com', '$2y$10$qtdmVIOF.LIVScjL6k35geDDAULs9vcy.RkLCmkwGrbeNaqNWZEYq');";
		int rawPersonneAdmin2 = jdbcTemplate.update(importPersonneAdmin2);
		if (rawPersonneAdmin2 > 0) {
			System.out.println("La personne Admin2 a bien été importée");
		}

		String importRoleAdmin2 = "INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (3, 'ADMIN');";
		int rawRoleAdmin2 = jdbcTemplate.update(importRoleAdmin2);
		if (rawRoleAdmin2 > 0) {
			System.out.println("Le role ADMIN de Admin2 a bien été importé");
		}

		String importAdminAdmin2 = "INSERT INTO Admin (identifiant, gid) VALUES (3, 1072);";
		int rawAdminAdmin2 = jdbcTemplate.update(importAdminAdmin2);
		if (rawAdminAdmin2 > 0) {
			System.out.println("Admin2 a bien été ajouté dans la table Admin");
		}

		// Ajout d'un premier médecin

		String importPersonneMedecin1 = "INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('medecin1', 'medecin1', 'medecin1@gmail.com', '$2y$10$kT/2RiZtCFCnczlJxp4a/.d6qirzp/4OdvBTAKLP4uMy3KhY1QK1W');";
		int rawPersonneMedecin1 = jdbcTemplate.update(importPersonneMedecin1);
		if (rawPersonneMedecin1 > 0) {
			System.out.println("La personne Medecin1 a bien été importée");
		}

		String importRoleMedecin1 = "INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (4, 'MEDECIN');";
		int rawRoleMedecin1 = jdbcTemplate.update(importRoleMedecin1);
		if (rawRoleMedecin1 > 0) {
			System.out.println("Le role MEDECIN de Medecin1 a bien été importé");
		}

		String importMedecinMedecin1 = "INSERT INTO Medecin (identifiant, gid) VALUES (4, 2727);";
		int rawMedecinMedecin1 = jdbcTemplate.update(importMedecinMedecin1);
		if (rawMedecinMedecin1 > 0) {
			System.out.println("Medecin1 a bien été ajouté dans la table Medecin");
		}

		// Ajout d'un deuxième médecin

		String importPersonneMedecin2 = "INSERT INTO Personne (nom, prenom, mail, mdp) VALUES ('medecin2', 'medecin2', 'medecin2@gmail.com', '$2y$10$FJdOT5ypWkshAsE6ac1HtOEd03qwI2nPGQERhhXQBgD0Zh7KvIY/C');";
		int rawPersonneMedecin2 = jdbcTemplate.update(importPersonneMedecin2);
		if (rawPersonneMedecin2 > 0) {
			System.out.println("La personne Medecin2 a bien été importée");
		}

		String importRoleMedecin2 = "INSERT INTO Personne_roles (personne_identifiant, roles) VALUES (5, 'MEDECIN');";
		int rawRoleMedecin2 = jdbcTemplate.update(importRoleMedecin2);
		if (rawRoleMedecin2 > 0) {
			System.out.println("Le role MEDECIN de Medecin2 a bien été importé");
		}

		String importMedecinMedecin2 = "INSERT INTO Medecin (identifiant, gid) VALUES (5, 188);";
		int rawMedecinMedecin2 = jdbcTemplate.update(importMedecinMedecin2);
		if (rawMedecinMedecin2 > 0) {
			System.out.println("Medecin2 a bien été ajouté dans la table Medecin");
		}

		// Ajout d'une personne Public

		String importPersonnePublic1 = "INSERT INTO Personne (nom, prenom, mail) VALUES ('public1', 'public1', 'public1@gmail.com');";
		int rawPersonnePublic1 = jdbcTemplate.update(importPersonnePublic1);
		if (rawPersonnePublic1 > 0) {
			System.out.println("La personne Public1 a bien été importée");
		}

		String importPublicPublic1 = "INSERT INTO Public (identifiant, dose) VALUES (6, 2)";
		int rawPublicPublic1 = jdbcTemplate.update(importPublicPublic1);
		if (rawPublicPublic1 > 0) {
			System.out.println("Public1 a bien été ajouté dans la table Public");
		}

	}

}
