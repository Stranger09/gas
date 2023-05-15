package com.stranger.gas.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.mapper.SocarMapper;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.socar.SocarStation;
import com.stranger.gas.model.socar.SocarStationInfo;
import com.stranger.gas.scrapper.SocarScrapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("socarAdapter")
@AllArgsConstructor
public class SocarAdapter implements Adapter {
    private SocarScrapper socarScrapper;
    private ObjectMapper objectMapper;
    private SocarMapper socarMapper;

    @Override
    public List<Station> collectInfo() {
        List<SocarStation> allStations = getAllSocarStations();

        return allStations.stream()
                .map(socarStation -> getSocarStationInfo(socarStation.getId()))
                .map(socarStationInfo -> socarMapper.mapStation(socarStationInfo))
                .collect(Collectors.toList());
    }

    @Override
    public List<Station> recoverCollectInfo(Exception e, String sql) {
        log.error("Socar adapter wasn't process data correctly");
        return Adapter.super.recoverCollectInfo(e, sql);
    }

    @SneakyThrows
    private List<SocarStation> getAllSocarStations() {
        Object allStationsInfo = socarScrapper.retrieveStations();

        return objectMapper.convertValue(allStationsInfo, new TypeReference<>() {});
    }

    @SneakyThrows
    public SocarStationInfo getSocarStationInfo(int stationId) {
        Object stationInfo = socarScrapper.retrieveStationInfo(stationId);

        return objectMapper.convertValue(stationInfo, new TypeReference<>() {});
    }
}
