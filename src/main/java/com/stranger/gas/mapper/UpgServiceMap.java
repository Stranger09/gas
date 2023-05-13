package com.stranger.gas.mapper;

import com.stranger.gas.model.Service;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class UpgServiceMap {
    private Map<String, Service.ServiceType> serviceNameToServiceType = new HashMap<>();

    {
        serviceNameToServiceType.putAll(Map.of(
                "Магазин", Service.ServiceType.GOODS,
                "WC", Service.ServiceType.OTHER,
                "Термінал EasyPay", Service.ServiceType.OTHER,
                "Wi-Fi", Service.ServiceType.OTHER,
                "Кава", Service.ServiceType.FOOD,
                "Їжа", Service.ServiceType.FOOD,
                "Підкачка шин", Service.ServiceType.CAR_SERVICE,
                "VIVO-cafe", Service.ServiceType.FOOD,
                "СТО", Service.ServiceType.CAR_SERVICE));
    }
}
