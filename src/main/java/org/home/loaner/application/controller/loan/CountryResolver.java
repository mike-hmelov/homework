package org.home.loaner.application.controller.loan;

import org.springframework.stereotype.Component;

@Component
public class CountryResolver {
    public String resolve(String remoteAddr) {
        return "lv";
    }
}
