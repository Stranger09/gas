package com.stranger.gas.saver;

import java.util.List;

import com.stranger.gas.adapters.Adapter;
import com.stranger.gas.model.Station;
import com.stranger.gas.repository.StationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Saver {
    private final List<Adapter> adapters;
    private final StationRepository stationRepository;

    public Saver(List<Adapter> adapters, StationRepository stationRepository) {
        this.adapters = adapters;
        this.stationRepository = stationRepository;
    }

    //TODO Check data in BD is just rewritten after task (not created again)
    @Scheduled(fixedDelay = 1000000)
    void saveInfo() {
        adapters.forEach(adapter -> {
            List<Station> stations = adapter.collectInfo();
            if (!stations.isEmpty()) {
                stationRepository.saveAll(stations);
            }
        });
    }
}
