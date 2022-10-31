package com.stranger.gas.services;

import java.util.List;
import java.util.stream.Collectors;

import com.stranger.gas.adapters.UpgAdapter;
import com.stranger.gas.adapters.WogAdapter;
import com.stranger.gas.model.Station;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GasService {
    @Autowired
    private WogAdapter wogAdapter;
    @Autowired
    UpgAdapter upgAdapter;

    public List<Station> getAllWogStationInfo() {
        log.info("we're inside gas service");
        return wogAdapter.getGasStationInfo();
    }

    public List<Station> getAllStationByCity(String city) {

        List<Station> gasStationInfo = wogAdapter.getGasStationInfo();

        return gasStationInfo.stream().filter(station -> station.getCity().equals(city)).collect(Collectors.toList());
    }

}
