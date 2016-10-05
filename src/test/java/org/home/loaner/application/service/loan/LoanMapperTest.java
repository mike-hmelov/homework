package org.home.loaner.application.service.loan;

import org.home.loaner.application.controller.client.ClientDTO;
import org.home.loaner.application.controller.loan.LoanDTO;
import org.home.loaner.application.service.client.ClientMapper;
import org.home.loaner.domain.Client;
import org.home.loaner.domain.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

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
        assertThat(mapper.map((Loan) null), nullValue());
    }

    @Test
    public void map() throws Exception {
        Date now = new Date();

        Loan loan = new Loan();
        loan.setClient(client);
        loan.setAmount(BigDecimal.TEN);
        loan.setTerm(now);
        loan.setId(10L);
        loan.setApproved(true);
        loan = spy(loan);

        when(clientMapper.map(client)).thenReturn(clientDTO);

        LoanDTO result = mapper.map(loan);

        assertThat(result, notNullValue());
        assertThat(result.getAmount(), equalTo(BigDecimal.TEN));
        assertThat(result.getId(), equalTo(10L));
        assertThat(result.getTerm(), equalTo(now));
        assertThat(result.getClient(), sameInstance(clientDTO));
        assertThat(result.isApproved(), is(true));

        verify(clientMapper).map(same(client));
        verify(loan).getClient();
        verify(loan).getId();
        verify(loan).getApproved();
        verify(loan).getAmount();
        verify(loan).getTerm();
        verifyNoMoreInteractions(loan);
    }

    @Test
    public void mapBackNull() throws Exception {
        assertThat(mapper.map((LoanDTO) null), nullValue());
    }

    @Test
    public void mapBack() throws Exception {
        LoanDTO dto = new LoanDTO();
        Date term = new Date();
        dto.setTerm(term);
        dto.setAmount(BigDecimal.TEN);
        dto.setClient(clientDTO);

        dto = spy(dto);
        Loan result = mapper.map(dto);

        assertThat(result.getId(), nullValue());
        assertThat(result.getTerm(), sameInstance(term));
        assertThat(result.getAmount(), equalTo(BigDecimal.TEN));
        assertThat(result.getApproved(), is(false));
        assertThat(result.getClient(), nullValue());

        verify(dto).getAmount();
        verify(dto).getTerm();
        verifyNoMoreInteractions(dto);
    }
}