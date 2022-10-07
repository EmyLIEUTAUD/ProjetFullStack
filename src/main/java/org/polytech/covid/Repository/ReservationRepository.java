package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    @Query(value = "SELECT * FROM reservation r WHERE r.identifiant=?1 AND r.gid=?2", nativeQuery = true)
    List<Reservation> findByPersonneAndCentre(List<Personne> personne, Centre centre);
}
