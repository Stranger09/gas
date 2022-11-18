package com.stranger.gas.adapters;

import com.stranger.gas.model.Station;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.Collections;
import java.util.List;

public interface Adapter {
    @Retryable(value = Exception.class, backoff = @Backoff(delay = 5000))
    List<Station> collectInfo();

    @Recover
    default List<Station> recoverCollectInfo(Exception e, String sql) {
        return Collections.emptyList();
    };


}
