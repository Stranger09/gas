package com.stranger.gas.scrapper;

public interface WogScrapper extends Scrapper {
    Object retrieveStationInfo(String link);
    Object retrieveFuelFilters();
}
