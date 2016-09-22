package org.home.mike.persistence;

import org.home.mike.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> getByApproved(boolean flag);
}
