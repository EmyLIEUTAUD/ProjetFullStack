package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Integer> {

    @Query("SELECT c FROM Centre c WHERE c.comnom LIKE %:ville%")
    List<Centre> findByComnomIgnoreCase(@Param("ville") String nomcom);

    List<Centre> findAll();

    @Query("SELECT c FROM Centre c WHERE c.gid = :gid")
    Optional<Centre> findById(@Param("gid") Integer gid);

}
