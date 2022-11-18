package com.stranger.gas.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;

@Entity
@Builder
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    private String name;
    private double price;
    private boolean isAvailable;

    public Fuel(Long id, FuelType fuelType, String name, double price, boolean isAvailable) {
        this.id = id;
        this.fuelType = fuelType;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public enum FuelType {
        //TODO Add 100
        A92, A95, DIESEL, GAS, UNKNOWN;
    }

    public Fuel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
