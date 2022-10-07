package org.polytech.covid.Repository;

import java.util.List;

import org.polytech.covid.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    List<Admin> findAll();

}
