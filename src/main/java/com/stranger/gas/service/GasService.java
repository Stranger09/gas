package com.stranger.gas.service;

import java.util.List;
import java.util.stream.Collectors;

import com.stranger.gas.adapters.UpgAdapter;
import com.stranger.gas.adapters.WogAdapter;
import com.stranger.gas.model.wog.WogStation;

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

    public List<WogStation> getAllWogStationInfo() {
        log.info("we're inside gas service");
        return wogAdapter.getGasStationInfo();
    }

    public List<WogStation> getAllStationByCity(String city) {

        List<WogStation> gasWogStationInfo = wogAdapter.getGasStationInfo();

        return gasWogStationInfo.stream().filter(station -> station.getCity().equals(city)).collect(Collectors.toList());
    }

}
