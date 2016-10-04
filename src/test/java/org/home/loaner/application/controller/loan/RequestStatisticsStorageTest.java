package org.home.loaner.application.controller.loan;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RequestStatisticsStorageTest {
    RequestStatisticsStorage storage = new RequestStatisticsStorage();

    @Test
    public void getCount() throws Exception {
        storage.increment("ctr");
        int count = storage.getCount("ctr");
        assertThat(count, equalTo(1));

        storage.increment("ctr");
        count = storage.getCount("ctr");
        assertThat(count, equalTo(2));
    }

    @Test
    public void decrementForAll() throws Exception {
        storage.increment("ctr");
        storage.increment("ctr");

        storage.increment("2");
        storage.increment("2");
        storage.increment("2");

        storage.decrementForAll();

        assertThat(storage.getCount("ctr"), equalTo(1));
        assertThat(storage.getCount("2"), equalTo(2));
    }

}