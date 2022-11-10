package com.stranger.gas.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {

    @Bean(name = "upgAdapter")
    public UpgAdapter getUpgAdapter() {
        return new UpgAdapter();
    }

}
