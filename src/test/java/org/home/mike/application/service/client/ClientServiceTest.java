package org.home.mike.application.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.application.controller.client.ClientDTO;
import org.junit.Test;

import java.io.StringWriter;

public class ClientServiceTest {
    @Test
    public void saveOrUpdate() throws Exception {
        StringWriter w = new StringWriter();
        LoanDTO value = new LoanDTO();
        value.setClient(new ClientDTO());
        new ObjectMapper().writerFor(LoanDTO.class).writeValue(w, value);
        System.out.println(w.toString());
    }

}