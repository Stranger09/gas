package com.stranger.gas.model;

import com.stranger.gas.model.Fuel.FuelType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public class Filter {

    String city;
    FuelType fuelType;
    String companyName;

    public Filter(String city, FuelType fuelType, String companyName) {
        this.city = city;
        this.fuelType = fuelType;
        this.companyName = companyName;
    }

    public Filter() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
