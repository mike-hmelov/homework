package org.home.mike.application.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path="client")
public class ClientController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    String getClients(){
        return "";
    }

    @RequestMapping(path="/{clientId}")
    String getClient(){
        return "";
    }

    @RequestMapping(params = "/{clientId}/loans")
    String getClientsLoans(){
        return "";
    }
}
