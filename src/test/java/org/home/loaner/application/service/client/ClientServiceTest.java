package org.home.loaner.application.service.client;

import org.home.loaner.application.controller.client.ClientDTO;
import org.home.loaner.application.controller.loan.LoanDTO;
import org.home.loaner.application.service.loan.LoanMapper;
import org.home.loaner.domain.Client;
import org.home.loaner.domain.Loan;
import org.home.loaner.persistence.ClientRepository;
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
public class ClientServiceTest {
    @InjectMocks
    ClientService clientService;

    @Mock
    ClientMapper clientMapper;
    @Mock
    ClientRepository clientRepository;
    @Mock
    LoanMapper loanMapper;

    @Test
    public void saveOrUpdateExists() throws Exception {
        ClientDTO dto = new ClientDTO();
        Client client = new Client();
        Client existing = new Client();
        client.setPersonalId("1");
        when(clientMapper.map(dto)).thenReturn(client);
        when(clientRepository.findByPersonalId("1")).thenReturn(existing);

        Client result = clientService.saveOrUpdate(dto);

        verify(clientRepository).findByPersonalId("1");
        verify(clientMapper).map(dto);

        assertThat(result, sameInstance(existing));

        verifyNoMoreInteractions(clientMapper);
        verifyNoMoreInteractions(clientRepository);
        verifyZeroInteractions(loanMapper);
    }

    @Test
    public void saveOrUpdateNew() throws Exception {
        ClientDTO dto = new ClientDTO();
        Client client = new Client();
        client.setPersonalId("1");
        when(clientMapper.map(dto)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.saveOrUpdate(dto);

        verify(clientRepository).findByPersonalId("1");
        verify(clientMapper).map(dto);
        verify(clientRepository).save(client);

        assertThat(result, sameInstance(client));

        verifyNoMoreInteractions(clientMapper);
        verifyNoMoreInteractions(clientRepository);
        verifyZeroInteractions(loanMapper);
    }

    @Test
    public void getClients() throws Exception {
        Client c1 = new Client();
        Client c2 = new Client();
        List<Client> list = Arrays.asList(c1, c2);
        when(clientRepository.findAll()).thenReturn(list);
        ClientDTO dto1 = new ClientDTO();
        ClientDTO dto2 = new ClientDTO();
        when(clientMapper.map(same(c1))).thenReturn(dto1);
        when(clientMapper.map(same(c2))).thenReturn(dto2);

        List<ClientDTO> clients = clientService.getClients();
        assertThat(clients, notNullValue());
        assertThat(clients.size(), equalTo(2));
        assertThat(clients.get(0), sameInstance(dto1));
        assertThat(clients.get(1), sameInstance(dto2));

        verify(clientRepository).findAll();
        verifyNoMoreInteractions(clientRepository);
        verify(clientMapper).map(same(c1));
        verify(clientMapper).map(same(c2));
        verifyNoMoreInteractions(clientMapper);
        verifyNoMoreInteractions(loanMapper);
    }

    @Test
    public void getClient() throws Exception {
        Client c1 = new Client();
        when(clientRepository.getOne(1L)).thenReturn(c1);
        ClientDTO dto = new ClientDTO();
        when(clientMapper.map(c1)).thenReturn(dto);

        ClientDTO result = clientService.getClient(1L);

        assertThat(result, sameInstance(dto));

        verify(clientMapper).map(c1);
        verifyNoMoreInteractions(clientMapper);
        verify(clientRepository).getOne(1L);
        verifyNoMoreInteractions(clientRepository);
        verifyZeroInteractions(loanMapper);
    }

    @Test
    public void clientLoans() throws Exception {
        Client client = new Client();
        Loan l1 = new Loan();
        Loan l2 = new Loan();
        Loan l3 = new Loan();
        l1.setApproved(true);
        l2.setApproved(false);
        l3.setApproved(true);
        client.setLoans(Arrays.asList(l1, l2, l3));

        when(clientRepository.getOne(1L)).thenReturn(client);

        LoanDTO dto1 = new LoanDTO();
        LoanDTO dto2 = new LoanDTO();
        when(loanMapper.map(same(l1))).thenReturn(dto1);
        when(loanMapper.map(same(l3))).thenReturn(dto2);

        List<LoanDTO> result = clientService.getClientLoans(1L);
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), sameInstance(dto1));
        assertThat(result.get(1), sameInstance(dto2));

        verifyZeroInteractions(clientMapper);
        verify(clientRepository).getOne(1L);
        verifyNoMoreInteractions(clientRepository);
        verify(loanMapper).map(same(l1));
        verify(loanMapper).map(same(l3));
        verifyNoMoreInteractions(loanMapper);
    }

    @Test(expected = InvalidClientForLoanException.class)
    public void blacklistedClient() throws Exception {
        Client client = new Client();
        client.setBlacklistReason("hello");

        clientService.validateForLoan(client);

        verifyZeroInteractions(clientMapper);
        verifyZeroInteractions(clientRepository);
        verifyZeroInteractions(loanMapper);
    }

    @Test(expected = InvalidClientForLoanException.class)
    public void blacklistedId() throws Exception {
        Client client = new Client();
        client.setPersonalId("1");
        when(clientRepository.isPersonalIdBlacklisted("1")).thenReturn(true);

        clientService.validateForLoan(client);

        verifyZeroInteractions(clientMapper);
        verify(clientRepository).isPersonalIdBlacklisted("1");
        verifyNoMoreInteractions(clientRepository);
        verifyZeroInteractions(loanMapper);
    }


}