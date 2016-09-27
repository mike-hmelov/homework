package org.home.mike.application.controller.loan;

import org.home.mike.application.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    String getLoan(@PathVariable("loanId") Long loanId) {
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain")
    ResponseEntity<Void> applyLoan(@RequestBody @Validated LoanDTO newLoan) {
        LoanDTO loanDTO = loanService.applyLoan(newLoan);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "" + loanDTO.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
