package com.stranger.gas.service.impl;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;
import com.stranger.gas.repository.StationRepository;
import com.stranger.gas.service.FilterService;
import com.stranger.gas.service.StationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private StationRepository stationRepository;
    private FilterService filterService;

    public StationServiceImpl(StationRepository stationRepository, FilterService filterService) {
        this.stationRepository = stationRepository;
        this.filterService = filterService;
    }

    public List<Station> filter(Filter filter) {

        List<Station> stations = getAllStations();

        return filterService.filter(stations, filter);
    }

    @Override
    public List<Station> getAllStations() {
        return this.stationRepository.findAll();
    }

    @Override
    public StationInfo getStationInfo(Long stationId) {
        return this.stationRepository.findById(stationId).get().getStationInfo();
    }
}
