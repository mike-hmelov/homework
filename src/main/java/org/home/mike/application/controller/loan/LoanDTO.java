package org.home.mike.application.controller.loan;

import lombok.Data;
import org.home.mike.application.controller.client.ClientDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanDTO {
    private Long id;
    @NotNull
    private ClientDTO client;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Date term;
}
