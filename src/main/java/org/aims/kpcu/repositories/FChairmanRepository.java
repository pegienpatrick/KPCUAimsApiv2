package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.CFactory;
import org.aims.kpcu.entities.FChairman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FChairmanRepository extends JpaRepository<FChairman,Long> {
    @Query("select u from FChairman u where email=?1")
    FChairman findByEMail(String email);

    @Query("select u from FChairman u where username=?1")
    FChairman findByUsername(String username);

    @Query("SELECT c FROM FChairman c join c.roles r WHERE r = ?1")
    List<FChairman> findChairmansForFactory(CFactory cf);


}
