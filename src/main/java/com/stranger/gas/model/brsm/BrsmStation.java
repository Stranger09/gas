package com.stranger.gas.model.brsm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrsmStation {
    int id;
    String region;
    String city;
    String address;
    String building;
    String phone;
    String lat;
    String lng;
    boolean active;
    int extId;
    String editDate;
}
