package com.stranger.gas.model;

import com.stranger.gas.model.Fuel.FuelType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filter {

    String city;
    FuelType fuelType;
    String companyName;

}
