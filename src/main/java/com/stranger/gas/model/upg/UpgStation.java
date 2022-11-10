package com.stranger.gas.model.upg;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgStation{

    public int id;
    @JsonProperty("Active")
    public boolean active;
    @JsonProperty("VersionId")
    public int versionId;
    @JsonProperty("Latitude")
    public String latitude;
    @JsonProperty("Longitude")
    public String longitude;
    @JsonProperty("FullName")
    public String fullName;
    @JsonProperty("ShortName")
    public String shortName;
    @JsonProperty("Address")
    public String address;
    @JsonProperty("Region")
    public String region;
    @JsonProperty("LastPriceUpdateDate")
    public String lastPriceUpdateDate;
    @JsonProperty("ServicesAsArray")
    public ArrayList<UpgService> servicesAsArray;
    @JsonProperty("FuelsAsArray")
    public ArrayList<UpgGas> fuelsAsArray;

}
