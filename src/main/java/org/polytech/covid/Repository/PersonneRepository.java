package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    List<Personne> findByNom(String nom);

    Personne findPersonneByTelephone(String telephone);
}
