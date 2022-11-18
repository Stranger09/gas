package com.stranger.gas.adapters;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.mapper.WogMapper;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.wog.WogFuelFilter;
import com.stranger.gas.model.wog.WogStation;
import com.stranger.gas.model.wog.WogStationInfo;
import com.stranger.gas.scrapper.WogScrapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

@Slf4j
@EnableCaching
@AllArgsConstructor
@Component("wogAdapter")
public class WogAdapter implements Adapter {

    private WogScrapper wogScrapper;
    private ObjectMapper objectMapper;
    private WogMapper wogMapper;

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

        if (allStationsInfo == null) {
            return Collections.emptyList();
        }

        return objectMapper.convertValue(allStationsInfo, new TypeReference<>() {
        });
    }

    @SneakyThrows
    private List<WogFuelFilter> getAllWogFuelFilters() {
        Object allFuelFiltersInfo = wogScrapper.retrieveFuelFilters();

        return objectMapper.convertValue(allFuelFiltersInfo, new TypeReference<>() {
        });
    }

    @SneakyThrows
    public WogStationInfo getWogStationInfo(String stationLink) {
        Object stationInfo = wogScrapper.retrieveStationInfo(stationLink);

        return objectMapper.convertValue(stationInfo, new TypeReference<>() {
        });
    }

}
