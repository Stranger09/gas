package com.stranger.gas.model;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Station {

    String link;
    String city;
    HashMap<String, Double> coordinates;
    String name;
    int id;

}
