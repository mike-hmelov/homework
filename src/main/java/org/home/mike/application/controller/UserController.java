package org.home.mike.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path="user")
public class UserController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    String getUsers(){
        return "";
    }

    @RequestMapping(path="/{userId}")
    String getUser(){
        return "";
    }

    @RequestMapping(params = "/{userId}/loans")
    String getUsersLoans(){
        return "";
    }
}
