package org.polytech.covid.JPAUnitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JPAUnitTestSuperAdmin {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private CentreRepository centreRepository;

    @Test
    public void should_find_no_admins_if_repository_is_empty() {
        Iterable admins = adminRepository.findAll();

        assertThat(admins).isEmpty();
    }

    @Test
    public void should_store_an_admin() {
        Personne personne = personneRepository.save(new Personne("NomPersonne", "PrenomPersonne",
                "mail.personne@mail.com", "+33111111111", "1, adresse de la personne", "mdp"));
        Centre centre = centreRepository.save(new Centre("Test Centre", "Baccarat", "1", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Admin admin = adminRepository.save(new Admin(personne, centre));

        assertThat(admin.getPersonne().getIdentifiant()).isEqualTo(personne.getIdentifiant());
        assertThat(admin.getPersonne().getNom()).isEqualTo(personne.getNom());
        assertThat(admin.getPersonne().getPrenom()).isEqualTo(personne.getPrenom());
        assertThat(admin.getPersonne().getMail()).isEqualTo(personne.getMail());
        assertThat(admin.getPersonne().getTelephone()).isEqualTo(personne.getTelephone());
        assertThat(admin.getPersonne().getAdresse()).isEqualTo(personne.getAdresse());
        assertThat(admin.getPersonne().getMdp()).isEqualTo(personne.getMdp());
        assertThat(admin.getCentre().getGid()).isEqualTo(centre.getGid());
        assertThat(admin.getCentre().getNom()).isEqualTo(centre.getNom());
        assertThat(admin.getCentre().getComnom()).isEqualTo(centre.getComnom());
        assertThat(admin.getCentre().getNumAdresse()).isEqualTo(centre.getNumAdresse());
        assertThat(admin.getCentre().getAdresse()).isEqualTo(centre.getAdresse());
        assertThat(admin.getCentre().getCp()).isEqualTo(centre.getCp());
        assertThat(admin.getCentre().getHorairesLundi()).isEqualTo(centre.getHorairesLundi());
        assertThat(admin.getCentre().getHorairesMardi()).isEqualTo(centre.getHorairesMardi());
        assertThat(admin.getCentre().getHorairesMercredi()).isEqualTo(centre.getHorairesMercredi());
        assertThat(admin.getCentre().getHorairesJeudi()).isEqualTo(centre.getHorairesJeudi());
        assertThat(admin.getCentre().getHorairesVendredi()).isEqualTo(centre.getHorairesVendredi());
        assertThat(admin.getCentre().getHorairesSamedi()).isEqualTo(centre.getHorairesSamedi());
        assertThat(admin.getCentre().getHorairesDimanche()).isEqualTo(centre.getHorairesDimanche());
    }

    @Test
    public void should_find_all_admins() {
        Centre centre1 = centreRepository.save(new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Centre centre2 = centreRepository.save(new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Centre centre3 = centreRepository.save(new Centre("Test Centre 3", "Jarville", "3", "rue de Jarville",
                54140,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-17h00", "9h30-11h30"));

        Personne personne1 = personneRepository.save(new Personne("NomPersonne1", "PrenomPersonne1",
                "mail.personne1@mail.com", "+33111111111", "1, adresse de la personne 1", "mdp1"));

        Personne personne2 = personneRepository.save(new Personne("NomPersonne2", "PrenomPersonne2",
                "mail.personn2@mail.com", "+33222222222", "2, adresse de la personne 2", "mdp2"));

        Personne personne3 = personneRepository.save(new Personne("NomPersonne3", "PrenomPersonne3",
                "mail.personn3@mail.com", "+33333333333", "3, adresse de la personne 3", "mdp3"));

        Admin admin1 = adminRepository.save(new Admin(personne1, centre1));

        Admin admin2 = adminRepository.save(new Admin(personne2, centre2));

        Admin admin3 = adminRepository.save(new Admin(personne3, centre3));

        Iterable admins = adminRepository.findAll();

        assertThat(admins).hasSize(3).contains(admin1, admin2, admin3);
    }

    @Test
    public void should_update_admin_by_id() {
        Centre centre1 = centreRepository.save(new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Centre centre2 = centreRepository.save(new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Personne personne1 = personneRepository.save(new Personne("NomPersonne1", "PrenomPersonne1",
                "mail.personne1@mail.com", "+33111111111", "1, adresse de la personne 1", "mdp1"));

        Admin admin1 = adminRepository.save(new Admin(personne1, centre1));

        Admin updatedAdmin = new Admin(personne1, centre2);

        Admin admin = adminRepository.findById(admin1.getId_admin()).get();
        admin.setPersonne(updatedAdmin.getPersonne());
        admin.setCentre(updatedAdmin.getCentre());
        adminRepository.save(admin);

        Admin checkAdmin = adminRepository.findById(admin1.getId_admin()).get();

        assertThat(checkAdmin.getId_admin()).isEqualTo(admin1.getId_admin());
        assertThat(checkAdmin.getPersonne()).isEqualTo(updatedAdmin.getPersonne());
        assertThat(checkAdmin.getCentre()).isEqualTo(updatedAdmin.getCentre());
    }

    @Test
    public void should_delete_admin_by_id() {
        Centre centre1 = centreRepository.save(new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Centre centre2 = centreRepository.save(new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Centre centre3 = centreRepository.save(new Centre("Test Centre 3", "Jarville", "3", "rue de Jarville",
                54140,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-17h00", "9h30-11h30"));

        Personne personne1 = personneRepository.save(new Personne("NomPersonne1", "PrenomPersonne1",
                "mail.personne1@mail.com", "+33111111111", "1, adresse de la personne 1", "mdp1"));

        Personne personne2 = personneRepository.save(new Personne("NomPersonne2", "PrenomPersonne2",
                "mail.personn2@mail.com", "+33222222222", "2, adresse de la personne 2", "mdp2"));

        Personne personne3 = personneRepository.save(new Personne("NomPersonne3", "PrenomPersonne3",
                "mail.personn3@mail.com", "+33333333333", "3, adresse de la personne 3", "mdp3"));

        Admin admin1 = adminRepository.save(new Admin(personne1, centre1));

        Admin admin2 = adminRepository.save(new Admin(personne2, centre2));

        Admin admin3 = adminRepository.save(new Admin(personne3, centre3));

        adminRepository.deleteById(admin2.getId_admin());

        Iterable admins = adminRepository.findAll();

        assertThat(admins).hasSize(2).contains(admin1, admin3);
    }

}
