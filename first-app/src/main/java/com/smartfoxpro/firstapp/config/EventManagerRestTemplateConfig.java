package com.smartfoxpro.firstapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventManagerRestTemplateConfig {

    @Bean(name = "event-manager-rest-template")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
