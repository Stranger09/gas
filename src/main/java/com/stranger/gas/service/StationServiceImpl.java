package com.stranger.gas.service;

import com.stranger.gas.adapters.Adapter;
import com.stranger.gas.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    /*@Autowired
    List<Adapter> allAdapters;

    //TODO: need extract class name to external property
    public List<Station> getAllWogStationInfo() {

        return allAdapters.stream()
            .filter(adapter -> getUserClass(adapter.getClass()).getSimpleName().equals("WogAdapter") )
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Wog Adapter Bean is missing")).getGasStationInfo();
    }

    public List<Station> getAllStationByCity(String city) {

        List<Station> collect = getAllStationInfo().stream().filter(station -> city.equals(station.getCity())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Station> getAllStations() {
        List<Station> collect = allAdapters
                .stream()
                .map(Adapter::getAllStations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return collect;
    }*/
}
