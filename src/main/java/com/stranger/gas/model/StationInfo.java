package com.stranger.gas.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
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
}
