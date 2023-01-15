package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

    /***
     * Requêtes SQL afin de récupérer des données dans la table Personne
     ***/

    Personne findByNom(String nom);

    @Query("SELECT p FROM Personne p WHERE p.mail = :mail")
    Optional<Personne> findByMail(@Param("mail") String mail);

    Optional<Personne> findById(@Param("identifiant") Integer identifiant);

    Boolean existsByMail(String email);

    @Query("SELECT pe FROM Personne pe JOIN FETCH pe.roles")
    List<Personne> getListPersonneWithRole();

    @Query("SELECT pe FROM Personne pe JOIN FETCH pe.roles WHERE pe.mail = :email")
    Personne getPersonneWithRole(@Param("email") String email);

    @Query("SELECT p FROM Personne p  where NOT p.mdp = ''")
    List<Personne> getListPersonne();

}
