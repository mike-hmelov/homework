package org.home.mike.application.service.client;

import org.home.mike.application.controller.user.ClientDTO;
import org.home.mike.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDTO map(Client client) {
        if (client == null)
            return null;
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        return clientDTO;
    }

    Client map(ClientDTO clientDTO) {
        if(clientDTO == null)
            return null;
        Client client = new Client();
        return client;
    }
}
