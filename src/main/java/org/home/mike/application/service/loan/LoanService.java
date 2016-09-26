package org.home.mike.application.service.loan;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.domain.Client;
import org.home.mike.domain.Loan;
import org.home.mike.persistence.ClientRepository;
import org.home.mike.persistence.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientRepository clientRepository;
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

    public void applyLoan() {
        Loan loan = new Loan();
        loan.setTerm(LocalDate.now());
        loan.setAmount(new BigDecimal(10000));
        Client client = new Client();
        clientRepository.save(client);
        loan.setClient(client);
        loanRepository.save(loan);
    }
}
