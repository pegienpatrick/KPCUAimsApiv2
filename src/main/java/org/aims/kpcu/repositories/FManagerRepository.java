package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.FManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FManagerRepository extends JpaRepository<FManager,Long> {

    @Query("select u from FManager u where email=?1")
    FManager findByEMail(String email);

    @Query("select u from FManager u where username=?1")
    FManager findByUsername(String username);

    @Query("select u from FManager u where username=?1")
    Optional<FManager> findManagerByUsername(String username);
}
