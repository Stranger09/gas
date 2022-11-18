package com.stranger.gas.model.ui;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StationDetails {
    private Long id;
    private String name;
    private String address;
    private String hotLine;
    private String link;
    private String linkName;
    private String schedule;
    private String lastUpdate;
    private String workDescription;
}
