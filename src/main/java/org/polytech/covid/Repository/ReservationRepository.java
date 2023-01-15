package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    /***
     * Requêtes SQL afin de récupérer des données dans la table Reservation
     ***/

    @Query("SELECT r FROM Reservation r WHERE r.centre.gid = :gid AND r.personne.nom LIKE %:nom%")
    List<Reservation> findByPersonneAndCentre(@Param("nom") String nom, @Param("gid") Integer gid);

    @Query("SELECT r FROM Reservation r WHERE r.centre.gid = :gid")
    List<Reservation> findByGid(@Param("gid") Integer gid);

    @Query("SELECT r FROM Reservation r WHERE r.centre.gid = :gid AND r.date_reservation = :date")
    List<Reservation> findByDateAndCentre(@Param("date") LocalDate date, @Param("gid") Integer gid);

}
