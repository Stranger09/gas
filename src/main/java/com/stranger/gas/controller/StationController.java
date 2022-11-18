package com.stranger.gas.controller;

import com.stranger.gas.mapper.StationMapper;
import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.ui.StationLine;
import com.stranger.gas.service.impl.StationServiceImpl;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {
    private StationServiceImpl stationService;
    private StationMapper stationMapper;

    public StationController(StationServiceImpl stationService, StationMapper stationMapper) {
        this.stationService = stationService;
        this.stationMapper = stationMapper;
    }

    @SneakyThrows
    @GetMapping(value = "/station/all/filter")
    public List<Station> getAllStationsByFilter(@RequestBody Filter filters) {

        return stationService.filter(filters);
    }

    @GetMapping(value = "/station", produces = "application/json")
    List<StationLine> getAllStations() {
        return stationMapper.mapStationToStationLine(stationService.getAllStations());
    }

}
