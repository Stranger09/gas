package com.stranger.gas.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Company company;
    private String name;
    private String address;
    private String city;
    private String lat;
    private String lng;
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private StationInfo stationInfo;

    public Station(Company company, String name, String address, String city, String lat, String lng, StationInfo stationInfo) {
        this.company = company;
        this.name = name;
        this.address = address;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
        this.stationInfo = stationInfo;
    }
}
