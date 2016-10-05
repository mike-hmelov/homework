package org.home.loaner.persistence;

import org.home.loaner.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> getByApproved(boolean flag);
}
