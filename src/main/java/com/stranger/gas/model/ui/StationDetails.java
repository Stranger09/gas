package com.stranger.gas.model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StationDetails {
    private Long id;
    private String name;
    private String address;
    private String hotLine;
    private String schedule;
    private String availableFuelsDescription;
    private String additionalInfo;
    private String link;
    private String linkName;
    private String lastUpdate;
}
