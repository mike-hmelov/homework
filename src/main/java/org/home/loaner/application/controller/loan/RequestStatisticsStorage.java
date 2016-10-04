package org.home.loaner.application.controller.loan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ApplicationScope
public class RequestStatisticsStorage {
    private static final Logger logger = LoggerFactory.getLogger(RequestStatisticsStorage.class);

    private Map<String, AtomicInteger> requestCountPerCountry = new ConcurrentHashMap<>();

    int getCount(String country) {
        AtomicInteger atomicInteger = requestCountPerCountry.get(country);
        return atomicInteger != null ? atomicInteger.get() : 0;
    }

    void increment(String country) {
        //TODO not thread safe - might potentially miss a hit
        AtomicInteger count = requestCountPerCountry.get(country);
        if (count == null) {
            count = new AtomicInteger(0);
            requestCountPerCountry.put(country, count);
        }
        count.incrementAndGet();
        logger.trace("" + requestCountPerCountry);
    }

    void decrementForAll() {
        //TODO not thread safe - might overlap with increment method
        Iterator<String> iterator = requestCountPerCountry.keySet().iterator();
        iterator.forEachRemaining(s -> {
            AtomicInteger atomicInteger = requestCountPerCountry.get(s);
            int i = atomicInteger.decrementAndGet();
            if (i <= 0)
                iterator.remove();
        });
        logger.trace("" + requestCountPerCountry);
    }
}
