package com.stranger.gas.service;

import com.stranger.gas.adapters.Adapter;
import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Fuel.FuelType;
import com.stranger.gas.model.Station;
import com.stranger.gas.repository.StationRepository;
import com.stranger.gas.service.matcher.Matcher;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    List<Adapter> allAdapters;

    @Autowired
    StationRepository stationRepository;

    public List<Station> filter(Filter filter) {

        List<Station> stations = stationRepository.findAll();

        stations = stations.stream()
            .filter(station -> isSimpleFilterTrue(filter.getCity(), station.getCity()))
            .filter(station -> isSimpleFilterTrue(filter.getCompanyName(), station.getCompany().getName()))
            .filter(station -> isFuelTypePresent(filter.getFuelType(), station))
            .collect(Collectors.toList());

        return stations;
    }

    private static boolean isSimpleFilterTrue(String filterParam, String stationParam) {
        return filterParam == null || stationParam.equals(filterParam);
    }

    private static boolean isFuelTypePresent(FuelType filterParam, Station station) {
        return filterParam == null || station.getStationInfo().getFuels().stream().anyMatch(fuel -> fuel.getFuelType().equals(filterParam));
    }
}
