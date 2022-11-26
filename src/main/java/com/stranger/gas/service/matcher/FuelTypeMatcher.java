package com.stranger.gas.service.matcher;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Station;
import org.springframework.stereotype.Component;

@Component
public class FuelTypeMatcher implements Matcher {

    @Override
    public boolean match(Station station, Filter filter) {
        return filter.getFuelType() == null
                || station.getStationInfo()
                .getFuels()
                .stream()
                .filter(Fuel::isAvailable)
                .map(Fuel::getFuelType)
                .anyMatch(fuelType -> fuelType.equals(filter.getFuelType()));
    }
}
