package org.home.mike.application.service.user;

import org.home.mike.application.controller.user.ClientDTO;
import org.home.mike.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDTO map(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        return clientDTO;
    }
}
