package com.stranger.gas.adapters;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.model.Station;
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
    @Override
    @SneakyThrows
    @Cacheable(value = "wogCache")
    @Scheduled(fixedDelay = 1000000000)
    public List<Station> getGasStationInfo() {
        String url = "https://api.wog.ua/fuel_stations";
        RestTemplate restTemplate = new RestTemplate();

        Object response = restTemplate.getForObject(url, Object.class);

        log.info("we're inside wog adapter");

        List<Station> stations = parseResponseToGasStationInfo(response);
        return stations;
    }

    @SneakyThrows
    private List<Station> parseResponseToGasStationInfo(Object response) {

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
