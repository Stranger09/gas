package com.stranger.gas.mapper;


import com.stranger.gas.model.Company;
import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;
import com.stranger.gas.model.brsm.BrsmFuel;
import com.stranger.gas.model.brsm.BrsmStation;
import com.stranger.gas.repository.CompanyRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class BrsmMappper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String BRAND_NAME = "BRSM";
    private Company brsmCompany;
    private CompanyRepository companyRepository;

    public BrsmMappper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Station mapStation(BrsmStation brsmStation, List<BrsmFuel> fuelList) {
        return Station.builder()
                .company(getCompany())
                .city(brsmStation.getCity())
                .name(mapName(brsmStation.getId()))
                .address(mapAddress(brsmStation.getAddress(), brsmStation.getBuilding()))
                .lat(brsmStation.getLat())
                .lng(brsmStation.getLng())
                .stationInfo(mapStationInfo(brsmStation, fuelList))
                .build();
    }

    private Company getCompany() {
        if (this.brsmCompany == null) {
            this.brsmCompany = this.companyRepository.getByName(BRAND_NAME);
        }
        return this.brsmCompany;
    }

    private String mapName(int id) {
        return "BRSM №" + id;
    }

    private String mapAddress(String address, String building) {
        return building.isEmpty() || building.equals("()") ? address : address + " " + building;
    }

    private StationInfo mapStationInfo(BrsmStation brsmStation, List<BrsmFuel> fuelList) {
        return StationInfo.builder()
                .schedule(mapSchedule(brsmStation.isActive()))
                .workDescription(mapWorkDescription(brsmStation.getPhone()))
                .lastUpdate(LocalDateTime.now().format(formatter))
                .fuels(mapFuels(fuelList))
                .build();
    }

    private String mapSchedule(boolean isActive) {
        return isActive ? "Відчинено" : "Зачинено";
    }

    private String mapWorkDescription(String phoneNumber) {
        return phoneNumber == null || phoneNumber.isEmpty() ? "" : "Номер телефону АЗС - " + phoneNumber;
    }

    private List<Fuel> mapFuels(List<BrsmFuel> fuelList) {
        return fuelList.stream()
                .map(this::mapFuel)
                .collect(Collectors.toList());
    }

    private Fuel mapFuel(BrsmFuel fuel) {
        return Fuel.builder()
                .name(fuel.getName())
                .price(fuel.getPrice())
                .isAvailable(true)
                .fuelType(mapFuelType(fuel.getName()))
                .build();
    }

    Fuel.FuelType mapFuelType(String name) {
       if (name.contains("92")) {
           return Fuel.FuelType.A92;
       } else if (name.contains("95")) {
           return Fuel.FuelType.A95;
       } else if (name.contains("ДП")) {
           return Fuel.FuelType.DIESEL;
       } else if (name.contains("Газ")) {
           return Fuel.FuelType.GAS;
       } else {
           return Fuel.FuelType.UNKNOWN;
       }
    }
}
