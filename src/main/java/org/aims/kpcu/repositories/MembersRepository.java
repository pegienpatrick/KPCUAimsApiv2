package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.CFactory;
import org.aims.kpcu.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembersRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where factory = ?1 and num= ?2 ")
    public Member findFactoryMember(CFactory f,Long num);

    @Query("select m from Member m where factory=?1")
    List<Member> findMemberByFactory(CFactory cf);



}
    

    
