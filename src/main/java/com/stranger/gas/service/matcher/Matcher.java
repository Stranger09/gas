package com.stranger.gas.service.matcher;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;

public interface Matcher {
    boolean match(Station station, Filter filter);
}