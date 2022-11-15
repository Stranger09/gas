package com.stranger.gas.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.mapper.WogMapper;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.wog.WogFuelFilter;
import com.stranger.gas.model.wog.WogStation;
import com.stranger.gas.model.wog.WogStationInfo;
import com.stranger.gas.scrapper.WogScrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@EnableCaching
@Component("wogAdapter")
public class WogAdapter implements Adapter {
    private WogScrapper wogScrapper;
    private ObjectMapper objectMapper;
    private WogMapper wogMapper;

    public WogAdapter(WogScrapper wogScrapper, WogMapper wogMapper) {
        this.wogScrapper = wogScrapper;
        this.wogMapper = wogMapper;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Station> collectInfo() {
        List<WogFuelFilter> allWogFuelFilters = getAllWogFuelFilters();
        wogMapper.setFuelFilters(allWogFuelFilters);

        List<WogStation> allStations = getAllWogStations();

        return allStations.stream()
                .map(wogStation -> getWogStationInfo(wogStation.getLink()))
                .map(wogStationInfo -> wogMapper.mapStation(wogStationInfo))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private List<WogStation> getAllWogStations() {
        Object allStationsInfo = wogScrapper.retrieveStations();

        List<WogStation> wogStations = objectMapper.convertValue(allStationsInfo, new TypeReference<>() {
        });

        return wogStations;
    }

    @SneakyThrows
    private List<WogFuelFilter> getAllWogFuelFilters() {
        Object allFuelFiltersInfo = wogScrapper.retrieveFuelFilters();

        List<WogFuelFilter> wogFuelFilters = objectMapper.convertValue(allFuelFiltersInfo, new TypeReference<>() {
        });

        return wogFuelFilters;
    }


    @SneakyThrows
    public WogStationInfo getWogStationInfo(String stationLink) {
        Object stationInfo = wogScrapper.retrieveStationInfo(stationLink);

        WogStationInfo wogStationInfo = objectMapper.convertValue(stationInfo, new TypeReference<>() {
        });

        return wogStationInfo;
    }

}
