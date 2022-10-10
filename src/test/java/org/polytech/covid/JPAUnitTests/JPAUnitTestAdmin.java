package org.polytech.covid.JPAUnitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.MedecinRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JPAUnitTestAdmin {

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private CentreRepository centreRepository;

    @Test
    public void should_find_no_medecins_if_repository_is_empty() {
        Iterable medecins = medecinRepository.findAll();

        assertThat(medecins).isEmpty();
    }

    @Test
    public void should_store_a_medecin() {
        Personne personne = personneRepository.save(new Personne("NomPersonne", "PrenomPersonne",
                "mail.personne@mail.com", "+33111111111", "1, adresse de la personne"));

        Centre centre = centreRepository.save(new Centre("Test Centre", "Baccarat", "1", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Medecin medecin = medecinRepository.save(new Medecin(personne, centre));

        assertThat(medecin.getPersonne().getIdentifiant()).isEqualTo(personne.getIdentifiant());
        assertThat(medecin.getPersonne().getNom()).isEqualTo(personne.getNom());
        assertThat(medecin.getPersonne().getPrenom()).isEqualTo(personne.getPrenom());
        assertThat(medecin.getPersonne().getMail()).isEqualTo(personne.getMail());
        assertThat(medecin.getPersonne().getTelephone()).isEqualTo(personne.getTelephone());
        assertThat(medecin.getPersonne().getAdresse()).isEqualTo(personne.getAdresse());
        assertThat(medecin.getCentre().getGid()).isEqualTo(centre.getGid());
        assertThat(medecin.getCentre().getNom()).isEqualTo(centre.getNom());
        assertThat(medecin.getCentre().getComnom()).isEqualTo(centre.getComnom());
        assertThat(medecin.getCentre().getNumAdresse()).isEqualTo(centre.getNumAdresse());
        assertThat(medecin.getCentre().getAdresse()).isEqualTo(centre.getAdresse());
        assertThat(medecin.getCentre().getCp()).isEqualTo(centre.getCp());
        assertThat(medecin.getCentre().getHorairesLundi()).isEqualTo(centre.getHorairesLundi());
        assertThat(medecin.getCentre().getHorairesMardi()).isEqualTo(centre.getHorairesMardi());
        assertThat(medecin.getCentre().getHorairesMercredi()).isEqualTo(centre.getHorairesMercredi());
        assertThat(medecin.getCentre().getHorairesJeudi()).isEqualTo(centre.getHorairesJeudi());
        assertThat(medecin.getCentre().getHorairesVendredi()).isEqualTo(centre.getHorairesVendredi());
        assertThat(medecin.getCentre().getHorairesSamedi()).isEqualTo(centre.getHorairesSamedi());
        assertThat(medecin.getCentre().getHorairesDimanche()).isEqualTo(centre.getHorairesDimanche());
    }

    @Test
    public void should_find_all_medecins() {
        Personne personne1 = personneRepository.save(new Personne("NomPersonne1", "PrenomPersonne1",
                "mail.personne1@mail.com", "+33111111111", "1, adresse de la personne 1"));

        Personne personne2 = personneRepository.save(new Personne("NomPersonne2", "PrenomPersonne2",
                "mail.personn2@mail.com", "+33222222222", "2, adresse de la personne 2"));

        Personne personne3 = personneRepository.save(new Personne("NomPersonne3", "PrenomPersonne3",
                "mail.personn3@mail.com", "+33333333333", "3, adresse de la personne 3"));

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

        Medecin medecin1 = medecinRepository.save(new Medecin(personne1, centre1));

        Medecin medecin2 = medecinRepository.save(new Medecin(personne2, centre2));

        Medecin medecin3 = medecinRepository.save(new Medecin(personne3, centre3));

        Iterable medecins = medecinRepository.findAll();

        assertThat(medecins).hasSize(3).contains(medecin1, medecin2, medecin3);
    }

    @Test
    public void should_update_medecin_by_id() {
        Centre centre1 = centreRepository.save(new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Centre centre2 = centreRepository.save(new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

        Personne personne1 = personneRepository.save(new Personne("NomPersonne1", "PrenomPersonne1",
                "mail.personne1@mail.com", "+33111111111", "1, adresse de la personne 1"));

        Medecin medecin1 = medecinRepository.save(new Medecin(personne1, centre1));

        Medecin updatedMedecin = new Medecin(personne1, centre2);

        Medecin medecin = medecinRepository.findById(medecin1.getId_medecin()).get();
        medecin.setPersonne(updatedMedecin.getPersonne());
        medecin.setCentre(updatedMedecin.getCentre());
        medecinRepository.save(medecin);

        Medecin checkMedecin = medecinRepository.findById(medecin1.getId_medecin()).get();

        assertThat(checkMedecin.getId_medecin()).isEqualTo(medecin1.getId_medecin());
        assertThat(checkMedecin.getPersonne()).isEqualTo(updatedMedecin.getPersonne());
        assertThat(checkMedecin.getCentre()).isEqualTo(updatedMedecin.getCentre());
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
                "mail.personne1@mail.com", "+33111111111", "1, adresse de la personne 1"));

        Personne personne2 = personneRepository.save(new Personne("NomPersonne2", "PrenomPersonne2",
                "mail.personn2@mail.com", "+33222222222", "2, adresse de la personne 2"));

        Personne personne3 = personneRepository.save(new Personne("NomPersonne3", "PrenomPersonne3",
                "mail.personn3@mail.com", "+33333333333", "3, adresse de la personne 3"));

        Medecin medecin1 = medecinRepository.save(new Medecin(personne1, centre1));

        Medecin medecin2 = medecinRepository.save(new Medecin(personne2, centre2));

        Medecin medecin3 = medecinRepository.save(new Medecin(personne3, centre3));

        medecinRepository.deleteById(medecin2.getId_medecin());

        Iterable medecins = medecinRepository.findAll();

        assertThat(medecins).hasSize(2).contains(medecin1, medecin3);
    }

}
