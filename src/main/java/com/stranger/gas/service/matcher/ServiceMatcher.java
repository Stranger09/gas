package com.stranger.gas.service.matcher;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Service;
import com.stranger.gas.model.Station;
import org.springframework.stereotype.Component;

@Component
public class ServiceMatcher implements Matcher {
    @Override
    public boolean match(Station station, Filter filter) {
        return filter.getServiceType() == null
                || station.getStationInfo()
                .getServices()
                .stream()
                .filter(Service::isAvailable)
                .map(Service::getServiceType)
                .anyMatch(serviceType -> serviceType.equals(filter.getServiceType()));
    }
}
