package com.stranger.gas.service;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;

import java.util.List;

public interface StationService {
    List<Station> getAllStations();

    StationInfo getStationInfo(Long stationId);

    List<Station> filter(Filter filter);
}
