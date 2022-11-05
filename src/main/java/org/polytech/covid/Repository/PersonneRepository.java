package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    List<Personne> findByNom(String nom);

    @Query("SELECT p FROM Personne p WHERE p.mail = :mail")
    Optional<Personne> findByMail(@Param("mail") String mail);

    // @Query("SELECT p FROM Personne p WHERE p.identifiant = :identifiant")
    Optional<Personne> findById(@Param("identifiant") Integer identifiant);
}
