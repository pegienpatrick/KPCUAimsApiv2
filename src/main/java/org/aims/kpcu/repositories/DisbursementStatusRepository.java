package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.DisbursementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisbursementStatusRepository extends JpaRepository<DisbursementStatus,Long> {
}
