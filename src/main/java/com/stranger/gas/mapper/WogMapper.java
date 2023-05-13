package com.stranger.gas.mapper;

import com.stranger.gas.model.*;
import com.stranger.gas.model.Fuel.FuelType;
import com.stranger.gas.model.wog.*;
import com.stranger.gas.repository.CompanyRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class WogMapper {
    private static final String UNAVAILABLE_FUEL_PATTERN = "Пальне відсутнє";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Company wogCompany;
    private CompanyRepository companyRepository;
    private List<WogFuelFilter> fuelFilters;
    private WogShortNamesMap wogShortNamesMap;
    private WogServiceMap wogServiceMap;
    public static final String BRAND_NAME = "WOG";

    public WogMapper(CompanyRepository companyRepository, WogShortNamesMap wogShortNamesMap, WogServiceMap wogServiceMap) {
        this.companyRepository = companyRepository;
        this.wogShortNamesMap = wogShortNamesMap;
        this.wogServiceMap = wogServiceMap;
    }

    public Station mapStation(WogStationInfo wogStationInfo) {
        return Station.builder()
                .city(wogStationInfo.getCity())
                .name(mapStationName(wogStationInfo.getId()))
                .address(wogStationInfo.getName())
                .company(getCompany())
                .lat(String.valueOf(wogStationInfo.getCoordinates().getLatitude()))
                .lng(String.valueOf(wogStationInfo.getCoordinates().getLongitude()))
                .stationInfo(mapStationInfo(wogStationInfo))
                .build();
    }

    //TODO Map lastUpdateField
    StationInfo mapStationInfo(WogStationInfo wogStationInfo) {
        return StationInfo.builder()
                .lastUpdate(LocalDateTime.now().format(formatter))
                .workDescription(wogStationInfo.getWorkDescription())
                .schedule(mapSchedule(wogStationInfo.getSchedule()))
                .fuels(mapFuelList(wogStationInfo.getFuels(), wogStationInfo.getWorkDescription()))
                .services(mapServiceList(wogStationInfo.getServices()))
                .build();
    }

    private List<Service> mapServiceList(List<WogService> services) {
        return services.stream()
                .map(this::mapService)
                .collect(Collectors.toList());
    }

    private List<Fuel> mapFuelList(List<WogFuel> fuels, String workDescription) {
        return fuels.stream()
                .map(wogFuel -> mapFuel(wogFuel, workDescription))
                .collect(Collectors.toList());
    }


    private String mapSchedule(List<WogSchedule> schedule) {
        return schedule.stream()
                .map(scheduleObj -> scheduleObj.getDay() + " " + scheduleObj.getInterval())
                .findFirst()
                .orElse("");
    }

    private String mapStationName(int id) {
        return BRAND_NAME + " " + id;
    }

    private Service mapService(WogService wogService) {
        return Service.builder()
                .name(wogService.getName())
                .serviceType(mapServiceType(wogService.getIcon()))
                .isAvailable(true)
                .build();
    }

    private Fuel mapFuel(WogFuel wogFuel, String workDescription) {
        return Fuel.builder()
                .name(mapFuelName(wogFuel.getName(), wogFuel.getBrand()))
                .price(mapFuelPrice(getFuelPrice(wogFuel.getId())))
                .fuelType(mapFuelType(wogFuel.getName()))
                .isAvailable(checkIfFuelIsAvailable(workDescription, mapFuelName(wogFuel.getName(), wogFuel.getBrand())))
                .build();
    }

    private boolean checkIfFuelIsAvailable(String workDescription, String fuelName) {
        String shortFuelName = this.wogShortNamesMap.getShortNames().get(fuelName);

        int fuelDescriptionStartIndex = workDescription.indexOf(shortFuelName);

        if (fuelDescriptionStartIndex < 0) {
            return false;
        }

        String fuelDescription = workDescription.substring(fuelDescriptionStartIndex);

        int fuelDescriptionEndIndex = fuelDescription.indexOf(".");

        if (fuelDescriptionEndIndex <= shortFuelName.length()) {
            return false;
        }
        String resultDescription = fuelDescription.substring(0, fuelDescriptionEndIndex).trim();
        return !resultDescription.contains(UNAVAILABLE_FUEL_PATTERN);
    }

    private String mapFuelName(String name, String brand) {
        return (brand != null && !brand.isEmpty()) ? name + " " + brand : name;
    }

    private int getFuelPrice(int fuelId) {
        return fuelFilters.stream()
                .filter(fuelFilter -> (fuelId == 11) ? fuelFilter.getId() == 3 : fuelFilter.getId() == fuelId)
                .findFirst()
                .orElseGet(WogFuelFilter::new)
                .getPrice();
    }

    //TODO Rewrite, bad, bad implementation
    private double mapFuelPrice(int price) {
        if (price == 0) {
            return 0.0;
        }

        String stringPrice = String.valueOf(price).trim();

        double drobne = Double.parseDouble(stringPrice.substring(stringPrice.length() - 2)) / 100;
        double zile = Double.parseDouble(stringPrice.substring(0, stringPrice.length() - 2));

        double result = zile + drobne;

        return result;
    }


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
            case "ГАЗ":
                fuelType = FuelType.GAS;
                break;
            default:
                break;
        }

        return fuelType;
    }

    private Service.ServiceType mapServiceType(String name) {
        return wogServiceMap.getServiceNameToServiceType().get(name);
    }

    private Company getCompany() {
        if (this.wogCompany == null) {
            this.wogCompany = this.companyRepository.getByName(BRAND_NAME);
        }

        return this.wogCompany;
    }
}
