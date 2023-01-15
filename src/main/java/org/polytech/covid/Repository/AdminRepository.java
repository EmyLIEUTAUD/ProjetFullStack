package org.polytech.covid.Repository;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /***
     * Requêtes SQL afin de récupérer des données dans la table Admin
     ***/

    List<Admin> findAll();

    @Query("SELECT a FROM Admin a WHERE a.personne.identifiant = :identifiant")
    Optional<Admin> findByIdentifiant(@Param("identifiant") Integer identifiant);

    @Query("SELECT a FROM Admin a WHERE a.centre.gid = :gid")
    List<Admin> findByGid(@Param("gid") Integer gid);
}
