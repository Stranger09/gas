package com.stranger.gas.model.ui;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Builder
@Data
@ToString
public class StationLine {
    private Long id;
    private String name;
    private String companyName;
    private String address;
    private String city;
    private String lat;
    private String lng;
    private Map<String, Boolean> fuelTypesAvailableInfo;
    private Long stationInfoId;
}
