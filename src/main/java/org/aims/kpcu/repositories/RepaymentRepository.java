package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.CFactory;
import org.aims.kpcu.entities.Member;
import org.aims.kpcu.entities.Repayment;
import org.aims.kpcu.models.repayments.BalanceModel;
import org.aims.kpcu.models.repayments.TmpMembersBalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepaymentRepository extends JpaRepository<Repayment,Long> {


    @Query("select r from Repayment r where r.factory=?1 and r.season like ?2 and payment like ?3")
    Optional<Repayment> findRepayment(CFactory f, String season, String payment);

    @Query("SELECT SUM(repayment.amount) FROM Repayment r JOIN r.repayments repayment WHERE r.num = ?1")
    Double getAmoundByRepayment(Long num);



//    @Query("select a.member.num,(coasce(sum(a.amount),0.0)-coasce( (select sum(r.amount) from MemberRepayment r where r.member=a.member) ,0.0)) as  from LoanApplication a where a.member.factory=?1 group by a.member")
//    List<BalanceModel> balanceByFactory(CFactory cf);

    @Query("SELECT a.member.id AS member, (COALESCE(SUM(a.amount), 0.0) - COALESCE((SELECT SUM(r.amount) FROM MemberRepayment r WHERE r.member = a.member), 0.0)) AS amount FROM LoanApplication a WHERE a.member.factory = ?1 and a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id is not null GROUP BY a.member.id")
    List<BalanceModel> balanceByFactory(CFactory cf);

    @Query("SELECT (COALESCE(SUM(a.amount), 0.0) - COALESCE((SELECT SUM(r.amount) FROM MemberRepayment r WHERE r.member = a.member), 0.0)) AS amount FROM LoanApplication a WHERE a.member = ?1 and a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id is not null GROUP BY a.member")
    Double balanceByMember(Member m);



//    @Query("SELECT a.member AS member, (COALESCE(SUM(a.amount), 0.0) - COALESCE((SELECT SUM(r.amount) FROM MemberRepayment r WHERE r.member = a.member), 0.0)) AS amount FROM LoanApplication a WHERE a.member.factory = ?1 and a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id is not null GROUP BY a.member")

    @Query("SELECT a.member.id AS member, (COALESCE(SUM(a.amount), 0.0) - COALESCE((SELECT SUM(r.amount) FROM MemberRepayment r WHERE r.member = a.member), 0.0)) AS amount FROM LoanApplication a WHERE a.member.factory = ?1 and a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id is not null group by a.member")
    List<TmpMembersBalanceModel> memberBalanceByFactory(CFactory cf);


//    @Query("select m from Member m where factory = ?1 and num= ?2 ")
//    public Member findMember(CFactory f, Long num);
//
//    @Query("select m from Member m where factory=?1")
//    List<Member> findByFactory(CFactory cf);




}
