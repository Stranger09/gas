package com.stranger.gas.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
public class StationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schedule;
    @Column(length = 65555)
    private String workDescription;

    //TODO Find a way not create duplicates of fuels for wog
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Fuel> fuels;
    private LocalDateTime lastUpdate;

    public StationInfo(Long id, String schedule, String workDescription, List<Fuel> fuels, LocalDateTime lastUpdate) {
        this.id = id;
        this.schedule = schedule;
        this.workDescription = workDescription;
        this.fuels = fuels;
        this.lastUpdate = lastUpdate;
    }

    public StationInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public List<Fuel> getFuels() {
        return fuels;
    }

    public void setFuels(List<Fuel> fuels) {
        this.fuels = fuels;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
