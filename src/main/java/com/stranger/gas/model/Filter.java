package com.stranger.gas.model;

import com.stranger.gas.model.Fuel.FuelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Filter {
    String city;
    FuelType fuelType;
    Service.ServiceType serviceType;
    String companyName;
}
