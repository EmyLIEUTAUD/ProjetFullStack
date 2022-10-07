package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

    List<Personne> findByNom(String nom);

    List<Personne> findAll();
}
