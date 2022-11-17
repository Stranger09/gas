package com.stranger.gas.service.matcher;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import org.springframework.stereotype.Component;

@Component
public class CompanyMatcher implements Matcher{

    @Override
    public boolean match(Station station, Filter filter) {
        return station.getCompany().getName().equals(filter.getCompanyName());
    }
}
