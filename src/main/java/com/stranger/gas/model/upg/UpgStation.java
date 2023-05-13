package com.stranger.gas.model.upg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpgStation {

    public int id;
    @JsonProperty("Active")
    public boolean active;
    @JsonProperty("FullName")
    public String fullName;
    @JsonProperty("Address")
    public String address;
    @JsonProperty("Region")
    public String region;
    @JsonProperty("Latitude")
    public String latitude;
    @JsonProperty("Longitude")
    public String longitude;
    @JsonProperty("LastPriceUpdateDate")
    //2022-11-13
    public String lastPriceUpdateDate;
    @JsonProperty("FuelsAsArray")
    public List<UpgFuel> fuelsAsArray;
    @JsonProperty("ServicesAsArray")
    public List<UpgService> servicesAsArray;
}
