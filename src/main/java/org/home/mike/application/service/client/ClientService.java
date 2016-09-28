package org.home.mike.application.service.client;

import org.home.mike.application.controller.user.ClientDTO;
import org.home.mike.domain.Client;
import org.home.mike.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientRepository clientRepository;

    public Client saveOrUpdate(ClientDTO clientDTO) {
        Client client = clientMapper.map(clientDTO);
        Client existingClient = clientRepository.findByPersonalId(client.getPersonalId());
        if (existingClient != null)
            return existingClient;
        return clientRepository.save(client);
    }
}
