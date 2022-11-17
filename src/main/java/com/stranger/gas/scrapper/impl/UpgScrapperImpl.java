package com.stranger.gas.scrapper.impl;

import com.stranger.gas.scrapper.Scrapper;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("upgScrapper")
public class UpgScrapperImpl implements Scrapper {
    private static final String ALL_STATIONS_URL = "https://upg.ua/merezha_azk/";
    private static final String UPG_STATION_DATA_VARIABLE = "var objmap";
    private static final String SCRIPT_TAG = "script";

    @Override
    @SneakyThrows
    public Object retrieveStations() {
        Document doc = Jsoup.connect(ALL_STATIONS_URL).get();

        Elements allElements = doc.getAllElements();
        List<Element> script = allElements.stream()
                .map(element -> element.getElementsByTag(SCRIPT_TAG)).collect(Collectors.toList())
                .stream().flatMap(Collection::stream).collect(Collectors.toList());

        String rowStationJson = script.stream()
                .map(element -> element.parentNode().toString())
                .filter(node -> node.contains(UPG_STATION_DATA_VARIABLE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no info about Fuel Station"));

        String finalStationsJson = getUpgStationJson(rowStationJson);

        return finalStationsJson;
    }

    private String getUpgStationJson(String element) {
        String substring = getSubstring(element, "\"data\":", "var map");
        String validJson = getSubstring(substring, "[{\"id\"", ",\"update\":[]};");
        return validJson;
    }

    private String getSubstring(String nodeString, String str, String var_map) {
        return nodeString.substring(nodeString.indexOf(str), nodeString.indexOf(var_map));
    }
}
