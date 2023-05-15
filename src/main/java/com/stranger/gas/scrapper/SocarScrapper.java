package com.stranger.gas.scrapper;

public interface SocarScrapper extends Scrapper {
    Object retrieveStationInfo(int stationId);
}
