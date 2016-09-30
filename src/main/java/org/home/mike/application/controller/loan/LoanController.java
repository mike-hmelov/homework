package org.home.mike.application.controller.loan;

import org.home.mike.application.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @RequestMapping(path = "/{loanId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    LoanDTO getLoan(@PathVariable("loanId") Long loanId) {
        return loanService.getLoan(loanId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> applyLoan(@RequestBody @Validated LoanDTO newLoan) {
        LoanDTO loanDTO = loanService.applyLoan(newLoan);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(loanDTO.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(path = "/{loanId}/approve", method = RequestMethod.POST)
    ResponseEntity<?> approveLoan(@PathVariable("loanId") Long loanId, @RequestParam(name = "flag") boolean approve) {
        loanService.approveLoan(loanId, approve);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
