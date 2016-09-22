package org.home.mike.application.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/loan")
public class LoanController {
    //TODO rewrite consumes and produces to something from constants
    @RequestMapping(path = "/", method = RequestMethod.GET, consumes = "application/json", produces = "text/plain")
    String getLoans() {
        return null;
    }

    @RequestMapping(path = "/{loanId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    String getLoan(@PathVariable("loanId") Long loanId) {
        return "";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    String applyLoan() {
        return "";
    }
}
