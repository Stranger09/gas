package com.stranger.gas.controller;

import java.util.List;

import com.stranger.gas.service.GasService;

import com.stranger.gas.model.Station;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Station> getStationsByCity(@RequestParam String city) {
        return gasService.getAllStationByCity(city);
    }
}
