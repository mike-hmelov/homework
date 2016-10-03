package org.home.mike.application.controller.loan;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LoanControllerMonitor {
    private static final int LIMIT_PER_COUNTRY = 3;

    @Autowired
    private CountryResolver countryResolver;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RequestStatisticsStorage requestStatisticsStorage;

    @Before("execution(* *..LoanController.applyLoan(..))")
    public void beforeApplyLoan() throws Throwable {
        String remoteAddr = request.getRemoteAddr();
        String country = countryResolver.resolve(remoteAddr);
        if (tooManyRequestsFromLocation(country))
            throw new TooMuchRequestsException(country);
        requestStatisticsStorage.increment(country);
    }

    private boolean tooManyRequestsFromLocation(String country) {
        int count = requestStatisticsStorage.getCount(country);
        return count > LIMIT_PER_COUNTRY;
    }
}
