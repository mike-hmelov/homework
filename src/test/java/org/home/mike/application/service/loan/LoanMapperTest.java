package org.home.mike.application.service.loan;

import org.home.mike.application.controller.loan.LoanDTO;
import org.home.mike.application.controller.user.ClientDTO;
import org.home.mike.application.service.user.ClientMapper;
import org.home.mike.domain.Client;
import org.home.mike.domain.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanMapperTest {
    @InjectMocks
    private LoanMapper mapper;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientDTO clientDTO;
    @Mock
    private Client client;

    @Test
    public void mapNull() throws Exception {
        assertThat(mapper.map(null), nullValue());
    }

    @Test
    public void map() throws Exception {
        LocalDate now = LocalDate.now();

        Loan loan = new Loan();
        loan.setClient(client);
        loan.setAmount(BigDecimal.TEN);
        loan.setTerm(now);
        loan.setId(10L);

        when(clientMapper.map(client)).thenReturn(clientDTO);

        LoanDTO result = mapper.map(loan);

        assertThat(result, notNullValue());
        assertThat(result.getAmount(), equalTo(BigDecimal.TEN));
        assertThat(result.getId(), equalTo(10L));
        assertThat(result.getTerm(), equalTo(now));
        assertThat(result.getClient(), sameInstance(clientDTO));

        verify(clientMapper).map(same(client));
    }

}