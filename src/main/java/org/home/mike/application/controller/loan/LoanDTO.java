package org.home.mike.application.controller.loan;

import lombok.Data;
import org.home.mike.application.controller.user.ClientDTO;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanDTO {
    private Long id;
    private ClientDTO client;
    private BigDecimal amount;
    private Date term;
}
