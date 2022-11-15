package com.stranger.gas.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.model.wog.WogFuelFilter;
import com.stranger.gas.model.wog.WogStation;
import com.stranger.gas.model.wog.WogStationInfo;
import com.stranger.gas.scrapper.WogScrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableCaching
@Component
public class WogAdapter{
    private WogScrapper wogScrapper;
    private ObjectMapper mapper = new ObjectMapper();

    public WogAdapter(WogScrapper wogScrapper) {
        this.wogScrapper = wogScrapper;
    }

    private static final String BRAND_NAME = "WOG";

    public void collectWogInfo() {

    }

    @Scheduled(fixedDelay = 1000000)
    public List<WogStation> getAllStations() {
        List<WogStation> allWogStations = getAllWogStations();
               /* allWogStations.stream().forEach(System.out::println);
*/
        return allWogStations;
    }

    @Scheduled(fixedDelay = 1000000)
    public List<WogFuelFilter> getAllFuelFilters() {
        getAllWogFuelFilters()
        .stream()/*.forEach(System.out::println)*/;

        return new ArrayList<>();
    }

    @Scheduled(fixedDelay = 1000000)
    public List<WogStationInfo> getStationInfo() {
        getAllWogStations()
                .stream()
                .map(WogStation::getLink)
                .limit(1)
                .forEach(link -> {
                    WogStationInfo wogStationInfo = getWogStationInfo(link);
                    /*System.out.println(wogStationInfo);*/
                });

        return new ArrayList<>();
    }

    @SneakyThrows
    private List<WogStation> getAllWogStations() {
        Object allStationsInfo = wogScrapper.retrieveStations();

        List<WogStation> wogStations = mapper.convertValue(allStationsInfo, new TypeReference<>() {
        });

        return wogStations;
    }

    @SneakyThrows
    private List<WogFuelFilter> getAllWogFuelFilters() {
        Object allFuelFiltersInfo = wogScrapper.retrieveFuelFilters();

        List<WogFuelFilter> wogFuelFilters = mapper.convertValue(allFuelFiltersInfo, new TypeReference<>() {
        });

        return wogFuelFilters;
    }


    @SneakyThrows
    public WogStationInfo getWogStationInfo(String stationLink) {
        Object stationInfo = wogScrapper.retrieveStationInfo(stationLink);

        WogStationInfo wogStationInfo = mapper.convertValue(stationInfo, new TypeReference<>() {
        });

        return wogStationInfo;
    }

}
