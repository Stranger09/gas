package com.stranger.gas.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.model.GasTypeInfo;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.upg.UpgStation;
import com.stranger.gas.model.wog.WogStation;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//@Component
public class UpgAdapter implements Adapter {

    private static final int MAGIC_ELEMENT_ID = 404;
    private static final String BRAND_NAME = "UPG";

    @SneakyThrows
    private List<UpgStation> getUpgGasStationInfo() {

        String url = "https://upg.ua/merezha_azk/";
        Document doc = Jsoup.connect(url).get();

        String validJson = getJson(getElement(doc, MAGIC_ELEMENT_ID));

        List<UpgStation> upgStations = new ObjectMapper().readValue(validJson, new TypeReference<List<UpgStation>>() {
        });
        return upgStations;
    }

    private Element getElement(Document doc, int id) {
        Elements allElements = doc.getAllElements();
        Element element = allElements.get(id);
        return element;
    }

    private String getJson(Element element) {
        String nodeString = element.parentNode().toString();
        String substring = getSubstring(nodeString, "\"data\":", "var map");
        String validJson = getSubstring(substring, "[{\"id\"", ",\"update\":[]};");
        return validJson;
    }

    private String getSubstring(String nodeString, String str, String var_map) {
        return nodeString.substring(nodeString.indexOf(str), nodeString.indexOf(var_map));
    }

    @Override
    @Cacheable(value = "upgCache")
    @Scheduled(fixedDelay = 1000000)
    public List<Station> getGasStationInfo() {
        return getAllStationInfo();
    }

    private List<Station> getAllStationInfo() {
        List<UpgStation> gasStationInfo = getUpgGasStationInfo();
        return gasStationInfo.stream().map(upg -> new Station(BRAND_NAME, getGasTypeInfo(upg), upg.region, upg.address)).collect(Collectors.toList());
    }

    private List<GasTypeInfo> getGasTypeInfo(UpgStation upg) {

        return upg.fuelsAsArray
            .stream()
            .map(gas -> new GasTypeInfo(gas.title, true, gas.price))
            .collect(Collectors.toList());
    }
}
