package org.polytech.covid.Repository;

import java.util.List;

import org.polytech.covid.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

    List<Medecin> findAll();

}
