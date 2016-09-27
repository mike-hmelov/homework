package org.home.mike.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Boolean approved;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    private BigDecimal amount;

    @Column
    @Temporal(TemporalType.DATE)
    private Date term;
}
