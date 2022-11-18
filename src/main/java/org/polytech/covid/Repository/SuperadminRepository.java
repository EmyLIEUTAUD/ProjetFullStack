package org.polytech.covid.Repository;

import org.polytech.covid.Entity.Superadmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperadminRepository extends JpaRepository<Superadmin, Integer> {

}
