package org.home.loaner.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SecondaryTable(name="client_blacklist", pkJoinColumns = @PrimaryKeyJoinColumn(name = "client_id"))
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

    @Column(table = "client_blacklist", name = "comment")
    private String blacklistReason;

    public boolean isBlacklisted() {
        return getBlacklistReason() != null;
    }
}
