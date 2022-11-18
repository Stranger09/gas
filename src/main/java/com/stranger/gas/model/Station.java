package com.stranger.gas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
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
    @OneToOne(cascade = {CascadeType.ALL})
    private StationInfo stationInfo;

    public Station(Long id, Company company, String name, String address, String city, StationInfo stationInfo) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.address = address;
        this.city = city;
        this.stationInfo = stationInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public StationInfo getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(StationInfo stationInfo) {
        this.stationInfo = stationInfo;
    }
}
