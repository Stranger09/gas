package com.stranger.gas.adapters;

import java.util.Collections;
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
import org.springframework.stereotype.Component;

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


    @SneakyThrows
    private List<UpgStation> getAllUpgStations() {
        String upgStationsInJson = (String) scrapper.retrieveStations();

        return objectMapper.readValue(upgStationsInJson, new TypeReference<>() {});
    }
}
