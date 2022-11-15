package com.stranger.gas.model;

import lombok.Value;

import javax.persistence.*;

@Value
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Company company;
    private String name;
    private String address;
    private String city;
    @OneToOne
    private StationInfo stationInfo;
}
