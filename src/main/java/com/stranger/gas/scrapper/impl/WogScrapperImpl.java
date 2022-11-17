package com.stranger.gas.scrapper.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.scrapper.WogScrapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component("wogScrapper")
public class WogScrapperImpl implements WogScrapper {
    private static final String ALL_STATIONS_URL = "https://api.wog.ua/fuel_stations";
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object retrieveStations() {
        Object rawResponse = restTemplate.getForObject(ALL_STATIONS_URL, Object.class);
        Object allStationsResponse = parseRowResponseToAllStationsResponse(rawResponse);
        return allStationsResponse;
    }

    @Override
    public Object retrieveStationInfo(final String station_url) {
        Object rawResponse = restTemplate.getForObject(station_url, Object.class);
        Object stationInfoResponse = parseRowResponseToStationInfoResponse(rawResponse);
        return stationInfoResponse;
    }

    @Override
    public Object retrieveFuelFilters() {
        Object rawResponse = restTemplate.getForObject(ALL_STATIONS_URL, Object.class);
        Object allFuelFiltersResponse = parseRowResponseToAllFuelFiltersResponse(rawResponse);
        return allFuelFiltersResponse;
    }

    @SneakyThrows
    private Object parseRowResponseToAllStationsResponse(Object rawResponse) {
        Object dataResponse = getObject(rawResponse, "data");

        Object allStationsResponse = getObject(dataResponse, "stations");

        return allStationsResponse;
    }

    @SneakyThrows
    private Object parseRowResponseToStationInfoResponse(Object rawResponse) {
        Object stationInfoResponse = getObject(rawResponse, "data");

        return stationInfoResponse;
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

        Map<String, Object> objectMap
                = objectMapper.readValue(json, new TypeReference<>(){});

        return objectMap.get(fieldInJson);
    }
}
