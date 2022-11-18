package com.stranger.gas.adapters;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.mapper.UpgMapper;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.upg.UpgStation;
import com.stranger.gas.scrapper.Scrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

@Slf4j
@EnableCaching
@Component("upgAdapter")
public class UpgAdapter implements Adapter {

    private Scrapper scrapper;
    private UpgMapper upgMapper;
    private ObjectMapper objectMapper;

    public UpgAdapter(@Qualifier("upgScrapper") Scrapper scrapper, UpgMapper upgMapper) {
        this.scrapper = scrapper;
        this.upgMapper = upgMapper;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Station> collectInfo() {
        return getAllUpgStations()
            .stream()
            .map(upgStation -> upgMapper.mapStation(upgStation))
            .collect(Collectors.toList());
    }


    @SneakyThrows
    private List<UpgStation> getAllUpgStations() {
        String upgStationsInJson = (String) scrapper.retrieveStations();

        if (upgStationsInJson == null) {
            return Collections.emptyList();
        }

        return objectMapper.readValue(upgStationsInJson, new TypeReference<>() {
        });
    }
}
