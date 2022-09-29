package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CentreRepositry extends JpaRepository<Centre,Integer> {
    List<Centre> findByComnom(String nomcom);


}
