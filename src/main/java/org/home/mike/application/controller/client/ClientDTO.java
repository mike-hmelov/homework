package org.home.mike.application.controller.client;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClientDTO {
    private Long id;

    @NotNull
    @Size(max=200)
    private String name;

    @NotNull
    @Size(max = 200)
    private String surname;

    @NotNull
    @Size(max = 200)
    private String personalId;
}
