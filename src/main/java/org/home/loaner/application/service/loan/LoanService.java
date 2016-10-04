package org.home.loaner.application.service.loan;

import org.home.loaner.application.controller.loan.LoanDTO;
import org.home.loaner.application.service.client.ClientService;
import org.home.loaner.domain.Client;
import org.home.loaner.domain.Loan;
import org.home.loaner.persistence.LoanRepository;
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
        clientService.validateForLoan(client);
        loan.setClient(client);
        Loan appliedLoan = loanRepository.save(loan);
        return loanMapper.map(appliedLoan);
    }

    public LoanDTO getLoan(Long loanId) {
        Loan loan = loanRepository.getOne(loanId);
        return loanMapper.map(loan);
    }

    public void approveLoan(Long loanId, boolean approve) {
        Loan loan = loanRepository.getOne(loanId);
        loan.setApproved(approve);
        loanRepository.save(loan);
    }
}
