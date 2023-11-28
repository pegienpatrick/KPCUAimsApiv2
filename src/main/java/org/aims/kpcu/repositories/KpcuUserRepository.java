package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.KpcuUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KpcuUserRepository extends JpaRepository<KpcuUser,Long> {
    @Query("select u from KpcuUser u where u.email=?1")
    KpcuUser findByEMail(String email);

    @Query("select u from KpcuUser u where u.username=?1")
    KpcuUser findByUsername(String username);

}
