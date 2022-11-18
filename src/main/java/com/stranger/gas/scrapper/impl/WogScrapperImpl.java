package com.stranger.gas.scrapper.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.scrapper.WogScrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component("wogScrapper")
public class WogScrapperImpl implements WogScrapper {

    @Value("${station.links.wog}")
    private String WOG_STATIONS_URL;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Object retrieveStations() {
        ResponseEntity<Object> rawResponse = restTemplate.getForEntity(WOG_STATIONS_URL, Object.class);

        return parseRowResponseToAllStationsResponse(rawResponse.getBody());
    }

    @Override
    public Object retrieveStationInfo(final String station_url) {
        Object rawResponse = restTemplate.getForObject(station_url, Object.class);

        return parseRowResponseToStationInfoResponse(rawResponse);
    }

    @Override
    public Object retrieveFuelFilters() {
        Object rawResponse = restTemplate.getForObject(WOG_STATIONS_URL, Object.class);

        return parseRowResponseToAllFuelFiltersResponse(rawResponse);
    }

    @SneakyThrows
    private Object parseRowResponseToAllStationsResponse(Object rawResponse) {
        Object dataResponse = getObject(rawResponse, "data");

        return getObject(dataResponse, "stations");
    }

    @SneakyThrows
    private Object parseRowResponseToStationInfoResponse(Object rawResponse) {

        return getObject(rawResponse, "data");
    }

    @SneakyThrows
    private Object parseRowResponseToAllFuelFiltersResponse(Object rawResponse) {
        Object dataResponse = getObject(rawResponse, "data");

        Object allFuelFiltersResponse = getObject(dataResponse, "fuel_filters");

        return allFuelFiltersResponse;
    }

    @SneakyThrows
    private Object getObject(Object objFromJson, String fieldInJson) {
        String json = objectMapper.writeValueAsString(objFromJson);

        Map<String, Object> objectMap = objectMapper.readValue(json, new TypeReference<>(){});

        return objectMap.get(fieldInJson);
    }
}
