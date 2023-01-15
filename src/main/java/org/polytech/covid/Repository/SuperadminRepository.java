package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Superadmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperadminRepository extends JpaRepository<Superadmin, Integer> {

    /***
     * Requêtes SQL afin de récupérer des données dans la table Superadmin
     ***/

    List<Superadmin> findAll();

}
