package org.home.mike.application.service.loan;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.application.service.user.ClientMapper;
import org.home.mike.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class LoanMapper {
    @Autowired
    private ClientMapper clientMapper;

    LoanDTO map(Loan loan) {
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
}
