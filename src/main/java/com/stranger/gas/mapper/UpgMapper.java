package com.stranger.gas.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.stranger.gas.model.Company;
import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Fuel.FuelType;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;
import com.stranger.gas.model.upg.UpgFuel;
import com.stranger.gas.model.upg.UpgStation;
import com.stranger.gas.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgMapper {
    private Company upgCompany;
    private CompanyRepository companyRepository;
    private static final String BRAND_NAME = "UPG";

    @Autowired
    private UpgCityMap upgCityMap;

    public UpgMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Station mapStation(UpgStation upgStation) {
        return Station.builder()
            .company(getCompany())
            .name(upgStation.getFullName())
            .address(upgStation.getAddress())
            .city(mapCity(upgStation.getRegion()))
            .stationInfo(mapStationInfo(upgStation))
            .build();
    }

    private String mapCity(String region) {
        return upgCityMap.getRegionToCity().get(region);
    }

    //TODO Workdescription can be useful
    private StationInfo mapStationInfo(UpgStation upgStation) {
        return StationInfo.builder()
            .fuels(mapFuels(upgStation.getFuelsAsArray(), upgStation.isActive()))
            //TODO in station title work hours are sometimes
            .schedule(mapSchedule(upgStation.isActive()))
            .workDescription(mapWorkDescription())
            .build();
    }

    //Just for beauty
    String mapWorkDescription() {
        return "Під час комендантської години обслуговується лише спеціальний транспорт, транспорт критичної інфраструктури та транспорт зі спеціальними " +
            "перепустками";
    }

    private String mapSchedule(boolean isActive) {
        return isActive ? "Відчинено" : "Зачинено";
    }

    List<Fuel> mapFuels(List<UpgFuel> fuels, boolean isActive) {
        return fuels.stream()
            .map(upgFuel -> mapFuel(upgFuel, isActive))
            .collect(Collectors.toList());
    }

    Fuel mapFuel(UpgFuel upgFuel, boolean isActive) {
        return Fuel.builder()
            .name(upgFuel.getTitle())
            .price(upgFuel.getPrice())
            .isAvailable(isActive)
            .fuelType(mapFuelType(upgFuel.getTitle()))
            .build();
    }

    //TODO Rewrite, duplication with WogMapper
    FuelType mapFuelType(String name) {
        FuelType fuelType = FuelType.UNKNOWN;

        switch (name) {
            case "92":
                fuelType = FuelType.A92;
                break;
            case "95":
                fuelType = FuelType.A95;
                break;
            case "ДП":
                fuelType = FuelType.DIESEL;
                break;
            case "Газ":
                fuelType = FuelType.GAS;
                break;
            default:
                break;
        }

        return fuelType;
    }

    private Company getCompany() {
        if (this.upgCompany == null) {
            this.upgCompany = this.companyRepository.getByName(BRAND_NAME);
        }

        return this.upgCompany;
    }
}
