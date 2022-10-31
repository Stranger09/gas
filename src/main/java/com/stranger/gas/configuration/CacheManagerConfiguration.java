package com.stranger.gas.configuration;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheManagerConfiguration {

    @Bean("gasStationCacheManager")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                    name,
                    CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.SECONDS)
//                        .refreshAfterWrite(60, TimeUnit.SECONDS)
                        .build().asMap(),
                    false);
            }
        };
    }

}
