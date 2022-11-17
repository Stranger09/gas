package com.stranger.gas.saver;

import com.stranger.gas.adapters.Adapter;
import com.stranger.gas.model.Station;
import com.stranger.gas.repository.StationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Saver {
    List<Adapter> adapters;
    private StationRepository stationRepository;

    public Saver(List<Adapter> adapters, StationRepository stationRepository) {
        this.adapters = adapters;
        this.stationRepository = stationRepository;
    }

    //TODO Check data in BD is just rewritten after task (not created again)
    //TODO Handle exceptions while Bad response from Server
    @Scheduled(fixedDelay = 1000000)
    void saveInfo() {
        adapters.forEach(adapter -> {
            List<Station> stations = adapter.collectInfo();
            stations.forEach(station -> stationRepository.save(station));
        });
    }
}
