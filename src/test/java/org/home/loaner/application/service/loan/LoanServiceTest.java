package org.home.loaner.application.service.loan;

import org.home.loaner.application.controller.loan.LoanDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {
    @InjectMocks
    LoanService loanService;

    @Test
    public void getLoans() throws Exception {
        List<LoanDTO> loans = loanService.getLoans();
    }

    @Test
    public void applyLoan() throws Exception {

    }

    @Test
    public void getLoan() throws Exception {

    }

    @Test
    public void approveLoan() throws Exception {

    }

}