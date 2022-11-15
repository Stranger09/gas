package com.stranger.gas.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Company company;
    private String name;
    private String address;
    private String city;
    @OneToOne(cascade = {CascadeType.ALL})
    private StationInfo stationInfo;
}
