package org.polytech.covid.JPAUnitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTestReservation {
    @Mock
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private CentreRepository centreRepository;

    @Test
    public void should_store_a_reservation(){
        Personne personne = personneRepository.save(new Personne("testNom","testPrenom","testMail","testTelephone","testAdresse","testRole"));
        Centre centre = centreRepository.save(new Centre("Test Centre", "Baccarat", "1", "rue de Baccarat",
                54120,
                "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h", "9h00-12h00/14h-18h",
                "9h00-12h00/14h-18h", "8h00-13h00", "fermé"));
        LocalDate testDate = LocalDate.of(2022,10,03);
        Reservation reservation = reservationRepository.save(new Reservation(testDate,personne,centre));
        assertThat(reservation).hasFieldOrPropertyWithValue("date_reservation", testDate);
        assertThat(reservation).hasFieldOrPropertyWithValue("personne", personne);
        assertThat(reservation).hasFieldOrPropertyWithValue("centre", centre);




    }


}
