package com.stranger.gas.mapper;

import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.ui.StationLine;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StationMapper {
    public List<StationLine> mapStationToStationLine(List<Station> stations) {
        return stations.stream()
                .map(station -> {
                    return StationLine.builder()
                            .id(station.getId())
                            .city(station.getCity())
                            .address(station.getAddress())
                            .companyName(station.getCompany().getName())
                            .stationInfoId(station.getStationInfo().getId())
                            .name(station.getName())
                            .fuelTypesAvailableInfo(mapFuelTypesAvailableInfo(station))
                            .build();
                }).collect(Collectors.toList());
    }

    private Map<String, Boolean> mapFuelTypesAvailableInfo(Station station) {
        List<Fuel> fuels = station.getStationInfo().getFuels();

        Map<String, Boolean> fuelTypesAvailableInfo = Arrays.stream(Fuel.FuelType.values())
                .filter(fuelType -> fuelType != Fuel.FuelType.UNKNOWN)
                .collect(Collectors.toMap(Enum::name, fuelType -> {
                    return fuels.stream()
                            .filter(fuel -> fuel.getFuelType() == fuelType).anyMatch(Fuel::isAvailable);
                }));

        return fuelTypesAvailableInfo;
    }
}
