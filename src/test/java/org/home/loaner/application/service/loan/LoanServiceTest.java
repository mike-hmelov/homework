package org.home.loaner.application.service.loan;

import org.home.loaner.application.controller.client.ClientDTO;
import org.home.loaner.application.controller.loan.LoanDTO;
import org.home.loaner.application.service.client.ClientService;
import org.home.loaner.domain.Client;
import org.home.loaner.domain.Loan;
import org.home.loaner.persistence.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {
    @InjectMocks
    LoanService loanService;

    @Mock
    LoanRepository loanRepository;
    @Mock
    LoanMapper loanMapper;
    @Mock
    ClientService clientService;

    @Test
    public void getLoans() throws Exception {
        Loan l1 = new Loan();
        Loan l2 = new Loan();
        when(loanRepository.getByApproved(true)).thenReturn(Arrays.asList(l1, l2));
        LoanDTO dto1 = new LoanDTO();
        LoanDTO dto2 = new LoanDTO();
        when(loanMapper.map(same(l1))).thenReturn(dto1);
        when(loanMapper.map(same(l2))).thenReturn(dto2);

        List<LoanDTO> loans = loanService.getLoans();

        assertThat(loans, notNullValue());
        assertThat(loans.size(), equalTo(2));
        assertThat(loans.get(0), sameInstance(dto1));
        assertThat(loans.get(1), sameInstance(dto2));

        verify(loanRepository).getByApproved(true);
        verifyNoMoreInteractions(loanRepository);
        verify(loanMapper).map(same(l1));
        verify(loanMapper).map(same(l2));
        verifyNoMoreInteractions(loanMapper);
        verifyZeroInteractions(clientService);
    }

    @Test
    public void applyLoan() throws Exception {
        LoanDTO dto = new LoanDTO();
        ClientDTO clientDTO = new ClientDTO();
        dto.setClient(clientDTO);
        Loan l1 = new Loan();
        Client c1 = new Client();
        l1.setClient(c1);
        when(loanMapper.map(dto)).thenReturn(l1);
        when(clientService.saveOrUpdate(clientDTO)).thenReturn(c1);
        when(loanRepository.save(l1)).thenReturn(l1);
        when(loanMapper.map(l1)).thenReturn(dto);

        LoanDTO result = loanService.applyLoan(dto);
        assertThat(result, sameInstance(dto));

        verify(loanMapper).map(dto);
        verify(loanMapper).map(l1);
        verifyNoMoreInteractions(loanMapper);
        verify(clientService).saveOrUpdate(clientDTO);
        verify(clientService).validateForLoan(c1);
        verifyNoMoreInteractions(clientService);
        verify(loanRepository).save(l1);
        verifyNoMoreInteractions(loanRepository);
    }

    @Test
    public void getLoan() throws Exception {
        Loan l1 = new Loan();
        when(loanRepository.getOne(1L)).thenReturn(l1);
        LoanDTO dto = new LoanDTO();
        when(loanMapper.map(l1)).thenReturn(dto);

        LoanDTO result = loanService.getLoan(1L);
        assertThat(result, sameInstance(dto));

        verify(loanRepository).getOne(1L);
        verifyNoMoreInteractions(loanRepository);
        verifyZeroInteractions(clientService);
        verify(loanMapper).map(l1);
        verifyNoMoreInteractions(loanMapper);
    }

}