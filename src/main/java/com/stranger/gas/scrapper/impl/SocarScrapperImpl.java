package com.stranger.gas.scrapper.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.gas.scrapper.SocarScrapper;
import lombok.SneakyThrows;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

@Component("socarScrapper")
public class SocarScrapperImpl implements SocarScrapper {

    @Value("${station.links.socar}")
    private String SOCAR_STATIONS_URL;
    private static RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    static {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = null;
        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        restTemplate = new RestTemplate(requestFactory);
    }

    @Override
    public Object retrieveStations() {
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        ResponseEntity<Object> rawResponse = restTemplate.getForEntity(SOCAR_STATIONS_URL, Object.class);

        return parseRowResponseToAllStationsResponse(rawResponse.getBody());
    }

    @Override
    public Object retrieveStationInfo(final int stationId) {
        String url = SOCAR_STATIONS_URL + "/" + stationId;
        return restTemplate.getForObject(url, Object.class);

    }

    @SneakyThrows
    private Object parseRowResponseToAllStationsResponse(Object rawResponse) {
        return getObject(rawResponse, "results");
    }

    @SneakyThrows
    private Object getObject(Object objFromJson, String fieldInJson) {
        String json = objectMapper.writeValueAsString(objFromJson);

        Map<String, Object> objectMap = objectMapper.readValue(json, new TypeReference<>(){});

        return objectMap.get(fieldInJson);
    }
}
