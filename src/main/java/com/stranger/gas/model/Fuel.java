package com.stranger.gas.model;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Value
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

    enum FuelType {
        A92, A95, DIESEL, GAS;
    }
}
