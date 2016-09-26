package org.home.mike.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Boolean approved;

    @ManyToOne
    private Client client;

    @Column
    private BigDecimal amount;

    @Column
    private LocalDate term;
}
