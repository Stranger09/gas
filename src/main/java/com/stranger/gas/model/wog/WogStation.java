package com.stranger.gas.model.wog;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WogStation{

    String link;
    String city;
    HashMap<String, Double> coordinates;
    String name;
    int id;

}
