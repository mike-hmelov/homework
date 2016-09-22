package org.home.mike.application.service.loan;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.domain.Loan;
import org.home.mike.persistence.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanMapper loanMapper;

    public List<LoanDTO> getLoans() {
        //~ apply some pagination here?
        List<Loan> approved = loanRepository.getByApproved(true);
        return approved
                .stream()
                .map(loan -> loanMapper.map(loan))
                .collect(Collectors.toList());
    }
}
