package org.home.loaner.application.controller.loan;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CountryResolverTest {
    CountryResolver countryResolver = new CountryResolver();

    @Test
    public void resolve() throws Exception {
        String result = countryResolver.resolve("");
        assertThat(result, equalTo("lv"));
    }

}