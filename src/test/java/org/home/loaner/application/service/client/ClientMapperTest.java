package org.home.loaner.application.service.client;

import org.hamcrest.CoreMatchers;
import org.home.loaner.application.controller.client.ClientDTO;
import org.home.loaner.domain.Client;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ClientMapperTest {
    private ClientMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ClientMapper();
    }

    @Test
    public void mapNull() throws Exception {
        assertThat(mapper.map((Client) null), nullValue());
    }

    @Test
    public void map() throws Exception {
        Client client = new Client();
        client.setId(10L);
        client = spy(client);
        ClientDTO result = mapper.map(client);

        assertThat(result.getId(), CoreMatchers.equalTo(10L));

        verify(client).getId();
        verify(client).getName();
        verify(client).getSurname();
        verify(client).getPersonalId();
        verifyNoMoreInteractions(client);
    }

    @Test
    public void mapBackNull() throws Exception {
        Client client = mapper.map((ClientDTO) null);

        assertThat(client, nullValue());
    }

    @Test
    public void mapBack() throws Exception {
        ClientDTO dto = new ClientDTO();
        dto.setPersonalId("1");
        dto.setName("n");
        dto.setSurname("s");
        dto = spy(dto);

        Client client = mapper.map(dto);

        assertThat(client, notNullValue());
        assertThat(client.getName(), equalTo("n"));
        assertThat(client.getSurname(), equalTo("s"));
        assertThat(client.getPersonalId(), equalTo("1"));

        verify(dto).getName();
        verify(dto).getSurname();
        verify(dto).getPersonalId();
        verifyNoMoreInteractions(dto);
    }
}