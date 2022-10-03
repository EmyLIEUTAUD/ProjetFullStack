package org.polytech.covid.JPAUnitTests;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Repository.CentreRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JPAUnitTestCentres {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private CentreRepositry centreRepository;

        @Test
        public void should_find_no_centers_if_repository_is_empty() {
                Iterable centres = centreRepository.findAll();

                assertThat(centres).isEmpty();
        }

        @Test
        public void should_store_a_center() {
                Centre centre = centreRepository.save(new Centre("Test Centre", "Baccarat", "1", "rue de Baccarat",
                                54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));

                assertThat(centre).hasFieldOrPropertyWithValue("nom", "Test Centre");
                assertThat(centre).hasFieldOrPropertyWithValue("comnom", "Baccarat");
                assertThat(centre).hasFieldOrPropertyWithValue("numAdresse", "1");
                assertThat(centre).hasFieldOrPropertyWithValue("adresse", "rue de Baccarat");
                assertThat(centre).hasFieldOrPropertyWithValue("cp", 54120);
                assertThat(centre).hasFieldOrPropertyWithValue("horairesLundi", "9h00-12h00/14h-18h");
                assertThat(centre).hasFieldOrPropertyWithValue("horairesMardi", "9h00-12h00/14h-18h");
                assertThat(centre).hasFieldOrPropertyWithValue("horairesMercredi", "9h00-12h00/14h-18h");
                assertThat(centre).hasFieldOrPropertyWithValue("horairesJeudi", "9h00-12h00/14h-18h");
                assertThat(centre).hasFieldOrPropertyWithValue("horairesVendredi", "9h00-12h00/14h-18h");
                assertThat(centre).hasFieldOrPropertyWithValue("horairesSamedi", "8h00-13h00");
                assertThat(centre).hasFieldOrPropertyWithValue("horairesDimanche", "fermé");
        }

        @Test
        public void should_find_all_centers() {
                Centre centre1 = new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre1);

                Centre centre2 = new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre2);

                Centre centre3 = new Centre("Test Centre 3", "Jarville", "3", "rue de Jarville", 54140,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-17h00", "9h30-11h30");
                entityManager.persist(centre3);

                Iterable centres = centreRepository.findAll();

                assertThat(centres).hasSize(3).contains(centre1, centre2, centre3);
        }

        @Test
        public void should_find_tutorial_by_id() {
                Centre centre1 = new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre1);

                Centre centre2 = new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre2);

                Centre centre3 = new Centre("Test Centre 3", "arville", "3", "rue de Jarville", 54140,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-17h00", "9h30-11h30");
                entityManager.persist(centre3);

                Iterable centres = centreRepository.findByComnom("Baccarat");

                assertThat(centres).hasSize(2).contains(centre1, centre2);
        }

        @Test
        public void should_update_centre_by_id() {
                Centre centre1 = new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre1);

                Centre centre2 = new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre2);

                Centre updatedCentre = new Centre("updated Centre 1", "Baccarat", "1", "rue de Baccarat updated", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "fermé", "fermé");

                Centre centre = centreRepository.findById(centre1.getGid()).get();
                centre.setNom(updatedCentre.getNom());
                centre.setComnom(updatedCentre.getComnom());
                centre.setNumAdresse(updatedCentre.getNumAdresse());
                centre.setAdresse(updatedCentre.getAdresse());
                centre.setCp(updatedCentre.getCp());
                centre.setHorairesLundi(updatedCentre.getHorairesLundi());
                centre.setHorairesMardi(updatedCentre.getHorairesMardi());
                centre.setHorairesMercredi(updatedCentre.getHorairesMercredi());
                centre.setHorairesJeudi(updatedCentre.getHorairesJeudi());
                centre.setHorairesVendredi(updatedCentre.getHorairesVendredi());
                centre.setHorairesSamedi(updatedCentre.getHorairesSamedi());
                centre.setHorairesDimanche(updatedCentre.getHorairesDimanche());
                centreRepository.save(centre);

                Centre checkCentre = centreRepository.findById(centre1.getGid()).get();

                assertThat(checkCentre.getGid()).isEqualTo(centre1.getGid());
                assertThat(checkCentre.getNom()).isEqualTo(updatedCentre.getNom());
                assertThat(checkCentre.getComnom()).isEqualTo(updatedCentre.getComnom());
                assertThat(checkCentre.getNumAdresse()).isEqualTo(updatedCentre.getNumAdresse());
                assertThat(checkCentre.getAdresse()).isEqualTo(updatedCentre.getAdresse());
                assertThat(checkCentre.getCp()).isEqualTo(updatedCentre.getCp());
                assertThat(checkCentre.getHorairesLundi()).isEqualTo(updatedCentre.getHorairesLundi());
                assertThat(checkCentre.getHorairesMardi()).isEqualTo(updatedCentre.getHorairesMardi());
                assertThat(checkCentre.getHorairesMercredi()).isEqualTo(updatedCentre.getHorairesMercredi());
                assertThat(checkCentre.getHorairesJeudi()).isEqualTo(updatedCentre.getHorairesJeudi());
                assertThat(checkCentre.getHorairesVendredi()).isEqualTo(updatedCentre.getHorairesVendredi());
                assertThat(checkCentre.getHorairesSamedi()).isEqualTo(updatedCentre.getHorairesSamedi());
                assertThat(checkCentre.getHorairesDimanche()).isEqualTo(updatedCentre.getHorairesDimanche());
        }

        @Test
        public void should_delete_centre_by_id() {
                Centre centre1 = new Centre("Test Centre 1", "Baccarat", "1", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre1);

                Centre centre2 = new Centre("Test Centre 2", "Baccarat", "2", "rue de Baccarat", 54120,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-13h00", "fermé");
                entityManager.persist(centre2);

                Centre centre3 = new Centre("Test Centre 3", "arville", "3", "rue de Jarville", 54140,
                                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                                "9h00-12h00/14h-18h", "8h00-17h00", "9h30-11h30");
                entityManager.persist(centre3);

                centreRepository.deleteById(centre2.getGid());

                Iterable centres = centreRepository.findAll();

                assertThat(centres).hasSize(2).contains(centre1, centre3);
        }

}
