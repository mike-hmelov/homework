package org.home.mike.application.controller.loan;

import org.home.mike.application.service.loan.LoanService;
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
@RequestMapping(path = "/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LoanDTO>> getLoans() {
        return new ResponseEntity<>(loanService.getLoans(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{loanId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    String getLoan(@PathVariable("loanId") Long loanId) {
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    void applyLoan() {
        loanService.applyLoan();
    }
}
