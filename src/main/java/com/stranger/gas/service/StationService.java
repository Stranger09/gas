package com.stranger.gas.service;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;

import java.util.List;

public interface StationService {
    List<Station> getAllStations();

    Station getStation(Long stationId);

    List<Station> filter(Filter filter);
}
