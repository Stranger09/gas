package com.stranger.gas.model;

import lombok.Value;

@Value
public class GasTypeInfo {

    String type;
    boolean isAvailable;
    double price;
}
