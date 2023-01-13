package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import liquibase.pro.packaged.P;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // @Query("SELECT r in Reservation r WHERE r.centre.gid = :gid AND
    // r.personne.identifiant = (SELECT p.identifiant FROM Personne p WHERE p.nom =
    // :nom)")
    // List<Reservation> findByPersonneInAndCentre(List<Personne> personne, Integer
    // gid);

    // @Query("SELECT r FROM Reservation r WHERE r.centre.gid = :gid AND
    // r.personne.identifiant = (SELECT p.identifiant FROM Personne p WHERE p.nom =
    // :nom)")
    @Query("SELECT r FROM Reservation r WHERE r.centre.gid = :gid AND r.personne.nom LIKE %:nom%")
    List<Reservation> findByPersonneAndCentre(@Param("nom") String nom, @Param("gid") Integer gid);

    @Query("SELECT r FROM Reservation r WHERE r.centre.gid = :gid")
    List<Reservation> findByGid(@Param("gid") Integer gid);

    @Query("SELECT r FROM Reservation r WHERE r.centre.gid = :gid AND r.date_reservation = :date")
    List<Reservation> findByDateAndCentre(@Param("date") String date, @Param("gid") Integer gid);

}
