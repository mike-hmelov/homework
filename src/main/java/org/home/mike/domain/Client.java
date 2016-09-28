package org.home.mike.domain;

import lombok.Data;

import javax.persistence.*;

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
}
