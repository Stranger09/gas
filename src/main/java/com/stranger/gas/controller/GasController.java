package com.stranger.gas.controller;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.model.Station;
import com.stranger.gas.service.GasService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GasController {

    @Autowired
    private GasService gasService;

    @GetMapping(value = "/getWogStationInfo")
    public List<Station> getAllWogStationInfo() {
        return gasService.getAllWogStationInfo();
    }

    @SneakyThrows
    @GetMapping(value = "/getStationByCity")
    public List<Station> getStationsByCity(@RequestBody String city) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> objectMap
            = mapper.convertValue(city, new TypeReference<>(){});

        city = objectMap.get("city");

        return gasService.getAllStationByCity(city);
    }
}
