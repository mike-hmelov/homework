package org.home.loaner.application.controller.loan;

import org.home.loaner.application.service.client.InvalidClientForLoanException;
import org.home.loaner.application.service.loan.LoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ServletUriComponentsBuilder.class)
public class LoanControllerTest {
    @InjectMocks
    LoanController controller;

    @Mock
    LoanService loanService;

    @Test
    public void getLoans() throws Exception {
        ArrayList<LoanDTO> list = new ArrayList<>();
        when(loanService.getLoans()).thenReturn(list);
        ResponseEntity<List<LoanDTO>> loans = controller.getLoans();

        assertThat(loans.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(loans.getBody(), sameInstance(list));

        verify(loanService).getLoans();
        verifyNoMoreInteractions(loanService);
    }

    @Test
    public void getLoan() throws Exception {
        LoanDTO dto = new LoanDTO();
        when(loanService.getLoan(10L)).thenReturn(dto);
        LoanDTO loan = controller.getLoan(10L);

        assertThat(loan, sameInstance(dto));

        verify(loanService).getLoan(10L);
        verifyNoMoreInteractions(loanService);
    }

    @Test
    public void applyLoan() throws Exception {
        PowerMockito.mockStatic(ServletUriComponentsBuilder.class);

        ServletUriComponentsBuilder builder = mock(ServletUriComponentsBuilder.class);
        PowerMockito.when(ServletUriComponentsBuilder.fromCurrentRequest()).thenReturn(builder);
        when(builder.path(anyString())).thenReturn(builder);
        UriComponents uriComponents = mock(UriComponents.class);
        when(builder.buildAndExpand((Object) anyObject())).thenReturn(uriComponents);
        URI uri = new URI("http://somehost");
        when(uriComponents.toUri()).thenReturn(uri);

        LoanDTO dto = new LoanDTO();
        LoanDTO applied = new LoanDTO();
        applied.setId(10L);
        when(loanService.applyLoan(dto)).thenReturn(applied);
        ResponseEntity<?> result = controller.applyLoan(dto);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), nullValue());
        assertThat(result.getHeaders(), notNullValue());
        assertThat(result.getHeaders().getLocation(), equalTo(uri));

        verify(loanService).applyLoan(dto);
        verifyNoMoreInteractions(loanService);
        verify(builder).path("/{id}");
        verify(builder).buildAndExpand(10L);
    }

    @Test
    public void approveLoan() throws Exception {
        ResponseEntity<?> result = controller.approveLoan(10L, true);
        verify(loanService).approveLoan(10L, true);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
    }

    @Test
    public void handleNotFound() throws Exception {
        ResponseEntity<?> result = controller.handleNotFound(new EntityNotFoundException("hello"));
        assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result.getBody(), equalTo("hello"));
    }

    @Test
    public void handleInvalidClient() throws Exception {
        ResponseEntity<?> result = controller.handleInvalidClient(new InvalidClientForLoanException("hello"));
        assertThat(result.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
        assertThat(result.getBody(), equalTo("hello"));
    }

    @Test
    public void handleTooManyRequests() throws Exception {
        ResponseEntity<?> result = controller.handleTooManyRequests(new TooManyRequestsException("hello"));
        assertThat(result.getStatusCode(), equalTo(HttpStatus.TOO_MANY_REQUESTS));
        assertThat(result.getBody(), equalTo("Too many requests from location: hello"));
    }

}