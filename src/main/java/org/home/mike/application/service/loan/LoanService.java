package org.home.mike.application.service.loan;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.application.service.client.ClientService;
import org.home.mike.domain.Client;
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
    @Autowired
    private ClientService clientService;

    public List<LoanDTO> getLoans() {
        //TODO apply some pagination here?
        List<Loan> approved = loanRepository.getByApproved(true);
        return approved
                .stream()
                .map(loan -> loanMapper.map(loan))
                .collect(Collectors.toList());
    }

    public LoanDTO applyLoan(LoanDTO newLoan) {
        Loan loan = loanMapper.map(newLoan);
        Client client = clientService.saveOrUpdate(newLoan.getClient());
        loan.setClient(client);
        Loan appliedLoan = loanRepository.save(loan);
        return loanMapper.map(appliedLoan);
    }

    public LoanDTO getLoan(Long loanId) {
        Loan loan = loanRepository.getOne(loanId);
        return loanMapper.map(loan);
    }
}
