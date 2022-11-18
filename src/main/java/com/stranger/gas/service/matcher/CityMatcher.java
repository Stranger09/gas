package com.stranger.gas.service.matcher;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import org.springframework.stereotype.Component;

@Component
public class CityMatcher implements Matcher{

    @Override
    public boolean match(Station station, Filter filter) {
        return filter.getCity() == null || station.getCity().equals(filter.getCity());
    }
}
