package org.home.loaner.application.service.client;

import org.home.loaner.application.controller.client.ClientDTO;
import org.home.loaner.application.controller.loan.LoanDTO;
import org.home.loaner.application.service.loan.LoanMapper;
import org.home.loaner.domain.Client;
import org.home.loaner.domain.Loan;
import org.home.loaner.persistence.ClientRepository;
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
        return client.getLoans()
                .stream()
                .filter(Loan::getApproved)
                .map(l -> loanMapper.map(l))
                .collect(Collectors.toList());
    }

    public void validateForLoan(Client client) {
        if (client.isBlacklisted())
            throw new InvalidClientForLoanException("Client is blacklisted: " + client.getBlacklistReason());

        if (clientRepository.isPersonalIdBlacklisted(client.getPersonalId()))
            throw new InvalidClientForLoanException(String.format("Personal id `%s` is blacklisted", client.getPersonalId()));

    }
}
