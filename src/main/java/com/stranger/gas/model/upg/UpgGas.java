package com.stranger.gas.model.upg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgGas {

    public int id;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Price")
    public double price;
}
