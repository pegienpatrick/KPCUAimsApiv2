package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.CFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FactoriesRepository extends JpaRepository<CFactory,Long> {

    @Query("select c from CFactory c where fname like ?1 ")
    CFactory findByName(String fname);

}
