package com.stranger.gas.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.mapper.BrsmMappper;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.brsm.BrsmFuel;
import com.stranger.gas.model.brsm.BrsmStation;
import com.stranger.gas.scrapper.BrsmScrapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("brsmAdapter")
@AllArgsConstructor
public class BrsmAdapter implements Adapter {

    @Qualifier(value = "brsmScrapper")
    private BrsmScrapper brsmScrapper;
    private ObjectMapper objectMapper;
    private BrsmMappper brsmMappper;

    @Override
    public List<Station> collectInfo() {
        List<BrsmStation> allBrsmStations = getAllBrsmStations();

        List<Station> collect = allBrsmStations
                .stream()
                .map(brsmStation -> brsmMappper.mapStation(brsmStation, getStationFuels(brsmStation.getId())))
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<Station> recoverCollectInfo(Exception e, String sql) {
        log.error("Brsm adapter wasn't process data correctly");
        return Adapter.super.recoverCollectInfo(e, sql);
    }

    @SneakyThrows
    private List<BrsmStation> getAllBrsmStations() {
        Object allStationsInfo = brsmScrapper.retrieveStations();

        return objectMapper.convertValue(allStationsInfo, new TypeReference<>() {});
    }

    @SneakyThrows
    private List<BrsmFuel> getStationFuels(int stationId) {
        Object allStationFuels = brsmScrapper.retrieveStationFuels(stationId);

        return objectMapper.convertValue(allStationFuels, new TypeReference<>() {});
    }
}
