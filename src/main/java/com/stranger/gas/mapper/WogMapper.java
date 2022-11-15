package com.stranger.gas.mapper;

import com.stranger.gas.model.Fuel;
import com.stranger.gas.model.Station;
import com.stranger.gas.model.StationInfo;
import com.stranger.gas.model.wog.WogFuel;
import com.stranger.gas.model.wog.WogFuelFilter;
import com.stranger.gas.model.wog.WogStation;
import com.stranger.gas.model.wog.WogStationInfo;
import org.springframework.stereotype.Component;

@Component
public class WogMapper {
/*    Station mapStations(WogStation wogStation) {

    }

    StationInfo mapStationInfo(WogStationInfo wogStationInfo) {

    }*/

 /*   Fuel mapFuel(WogFuel wogFuel) {
        
    }*/

    private boolean checkIfFuelIsAvailable(String workDescription) {
        //TODO implement
        return true;
    }
}
