package org.home.loaner.application.controller.loan;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ApplyLoanMonitor.class)
public class ApplyLoanMonitorTest {
    @Mock
    ApplyLoanMonitor monitor;

    @Mock
    private CountryResolver countryResolver;
    @Mock
    private HttpServletRequest request;
    @Mock
    private RequestStatisticsStorage requestStatisticsStorage;

    @Before
    public void setUp() throws Exception {
        Whitebox.setInternalState(monitor, "request", request);
        Whitebox.setInternalState(monitor, "countryResolver", countryResolver);
        Whitebox.setInternalState(monitor, "requestStatisticsStorage", requestStatisticsStorage);
    }

    @Test
    public void beforeApplyLoan() throws Exception {
        doCallRealMethod().when(monitor).beforeApplyLoan();
        PowerMockito.when(monitor, "tooManyRequestsFromLocation", "lv").thenReturn(false);

        when(request.getRemoteAddr()).thenReturn("addr");
        when(countryResolver.resolve("addr")).thenReturn("lv");

        monitor.beforeApplyLoan();

        verify(request).getRemoteAddr();
        verifyNoMoreInteractions(request);
        verify(countryResolver).resolve("addr");
        verifyNoMoreInteractions(countryResolver);
        verify(requestStatisticsStorage).increment("lv");
        verifyNoMoreInteractions(requestStatisticsStorage);
    }

    @Test(expected = TooManyRequestsException.class)
    public void beforeApplyLoanOverLimit() throws Exception {
        doCallRealMethod().when(monitor).beforeApplyLoan();
        PowerMockito.when(monitor, "tooManyRequestsFromLocation", "lv").thenReturn(true);

        when(request.getRemoteAddr()).thenReturn("addr");
        when(countryResolver.resolve("addr")).thenReturn("lv");

        monitor.beforeApplyLoan();
    }

    @Test
    public void tooManyRequestsFromLocationTrue() throws Exception {
        PowerMockito.doCallRealMethod().when(monitor, "tooManyRequestsFromLocation", anyString());
        when(requestStatisticsStorage.getCount("lv")).thenReturn(3);

        boolean result = Whitebox.invokeMethod(monitor, "tooManyRequestsFromLocation", "lv");
        assertThat(result, is(true));
        verify(requestStatisticsStorage).getCount("lv");
        verifyNoMoreInteractions(requestStatisticsStorage);
        verifyZeroInteractions(request);
        verifyZeroInteractions(countryResolver);
    }

    @Test
    public void tooManyRequestsFromLocationFalse() throws Exception {
        PowerMockito.doCallRealMethod().when(monitor, "tooManyRequestsFromLocation", anyString());
        when(requestStatisticsStorage.getCount("lv")).thenReturn(1);

        boolean result = Whitebox.invokeMethod(monitor, "tooManyRequestsFromLocation", "lv");
        assertThat(result, is(false));
        verifyZeroInteractions(request);
        verifyZeroInteractions(countryResolver);
    }

    @Test
    public void countryHitDecrement() throws Exception {
        doCallRealMethod().when(monitor).countryHitDecrement();

        monitor.countryHitDecrement();

        verify(requestStatisticsStorage).decrementForAll();
        verifyNoMoreInteractions(requestStatisticsStorage);
        verifyZeroInteractions(request);
        verifyZeroInteractions(countryResolver);
    }

}