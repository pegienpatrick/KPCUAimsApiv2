package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BanksRepository extends JpaRepository<Bank,Long> {

    @Query("select b from Bank b where hint= ?1")
    Bank findByHint(String hint);

    @Query("select b from Bank b where fname like ?1")
    Bank findByName(String fname);



}
