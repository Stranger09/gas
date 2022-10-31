package com.stranger.gas.adapters;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.List;

import com.stranger.gas.model.Station;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UpgAdapter implements Adapter{

    @Override
    @SneakyThrows
    @Cacheable(value = "okkoCache")
    @Scheduled(fixedDelay = 1000000)
    public List<Station> getGasStationInfo() {

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        String url = "https://upg.ua/merezha_azk/";

        Document doc = Jsoup.connect(url).get();

        Elements allElements = doc.getAllElements();
        Element element = allElements.get(404);
        String data = element.data();

        HashMap<String, Double> coordinates = new HashMap<>();
        coordinates.put("longitude", 10.0);
        coordinates.put("latitude", 20.0);

        List<Station> okkoStations = List.of(new Station("link", "Вінниця", coordinates, "Okko", 1));

        log.info("we're inside upg adapter");

        return okkoStations;
    }
}
