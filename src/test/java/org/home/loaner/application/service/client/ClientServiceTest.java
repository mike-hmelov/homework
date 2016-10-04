package org.home.loaner.application.service.client;

import org.home.loaner.application.controller.client.ClientDTO;
import org.home.loaner.application.service.loan.LoanMapper;
import org.home.loaner.domain.Client;
import org.home.loaner.persistence.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.sameInstance;
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

    }
}