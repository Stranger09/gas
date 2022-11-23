package com.stranger.gas.saver;

import java.util.List;
import java.util.Optional;

import com.stranger.gas.adapters.Adapter;
import com.stranger.gas.model.Station;
import com.stranger.gas.repository.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Saver {
    private final List<Adapter> adapters;
    private final StationRepository stationRepository;

    @Scheduled(fixedDelay = 300000)
    void checkStationStatus() {
        if(stationRepository.count() > 0) {
            updateInfo();
        }
        else saveInfo();
    }

    void saveInfo() {
        adapters.forEach(adapter -> {
            List<Station> stations = adapter.collectInfo();
            if (!stations.isEmpty()) {
                stationRepository.saveAll(stations);
            }
        });
    }

    void updateInfo() {

        adapters.forEach(adapter -> {
            List<Station> stations = adapter.collectInfo();
            if (!stations.isEmpty()) {
                stations.forEach(this::updateStationInfo);
            }
        });
    }

    private void updateStationInfo(Station station) {
        Optional<Station> optionalStation = stationRepository.findStationByName(station.getName());
        if(optionalStation.isPresent()) {
            Station stationByName = optionalStation.get();

            stationByName.setStationInfo(station.getStationInfo());
            stationRepository.save(stationByName);
        }
    }

}
