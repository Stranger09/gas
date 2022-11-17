package com.stranger.gas.model.wog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WogStation {
    String link;
    String city;
    String name;
    int id;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
