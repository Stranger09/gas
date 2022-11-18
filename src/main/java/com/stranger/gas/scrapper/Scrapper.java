package com.stranger.gas.scrapper;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface Scrapper {
    @Retryable(value = Exception.class, backoff = @Backoff(delay = 5000))
    Object retrieveStations();

    @Recover
    default Object recoverRetrieveStations(Exception e, String sql) {
        return null;
    };

}
