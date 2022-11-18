package com.stranger.gas.controller;

import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Station;
import com.stranger.gas.service.FilterService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    @Autowired
    FilterService filterService;


   /* @GetMapping(value = "/station/all")
    public List<Station> getAllStations() {
        wogAdapter.getAllStations();
        return new ArrayList<>();
    }

    @GetMapping(value = "/station/fuel/all")
    public List<Station> getAllFuels() {
        wogAdapter.getAllFuelFilters();
        return new ArrayList<>();
    }

    @SneakyThrows
    @GetMapping(value = "/station/info")
    public List<Station> getStationInfo() {
        wogAdapter.getStationInfo();
        return new ArrayList<>();
    }


    @SneakyThrows
    @GetMapping(value = "/test")
    @Scheduled(fixedDelay = 1000000)
    public List<StationInfo> test() {
        List<WogStation> allStations = wogAdapter.getAllStations();

        allStations.stream()
                .map(WogStation::getLink)
                .forEach(link -> {
                    WogStationInfo wogStationInfo = wogAdapter.getWogStationInfo(link);
                    System.out.println("Description: " + wogStationInfo.getWorkDescription());
                    System.out.println("Fuels");
                    wogStationInfo.getFuels().stream()
                            .forEach(System.out::println);
                });
        return new ArrayList<>();
    }*/

    @SneakyThrows
    @GetMapping(value = "/station/filter")
    public List<Station> getStationsByCity(@RequestBody Filter filters) {
        return filterService.filter(filters);
    }

//    @SneakyThrows
//    @GetMapping(value = "/station/all/fuel")
//    public List<Station> getStationsByFuel(@RequestParam String city) {
//        return gasService.getAllStationByCity(city);
//    }
}
