package com.stranger.gas.model.wog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WogStationInfo {
    String link;
    String city;
    String workDescription;
    WogCoordinates coordinates;
    List<WogFuel> fuels;
    List<WogSchedule> schedule;
    List<WogService> services;
    String name;
    int id;
}
