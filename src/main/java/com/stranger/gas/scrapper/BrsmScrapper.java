package com.stranger.gas.scrapper;

public interface BrsmScrapper extends Scrapper {
    Object retrieveStationFuels(int stationId);
}
