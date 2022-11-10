package com.stranger.gas.model.wog;

import java.util.HashMap;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WogStation{

    String link;
    String city;
    HashMap<String, Double> coordinates;
    String name;
    int id;

}
