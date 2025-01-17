package org.home.loaner.application.service.client;

import org.home.loaner.application.controller.client.ClientDTO;
import org.home.loaner.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDTO map(Client client) {
        if (client == null)
            return null;
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setSurname(client.getSurname());
        clientDTO.setPersonalId(client.getPersonalId());
        return clientDTO;
    }

    Client map(ClientDTO clientDTO) {
        if (clientDTO == null)
            return null;
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        client.setPersonalId(clientDTO.getPersonalId());
        return client;
    }
}
