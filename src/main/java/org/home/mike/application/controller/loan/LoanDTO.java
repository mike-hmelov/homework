package org.home.mike.application.controller.loan;

import lombok.Data;
import org.home.mike.application.controller.user.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanDTO {
    private Long id;
    private UserDTO user;
    private BigDecimal amount;
    private LocalDate term;
}
