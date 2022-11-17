package com.stranger.gas.service;

import java.util.List;
import java.util.stream.Collectors;


import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import com.stranger.gas.service.matcher.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    @Autowired
    private List<Matcher> matchers;

    public List<Station> filter(List<Station> stations, Filter filter) {
        return stations.stream().filter(station -> isMatch(station, filter))
            .collect(Collectors.toList());
    }

    private boolean isMatch(Station station, Filter filter) {
        return matchers.stream().allMatch(matcher -> matcher.match(station, filter));
    }

}
