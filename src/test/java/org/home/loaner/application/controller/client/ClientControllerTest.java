package org.home.loaner.application.controller.client;

import org.home.loaner.application.controller.loan.LoanDTO;
import org.home.loaner.application.service.client.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {
    @InjectMocks
    ClientController controller;

    @Mock
    ClientService clientService;

    @Test
    public void getClients() throws Exception {
        ArrayList<ClientDTO> clientList = new ArrayList<>();
        when(clientService.getClients()).thenReturn(clientList);
        ResponseEntity<List<ClientDTO>> result = controller.getClients();

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(result.getBody(), sameInstance(clientList));
        verify(clientService).getClients();
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void getClient() throws Exception {
        ClientDTO dto = new ClientDTO();
        when(clientService.getClient(10L)).thenReturn(dto);
        ClientDTO result = controller.getClient(10L);

        assertThat(result, sameInstance(dto));
        verify(clientService).getClient(10L);
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void getClientsLoans() throws Exception {
        ArrayList<LoanDTO> list = new ArrayList<>();
        when(clientService.getClientLoans(10L)).thenReturn(list);
        ResponseEntity<List<LoanDTO>> result = controller.getClientsLoans(10L);

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(result.getBody(), sameInstance(list));
    }

}