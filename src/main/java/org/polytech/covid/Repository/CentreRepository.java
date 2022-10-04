package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Integer> {

    List<Centre> findByComnomIgnoreCase(String nomcom);

    List<Centre> findAll();

}
