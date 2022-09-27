package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Centre,Integer> {

}
