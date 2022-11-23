package com.stranger.gas.controller;

import com.stranger.gas.mapper.StationMapper;
import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.ui.StationDetails;
import com.stranger.gas.model.ui.StationLine;
import com.stranger.gas.service.FilterService;
import com.stranger.gas.service.StationService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StationController {
    private FilterService filterService;
    private StationService stationService;
    private StationMapper stationMapper;

    public StationController(FilterService filterService, StationService stationService, StationMapper stationMapper) {
        this.filterService = filterService;
        this.stationService = stationService;
        this.stationMapper = stationMapper;
    }

    @SneakyThrows
    @GetMapping(value = "/station/filter")
    public List<Station> getStationsByCity(@RequestBody Filter filters) {
        return filterService.filter(filters);
    }

    @GetMapping(value = "/station")
    public List<StationLine> getAllStations() {
        return stationMapper.mapStationToStationLine(stationService.getAllStations());
    }

    @GetMapping(value = "/station/details/{stationId}")
    public StationDetails getStationInfo(@PathVariable Long stationId) {
        Station station = stationService.getStation(stationId);

        StationDetails stationDetails = stationMapper.mapStationToStationDetails(station);

        return stationDetails;
    }
}
