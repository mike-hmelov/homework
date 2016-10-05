package org.home.loaner.application.controller.loan;

import lombok.Data;
import org.home.loaner.application.controller.client.ClientDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanDTO {
    private Long id;
    @NotNull
    @Valid
    private ClientDTO client;
    @NotNull
    @Min(value = 1)
    private BigDecimal amount;
    @NotNull
    private Date term;

    private boolean approved;
}
