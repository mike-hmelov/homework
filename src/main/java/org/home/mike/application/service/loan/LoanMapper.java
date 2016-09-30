package org.home.mike.application.service.loan;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.application.service.client.ClientMapper;
import org.home.mike.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    @Autowired
    private ClientMapper clientMapper;

    public LoanDTO map(Loan loan) {
        if(loan == null)
            return null;
        //TODO make it automatically??
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setAmount(loan.getAmount());
        loanDTO.setClient(clientMapper.map(loan.getClient()));
        loanDTO.setTerm(loan.getTerm());
        return loanDTO;
    }

    Loan map(LoanDTO loanDTO) {
        if(loanDTO == null)
            return null;
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setApproved(false);
        loan.setTerm(loanDTO.getTerm());
        return loan;
    }
}
