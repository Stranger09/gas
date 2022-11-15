package com.stranger.gas.model;

import lombok.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Entity
public class StationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schedule;
    private String workDescription;
    @ManyToMany
    private List<Fuel> fuels;
    private LocalDateTime lastUpdate;
}
