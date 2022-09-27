package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CentreRepositry extends JpaRepository<Centre,Integer> {
    List<Centre> findByVilleId(Integer villeId);

}
