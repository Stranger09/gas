package com.stranger.gas.mapper;

import com.stranger.gas.model.Service;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SocarServiceMap {
    private Map<String, Service.ServiceType> serviceNameToServiceType = new HashMap<>();

    {
        serviceNameToServiceType.putAll(Map.of(
                "Виніс термінала", Service.ServiceType.OTHER,
                "Поштомат Нова Почта", Service.ServiceType.OTHER,
                "Швидкісна ПРК TIR", Service.ServiceType.CAR_SERVICE,
                "Телефон", Service.ServiceType.OTHER,
                "Wi-Fi", Service.ServiceType.OTHER,
                "Кава/напої", Service.ServiceType.FOOD,
                "Buta Market", Service.ServiceType.GOODS,
                "SOCAR Pay", Service.ServiceType.OTHER,
                "Картки sCard", Service.ServiceType.OTHER,
                "Обмін валют", Service.ServiceType.OTHER));

        serviceNameToServiceType.putAll(Map.of(
                "Платіжний термінал", Service.ServiceType.OTHER,
                "Зарядка для смартфонiв", Service.ServiceType.OTHER,
                "WC", Service.ServiceType.OTHER,
                "Парковка", Service.ServiceType.CAR_SERVICE,
                "Автоматична чистка взуття", Service.ServiceType.OTHER,
                "Літня тераса", Service.ServiceType.OTHER,
                "Парковка TIR", Service.ServiceType.OTHER,
                "Підкачка шин", Service.ServiceType.CAR_SERVICE,
                "Business corner", Service.ServiceType.OTHER,
                "Доставка Bolt Food", Service.ServiceType.FOOD));

        serviceNameToServiceType.putAll(Map.of(
                "Повноцінна кухня", Service.ServiceType.FOOD,
                "Електронна черга", Service.ServiceType.OTHER,
                "Пеленальний столик", Service.ServiceType.OTHER,
                "WC для осіб з інвалідністю", Service.ServiceType.OTHER,
                "Дитяче крісло", Service.ServiceType.OTHER,
                "Мийка авто", Service.ServiceType.CAR_SERVICE,
                "Доставка Glovo", Service.ServiceType.FOOD,
                "Пилосос", Service.ServiceType.CAR_SERVICE,
                "Розклад авіа-рейсів", Service.ServiceType.OTHER));
    }
}
