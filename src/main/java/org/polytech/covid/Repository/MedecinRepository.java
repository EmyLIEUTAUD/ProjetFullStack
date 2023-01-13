package org.polytech.covid.Repository;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

    List<Medecin> findAll();

    @Query("SELECT m FROM Medecin m WHERE m.personne.identifiant = :identifiant")
    Optional<Medecin> findMedecinByID(@Param("identifiant") Integer identifiant);

    @Query("SELECT m FROM Medecin m WHERE m.centre.gid = :gid")
    List<Medecin> findByGid(@Param("gid") Integer gid);

    @Query("SELECT a FROM Medecin a WHERE a.personne.identifiant = :identifiant")
    Optional<Medecin> findByIdentifiant(@Param("identifiant") Integer identifiant);

}
