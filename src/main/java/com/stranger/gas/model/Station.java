package com.stranger.gas.model;

import java.util.List;

import lombok.Value;

@Value
public class Station {

    String brandName;
    List<GasTypeInfo> gasTypeInfo;
    String city;
    String address;

}
