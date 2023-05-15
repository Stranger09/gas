package com.stranger.gas.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    private String name;
    @Column(length = 2555)
    private String description;
    private boolean isAvailable;

    public enum ServiceType {
        FOOD, GOODS, CAR_SERVICE, OTHER
    }
}
