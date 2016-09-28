package org.home.mike.application.controller.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "client")
public class ClientController {
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getClients() {
        return "";
    }

    @RequestMapping(path = "/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getClient() {
        return "";
    }

    @RequestMapping(params = "/{clientId}/loans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getClientsLoans() {
        return "";
    }
}
