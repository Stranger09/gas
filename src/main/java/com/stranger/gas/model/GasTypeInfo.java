package com.stranger.gas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GasTypeInfo {

    String name;
    boolean isAvailable;
    int price;
}
