package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    List<Personne> findByNom(String nom);

    Personne findPersonneByTelephone(String telephone);

    @Query("SELECT p FROM Personne p WHERE p.mail = :mail")
    Optional<Personne> findByMail(@Param("mail") String mail);
}
