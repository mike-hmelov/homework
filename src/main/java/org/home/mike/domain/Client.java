package org.home.mike.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @Column(name = "personal_id")
    private String personalId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private List<Loan> loans;

    public List<Loan> getLoans() {
        return loans;
    }
}
