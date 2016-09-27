package org.home.mike.application.service.client;

import org.hamcrest.CoreMatchers;
import org.home.mike.application.controller.user.ClientDTO;
import org.home.mike.domain.Client;
import org.junit.Before;
import org.junit.Test;

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
        assertThat(mapper.map((Client) null), CoreMatchers.nullValue());
    }

    @Test
    public void map() throws Exception {
        Client client = new Client();
        client.setId(10L);
        client = spy(client);
        ClientDTO result = mapper.map(client);

        assertThat(result.getId(), CoreMatchers.equalTo(10L));

        verify(client).getId();
        verifyNoMoreInteractions(client);
    }
}