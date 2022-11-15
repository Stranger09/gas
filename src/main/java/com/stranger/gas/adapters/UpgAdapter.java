package com.stranger.gas.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.upg.UpgStation;
import com.stranger.gas.scrapper.Scrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableCaching
@Component
public class UpgAdapter {

    private Scrapper scrapper;

    public UpgAdapter(@Qualifier("upgScrapper") Scrapper scrapper) {
        this.scrapper = scrapper;
    }

    @Scheduled(fixedDelay = 10000000)
    public List<Station> getAllStations() {
        getAllUpgStations()
                .stream()
                /*.forEach(System.out::println)*/;

        return new ArrayList<>();
    }

    @SneakyThrows
    private List<UpgStation> getAllUpgStations() {
        String upgStationsInJson = (String) scrapper.retrieveStations();

        List<UpgStation> upgStations = new ObjectMapper().readValue(upgStationsInJson, new TypeReference<>() {
        });
        return upgStations;
    }
}
