package com.stranger.gas.mapper;

import com.stranger.gas.model.*;
import com.stranger.gas.model.socar.SocarService;
import com.stranger.gas.model.socar.SocarStationInfo;
import com.stranger.gas.model.socar.SocarWorkingHours;
import com.stranger.gas.repository.CompanyRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class SocarMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String BRAND_NAME = "SOCAR";
    private Company socarCompany;
    private CompanyRepository companyRepository;
    private SocarServiceMap socarServiceMap;

    public SocarMapper(CompanyRepository companyRepository, SocarServiceMap socarServiceMap) {
        this.companyRepository = companyRepository;
        this.socarServiceMap = socarServiceMap;
    }

    public Station mapStation(SocarStationInfo stationInfo) {
        return Station.builder()
                .company(getCompany())
                .lat(stationInfo.getCoordinates().getLat())
                .lng(stationInfo.getCoordinates().getLng())
                .name(mapName(stationInfo.getId()))
                .address(stationInfo.getAddress())
                .city(stationInfo.getCity())
                .stationInfo(mapStationInfo(stationInfo))
                .build();
    }

    public StationInfo mapStationInfo(SocarStationInfo stationInfo) {
        return StationInfo.builder()
                .schedule(mapSchedule(stationInfo.getWorking_hours()))
                .lastUpdate(LocalDateTime.now().format(formatter))
                .fuels(mapFuels(stationInfo.getServices()))
                .services(mapServices(stationInfo.getServices()))
                .workDescription("")
                .build();
    }

    public List<Service> mapServices(List<SocarService> socarServices) {
        return socarServices.stream()
                .filter(socarService -> socarService.getType().equals("service") || (socarService.getType().equals("fuel") && socarService.getName().equals("Зарядка для електроавто")))
                .map(this::mapService)
                .collect(Collectors.toList());
    }

    public Service mapService(SocarService socarService) {
        return Service.builder()
                .name(socarService.getName())
                .description(socarService.getDescription())
                .serviceType(socarServiceMap.getServiceNameToServiceType().get(socarService.getName()))
                .isAvailable(true)
                .build();
    }

    public List<Fuel> mapFuels(List<SocarService> socarServices) {
        return socarServices.stream()
                .filter(socarService -> socarService.getType().equals("fuel") && !socarService.getName().equals("Зарядка для електроавто"))
                .map(this::mapFuel)
                .collect(Collectors.toList());
    }

    public Fuel mapFuel(SocarService socarService) {
        return Fuel.builder()
                .name(socarService.getName())
                .fuelType(mapFuelType(socarService.getName()))
                .price(socarService.getPrice())
                .isAvailable(true)
                .build();
    }

    Fuel.FuelType mapFuelType(String name) {
        Fuel.FuelType fuelType = Fuel.FuelType.UNKNOWN;

        switch (name) {
            case "Diesel Nano Extro":
            case "NANO ДТ":
                fuelType = Fuel.FuelType.DIESEL;
                break;
            case "A 95":
            case "NANO 95":
                fuelType = Fuel.FuelType.A95;
                break;
            case "LPG":
                fuelType = Fuel.FuelType.GAS;
                break;
            case "A 92":
                fuelType = Fuel.FuelType.A92;
                break;
            case "NANO 98":
            case "AdBlue":
                fuelType = Fuel.FuelType.UNKNOWN;
            default:
                break;
        }

        return fuelType;
    }

    private Company getCompany() {
        if (this.socarCompany == null) {
            this.socarCompany = this.companyRepository.getByName(BRAND_NAME);
        }
        return this.socarCompany;
    }

    private String mapName(int id) {
        return "SOCAR №" + id;
    }

    private String mapSchedule(SocarWorkingHours workingHours) {
        return workingHours != null ? "Відчинено " + workingHours.getFrom() + "-" + workingHours.getTo() : "Відчинено";
    }
}
