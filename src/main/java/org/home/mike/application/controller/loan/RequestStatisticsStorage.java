package org.home.mike.application.controller.loan;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ApplicationScope
public class RequestStatisticsStorage {
    Map<String, AtomicInteger> requestCountPerCountry = new ConcurrentHashMap<>();

    public int getCount(String country) {
        AtomicInteger atomicInteger = requestCountPerCountry.get(country);
        return atomicInteger != null ? atomicInteger.get() : 0;
    }

    public void increment(String country) {
        AtomicInteger count = requestCountPerCountry.get(country);
        if (count == null) {
            count = new AtomicInteger(0);
            requestCountPerCountry.put(country, count);
        }
        count.incrementAndGet();
    }
}
