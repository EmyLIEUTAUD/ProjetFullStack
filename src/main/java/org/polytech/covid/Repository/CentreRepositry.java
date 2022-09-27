package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentreRepositry extends JpaRepository<Centre, Integer> {

    @Query("select v from Centre where v.ville = :ville")
    List<Centre> findByVille(@Param("ville") String ville);

}
