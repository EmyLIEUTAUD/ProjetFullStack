package org.polytech.covid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepo extends JpaRepository<Centre, Integer> {

}
