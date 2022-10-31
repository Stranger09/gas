package com.stranger.gas.dto;

import java.util.List;

import com.stranger.gas.model.GasTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GasStationInfo {

    String name;
    List<GasTypeInfo> gasTypeInfo;
    String description;
    String address;

}
