package org.home.mike.application.service.client;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.application.controller.user.ClientDTO;
import org.home.mike.application.service.loan.LoanMapper;
import org.home.mike.domain.Client;
import org.home.mike.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientService {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LoanMapper loanMapper;

    public Client saveOrUpdate(ClientDTO clientDTO) {
        Client client = clientMapper.map(clientDTO);
        Client existingClient = clientRepository.findByPersonalId(client.getPersonalId());
        if (existingClient != null)
            return existingClient;
        return clientRepository.save(client);
    }

    public List<ClientDTO> getClients() {
        List<Client> all = clientRepository.findAll();
        return all.stream().map(c -> clientMapper.map(c)).collect(Collectors.toList());
    }

    public ClientDTO getClient(Long clientId) {
        Client client = clientRepository.getOne(clientId);
        return clientMapper.map(client);
    }

    public List<LoanDTO> getClientLoans(Long clientId) {
        Client client = clientRepository.getOne(clientId);
        return client.getLoans().stream().map(l -> loanMapper.map(l)).collect(Collectors.toList());
    }
}
