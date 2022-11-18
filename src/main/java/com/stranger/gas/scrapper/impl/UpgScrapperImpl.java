package com.stranger.gas.scrapper.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.stranger.gas.scrapper.Scrapper;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("upgScrapper")
public class UpgScrapperImpl implements Scrapper {

    @Value("${station.links.upg}")
    private String UPG_STATIONS_URL;

    private static final String UPG_STATION_DATA_VARIABLE = "var objmap";
    private static final String SCRIPT_TAG = "script";

    @Override
    @SneakyThrows
    public Object retrieveStations() {

        Connection connect = Jsoup.connect(UPG_STATIONS_URL);

        Document doc = connect.get();
        Elements allElements = doc.getAllElements();
        List<Element> scriptElements = getScriptElements(allElements);

        String rowStationJson = getRowStationJson(scriptElements);

        return getUpgStationJson(rowStationJson);
    }

    private static List<Element> getScriptElements(Elements allElements) {
        return allElements.stream()
            .map(element -> element.getElementsByTag(SCRIPT_TAG)).collect(Collectors.toList())
            .stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private String getRowStationJson(List<Element> script) {
        return script.stream()
            .map(element -> Objects.requireNonNull(element.parentNode()).toString())
            .filter(node -> node.contains(UPG_STATION_DATA_VARIABLE))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("There is no info about Fuel Station"));
    }

    private String getUpgStationJson(String element) {

        String substring = getSubstring(element, "\"data\":", "var map");

        return getSubstring(substring, "[{\"id\"", ",\"update\":[]};");
    }

    private String getSubstring(String nodeString, String startSubStr, String endSubStr) {


        return nodeString.substring(nodeString.indexOf(startSubStr), nodeString.indexOf(endSubStr));
    }
}
