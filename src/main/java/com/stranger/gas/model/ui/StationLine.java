package com.stranger.gas.model.ui;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class StationLine {
    private Long id;
    private String name;
    private String companyName;
    private String address;
    private String city;
    private Map<String, Boolean> fuelTypesAvailableInfo;
    private Long stationInfoId;
}
