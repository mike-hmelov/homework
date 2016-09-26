package org.home.mike.application.controller.loan;

import lombok.Data;
import org.home.mike.application.controller.user.ClientDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanDTO {
    private Long id;
    private ClientDTO client;
    private BigDecimal amount;
    private LocalDate term;
}
