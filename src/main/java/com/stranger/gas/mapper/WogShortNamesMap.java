package com.stranger.gas.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class WogShortNamesMap {
    private Map<String, String> shortNames = new HashMap<>();

    {
        shortNames.putAll(Map.of(
                "ДП Євро5", "ДП",
                "ДП Mustang+", "МДП+",
                "92 Євро5", "А92",
                "95 Євро5", "А95",
                "95 Mustang", "М95",
                "ГАЗ", "ГАЗ",
                "100 Mustang", "М100",
                "98", "А-98"
        ));
    }

}
