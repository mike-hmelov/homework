package org.home.mike.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Boolean approved;
}
