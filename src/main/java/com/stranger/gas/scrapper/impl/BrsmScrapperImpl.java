package com.stranger.gas.scrapper.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.scrapper.BrsmScrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component(value = "brsmScrapper")
public class BrsmScrapperImpl implements BrsmScrapper {

    @Value("${station.links.brsm}")
    private String BRSM_STATIONS_URL;
    @Value("${fuel.links.brsm}")
    private String BRSM_FUELS_URL;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public  Object retrieveStations() {
        ResponseEntity<Object> rawResponse = restTemplate.getForEntity(BRSM_STATIONS_URL, Object.class);

        return getObject(rawResponse.getBody());
    }

    @Override
    public Object retrieveStationFuels(int stationId) {
        String station_fuels_url = BRSM_FUELS_URL + "/" + stationId;
        ResponseEntity<Object> rawResponse = restTemplate.getForEntity(station_fuels_url, Object.class);

        return getObject(rawResponse.getBody());
    }

    @SneakyThrows
    private Object getObject(Object objFromJson) {
        String json = objectMapper.writeValueAsString(objFromJson);

        return objectMapper.readValue(json, new TypeReference<>() {});
    }
}
