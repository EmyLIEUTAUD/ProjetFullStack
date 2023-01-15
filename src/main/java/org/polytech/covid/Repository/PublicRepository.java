package org.polytech.covid.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.polytech.covid.Entity.Public;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PublicRepository extends JpaRepository<Public, Integer> {

    /***
     * Requêtes SQL afin de récupérer des données dans la table Public
     ***/

    @Query("SELECT p FROM Public p WHERE p.personne.identifiant = :identifiant")
    Optional<Public> findByIdentifiant(@Param("identifiant") Integer identifiant);

}
