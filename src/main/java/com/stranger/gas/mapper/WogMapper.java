package com.stranger.gas.mapper;

import com.stranger.gas.model.Company;
import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;
import com.stranger.gas.model.wog.WogSchedule;
import com.stranger.gas.model.wog.WogFuel;
import com.stranger.gas.model.wog.WogFuelFilter;
import com.stranger.gas.model.wog.WogStationInfo;
import com.stranger.gas.repository.CompanyRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class WogMapper {

    private Company wogCompany;
    private CompanyRepository companyRepository;
    private List<WogFuelFilter> fuelFilters;
    public static final String BRAND_NAME = "WOG";

    public WogMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Station mapStation(WogStationInfo wogStationInfo) {
        return Station.builder()
            .city(wogStationInfo.getCity())
            .name(mapStationName(wogStationInfo.getId()))
            .address(wogStationInfo.getName())
            .company(getCompany())
            .stationInfo(mapStationInfo(wogStationInfo))
            .build();
    }

    //TODO Map lastUpdateField
    StationInfo mapStationInfo(WogStationInfo wogStationInfo) {
        return StationInfo.builder()
            .lastUpdate(LocalDateTime.now())
            .workDescription(wogStationInfo.getWorkDescription())
            .schedule(mapSchedule(wogStationInfo.getSchedule()))

            .fuels(mapFuelList(wogStationInfo.getFuels(), wogStationInfo.getWorkDescription()))
            .build();
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
                .orElse("Розклад роботи для станції відсутній");
    }

    private String mapStationName(int id) {
        return BRAND_NAME + " " + id;
    }

    private Fuel mapFuel(WogFuel wogFuel, String workDescription) {
        return Fuel.builder()
            .name(mapFuelName(wogFuel.getName(), wogFuel.getBrand()))
            .price(mapFuelPrice(getFuelPrice(wogFuel.getId())))
            .fuelType(mapFuelType(wogFuel.getName()))
            .isAvailable(checkIfFuelIsAvailable(workDescription))
            .build();
    }

    private boolean checkIfFuelIsAvailable(String workDescription) {
        //TODO implement
        return true;
    }

    private String mapFuelName(String name, String brand) {
        return (brand != null && !brand.isEmpty()) ? name + "_" + brand : name;
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

    private Fuel.FuelType mapFuelType(String name) {
        Fuel.FuelType fuelType = Fuel.FuelType.UNKNOWN;

        switch (name) {
            case "92":
                fuelType = Fuel.FuelType.A92;
                break;
            case "95":
                fuelType = Fuel.FuelType.A95;
                break;
            case "ДП":
                fuelType = Fuel.FuelType.DIESEL;
                break;
            case "ГАЗ":
                fuelType = Fuel.FuelType.GAS;
                break;
            default:
                break;
        }

        return fuelType;
    }

    private Company getCompany() {
        if (this.wogCompany == null) {
            this.wogCompany = this.companyRepository.getByName(BRAND_NAME);
        }

        return this.wogCompany;
    }
}
