package com.stranger.gas.adapters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.model.GasTypeInfo;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.wog.WogStation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableCaching
@Component
public class WogAdapter implements Adapter{

    private static final String BRAND_NAME = "WOG";

    @SneakyThrows
    private List<WogStation> getWogGasStationInfo() {
        String url = "https://api.wog.ua/fuel_stations";
        RestTemplate restTemplate = new RestTemplate();

        Object response = restTemplate.getForObject(url, Object.class);

        List<WogStation> wogStations = parseResponseToGasStationInfo(response);
        return wogStations;
    }
    @Override
    @Cacheable(value = "wogCache")
    @Scheduled(fixedDelay = 100000)
    public List<Station> getGasStationInfo() {
        return getAllStationInfo();
    }

    private List<Station> getAllStationInfo() {
        List<WogStation> gasStationInfo = getWogGasStationInfo();
        return gasStationInfo
            .stream()
            .map(wog -> new Station(BRAND_NAME, getGasTypeInfo(wog), wog.getCity(), wog.getCity())).collect(Collectors.toList());
    }

    private List<GasTypeInfo> getGasTypeInfo(WogStation wog) {

        return List.of(new GasTypeInfo("Stub", true, 50.0));
    }

    @SneakyThrows
    private List<WogStation> parseResponseToGasStationInfo(Object response) {

        ObjectMapper mapper = new ObjectMapper();

        Object data = getObject(mapper, response, "data");

        Object stations = getObject(mapper, data, "stations");

        return mapper.convertValue(stations, new TypeReference<>() {});
    }

    @SneakyThrows
    private Object getObject(ObjectMapper mapper, Object objFromJson, String fieldInJson) {
        String json = mapper.writeValueAsString(objFromJson);

        Map<String, Object> objectMap
            = mapper.readValue(json, new TypeReference<>(){});

        return objectMap.get(fieldInJson);
    }
}
