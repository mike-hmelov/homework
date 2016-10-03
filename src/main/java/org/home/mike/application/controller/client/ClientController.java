package org.home.mike.application.controller.client;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.application.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientDTO>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDTO getClient(@PathVariable("clientId") Long clientId) {
        return clientService.getClient(clientId);
    }

    @RequestMapping(path = "/{clientId}/loans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LoanDTO>> getClientsLoans(@PathVariable("clientId") Long clientId) {
        return new ResponseEntity<>(clientService.getClientLoans(clientId), HttpStatus.OK);
    }
}
