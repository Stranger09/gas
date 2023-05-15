package com.stranger.gas.model.socar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocarStationInfo {
    int id;
    String region;
    String city;
    String street;
    List<SocarService> services;
    SocarWorkingHours working_hours;
    SocarCoordinates coordinates;
    String phone;
    String address;
}
