package com.stranger.gas.mapper;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UpgCityMap {
    private Map<String, String> regionToCity = new HashMap<>();

    {
        regionToCity.putAll(Map.of("Вінницька", "Вінниця",
            "Рівненська", "Рівне",
            "Львівська", "Львів",
            "Житомирська", "Житомир",
            "Сумська", "Суми",
            "Київська", "Київ",
            "Запорізька", "Запоріжжя",
            "Кіровоградська", "Кропивницький",
            "Закарпатська", "Ужгород"));

        regionToCity.putAll(Map.of(
            "Дніпропетровська", "Дніпро",
            "Волинська", "Луцьк",
            "Черкаська", "Черкаси",
            "Одеська", "Одеса",
            "Чернівецька", "Чернівці",
            "Хмельницька", "Хмельницький",
            "Харківська", "Харків",
            "Полтавська", "Полтава",
            "Тернопільська", "Тернопіль",
            "Івано-Франківська", "Івано-Франківськ"));
    }
}
