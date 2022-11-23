package com.stranger.gas.mapper;

import com.stranger.gas.model.Company;
import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Fuel.FuelType;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;
import com.stranger.gas.model.upg.UpgFuel;
import com.stranger.gas.model.upg.UpgStation;
import com.stranger.gas.repository.CompanyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class UpgMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String BRAND_NAME = "UPG";

    private Company upgCompany;
    private CompanyRepository companyRepository;
    private UpgCityMap upgCityMap;

    public UpgMapper(CompanyRepository companyRepository, UpgCityMap upgCityMap) {
        this.companyRepository = companyRepository;
        this.upgCityMap = upgCityMap;
    }

    public Station mapStation(UpgStation upgStation) {
        return Station.builder()
                .company(getCompany())
                .name(mapName(upgStation.getFullName()))
                .address(upgStation.getAddress())
                .city(mapCity(upgStation.getRegion()))
                .stationInfo(mapStationInfo(upgStation))
                .build();
    }

    private String mapCity(String region) {
        return upgCityMap.getRegionToCity().get(region);
    }

    private String mapName(String fullName) {
        Pattern compile = Pattern.compile(".*№\\d*");
        Matcher matcher = compile.matcher(fullName);
        matcher.find();

        return matcher.group();
    }

    //TODO WorkDescription can be useful
    private StationInfo mapStationInfo(UpgStation upgStation) {
        return StationInfo.builder()
                .fuels(mapFuels(upgStation.getFuelsAsArray(), upgStation.isActive()))
                .lastUpdate(LocalDateTime.now().format(formatter))
                //TODO in station title work hours are sometimes
                .schedule(mapSchedule(upgStation.isActive(), upgStation.getFullName()))
                .workDescription(mapWorkDescription())
                .build();
    }

    //Just for beauty
    String mapWorkDescription() {
        return "Під час комендантської години обслуговується лише спеціальний транспорт, транспорт критичної інфраструктури та транспорт зі спеціальними " +
                "перепустками";
    }

    private String mapSchedule(boolean isActive, String fullName) {
        String baseString = isActive ? "Відчинено" : "Зачинено";

        String workHours = fullName.replaceAll(".*№\\d*\\s", "");
        if(workHours.length() == fullName.length()) {
            return baseString;
        }

        String result = baseString + " " + workHours;
        return result.trim();
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
                .isAvailable(isActive && upgFuel.getPrice() != 0.0)
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
