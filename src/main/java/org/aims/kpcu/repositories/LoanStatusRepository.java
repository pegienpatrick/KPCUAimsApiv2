package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanStatusRepository extends JpaRepository<ApplicationStatus,Long> {
}
