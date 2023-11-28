package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.CFactory;
import org.aims.kpcu.entities.FManager;
import org.aims.kpcu.entities.LoanApplication;
import org.aims.kpcu.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanApplicationsRepository extends JpaRepository<LoanApplication,Long> {

    @Query("select a from LoanApplication a join Member m where m.factory=?1")
    List<LoanApplication> findFactoryApplications(CFactory factory);

    @Query("select a from LoanApplication a where a.applicationType = org.aims.kpcu.entities.ApplicationType.INDIVIDUAL and a.member = ?1")
    List<LoanApplication> findMyApplications(Member member);

    @Query("select a from LoanApplication a where a.member=?1")
    List<LoanApplication> findMemberApplications(Member member);

    @Query("select a from LoanApplication a where a.applicationType= org.aims.kpcu.entities.ApplicationType.MANAGER and maker=?1")
    List<LoanApplication> findMyApplications(FManager maker);


    @Query("select a from LoanApplication a where a.member.factory=?1 and a.status.response=org.aims.kpcu.entities.ApplicationResponse.PENDING ")
    List<LoanApplication> findPendingApplications(CFactory paramCFactory);

    @Query("select a from LoanApplication a where a.member.factory=?1 and a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED ")
    List<LoanApplication> findApprovedApplications(CFactory paramCFactory);

    @Query("select a from LoanApplication a where a.member.factory=?1 and a.status.response=org.aims.kpcu.entities.ApplicationResponse.REJECTED ")
    List<LoanApplication> findRejectedApplications(CFactory paramCFactory);

    @Query("select a from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus is null")
    List<LoanApplication> findPendingDisbursements();

    @Query("select sum(a.amount) from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus is null")
    String findPendingDisbursementAmount();

    @Query("select a from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id =?1")
    List<LoanApplication> findDisbursements(Long paramLong);

    @Query("select sum(a.amount) from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id =?1")
    String findDisbursementAmount(Long paramLong);

    @Query("select a.bank from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id =?1 group by a.bank")
    List<String> findDisbursementBanks(Long paramLong);

    @Query("select a from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id =?1 and a.bank like ?2 ")
    List<LoanApplication> findDisbursements(Long paramLong, String paramString);

    @Query("select sum(a.amount) from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id =?1 and a.bank like ?2 ")
    String findDisbursementAmounts(Long paramLong, String paramString);


    @Query("select a from LoanApplication a where  a.status.response=org.aims.kpcu.entities.ApplicationResponse.APPROVED and a.status.disbursementStatus.id is not null and a.member.factory=?1")
   List<LoanApplication> findFactoryDisbursments(CFactory f);
}
