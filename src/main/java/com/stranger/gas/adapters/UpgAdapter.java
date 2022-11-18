package com.stranger.gas.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.mapper.UpgMapper;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.upg.UpgStation;
import com.stranger.gas.scrapper.impl.UpgScrapperImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("upgAdapter")
@AllArgsConstructor
public class UpgAdapter implements Adapter {

    private final UpgScrapperImpl scrapper;
    private final UpgMapper upgMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<Station> collectInfo() {
        return getAllUpgStations().stream().map(upgMapper::mapStation).collect(Collectors.toList());
    }

    @Override
    public List<Station> recoverCollectInfo(Exception e, String sql) {
        log.error("Wog adapter wasn't process data correctly");
        return Adapter.super.recoverCollectInfo(e, sql);
    }


    @SneakyThrows
    private List<UpgStation> getAllUpgStations() {
        String upgStationsInJson = (String) scrapper.retrieveStations();

        return objectMapper.readValue(upgStationsInJson, new TypeReference<>() {});
    }
}
