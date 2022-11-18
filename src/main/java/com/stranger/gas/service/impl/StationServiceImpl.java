package com.stranger.gas.service.impl;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;
import com.stranger.gas.repository.StationRepository;
import com.stranger.gas.service.FilterService;
import com.stranger.gas.service.StationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService {

    private StationRepository stationRepository;
    private FilterService filterService;

    public List<Station> filter(Filter filter) {
        return filterService.filter(filter);
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
