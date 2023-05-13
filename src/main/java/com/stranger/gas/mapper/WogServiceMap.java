package com.stranger.gas.mapper;

import com.stranger.gas.model.Service;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class WogServiceMap {
    private Map<String, Service.ServiceType> serviceNameToServiceType = new HashMap<>();

    {
        serviceNameToServiceType.putAll(Map.of(
                "vilka", Service.ServiceType.OTHER,
                "wogpride", Service.ServiceType.OTHER,
                "carwash", Service.ServiceType.CAR_SERVICE,
                "tirefitting", Service.ServiceType.CAR_SERVICE,
                "parking", Service.ServiceType.CAR_SERVICE,
                "wc", Service.ServiceType.OTHER,
                "wifi", Service.ServiceType.OTHER,
                "wogcafe", Service.ServiceType.FOOD,
                "wogmarket", Service.ServiceType.GOODS));

        serviceNameToServiceType.putAll(Map.of(
                "charge", Service.ServiceType.OTHER,
                "baby", Service.ServiceType.OTHER,
                "rozetka2", Service.ServiceType.GOODS,
                "ibox", Service.ServiceType.OTHER,
                "tire", Service.ServiceType.CAR_SERVICE,
                "kso", Service.ServiceType.OTHER,
                "logo", Service.ServiceType.CAR_SERVICE,
                "diesel", Service.ServiceType.OTHER
        ));
    }
}
