package com.stranger.gas.service;

import java.util.List;

import com.stranger.gas.adapters.Adapter;
import com.stranger.gas.model.Filter;
import com.stranger.gas.model.Fuel.FuelType;
import com.stranger.gas.model.Station;
import com.stranger.gas.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    List<Adapter> allAdapters;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    FilterService filterService;

    @Scheduled(fixedDelay = 100)
    public void method(){
        filter(Filter.builder().city("Вінниця").fuelType(FuelType.A95).build());
    }

    public List<Station> filter(Filter filter) {

        List<Station> stations = stationRepository.findAll();

        return filterService.filter(stations, filter);
    }

}
