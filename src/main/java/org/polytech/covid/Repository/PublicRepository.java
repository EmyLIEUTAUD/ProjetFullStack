package org.polytech.covid.Repository;

import org.springframework.stereotype.Repository;
import org.polytech.covid.Entity.Public;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PublicRepository extends JpaRepository<Public, Integer> {

}
